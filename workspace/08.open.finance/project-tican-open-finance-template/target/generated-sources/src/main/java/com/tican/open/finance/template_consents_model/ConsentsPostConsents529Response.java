package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.tican.open.finance.template_consents_model.ConsentsPostConsents529ResponseErrorsInner;
import com.tican.open.finance.template_consents_model.ConsentsPostConsents529ResponseMeta;
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
 * ConsentsPostConsents529Response
 */

@JsonTypeName("consentsPostConsents_529_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ConsentsPostConsents529Response {

  @Valid
  private List<@Valid ConsentsPostConsents529ResponseErrorsInner> errors = new ArrayList<>();

  private @Nullable ConsentsPostConsents529ResponseMeta meta;

  public ConsentsPostConsents529Response() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ConsentsPostConsents529Response(List<@Valid ConsentsPostConsents529ResponseErrorsInner> errors) {
    this.errors = errors;
  }

  /**
   * Constructor with all args parameters
   */
  public ConsentsPostConsents529Response(List<@Valid ConsentsPostConsents529ResponseErrorsInner> errors, @Nullable ConsentsPostConsents529ResponseMeta meta) {
      this.errors = errors;
      this.meta = meta;
  }

  public ConsentsPostConsents529Response errors(List<@Valid ConsentsPostConsents529ResponseErrorsInner> errors) {
    this.errors = errors;
    return this;
  }

  public ConsentsPostConsents529Response addErrorsItem(ConsentsPostConsents529ResponseErrorsInner errorsItem) {
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
  public List<@Valid ConsentsPostConsents529ResponseErrorsInner> getErrors() {
    return errors;
  }

  public void setErrors(List<@Valid ConsentsPostConsents529ResponseErrorsInner> errors) {
    this.errors = errors;
  }

  public ConsentsPostConsents529Response meta(ConsentsPostConsents529ResponseMeta meta) {
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
  public ConsentsPostConsents529ResponseMeta getMeta() {
    return meta;
  }

  public void setMeta(ConsentsPostConsents529ResponseMeta meta) {
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
    ConsentsPostConsents529Response consentsPostConsents529Response = (ConsentsPostConsents529Response) o;
    return Objects.equals(this.errors, consentsPostConsents529Response.errors) &&
        Objects.equals(this.meta, consentsPostConsents529Response.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errors, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConsentsPostConsents529Response {\n");
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

    private ConsentsPostConsents529Response instance;

    public Builder() {
      this(new ConsentsPostConsents529Response());
    }

    protected Builder(ConsentsPostConsents529Response instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ConsentsPostConsents529Response value) { 
      this.instance.setErrors(value.errors);
      this.instance.setMeta(value.meta);
      return this;
    }

    public ConsentsPostConsents529Response.Builder errors(List<ConsentsPostConsents529ResponseErrorsInner> errors) {
      this.instance.errors(errors);
      return this;
    }
    
    public ConsentsPostConsents529Response.Builder meta(ConsentsPostConsents529ResponseMeta meta) {
      this.instance.meta(meta);
      return this;
    }
    
    /**
    * returns a built ConsentsPostConsents529Response instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ConsentsPostConsents529Response build() {
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
  public static ConsentsPostConsents529Response.Builder builder() {
    return new ConsentsPostConsents529Response.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ConsentsPostConsents529Response.Builder toBuilder() {
    ConsentsPostConsents529Response.Builder builder = new ConsentsPostConsents529Response.Builder();
    return builder.copyOf(this);
  }

}

