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

@Schema(name = "Meta", description = "Meta informações referente à API requisitada.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:09.506997400-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class Meta {

  private Integer totalRecords;

  private Integer totalPages;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime requestDateTime;

  public Meta() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public Meta(Integer totalRecords, Integer totalPages, OffsetDateTime requestDateTime) {
    this.totalRecords = totalRecords;
    this.totalPages = totalPages;
    this.requestDateTime = requestDateTime;
  }

  public Meta totalRecords(Integer totalRecords) {
    this.totalRecords = totalRecords;
    return this;
  }

  /**
   * Número total de registros no resultado
   * @return totalRecords
   */
  @NotNull 
  @Schema(name = "totalRecords", example = "1", description = "Número total de registros no resultado", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("totalRecords")
  public Integer getTotalRecords() {
    return totalRecords;
  }

  public void setTotalRecords(Integer totalRecords) {
    this.totalRecords = totalRecords;
  }

  public Meta totalPages(Integer totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  /**
   * Número total de páginas no resultado
   * @return totalPages
   */
  @NotNull 
  @Schema(name = "totalPages", example = "1", description = "Número total de páginas no resultado", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("totalPages")
  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public Meta requestDateTime(OffsetDateTime requestDateTime) {
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
    Meta meta = (Meta) o;
    return Objects.equals(this.totalRecords, meta.totalRecords) &&
        Objects.equals(this.totalPages, meta.totalPages) &&
        Objects.equals(this.requestDateTime, meta.requestDateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalRecords, totalPages, requestDateTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Meta {\n");
    sb.append("    totalRecords: ").append(toIndentedString(totalRecords)).append("\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
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

    private Meta instance;

    public Builder() {
      this(new Meta());
    }

    protected Builder(Meta instance) {
      this.instance = instance;
    }

    protected Builder copyOf(Meta value) { 
      this.instance.setTotalRecords(value.totalRecords);
      this.instance.setTotalPages(value.totalPages);
      this.instance.setRequestDateTime(value.requestDateTime);
      return this;
    }

    public Meta.Builder totalRecords(Integer totalRecords) {
      this.instance.totalRecords(totalRecords);
      return this;
    }
    
    public Meta.Builder totalPages(Integer totalPages) {
      this.instance.totalPages(totalPages);
      return this;
    }
    
    public Meta.Builder requestDateTime(OffsetDateTime requestDateTime) {
      this.instance.requestDateTime(requestDateTime);
      return this;
    }
    
    /**
    * returns a built Meta instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public Meta build() {
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
  public static Meta.Builder builder() {
    return new Meta.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public Meta.Builder toBuilder() {
    Meta.Builder builder = new Meta.Builder();
    return builder.copyOf(this);
  }

}

