package com.ofb.resources.camel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @Builder @AllArgsConstructor
public class RequestParam {
    private String paramName;
    private Object paramValue;
}
