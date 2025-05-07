package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_consents_model.BusinessEntityDocument;
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
 * Titular, pessoa jurídica a quem se referem os dados que são objeto de compartilhamento.  É obrigatório que o número do CNPJ utilizado seja um número válido. A transmissora pode utilizar algoritmos de validação de documento para garantir que se trata de um documento válido, como por exemplo: Cálculo de DV módulo 11 para o CNPJ. 
 */

@Schema(name = "BusinessEntity", description = "Titular, pessoa jurídica a quem se referem os dados que são objeto de compartilhamento.  É obrigatório que o número do CNPJ utilizado seja um número válido. A transmissora pode utilizar algoritmos de validação de documento para garantir que se trata de um documento válido, como por exemplo: Cálculo de DV módulo 11 para o CNPJ. ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class BusinessEntity {

  private BusinessEntityDocument document;

  public BusinessEntity() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public BusinessEntity(BusinessEntityDocument document) {
    this.document = document;
  }

  public BusinessEntity document(BusinessEntityDocument document) {
    this.document = document;
    return this;
  }

  /**
   * Get document
   * @return document
   */
  @NotNull @Valid 
  @Schema(name = "document", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("document")
  public BusinessEntityDocument getDocument() {
    return document;
  }

  public void setDocument(BusinessEntityDocument document) {
    this.document = document;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BusinessEntity businessEntity = (BusinessEntity) o;
    return Objects.equals(this.document, businessEntity.document);
  }

  @Override
  public int hashCode() {
    return Objects.hash(document);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BusinessEntity {\n");
    sb.append("    document: ").append(toIndentedString(document)).append("\n");
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

    private BusinessEntity instance;

    public Builder() {
      this(new BusinessEntity());
    }

    protected Builder(BusinessEntity instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BusinessEntity value) { 
      this.instance.setDocument(value.document);
      return this;
    }

    public BusinessEntity.Builder document(BusinessEntityDocument document) {
      this.instance.document(document);
      return this;
    }
    
    /**
    * returns a built BusinessEntity instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BusinessEntity build() {
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
  public static BusinessEntity.Builder builder() {
    return new BusinessEntity.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BusinessEntity.Builder toBuilder() {
    BusinessEntity.Builder builder = new BusinessEntity.Builder();
    return builder.copyOf(this);
  }

}

