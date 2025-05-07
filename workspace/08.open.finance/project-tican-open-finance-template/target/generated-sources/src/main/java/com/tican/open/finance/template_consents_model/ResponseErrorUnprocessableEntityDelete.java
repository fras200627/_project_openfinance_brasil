package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_consents_model.MetaError;
import com.tican.open.finance.template_consents_model.ResponseErrorUnprocessableEntityDeleteErrorsInner;
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
 * ResponseErrorUnprocessableEntityDelete
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseErrorUnprocessableEntityDelete {

  @Valid
  private List<@Valid ResponseErrorUnprocessableEntityDeleteErrorsInner> errors = new ArrayList<>();

  private @Nullable MetaError meta;

  public ResponseErrorUnprocessableEntityDelete() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ResponseErrorUnprocessableEntityDelete(List<@Valid ResponseErrorUnprocessableEntityDeleteErrorsInner> errors) {
    this.errors = errors;
  }

  /**
   * Constructor with all args parameters
   */
  public ResponseErrorUnprocessableEntityDelete(List<@Valid ResponseErrorUnprocessableEntityDeleteErrorsInner> errors, @Nullable MetaError meta) {
      this.errors = errors;
      this.meta = meta;
  }

  public ResponseErrorUnprocessableEntityDelete errors(List<@Valid ResponseErrorUnprocessableEntityDeleteErrorsInner> errors) {
    this.errors = errors;
    return this;
  }

  public ResponseErrorUnprocessableEntityDelete addErrorsItem(ResponseErrorUnprocessableEntityDeleteErrorsInner errorsItem) {
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
  public List<@Valid ResponseErrorUnprocessableEntityDeleteErrorsInner> getErrors() {
    return errors;
  }

  public void setErrors(List<@Valid ResponseErrorUnprocessableEntityDeleteErrorsInner> errors) {
    this.errors = errors;
  }

  public ResponseErrorUnprocessableEntityDelete meta(MetaError meta) {
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
    ResponseErrorUnprocessableEntityDelete responseErrorUnprocessableEntityDelete = (ResponseErrorUnprocessableEntityDelete) o;
    return Objects.equals(this.errors, responseErrorUnprocessableEntityDelete.errors) &&
        Objects.equals(this.meta, responseErrorUnprocessableEntityDelete.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errors, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseErrorUnprocessableEntityDelete {\n");
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

    private ResponseErrorUnprocessableEntityDelete instance;

    public Builder() {
      this(new ResponseErrorUnprocessableEntityDelete());
    }

    protected Builder(ResponseErrorUnprocessableEntityDelete instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseErrorUnprocessableEntityDelete value) { 
      this.instance.setErrors(value.errors);
      this.instance.setMeta(value.meta);
      return this;
    }

    public ResponseErrorUnprocessableEntityDelete.Builder errors(List<ResponseErrorUnprocessableEntityDeleteErrorsInner> errors) {
      this.instance.errors(errors);
      return this;
    }
    
    public ResponseErrorUnprocessableEntityDelete.Builder meta(MetaError meta) {
      this.instance.meta(meta);
      return this;
    }
    
    /**
    * returns a built ResponseErrorUnprocessableEntityDelete instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseErrorUnprocessableEntityDelete build() {
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
  public static ResponseErrorUnprocessableEntityDelete.Builder builder() {
    return new ResponseErrorUnprocessableEntityDelete.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseErrorUnprocessableEntityDelete.Builder toBuilder() {
    ResponseErrorUnprocessableEntityDelete.Builder builder = new ResponseErrorUnprocessableEntityDelete.Builder();
    return builder.copyOf(this);
  }

}

