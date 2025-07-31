package com.ofb.consents.model;

import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @Builder
@AllArgsConstructor @RequiredArgsConstructor
/**
 * This Default Object for responses methowds
 * @apiNote
 */
public final class ResponseValidateConsentModel {
    private boolean                        errorsListed            = false;
    private List<ResponseErrorsInnerTemplate> responseErrorsList      = new ArrayList<>();
    private Object                         objectData              = null;
    private Exception                      objectException         = null;
}
