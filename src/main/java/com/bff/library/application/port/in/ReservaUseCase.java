package com.bff.library.application.port.in;

import com.bff.library.domain.model.ReservaLibro;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface ReservaUseCase {
    ReservaLibro createReserva(ReservaLibro reserva);
    Optional<ReservaLibro> getReservaById(String id);
    List<ReservaLibro> getAllReservas();
    ReservaLibro updateReserva(String id, ReservaLibro reserva);
    Void deleteReserva(String id);
}
