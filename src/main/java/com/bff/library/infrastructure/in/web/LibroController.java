package com.bff.library.infrastructure.in.web;

import com.bff.library.application.port.in.LibroUseCase;
import com.bff.library.domain.model.Libro;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
@RequiredArgsConstructor
public class LibroController {

    private final LibroUseCase libroUseCase;

    @PostMapping
    public Libro createLibro(@RequestBody Libro libro) {
        return libroUseCase.createLibro(libro);
    }

    @GetMapping("/{id}")
    public Optional<Libro> getLibroById(@PathVariable String id) {
        return libroUseCase.getLibroById(id);
    }

    @GetMapping
    public List<Libro> getAllLibros() {
        return libroUseCase.getAllLibros();
    }

    @PutMapping("/{id}")
    public Libro updateLibro(@PathVariable String id, @RequestBody Libro libro) {
        return libroUseCase.updateLibro(id, libro);
    }

    @DeleteMapping("/{id}")
    public Void deleteLibro(@PathVariable String id) {
        return libroUseCase.deleteLibro(id);
    }
}
