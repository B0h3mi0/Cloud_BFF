package com.bff.library.infrastructure.out.azure;

import com.bff.library.application.port.out.ReservaFunctionPort;
import com.bff.library.domain.model.ReservaLibro;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Component
public class ReservaAzureAdapter implements ReservaFunctionPort {

    private final WebClient webClient;
    private final String baseUrl;

    public ReservaAzureAdapter(WebClient webClient, @Value("${azure.functions.bookloans.url}") String baseUrl) {
        this.webClient = webClient;
        this.baseUrl = baseUrl;
    }

    @Override
    public ReservaLibro createReserva(ReservaLibro reserva) {
        return webClient.post()
                .uri(baseUrl)
                .bodyValue(reserva)
                .retrieve()
                .bodyToMono(ReservaLibro.class)
                .block();
    }

    @Override
    public Optional<ReservaLibro> getReservaById(String id) {
        return webClient.get()
                .uri(baseUrl + "/{id}", id)
                .retrieve()
                .bodyToMono(ReservaLibro.class)
                .blockOptional();
    }

    @Override
    public List<ReservaLibro> getAllReservas() {
        return webClient.get()
                .uri(baseUrl)
                .retrieve()
                .bodyToFlux(ReservaLibro.class)
                .collectList()
                .block();
    }

    @Override
    public ReservaLibro updateReserva(String id, ReservaLibro reserva) {
        return webClient.put()
                .uri(baseUrl + "/{id}", id)
                .bodyValue(reserva)
                .retrieve()
                .bodyToMono(ReservaLibro.class)
                .block();
    }

    @Override
    public Void deleteReserva(String id) {
        return webClient.delete()
                .uri(baseUrl + "/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
