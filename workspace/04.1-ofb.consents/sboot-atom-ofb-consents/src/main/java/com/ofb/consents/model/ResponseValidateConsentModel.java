package com.ofb.consents.model;

import com.ofb.consents.server.consents.resources.model.ResponseConsentData;
import com.ofb.consents.server.consents.resources.model.ResponseErrorErrorsInner;
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
    private List<ResponseErrorErrorsInner> responseErrorsList      = new ArrayList<>();
    private Object                         objectData              = null;
    private Exception                      objectException         = null;
}
