package com.bff.library.application.port.out;

import com.bff.library.domain.model.ReservaLibro;
import java.util.List;
import java.util.Optional;

public interface ReservaFunctionPort {
    ReservaLibro createReserva(ReservaLibro reserva);
    Optional<ReservaLibro> getReservaById(String id);
    List<ReservaLibro> getAllReservas();
    ReservaLibro updateReserva(String id, ReservaLibro reserva);
    Void deleteReserva(String id);
}
