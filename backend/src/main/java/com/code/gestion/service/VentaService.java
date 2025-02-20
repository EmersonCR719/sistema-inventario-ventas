package com.code.gestion.service;

import com.code.gestion.dto.DetalleVentaRequest;
import com.code.gestion.dto.ProductoVentaDTO;
import com.code.gestion.dto.ReporteVentasDTO;
import com.code.gestion.dto.VentaRequest;
import com.code.gestion.exceptions.ProductoExceptions;
import com.code.gestion.presistence.entity.DetalleVenta;
import com.code.gestion.presistence.entity.Producto;
import com.code.gestion.presistence.entity.Venta;
import com.code.gestion.presistence.repository.DetalleVentaRepository;
import com.code.gestion.presistence.repository.ProductoRepository;
import com.code.gestion.presistence.repository.VentaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    private final ProductoRepository productoRepository;

    private final DetalleVentaRepository detalleVentaRepository;

    public VentaService(VentaRepository ventaRepository,
                        ProductoRepository productoRepository,
                        DetalleVentaRepository detalleVentaRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
        this.detalleVentaRepository = detalleVentaRepository;
    }

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @Transactional
    public Venta registrarVenta(VentaRequest ventaRequest) {
        Venta venta = new Venta();
        List<DetalleVenta> detalles = new ArrayList<>();

        BigDecimal totalVenta = BigDecimal.ZERO;

        if (ventaRequest.getDetalles() == null || ventaRequest.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("La lista de detalles no puede estar vacía o nula.");
        }

        for (DetalleVentaRequest detalleRequest : ventaRequest.getDetalles()) {
            Producto producto = productoRepository.findById(detalleRequest.getProductoId())
                    .orElseThrow(() -> new ProductoExceptions("Producto no encontrado", HttpStatus.NOT_FOUND));

            if (producto.getStock() < detalleRequest.getCantidad()) {
                throw new ProductoExceptions("Stock insuficiente para el producto: " + producto.getNombre(), HttpStatus.BAD_REQUEST);
            }

            DetalleVenta detalle = new DetalleVenta(venta, producto, detalleRequest.getCantidad());
            detalles.add(detalle);

            totalVenta = totalVenta.add(detalle.getSubtotal());

            // Actualizar el stock
            producto.setStock(producto.getStock() - detalleRequest.getCantidad());
            productoRepository.save(producto);
        }

        venta.setTotal(totalVenta);
        venta.setDetalles(detalles);

        Venta ventaGuardada = ventaRepository.save(venta);
        detalleVentaRepository.saveAll(detalles);

        return ventaGuardada;
    }

    public ReporteVentasDTO generarReporteVentas() {
        // Simulación de datos (esto debería venir de la BD)
        List<ProductoVentaDTO> productosVendidos = List.of(
                new ProductoVentaDTO("Producto A", 10, 50000),
                new ProductoVentaDTO("Producto B", 5, 25000)
        );

        int numeroTransacciones = 2;
        double totalIngresos = productosVendidos.stream().mapToDouble(ProductoVentaDTO::getTotal).sum();

        return new ReporteVentasDTO(numeroTransacciones, productosVendidos, totalIngresos);
    }

    public Optional<Venta> buscarVentaPorId(Long id) {
        return ventaRepository.findById(id);
    }
}