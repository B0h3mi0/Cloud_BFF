package com.bff.library.infrastructure.in.web;

import com.bff.library.application.port.in.GraphQLLibroUseCase;
import com.bff.library.domain.model.GraphQLRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/graphql/libros")
@RequiredArgsConstructor
public class GraphQLLibroController {

    private final GraphQLLibroUseCase graphQLLibroUseCase;

    @GetMapping
    public Object graphQLLibroGet(
            @RequestParam(name = "query", required = false) String query,
            @RequestParam(name = "operationName", required = false) String operationName,
            @RequestParam(name = "variables", required = false) String variables) {
        return graphQLLibroUseCase.executeGraphQLGet(query, operationName, variables);
    }

    @PostMapping
    public Object graphQLLibroPost(@RequestBody GraphQLRequest request) {
        return graphQLLibroUseCase.executeGraphQLPost(request);
    }
}
