package com.tican.open.finance.template_resources_model;

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

@Schema(name = "MetaResponse", description = "Meta informações referente à API requisitada.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:04.924183300-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class MetaResponse {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime requestDateTime;

  private Integer totalRecords;

  private Integer totalPages;

  public MetaResponse() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public MetaResponse(OffsetDateTime requestDateTime, Integer totalRecords, Integer totalPages) {
    this.requestDateTime = requestDateTime;
    this.totalRecords = totalRecords;
    this.totalPages = totalPages;
  }

  public MetaResponse requestDateTime(OffsetDateTime requestDateTime) {
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

  public MetaResponse totalRecords(Integer totalRecords) {
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

  public MetaResponse totalPages(Integer totalPages) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetaResponse metaResponse = (MetaResponse) o;
    return Objects.equals(this.requestDateTime, metaResponse.requestDateTime) &&
        Objects.equals(this.totalRecords, metaResponse.totalRecords) &&
        Objects.equals(this.totalPages, metaResponse.totalPages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestDateTime, totalRecords, totalPages);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MetaResponse {\n");
    sb.append("    requestDateTime: ").append(toIndentedString(requestDateTime)).append("\n");
    sb.append("    totalRecords: ").append(toIndentedString(totalRecords)).append("\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
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

    private MetaResponse instance;

    public Builder() {
      this(new MetaResponse());
    }

    protected Builder(MetaResponse instance) {
      this.instance = instance;
    }

    protected Builder copyOf(MetaResponse value) { 
      this.instance.setRequestDateTime(value.requestDateTime);
      this.instance.setTotalRecords(value.totalRecords);
      this.instance.setTotalPages(value.totalPages);
      return this;
    }

    public MetaResponse.Builder requestDateTime(OffsetDateTime requestDateTime) {
      this.instance.requestDateTime(requestDateTime);
      return this;
    }
    
    public MetaResponse.Builder totalRecords(Integer totalRecords) {
      this.instance.totalRecords(totalRecords);
      return this;
    }
    
    public MetaResponse.Builder totalPages(Integer totalPages) {
      this.instance.totalPages(totalPages);
      return this;
    }
    
    /**
    * returns a built MetaResponse instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public MetaResponse build() {
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
  public static MetaResponse.Builder builder() {
    return new MetaResponse.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public MetaResponse.Builder toBuilder() {
    MetaResponse.Builder builder = new MetaResponse.Builder();
    return builder.copyOf(this);
  }

}

