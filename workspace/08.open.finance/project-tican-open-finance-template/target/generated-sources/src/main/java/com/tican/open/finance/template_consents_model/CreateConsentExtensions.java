package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_consents_model.CreateConsentExtensionsData;
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
 * CreateConsentExtensions
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class CreateConsentExtensions {

  private CreateConsentExtensionsData data;

  public CreateConsentExtensions() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public CreateConsentExtensions(CreateConsentExtensionsData data) {
    this.data = data;
  }

  public CreateConsentExtensions data(CreateConsentExtensionsData data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
   */
  @NotNull @Valid 
  @Schema(name = "data", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("data")
  public CreateConsentExtensionsData getData() {
    return data;
  }

  public void setData(CreateConsentExtensionsData data) {
    this.data = data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateConsentExtensions createConsentExtensions = (CreateConsentExtensions) o;
    return Objects.equals(this.data, createConsentExtensions.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateConsentExtensions {\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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

    private CreateConsentExtensions instance;

    public Builder() {
      this(new CreateConsentExtensions());
    }

    protected Builder(CreateConsentExtensions instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CreateConsentExtensions value) { 
      this.instance.setData(value.data);
      return this;
    }

    public CreateConsentExtensions.Builder data(CreateConsentExtensionsData data) {
      this.instance.data(data);
      return this;
    }
    
    /**
    * returns a built CreateConsentExtensions instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CreateConsentExtensions build() {
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
  public static CreateConsentExtensions.Builder builder() {
    return new CreateConsentExtensions.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CreateConsentExtensions.Builder toBuilder() {
    CreateConsentExtensions.Builder builder = new CreateConsentExtensions.Builder();
    return builder.copyOf(this);
  }

}

