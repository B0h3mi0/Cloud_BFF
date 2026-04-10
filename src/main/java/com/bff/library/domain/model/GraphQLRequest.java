package com.bff.library.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphQLRequest {
    private String query;
    private String operationName;
    private Map<String, Object> variables;
}
