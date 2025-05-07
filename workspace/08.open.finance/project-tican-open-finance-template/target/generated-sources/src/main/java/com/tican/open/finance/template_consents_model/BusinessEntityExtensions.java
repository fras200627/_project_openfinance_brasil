package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_consents_model.BusinessEntityDocumentExtensions;
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
 * Titular, pessoa jurídica a quem se referem os dados que são objeto de compartilhamento.   Deve ser informado apenas para casos de consentimento pessoa jurídica.   Não precisa ser armazenado separadamente. Para fins de renovação de consentimento, será utilizado apenas para verificação do consentimento vigente, pois é um atributo imutável. 
 */

@Schema(name = "BusinessEntityExtensions", description = "Titular, pessoa jurídica a quem se referem os dados que são objeto de compartilhamento.   Deve ser informado apenas para casos de consentimento pessoa jurídica.   Não precisa ser armazenado separadamente. Para fins de renovação de consentimento, será utilizado apenas para verificação do consentimento vigente, pois é um atributo imutável. ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class BusinessEntityExtensions {

  private BusinessEntityDocumentExtensions document;

  public BusinessEntityExtensions() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public BusinessEntityExtensions(BusinessEntityDocumentExtensions document) {
    this.document = document;
  }

  public BusinessEntityExtensions document(BusinessEntityDocumentExtensions document) {
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
  public BusinessEntityDocumentExtensions getDocument() {
    return document;
  }

  public void setDocument(BusinessEntityDocumentExtensions document) {
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
    BusinessEntityExtensions businessEntityExtensions = (BusinessEntityExtensions) o;
    return Objects.equals(this.document, businessEntityExtensions.document);
  }

  @Override
  public int hashCode() {
    return Objects.hash(document);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BusinessEntityExtensions {\n");
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

    private BusinessEntityExtensions instance;

    public Builder() {
      this(new BusinessEntityExtensions());
    }

    protected Builder(BusinessEntityExtensions instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BusinessEntityExtensions value) { 
      this.instance.setDocument(value.document);
      return this;
    }

    public BusinessEntityExtensions.Builder document(BusinessEntityDocumentExtensions document) {
      this.instance.document(document);
      return this;
    }
    
    /**
    * returns a built BusinessEntityExtensions instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BusinessEntityExtensions build() {
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
  public static BusinessEntityExtensions.Builder builder() {
    return new BusinessEntityExtensions.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BusinessEntityExtensions.Builder toBuilder() {
    BusinessEntityExtensions.Builder builder = new BusinessEntityExtensions.Builder();
    return builder.copyOf(this);
  }

}

