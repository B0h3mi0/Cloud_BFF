package com.bff.library.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Libro {
    private String id_libro;
    private String titulo;
    private String escritor;
    private String genero;
    private Integer anio;
    private String premisa;
    private Integer stock;
}
