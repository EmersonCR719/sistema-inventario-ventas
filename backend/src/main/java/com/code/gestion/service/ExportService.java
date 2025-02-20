package com.code.gestion.service;

import com.code.gestion.dto.ProductoVentaDTO;
import com.code.gestion.dto.ReporteVentasDTO;
import org.springframework.stereotype.Service;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import java.io.ByteArrayOutputStream;

@Service
public class ExportService {

    public byte[] generarPDF(ReporteVentasDTO reporte) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph("Reporte de Ventas del Día").setBold().setFontSize(16));
            document.add(new Paragraph("Número de Transacciones: " + reporte.getNumeroTransacciones()));
            document.add(new Paragraph("Total Ingresos: $" + reporte.getTotalIngresos()));

            Table table = new Table(3);
            table.addHeaderCell("Producto");
            table.addHeaderCell("Cantidad Vendida");
            table.addHeaderCell("Total");

            for (ProductoVentaDTO producto : reporte.getProductosVendidos()) {
                table.addCell(producto.getNombre());
                table.addCell(String.valueOf(producto.getCantidadVendida()));
                table.addCell(String.valueOf(producto.getTotal()));
            }

            document.add(table);
            document.close();

            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
