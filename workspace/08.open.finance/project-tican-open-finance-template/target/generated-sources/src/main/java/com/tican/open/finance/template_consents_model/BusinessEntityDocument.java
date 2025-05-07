package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * BusinessEntityDocument
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class BusinessEntityDocument {

  private String identification;

  private String rel;

  public BusinessEntityDocument() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public BusinessEntityDocument(String identification, String rel) {
    this.identification = identification;
    this.rel = rel;
  }

  public BusinessEntityDocument identification(String identification) {
    this.identification = identification;
    return this;
  }

  /**
   * Número do documento de identificação oficial do titular pessoa jurídica.
   * @return identification
   */
  @NotNull @Pattern(regexp = "^\\d{14}$") @Size(max = 14) 
  @Schema(name = "identification", example = "11111111111111", description = "Número do documento de identificação oficial do titular pessoa jurídica.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("identification")
  public String getIdentification() {
    return identification;
  }

  public void setIdentification(String identification) {
    this.identification = identification;
  }

  public BusinessEntityDocument rel(String rel) {
    this.rel = rel;
    return this;
  }

  /**
   * Tipo do documento de identificação oficial do titular pessoa jurídica.
   * @return rel
   */
  @NotNull @Pattern(regexp = "^[A-Z]{4}$") @Size(max = 4) 
  @Schema(name = "rel", example = "CNPJ", description = "Tipo do documento de identificação oficial do titular pessoa jurídica.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("rel")
  public String getRel() {
    return rel;
  }

  public void setRel(String rel) {
    this.rel = rel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BusinessEntityDocument businessEntityDocument = (BusinessEntityDocument) o;
    return Objects.equals(this.identification, businessEntityDocument.identification) &&
        Objects.equals(this.rel, businessEntityDocument.rel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identification, rel);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BusinessEntityDocument {\n");
    sb.append("    identification: ").append(toIndentedString(identification)).append("\n");
    sb.append("    rel: ").append(toIndentedString(rel)).append("\n");
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

    private BusinessEntityDocument instance;

    public Builder() {
      this(new BusinessEntityDocument());
    }

    protected Builder(BusinessEntityDocument instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BusinessEntityDocument value) { 
      this.instance.setIdentification(value.identification);
      this.instance.setRel(value.rel);
      return this;
    }

    public BusinessEntityDocument.Builder identification(String identification) {
      this.instance.identification(identification);
      return this;
    }
    
    public BusinessEntityDocument.Builder rel(String rel) {
      this.instance.rel(rel);
      return this;
    }
    
    /**
    * returns a built BusinessEntityDocument instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BusinessEntityDocument build() {
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
  public static BusinessEntityDocument.Builder builder() {
    return new BusinessEntityDocument.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BusinessEntityDocument.Builder toBuilder() {
    BusinessEntityDocument.Builder builder = new BusinessEntityDocument.Builder();
    return builder.copyOf(this);
  }

}

