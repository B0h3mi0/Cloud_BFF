package com.bff.library.application.service;

import com.bff.library.application.port.in.GraphQLReservaUseCase;
import com.bff.library.application.port.out.GraphQLReservaFunctionPort;
import com.bff.library.domain.model.GraphQLRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GraphQLReservaService implements GraphQLReservaUseCase {

    private final GraphQLReservaFunctionPort graphQLReservaFunctionPort;

    @Override
    public Object executeGraphQLGet(String query, String operationName, String variables) {
        return graphQLReservaFunctionPort.executeGraphQLGet(query, operationName, variables);
    }

    @Override
    public Object executeGraphQLPost(GraphQLRequest request) {
        return graphQLReservaFunctionPort.executeGraphQLPost(request);
    }
}
