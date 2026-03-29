package com.bff.library.infrastructure.out.azure;

import com.bff.library.application.port.out.UserFunctionPort;
import com.bff.library.domain.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Component
public class UserAzureAdapter implements UserFunctionPort {

    private final WebClient webClient;
    private final String baseUrl;

    public UserAzureAdapter(WebClient webClient, @Value("${azure.functions.users.url}") String baseUrl) {
        this.webClient = webClient;
        this.baseUrl = baseUrl;
    }

    @Override
    public User createUser(User user) {
        return webClient.post()
                .uri(baseUrl)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    @Override
    public Optional<User> getUserById(String id) {
        return webClient.get()
                .uri(baseUrl + "/{id}", id)
                .retrieve()
                .bodyToMono(User.class)
                .blockOptional();
    }

    @Override
    public List<User> getAllUsers() {
        return webClient.get()
                .uri(baseUrl)
                .retrieve()
                .bodyToFlux(User.class)
                .collectList()
                .block();
    }

    @Override
    public User updateUser(String id, User user) {
        return webClient.put()
                .uri(baseUrl + "/{id}", id)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    @Override
    public Void deleteUser(String id) {
        return webClient.delete()
                .uri(baseUrl + "/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
