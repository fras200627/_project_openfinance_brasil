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
 * Indicador do tipo de lançamento:  Débito (no extrato) Em um extrato bancário, os débitos, marcados com a letra “D” ao lado do valor registrado, informam as saídas de dinheiro na conta-corrente. Crédito (no extrato) Em um extrato bancário, os créditos, marcados com a letra “C” ao lado do valor registrado, informam as entradas de dinheiro na conta-corrente. 
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:09.506997400-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public enum EnumCreditDebitIndicator {
  
  CREDITO("CREDITO"),
  
  DEBITO("DEBITO");

  private String value;

  EnumCreditDebitIndicator(String value) {
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
  public static EnumCreditDebitIndicator fromValue(String value) {
    for (EnumCreditDebitIndicator b : EnumCreditDebitIndicator.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

