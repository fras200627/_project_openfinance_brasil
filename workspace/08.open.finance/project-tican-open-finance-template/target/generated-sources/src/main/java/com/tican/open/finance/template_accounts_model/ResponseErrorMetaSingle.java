package com.tican.open.finance.template_accounts_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_accounts_model.MetaOnlyRequestDateTime;
import com.tican.open.finance.template_accounts_model.ResponseErrorMetaSingleErrorsInner;
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
 * ResponseErrorMetaSingle
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:09.506997400-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseErrorMetaSingle {

  @Valid
  private List<@Valid ResponseErrorMetaSingleErrorsInner> errors = new ArrayList<>();

  private @Nullable MetaOnlyRequestDateTime meta;

  public ResponseErrorMetaSingle() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ResponseErrorMetaSingle(List<@Valid ResponseErrorMetaSingleErrorsInner> errors) {
    this.errors = errors;
  }

  /**
   * Constructor with all args parameters
   */
  public ResponseErrorMetaSingle(List<@Valid ResponseErrorMetaSingleErrorsInner> errors, @Nullable MetaOnlyRequestDateTime meta) {
      this.errors = errors;
      this.meta = meta;
  }

  public ResponseErrorMetaSingle errors(List<@Valid ResponseErrorMetaSingleErrorsInner> errors) {
    this.errors = errors;
    return this;
  }

  public ResponseErrorMetaSingle addErrorsItem(ResponseErrorMetaSingleErrorsInner errorsItem) {
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
  public List<@Valid ResponseErrorMetaSingleErrorsInner> getErrors() {
    return errors;
  }

  public void setErrors(List<@Valid ResponseErrorMetaSingleErrorsInner> errors) {
    this.errors = errors;
  }

  public ResponseErrorMetaSingle meta(MetaOnlyRequestDateTime meta) {
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
  public MetaOnlyRequestDateTime getMeta() {
    return meta;
  }

  public void setMeta(MetaOnlyRequestDateTime meta) {
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
    ResponseErrorMetaSingle responseErrorMetaSingle = (ResponseErrorMetaSingle) o;
    return Objects.equals(this.errors, responseErrorMetaSingle.errors) &&
        Objects.equals(this.meta, responseErrorMetaSingle.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errors, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseErrorMetaSingle {\n");
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

    private ResponseErrorMetaSingle instance;

    public Builder() {
      this(new ResponseErrorMetaSingle());
    }

    protected Builder(ResponseErrorMetaSingle instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseErrorMetaSingle value) { 
      this.instance.setErrors(value.errors);
      this.instance.setMeta(value.meta);
      return this;
    }

    public ResponseErrorMetaSingle.Builder errors(List<ResponseErrorMetaSingleErrorsInner> errors) {
      this.instance.errors(errors);
      return this;
    }
    
    public ResponseErrorMetaSingle.Builder meta(MetaOnlyRequestDateTime meta) {
      this.instance.meta(meta);
      return this;
    }
    
    /**
    * returns a built ResponseErrorMetaSingle instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseErrorMetaSingle build() {
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
  public static ResponseErrorMetaSingle.Builder builder() {
    return new ResponseErrorMetaSingle.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseErrorMetaSingle.Builder toBuilder() {
    ResponseErrorMetaSingle.Builder builder = new ResponseErrorMetaSingle.Builder();
    return builder.copyOf(this);
  }

}

