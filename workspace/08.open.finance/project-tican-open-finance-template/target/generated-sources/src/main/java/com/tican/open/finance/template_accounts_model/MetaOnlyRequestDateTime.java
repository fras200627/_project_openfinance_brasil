package com.tican.open.finance.template_accounts_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Meta informações referente à API requisitada.
 */

@Schema(name = "MetaOnlyRequestDateTime", description = "Meta informações referente à API requisitada.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:09.506997400-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class MetaOnlyRequestDateTime {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime requestDateTime;

  public MetaOnlyRequestDateTime() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public MetaOnlyRequestDateTime(OffsetDateTime requestDateTime) {
    this.requestDateTime = requestDateTime;
  }

  public MetaOnlyRequestDateTime requestDateTime(OffsetDateTime requestDateTime) {
    this.requestDateTime = requestDateTime;
    return this;
  }

  /**
   * Data e hora da consulta, conforme especificação RFC-3339, formato UTC.
   * @return requestDateTime
   */
  @NotNull @Valid @Size(max = 20) 
  @Schema(name = "requestDateTime", example = "2021-05-21T08:30Z", description = "Data e hora da consulta, conforme especificação RFC-3339, formato UTC.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("requestDateTime")
  public OffsetDateTime getRequestDateTime() {
    return requestDateTime;
  }

  public void setRequestDateTime(OffsetDateTime requestDateTime) {
    this.requestDateTime = requestDateTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetaOnlyRequestDateTime metaOnlyRequestDateTime = (MetaOnlyRequestDateTime) o;
    return Objects.equals(this.requestDateTime, metaOnlyRequestDateTime.requestDateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestDateTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MetaOnlyRequestDateTime {\n");
    sb.append("    requestDateTime: ").append(toIndentedString(requestDateTime)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
  public static class Builder {

    private MetaOnlyRequestDateTime instance;

    public Builder() {
      this(new MetaOnlyRequestDateTime());
    }

    protected Builder(MetaOnlyRequestDateTime instance) {
      this.instance = instance;
    }

    protected Builder copyOf(MetaOnlyRequestDateTime value) { 
      this.instance.setRequestDateTime(value.requestDateTime);
      return this;
    }

    public MetaOnlyRequestDateTime.Builder requestDateTime(OffsetDateTime requestDateTime) {
      this.instance.requestDateTime(requestDateTime);
      return this;
    }
    
    /**
    * returns a built MetaOnlyRequestDateTime instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public MetaOnlyRequestDateTime build() {
      try {
        return this.instance;
      } finally {
        // ensure that this.instance is not reused
        this.instance = null;
      }
    }

    @Override
    public String toString() {
      return getClass() + "=(" + instance + ")";
    }
  }

  /**
  * Create a builder with no initialized field (except for the default values).
  */
  public static MetaOnlyRequestDateTime.Builder builder() {
    return new MetaOnlyRequestDateTime.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public MetaOnlyRequestDateTime.Builder toBuilder() {
    MetaOnlyRequestDateTime.Builder builder = new MetaOnlyRequestDateTime.Builder();
    return builder.copyOf(this);
  }

}

