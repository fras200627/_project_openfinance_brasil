package com.ofb.resources.camel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RequestOrchParams {
    private List<RequestParam> requestOrchParams;
}
