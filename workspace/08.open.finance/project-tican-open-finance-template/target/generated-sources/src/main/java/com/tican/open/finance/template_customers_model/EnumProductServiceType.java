package com.tican.open.finance.template_customers_model;

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
 * Lista com a relação dos produtos e serviços com contrato vigente.
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public enum EnumProductServiceType {
  
  CONTA_DEPOSITO_A_VISTA("CONTA_DEPOSITO_A_VISTA"),
  
  CONTA_POUPANCA("CONTA_POUPANCA"),
  
  CONTA_PAGAMENTO_PRE_PAGA("CONTA_PAGAMENTO_PRE_PAGA"),
  
  CARTAO_CREDITO("CARTAO_CREDITO"),
  
  OPERACAO_CREDITO("OPERACAO_CREDITO"),
  
  SEGURO("SEGURO"),
  
  PREVIDENCIA("PREVIDENCIA"),
  
  INVESTIMENTO("INVESTIMENTO"),
  
  OPERACOES_CAMBIO("OPERACOES_CAMBIO"),
  
  CONTA_SALARIO("CONTA_SALARIO"),
  
  CREDENCIAMENTO("CREDENCIAMENTO"),
  
  OUTROS("OUTROS");

  private String value;

  EnumProductServiceType(String value) {
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
  public static EnumProductServiceType fromValue(String value) {
    for (EnumProductServiceType b : EnumProductServiceType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

