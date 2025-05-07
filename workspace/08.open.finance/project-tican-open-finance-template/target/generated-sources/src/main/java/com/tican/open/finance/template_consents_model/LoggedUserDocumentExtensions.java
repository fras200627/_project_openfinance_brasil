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
 * LoggedUserDocumentExtensions
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class LoggedUserDocumentExtensions {

  private String identification;

  private String rel;

  public LoggedUserDocumentExtensions() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public LoggedUserDocumentExtensions(String identification, String rel) {
    this.identification = identification;
    this.rel = rel;
  }

  public LoggedUserDocumentExtensions identification(String identification) {
    this.identification = identification;
    return this;
  }

  /**
   * Número do documento de identificação oficial do usuário.
   * @return identification
   */
  @NotNull @Pattern(regexp = "^\\d{11}$") @Size(max = 11) 
  @Schema(name = "identification", example = "11111111111", description = "Número do documento de identificação oficial do usuário.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("identification")
  public String getIdentification() {
    return identification;
  }

  public void setIdentification(String identification) {
    this.identification = identification;
  }

  public LoggedUserDocumentExtensions rel(String rel) {
    this.rel = rel;
    return this;
  }

  /**
   * Tipo do documento de identificação oficial do usuário.
   * @return rel
   */
  @NotNull @Pattern(regexp = "^[A-Z]{3}$") @Size(max = 3) 
  @Schema(name = "rel", example = "CPF", description = "Tipo do documento de identificação oficial do usuário.", requiredMode = Schema.RequiredMode.REQUIRED)
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
    LoggedUserDocumentExtensions loggedUserDocumentExtensions = (LoggedUserDocumentExtensions) o;
    return Objects.equals(this.identification, loggedUserDocumentExtensions.identification) &&
        Objects.equals(this.rel, loggedUserDocumentExtensions.rel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identification, rel);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoggedUserDocumentExtensions {\n");
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

    private LoggedUserDocumentExtensions instance;

    public Builder() {
      this(new LoggedUserDocumentExtensions());
    }

    protected Builder(LoggedUserDocumentExtensions instance) {
      this.instance = instance;
    }

    protected Builder copyOf(LoggedUserDocumentExtensions value) { 
      this.instance.setIdentification(value.identification);
      this.instance.setRel(value.rel);
      return this;
    }

    public LoggedUserDocumentExtensions.Builder identification(String identification) {
      this.instance.identification(identification);
      return this;
    }
    
    public LoggedUserDocumentExtensions.Builder rel(String rel) {
      this.instance.rel(rel);
      return this;
    }
    
    /**
    * returns a built LoggedUserDocumentExtensions instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public LoggedUserDocumentExtensions build() {
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
  public static LoggedUserDocumentExtensions.Builder builder() {
    return new LoggedUserDocumentExtensions.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public LoggedUserDocumentExtensions.Builder toBuilder() {
    LoggedUserDocumentExtensions.Builder builder = new LoggedUserDocumentExtensions.Builder();
    return builder.copyOf(this);
  }

}

