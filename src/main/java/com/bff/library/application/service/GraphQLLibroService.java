package com.bff.library.application.service;

import com.bff.library.application.port.in.GraphQLLibroUseCase;
import com.bff.library.application.port.out.GraphQLLibroFunctionPort;
import com.bff.library.domain.model.GraphQLRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GraphQLLibroService implements GraphQLLibroUseCase {

    private final GraphQLLibroFunctionPort graphQLLibroFunctionPort;

    @Override
    public Object executeGraphQLGet(String query, String operationName, String variables) {
        return graphQLLibroFunctionPort.executeGraphQLGet(query, operationName, variables);
    }

    @Override
    public Object executeGraphQLPost(GraphQLRequest request) {
        return graphQLLibroFunctionPort.executeGraphQLPost(request);
    }
}
