package com.code.gestion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteVentasDTO {

    private int numeroTransacciones;
    private List<ProductoVentaDTO> productosVendidos;
    private double totalIngresos;
}
