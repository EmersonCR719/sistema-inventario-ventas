package com.code.gestion.service;

import com.code.gestion.dto.ProductoVentaDTO;
import com.code.gestion.dto.ReporteVentasDTO;
import com.code.gestion.presistence.entity.Venta;
import com.code.gestion.presistence.repository.VentaRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteVentasService {

    private final VentaRepository ventaRepository;

    public ReporteVentasService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public ReporteVentasDTO generarReporteDelDia() {
        LocalDate hoy = LocalDate.now();

        List<Venta> ventasDelDia = ventaRepository.findByFecha(hoy);
        System.out.println("Ventas del dia: " + ventasDelDia);

        int numeroTransacciones = ventasDelDia.size();

        // Agrupar productos vendidos y calcular total
        List<ProductoVentaDTO> productosVendidos = ventasDelDia.stream()
                .flatMap(venta -> venta.getDetalles().stream())
                .collect(Collectors.groupingBy(
                        detalle -> detalle.getProducto().getNombre(),
                        Collectors.summingDouble(detalle ->
                                detalle.getPrecioUnitario().multiply(BigDecimal.valueOf(detalle.getCantidad())).doubleValue()
                        )
                ))
                .entrySet().stream()
                .map(entry -> new ProductoVentaDTO(entry.getKey(),
                        ventasDelDia.stream()
                                .flatMap(venta -> venta.getDetalles().stream())
                                .filter(detalle -> detalle.getProducto().getNombre().equals(entry.getKey()))
                                .mapToInt(detalle -> detalle.getCantidad())
                                .sum(),
                        entry.getValue()
                ))
                .collect(Collectors.toList());

        double totalIngresos = productosVendidos.stream().mapToDouble(ProductoVentaDTO::getTotal).sum();

        return new ReporteVentasDTO(numeroTransacciones, productosVendidos, totalIngresos);
    }
}
