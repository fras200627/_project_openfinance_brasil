package com.ofb.consents.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor @RequiredArgsConstructor
public class MQSendMessageAuthorizeConsentModel implements Serializable {
    private String sendMessageDatetime;
    private String    correlationId;
    private String    consentId;
    private String    objectRequest;
    private String    objectData;
}
