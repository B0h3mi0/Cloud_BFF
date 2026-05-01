package com.bff.library.application.service;

import com.bff.library.application.port.in.LibroUseCase;
import com.bff.library.application.port.out.LibroFunctionPort;
import com.bff.library.domain.model.Libro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibroService implements LibroUseCase {

    private final LibroFunctionPort libroFunctionPort;

    @Override
    public Libro createLibro(Libro libro) {
        return libroFunctionPort.createLibro(libro);
    }

    @Override
    public Optional<Libro> getLibroById(String id) {
        return libroFunctionPort.getLibroById(id);
    }

    @Override
    public List<Libro> getAllLibros() {
        return libroFunctionPort.getAllLibros();
    }

    @Override
    public Libro updateLibro(String id, Libro libro) {
        return libroFunctionPort.updateLibro(id, libro);
    }

    @Override
    public Void deleteLibro(String id) {
        return libroFunctionPort.deleteLibro(id);
    }
}
