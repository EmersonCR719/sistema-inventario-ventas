package com.code.gestion.controller;

import com.code.gestion.dto.VentaRequest;
import com.code.gestion.exceptions.VentaExceptions;
import com.code.gestion.presistence.entity.Venta;
import com.code.gestion.service.ExportService;
import com.code.gestion.service.VentaService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;
    private final ExportService exportService;

    public VentaController(VentaService ventaService, ExportService exportService) {
        this.ventaService = ventaService;
        this.exportService = exportService;
    }

    @GetMapping
    public List<Venta> listar() {
        return this.ventaService.listarVentas();
    }

    @PostMapping
    public ResponseEntity<Venta> registrarVenta(@RequestBody VentaRequest request) {
        Venta venta = ventaService.registrarVenta(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(venta);
    }

    @GetMapping("/reporte/pdf")
    public void descargarReportePDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=reporte_ventas.pdf");

        byte[] pdfBytes = exportService.generarPDF(ventaService.generarReporteVentas());
        response.getOutputStream().write(pdfBytes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerVenta(@PathVariable Long id) {
        Optional<Venta> response;
        response = this.ventaService.buscarVentaPorId(id);
        if (response.isPresent()) {
            return ResponseEntity.ok(response.get());
        } else {
            throw new VentaExceptions("No existe una venta con el id: " + id, HttpStatus.NOT_FOUND);
        }
    }

}
