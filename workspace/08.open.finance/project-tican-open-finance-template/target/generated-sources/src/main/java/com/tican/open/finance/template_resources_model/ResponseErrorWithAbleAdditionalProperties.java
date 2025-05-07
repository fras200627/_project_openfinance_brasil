package com.tican.open.finance.template_resources_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_resources_model.Meta;
import com.tican.open.finance.template_resources_model.ResponseErrorWithAbleAdditionalPropertiesErrorsInner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
 * ResponseErrorWithAbleAdditionalProperties
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:04.924183300-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseErrorWithAbleAdditionalProperties {

  @Valid
  private List<@Valid ResponseErrorWithAbleAdditionalPropertiesErrorsInner> errors = new ArrayList<>();

  private @Nullable Meta meta;

  public ResponseErrorWithAbleAdditionalProperties() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ResponseErrorWithAbleAdditionalProperties(List<@Valid ResponseErrorWithAbleAdditionalPropertiesErrorsInner> errors) {
    this.errors = errors;
  }

  /**
   * Constructor with all args parameters
   */
  public ResponseErrorWithAbleAdditionalProperties(List<@Valid ResponseErrorWithAbleAdditionalPropertiesErrorsInner> errors, @Nullable Meta meta) {
      this.errors = errors;
      this.meta = meta;
  }

  public ResponseErrorWithAbleAdditionalProperties errors(List<@Valid ResponseErrorWithAbleAdditionalPropertiesErrorsInner> errors) {
    this.errors = errors;
    return this;
  }

  public ResponseErrorWithAbleAdditionalProperties addErrorsItem(ResponseErrorWithAbleAdditionalPropertiesErrorsInner errorsItem) {
    if (this.errors == null) {
      this.errors = new ArrayList<>();
    }
    this.errors.add(errorsItem);
    return this;
  }

  /**
   * Get errors
   * @return errors
   */
  @NotNull @Valid @Size(min = 1, max = 13) 
  @Schema(name = "errors", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("errors")
  public List<@Valid ResponseErrorWithAbleAdditionalPropertiesErrorsInner> getErrors() {
    return errors;
  }

  public void setErrors(List<@Valid ResponseErrorWithAbleAdditionalPropertiesErrorsInner> errors) {
    this.errors = errors;
  }

  public ResponseErrorWithAbleAdditionalProperties meta(Meta meta) {
    this.meta = meta;
    return this;
  }

  /**
   * Get meta
   * @return meta
   */
  @Valid 
  @Schema(name = "meta", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("meta")
  public Meta getMeta() {
    return meta;
  }

  public void setMeta(Meta meta) {
    this.meta = meta;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseErrorWithAbleAdditionalProperties responseErrorWithAbleAdditionalProperties = (ResponseErrorWithAbleAdditionalProperties) o;
    return Objects.equals(this.errors, responseErrorWithAbleAdditionalProperties.errors) &&
        Objects.equals(this.meta, responseErrorWithAbleAdditionalProperties.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errors, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseErrorWithAbleAdditionalProperties {\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
    sb.append("    meta: ").append(toIndentedString(meta)).append("\n");
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

    private ResponseErrorWithAbleAdditionalProperties instance;

    public Builder() {
      this(new ResponseErrorWithAbleAdditionalProperties());
    }

    protected Builder(ResponseErrorWithAbleAdditionalProperties instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseErrorWithAbleAdditionalProperties value) { 
      this.instance.setErrors(value.errors);
      this.instance.setMeta(value.meta);
      return this;
    }

    public ResponseErrorWithAbleAdditionalProperties.Builder errors(List<ResponseErrorWithAbleAdditionalPropertiesErrorsInner> errors) {
      this.instance.errors(errors);
      return this;
    }
    
    public ResponseErrorWithAbleAdditionalProperties.Builder meta(Meta meta) {
      this.instance.meta(meta);
      return this;
    }
    
    /**
    * returns a built ResponseErrorWithAbleAdditionalProperties instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseErrorWithAbleAdditionalProperties build() {
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
  public static ResponseErrorWithAbleAdditionalProperties.Builder builder() {
    return new ResponseErrorWithAbleAdditionalProperties.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseErrorWithAbleAdditionalProperties.Builder toBuilder() {
    ResponseErrorWithAbleAdditionalProperties.Builder builder = new ResponseErrorWithAbleAdditionalProperties.Builder();
    return builder.copyOf(this);
  }

}

