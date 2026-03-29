package com.bff.library.infrastructure.in.web;

import com.bff.library.application.port.in.ReservaUseCase;
import com.bff.library.domain.model.ReservaLibro;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaUseCase reservaLibroUseCase;

    @PostMapping
    public ReservaLibro createReserva(@RequestBody ReservaLibro Reserva) {
        return reservaLibroUseCase.createReserva(Reserva);
    }

    @GetMapping("/{id}")
    public Optional<ReservaLibro> getReservaById(@PathVariable String id) {
        return reservaLibroUseCase.getReservaById(id);
    }

    @GetMapping
    public List<ReservaLibro> getAllReservas() {
        return reservaLibroUseCase.getAllReservas();
    }

    @PutMapping("/{id}")
    public ReservaLibro updateReserva(@PathVariable String id, @RequestBody ReservaLibro Reserva) {
        return reservaLibroUseCase.updateReserva(id, Reserva);
    }

    @DeleteMapping("/{id}")
    public Void deleteReserva(@PathVariable String id) {
        return reservaLibroUseCase.deleteReserva(id);
    }
}
