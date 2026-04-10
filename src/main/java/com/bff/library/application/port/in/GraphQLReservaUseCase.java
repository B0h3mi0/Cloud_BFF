package com.bff.library.application.port.in;

import com.bff.library.domain.model.GraphQLRequest;

public interface GraphQLReservaUseCase {
    Object executeGraphQLGet(String query, String operationName, String variables);
    Object executeGraphQLPost(GraphQLRequest request);
}
