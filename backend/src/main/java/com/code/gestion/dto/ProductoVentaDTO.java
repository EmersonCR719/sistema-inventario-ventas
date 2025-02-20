package com.code.gestion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoVentaDTO {

    private String nombre;
    private int cantidadVendida;
    private double total;
}
