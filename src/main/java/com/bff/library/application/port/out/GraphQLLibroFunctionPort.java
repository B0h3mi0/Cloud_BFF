package com.bff.library.application.port.out;

import com.bff.library.domain.model.GraphQLRequest;

public interface GraphQLLibroFunctionPort {
    Object executeGraphQLGet(String query, String operationName, String variables);
    Object executeGraphQLPost(GraphQLRequest request);
}
