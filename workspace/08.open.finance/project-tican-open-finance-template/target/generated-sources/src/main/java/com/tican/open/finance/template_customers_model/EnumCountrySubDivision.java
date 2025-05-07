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
 * Enumeração referente a cada sigla da unidade da federação que identifica o estado ou o distrito federal, no qual o endereço está localizado. p.ex. 'AC'. São consideradas apenas as siglas para os estados brasileiros
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public enum EnumCountrySubDivision {
  
  AC("AC"),
  
  AL("AL"),
  
  AP("AP"),
  
  AM("AM"),
  
  BA("BA"),
  
  CE("CE"),
  
  DF("DF"),
  
  ES("ES"),
  
  GO("GO"),
  
  MA("MA"),
  
  MT("MT"),
  
  MS("MS"),
  
  MG("MG"),
  
  PA("PA"),
  
  PB("PB"),
  
  PR("PR"),
  
  PE("PE"),
  
  PI("PI"),
  
  RJ("RJ"),
  
  RN("RN"),
  
  RS("RS"),
  
  RO("RO"),
  
  RR("RR"),
  
  SC("SC"),
  
  SP("SP"),
  
  SE("SE"),
  
  TO("TO");

  private String value;

  EnumCountrySubDivision(String value) {
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
  public static EnumCountrySubDivision fromValue(String value) {
    for (EnumCountrySubDivision b : EnumCountrySubDivision.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

