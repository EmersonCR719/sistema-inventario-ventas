package com.code.gestion.controller;

import com.code.gestion.exceptions.ProductoExceptions;
import com.code.gestion.presistence.entity.Producto;
import com.code.gestion.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping()
    public List<Producto> findAll() {
        return this.productoService.listarProductos();
    }

    @GetMapping("/obtener/{id}")
    public Producto findById(@PathVariable long id) {
        Optional<Producto> producto = this.productoService.obtenerProductoPorId(id);
        if (producto.isPresent())
            return producto.get();
        else
            throw new ProductoExceptions("Producto no encontrado", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public Producto create(@RequestBody Producto producto) {
        return this.productoService.guardarProducto(producto);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        this.productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

}
