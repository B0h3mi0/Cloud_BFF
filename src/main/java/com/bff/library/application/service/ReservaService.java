package com.bff.library.application.service;

import com.bff.library.application.port.in.ReservaUseCase;
import com.bff.library.application.port.out.ReservaFunctionPort;
import com.bff.library.domain.model.ReservaLibro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaService implements ReservaUseCase {

    private final ReservaFunctionPort reservaFunctionPort;

    @Override
    public ReservaLibro createReserva(ReservaLibro reserva) {
        return reservaFunctionPort.createReserva(reserva);
    }

    @Override
    public Optional<ReservaLibro> getReservaById(String id) {
        return reservaFunctionPort.getReservaById(id);
    }

    @Override
    public List<ReservaLibro> getAllReservas() {
        return reservaFunctionPort.getAllReservas();
    }

    @Override
    public ReservaLibro updateReserva(String id, ReservaLibro reserva) {
        return reservaFunctionPort.updateReserva(id, reserva);
    }

    @Override
    public Void deleteReserva(String id) {
        return reservaFunctionPort.deleteReserva(id);
    }
}
