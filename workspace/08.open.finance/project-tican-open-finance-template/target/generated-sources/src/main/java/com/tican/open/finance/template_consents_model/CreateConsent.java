package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_consents_model.CreateConsentData;
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
 * CreateConsent
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class CreateConsent {

  private CreateConsentData data;

  public CreateConsent() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public CreateConsent(CreateConsentData data) {
    this.data = data;
  }

  public CreateConsent data(CreateConsentData data) {
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
  public CreateConsentData getData() {
    return data;
  }

  public void setData(CreateConsentData data) {
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
    CreateConsent createConsent = (CreateConsent) o;
    return Objects.equals(this.data, createConsent.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateConsent {\n");
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

    private CreateConsent instance;

    public Builder() {
      this(new CreateConsent());
    }

    protected Builder(CreateConsent instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CreateConsent value) { 
      this.instance.setData(value.data);
      return this;
    }

    public CreateConsent.Builder data(CreateConsentData data) {
      this.instance.data(data);
      return this;
    }
    
    /**
    * returns a built CreateConsent instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CreateConsent build() {
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
  public static CreateConsent.Builder builder() {
    return new CreateConsent.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CreateConsent.Builder toBuilder() {
    CreateConsent.Builder builder = new CreateConsent.Builder();
    return builder.copyOf(this);
  }

}

