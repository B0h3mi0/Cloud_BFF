package com.bff.library.infrastructure.in.web;

import com.bff.library.application.port.in.GraphQLReservaUseCase;
import com.bff.library.domain.model.GraphQLRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/graphql/reservas")
@RequiredArgsConstructor
public class GraphQLReservaController {

    private final GraphQLReservaUseCase graphQLReservaUseCase;

    @GetMapping
    public Object graphQLReservaGet(
            @RequestParam(name = "query", required = false) String query,
            @RequestParam(name = "operationName", required = false) String operationName,
            @RequestParam(name = "variables", required = false) String variables) {
        return graphQLReservaUseCase.executeGraphQLGet(query, operationName, variables);
    }

    @PostMapping
    public Object graphQLReservaPost(@RequestBody GraphQLRequest request) {
        return graphQLReservaUseCase.executeGraphQLPost(request);
    }
}
