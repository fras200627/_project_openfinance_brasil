package com.ofb.consents.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Data @Builder
@AllArgsConstructor @RequiredArgsConstructor
public final class RespponseExpirationDatetimeModel {
    private Timestamp  expirationDateTimeStamp;
    private Timestamp  expirationDateTimeAdjusted;
    private Timestamp  expirationDateTimeRequested;
    private Long       expirationInMonths;
    private String     expirationDateInfo;
}
