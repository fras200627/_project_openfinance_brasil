package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_consents_model.LoggedUserDocumentExtensions;
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
 * Usuário (pessoa natural) que encontra-se logado na instituição receptora e que iniciará o processo de consentimento para compartilhamento de dados.   Deve ser armazenado como novo usuário logado responsável pela renovação do consentimento atual. 
 */

@Schema(name = "LoggedUserExtensions", description = "Usuário (pessoa natural) que encontra-se logado na instituição receptora e que iniciará o processo de consentimento para compartilhamento de dados.   Deve ser armazenado como novo usuário logado responsável pela renovação do consentimento atual. ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class LoggedUserExtensions {

  private LoggedUserDocumentExtensions document;

  public LoggedUserExtensions() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public LoggedUserExtensions(LoggedUserDocumentExtensions document) {
    this.document = document;
  }

  public LoggedUserExtensions document(LoggedUserDocumentExtensions document) {
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
  public LoggedUserDocumentExtensions getDocument() {
    return document;
  }

  public void setDocument(LoggedUserDocumentExtensions document) {
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
    LoggedUserExtensions loggedUserExtensions = (LoggedUserExtensions) o;
    return Objects.equals(this.document, loggedUserExtensions.document);
  }

  @Override
  public int hashCode() {
    return Objects.hash(document);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoggedUserExtensions {\n");
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

    private LoggedUserExtensions instance;

    public Builder() {
      this(new LoggedUserExtensions());
    }

    protected Builder(LoggedUserExtensions instance) {
      this.instance = instance;
    }

    protected Builder copyOf(LoggedUserExtensions value) { 
      this.instance.setDocument(value.document);
      return this;
    }

    public LoggedUserExtensions.Builder document(LoggedUserDocumentExtensions document) {
      this.instance.document(document);
      return this;
    }
    
    /**
    * returns a built LoggedUserExtensions instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public LoggedUserExtensions build() {
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
  public static LoggedUserExtensions.Builder builder() {
    return new LoggedUserExtensions.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public LoggedUserExtensions.Builder toBuilder() {
    LoggedUserExtensions.Builder builder = new LoggedUserExtensions.Builder();
    return builder.copyOf(this);
  }

}

