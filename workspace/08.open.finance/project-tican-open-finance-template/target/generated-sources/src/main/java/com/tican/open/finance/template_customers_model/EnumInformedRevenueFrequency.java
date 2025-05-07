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
 * Traz a frequência ou período do faturamento informado. \"O faturamento é calculado a partir de todos os benefícios que a empresa conseguiu com sua atividade econômica em um determinado período. Esses benefícios são os rendimentos ou ganhos da organização através de suas vendas ou serviços prestados\". 
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public enum EnumInformedRevenueFrequency {
  
  DIARIA("DIARIA"),
  
  SEMANAL("SEMANAL"),
  
  QUINZENAL("QUINZENAL"),
  
  MENSAL("MENSAL"),
  
  BIMESTRAL("BIMESTRAL"),
  
  TRIMESTRAL("TRIMESTRAL"),
  
  SEMESTRAL("SEMESTRAL"),
  
  ANUAL("ANUAL"),
  
  OUTROS("OUTROS");

  private String value;

  EnumInformedRevenueFrequency(String value) {
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
  public static EnumInformedRevenueFrequency fromValue(String value) {
    for (EnumInformedRevenueFrequency b : EnumInformedRevenueFrequency.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

