package com.tican.open.finance.template_accounts_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Indicador da transação:    - Transação efetivada: a transação atinge esse status quando o `transactionId` torna-se imutável;   - Lançamento futuro: a transação será efetivada em momento futuro, ou seja, o `transactionId` pode mudar;   - Transação processando: a transação está em processamento, ou seja, o `transactionId` pode mudar. 
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:09.506997400-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public enum EnumCompletedAuthorisedPaymentIndicator {
  
  TRANSACAO_EFETIVADA("TRANSACAO_EFETIVADA"),
  
  LANCAMENTO_FUTURO("LANCAMENTO_FUTURO"),
  
  TRANSACAO_PROCESSANDO("TRANSACAO_PROCESSANDO");

  private String value;

  EnumCompletedAuthorisedPaymentIndicator(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static EnumCompletedAuthorisedPaymentIndicator fromValue(String value) {
    for (EnumCompletedAuthorisedPaymentIndicator b : EnumCompletedAuthorisedPaymentIndicator.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

