package com.bff.library.infrastructure.out.azure;

import com.bff.library.application.port.out.GraphQLLibroFunctionPort;
import com.bff.library.domain.model.GraphQLRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class GraphQLLibroAzureAdapter implements GraphQLLibroFunctionPort {

    private final WebClient webClient;
    private final String baseUrl;

    public GraphQLLibroAzureAdapter(WebClient webClient, @Value("${azure.functions.graphql.reservas.url}") String baseUrl) {
        this.webClient = webClient;
        this.baseUrl = baseUrl;
    }

    @Override
    public Object executeGraphQLGet(String query, String operationName, String variables) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);
        if (query != null) {
            builder.queryParam("query", query);
        }
        if (operationName != null) {
            builder.queryParam("operationName", operationName);
        }
        if (variables != null) {
            builder.queryParam("variables", variables);
        }

        return webClient.get()
                .uri(builder.build().toUriString())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object executeGraphQLPost(GraphQLRequest request) {
        return webClient.post()
                .uri(baseUrl)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
