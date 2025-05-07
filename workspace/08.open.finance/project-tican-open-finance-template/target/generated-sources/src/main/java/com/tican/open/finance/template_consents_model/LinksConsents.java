package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
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
 * Referências para outros recusos da API requisitada.
 */

@Schema(name = "LinksConsents", description = "Referências para outros recusos da API requisitada.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class LinksConsents {

  private URI self;

  public LinksConsents() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public LinksConsents(URI self) {
    this.self = self;
  }

  public LinksConsents self(URI self) {
    this.self = self;
    return this;
  }

  /**
   * URI completo que gerou a resposta atual.
   * @return self
   */
  @NotNull @Valid @Pattern(regexp = "^(https://)?(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)$") @Size(max = 2000) 
  @Schema(name = "self", example = "https://api.banco.com.br/open-banking/api/v1/resource", description = "URI completo que gerou a resposta atual.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("self")
  public URI getSelf() {
    return self;
  }

  public void setSelf(URI self) {
    this.self = self;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LinksConsents linksConsents = (LinksConsents) o;
    return Objects.equals(this.self, linksConsents.self);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LinksConsents {\n");
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
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

    private LinksConsents instance;

    public Builder() {
      this(new LinksConsents());
    }

    protected Builder(LinksConsents instance) {
      this.instance = instance;
    }

    protected Builder copyOf(LinksConsents value) { 
      this.instance.setSelf(value.self);
      return this;
    }

    public LinksConsents.Builder self(URI self) {
      this.instance.self(self);
      return this;
    }
    
    /**
    * returns a built LinksConsents instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public LinksConsents build() {
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
  public static LinksConsents.Builder builder() {
    return new LinksConsents.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public LinksConsents.Builder toBuilder() {
    LinksConsents.Builder builder = new LinksConsents.Builder();
    return builder.copyOf(this);
  }

}

