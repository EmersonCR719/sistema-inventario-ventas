package com.code.gestion.controller;

import com.code.gestion.dto.ReporteVentasDTO;
import com.code.gestion.exceptions.VentaExceptions;
import com.code.gestion.service.ExportService;
import com.code.gestion.service.ReporteVentasService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/reportes")
public class ReporteVentasController {

    private final ReporteVentasService reporteVentasService;
    private final ExportService exportService;

    public ReporteVentasController(ReporteVentasService reporteVentasService, ExportService exportService) {
        this.reporteVentasService = reporteVentasService;
        this.exportService = exportService;
    }

    @GetMapping("/ventas-del-dia")
    public ResponseEntity<byte[]> generarReporteVentas() {
        ReporteVentasDTO reporte = reporteVentasService.generarReporteDelDia();

        if (reporte == null || reporte.getProductosVendidos().isEmpty()) {
            throw new VentaExceptions("No hay productos vendidos", HttpStatus.NO_CONTENT);
        }

        byte[] pdfBytes = exportService.generarPDF(reporte);
        if (pdfBytes == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_ventas.pdf")
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .body(pdfBytes);
    }
}
