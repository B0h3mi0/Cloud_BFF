package com.bff.library.application.port.out;

import com.bff.library.domain.model.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroFunctionPort {
    Libro createLibro(Libro libro);
    Optional<Libro> getLibroById(String id);
    List<Libro> getAllLibros();
    Libro updateLibro(String id, Libro libro);
    Void deleteLibro(String id);
}
