package com.bff.library.infrastructure.out.azure;

import com.bff.library.application.port.out.LibroFunctionPort;
import com.bff.library.domain.model.Libro;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Component
public class LibroAzureAdapter implements LibroFunctionPort {

    private final WebClient webClient;
    private final String baseUrl;

    public LibroAzureAdapter(WebClient webClient, @Value("${azure.functions.bookloans.url}") String baseUrl) {
        this.webClient = webClient;
        this.baseUrl = baseUrl;
    }

    @Override
    public Libro createLibro(Libro libro) {
        return webClient.post()
                .uri(baseUrl)
                .bodyValue(libro)
                .retrieve()
                .bodyToMono(Libro.class)
                .block();
    }

    @Override
    public Optional<Libro> getLibroById(String id) {
        return webClient.get()
                .uri(baseUrl + "/{id}", id)
                .retrieve()
                .bodyToMono(Libro.class)
                .blockOptional();
    }

    @Override
    public List<Libro> getAllLibros() {
        return webClient.get()
                .uri(baseUrl)
                .retrieve()
                .bodyToFlux(Libro.class)
                .collectList()
                .block();
    }

    @Override
    public Libro updateLibro(String id, Libro libro) {
        return webClient.put()
                .uri(baseUrl + "/{id}", id)
                .bodyValue(libro)
                .retrieve()
                .bodyToMono(Libro.class)
                .block();
    }

    @Override
    public Void deleteLibro(String id) {
        return webClient.delete()
                .uri(baseUrl + "/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
