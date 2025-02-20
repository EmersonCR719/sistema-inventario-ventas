package com.code.gestion.controller;

import com.code.gestion.presistence.entity.DetalleVenta;
import com.code.gestion.service.DetalleVentaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/detalleVenta")
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping
    public List<DetalleVenta> listar() {
        return this.detalleVentaService.listarDetallesVenta();
    }

    @PostMapping
    public DetalleVenta agregar(@RequestBody DetalleVenta detalleVenta) {
        return this.detalleVentaService.guardarDetalleVenta(detalleVenta);
    }
}
