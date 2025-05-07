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
 * Identificação do Tipo de Pessoa da pessoa envolvida na transação.    Pessoa Natural - Informar CPF no campo “partieCnpjCpf”.    Pessoa Jurídica - Informar CNPJ no campo “partieCnpjCpf”. 
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:09.506997400-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public enum EnumPartiePersonType {
  
  PESSOA_NATURAL("PESSOA_NATURAL"),
  
  PESSOA_JURIDICA("PESSOA_JURIDICA");

  private String value;

  EnumPartiePersonType(String value) {
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
  public static EnumPartiePersonType fromValue(String value) {
    for (EnumPartiePersonType b : EnumPartiePersonType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

