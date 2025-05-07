package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_consents_model.MetaError;
import com.tican.open.finance.template_consents_model.ResponseErrorUnprocessableEntityErrorsInner;
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
 * ResponseErrorUnprocessableEntity
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseErrorUnprocessableEntity {

  @Valid
  private List<@Valid ResponseErrorUnprocessableEntityErrorsInner> errors = new ArrayList<>();

  private @Nullable MetaError meta;

  public ResponseErrorUnprocessableEntity() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ResponseErrorUnprocessableEntity(List<@Valid ResponseErrorUnprocessableEntityErrorsInner> errors) {
    this.errors = errors;
  }

  /**
   * Constructor with all args parameters
   */
  public ResponseErrorUnprocessableEntity(List<@Valid ResponseErrorUnprocessableEntityErrorsInner> errors, @Nullable MetaError meta) {
      this.errors = errors;
      this.meta = meta;
  }

  public ResponseErrorUnprocessableEntity errors(List<@Valid ResponseErrorUnprocessableEntityErrorsInner> errors) {
    this.errors = errors;
    return this;
  }

  public ResponseErrorUnprocessableEntity addErrorsItem(ResponseErrorUnprocessableEntityErrorsInner errorsItem) {
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
  public List<@Valid ResponseErrorUnprocessableEntityErrorsInner> getErrors() {
    return errors;
  }

  public void setErrors(List<@Valid ResponseErrorUnprocessableEntityErrorsInner> errors) {
    this.errors = errors;
  }

  public ResponseErrorUnprocessableEntity meta(MetaError meta) {
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
  public MetaError getMeta() {
    return meta;
  }

  public void setMeta(MetaError meta) {
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
    ResponseErrorUnprocessableEntity responseErrorUnprocessableEntity = (ResponseErrorUnprocessableEntity) o;
    return Objects.equals(this.errors, responseErrorUnprocessableEntity.errors) &&
        Objects.equals(this.meta, responseErrorUnprocessableEntity.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errors, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseErrorUnprocessableEntity {\n");
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

    private ResponseErrorUnprocessableEntity instance;

    public Builder() {
      this(new ResponseErrorUnprocessableEntity());
    }

    protected Builder(ResponseErrorUnprocessableEntity instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseErrorUnprocessableEntity value) { 
      this.instance.setErrors(value.errors);
      this.instance.setMeta(value.meta);
      return this;
    }

    public ResponseErrorUnprocessableEntity.Builder errors(List<ResponseErrorUnprocessableEntityErrorsInner> errors) {
      this.instance.errors(errors);
      return this;
    }
    
    public ResponseErrorUnprocessableEntity.Builder meta(MetaError meta) {
      this.instance.meta(meta);
      return this;
    }
    
    /**
    * returns a built ResponseErrorUnprocessableEntity instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseErrorUnprocessableEntity build() {
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
  public static ResponseErrorUnprocessableEntity.Builder builder() {
    return new ResponseErrorUnprocessableEntity.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseErrorUnprocessableEntity.Builder toBuilder() {
    ResponseErrorUnprocessableEntity.Builder builder = new ResponseErrorUnprocessableEntity.Builder();
    return builder.copyOf(this);
  }

}

