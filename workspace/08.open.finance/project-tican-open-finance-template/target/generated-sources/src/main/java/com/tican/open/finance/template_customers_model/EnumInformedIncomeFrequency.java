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
 * Traz a frequência ou período da renda informada.
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public enum EnumInformedIncomeFrequency {
  
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

  EnumInformedIncomeFrequency(String value) {
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
  public static EnumInformedIncomeFrequency fromValue(String value) {
    for (EnumInformedIncomeFrequency b : EnumInformedIncomeFrequency.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

