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
 * Estado marital do cliente. 
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public enum EnumMaritalStatusCode {
  
  SOLTEIRO("SOLTEIRO"),
  
  CASADO("CASADO"),
  
  VIUVO("VIUVO"),
  
  SEPARADO_JUDICIALMENTE("SEPARADO_JUDICIALMENTE"),
  
  DIVORCIADO("DIVORCIADO"),
  
  UNIAO_ESTAVEL("UNIAO_ESTAVEL"),
  
  OUTRO("OUTRO");

  private String value;

  EnumMaritalStatusCode(String value) {
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
  public static EnumMaritalStatusCode fromValue(String value) {
    for (EnumMaritalStatusCode b : EnumMaritalStatusCode.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

