package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_consents_model.LoggedUserDocument;
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
 * Usuário (pessoa natural) que encontra-se logado na instituição receptora e que iniciará o processo de consentimento para compartilhamento de dados.  É obrigatório que o número do documento utilizado seja um número válido e pertencente ao usuário logado. A transmissora pode utilizar algoritmos de validação de documento para garantir que se trata de um documento válido, como por exemplo: Cálculo de DV módulo 11 para o CPF. 
 */

@Schema(name = "LoggedUser", description = "Usuário (pessoa natural) que encontra-se logado na instituição receptora e que iniciará o processo de consentimento para compartilhamento de dados.  É obrigatório que o número do documento utilizado seja um número válido e pertencente ao usuário logado. A transmissora pode utilizar algoritmos de validação de documento para garantir que se trata de um documento válido, como por exemplo: Cálculo de DV módulo 11 para o CPF. ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class LoggedUser {

  private LoggedUserDocument document;

  public LoggedUser() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public LoggedUser(LoggedUserDocument document) {
    this.document = document;
  }

  public LoggedUser document(LoggedUserDocument document) {
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
  public LoggedUserDocument getDocument() {
    return document;
  }

  public void setDocument(LoggedUserDocument document) {
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
    LoggedUser loggedUser = (LoggedUser) o;
    return Objects.equals(this.document, loggedUser.document);
  }

  @Override
  public int hashCode() {
    return Objects.hash(document);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoggedUser {\n");
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

    private LoggedUser instance;

    public Builder() {
      this(new LoggedUser());
    }

    protected Builder(LoggedUser instance) {
      this.instance = instance;
    }

    protected Builder copyOf(LoggedUser value) { 
      this.instance.setDocument(value.document);
      return this;
    }

    public LoggedUser.Builder document(LoggedUserDocument document) {
      this.instance.document(document);
      return this;
    }
    
    /**
    * returns a built LoggedUser instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public LoggedUser build() {
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
  public static LoggedUser.Builder builder() {
    return new LoggedUser.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public LoggedUser.Builder toBuilder() {
    LoggedUser.Builder builder = new LoggedUser.Builder();
    return builder.copyOf(this);
  }

}

