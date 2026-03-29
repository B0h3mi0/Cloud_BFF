package com.bff.library.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaLibro {
    private String id;
    private String userId;
    private String libroId;
    private LocalDate fechaReserva;
    private LocalDate fechaDevolucion;
    private String status;
}
