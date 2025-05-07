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
 * Subtipo de conta (vide Enum):  Conta individual - possui um único titular Conta conjunta simples - onde as movimentações financeiras só podem serem realizadas mediante autorização de TODOS os correntistas da conta. Conta conjunta solidária - é a modalidade cujos titulares podem realizar movimentações de forma isolada, isto é, sem que seja necessária a autorização dos demais titulares 
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:09.506997400-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public enum EnumAccountSubType {
  
  INDIVIDUAL("INDIVIDUAL"),
  
  CONJUNTA_SIMPLES("CONJUNTA_SIMPLES"),
  
  CONJUNTA_SOLIDARIA("CONJUNTA_SOLIDARIA");

  private String value;

  EnumAccountSubType(String value) {
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
  public static EnumAccountSubType fromValue(String value) {
    for (EnumAccountSubType b : EnumAccountSubType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

