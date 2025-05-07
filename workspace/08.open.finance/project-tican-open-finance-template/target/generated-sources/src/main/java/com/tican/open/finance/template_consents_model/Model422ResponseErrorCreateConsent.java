package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.tican.open.finance.template_consents_model.Meta;
import com.tican.open.finance.template_consents_model.Model422ResponseErrorCreateConsentErrorsInner;
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
 * Model422ResponseErrorCreateConsent
 */

@JsonTypeName("422ResponseErrorCreateConsent")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class Model422ResponseErrorCreateConsent {

  @Valid
  private List<@Valid Model422ResponseErrorCreateConsentErrorsInner> errors = new ArrayList<>();

  private @Nullable Meta meta;

  public Model422ResponseErrorCreateConsent() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Model422ResponseErrorCreateConsent(List<@Valid Model422ResponseErrorCreateConsentErrorsInner> errors) {
    this.errors = errors;
  }

  /**
   * Constructor with all args parameters
   */
  public Model422ResponseErrorCreateConsent(List<@Valid Model422ResponseErrorCreateConsentErrorsInner> errors, @Nullable Meta meta) {
      this.errors = errors;
      this.meta = meta;
  }

  public Model422ResponseErrorCreateConsent errors(List<@Valid Model422ResponseErrorCreateConsentErrorsInner> errors) {
    this.errors = errors;
    return this;
  }

  public Model422ResponseErrorCreateConsent addErrorsItem(Model422ResponseErrorCreateConsentErrorsInner errorsItem) {
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
  @NotNull @Valid @Size(min = 1, max = 3) 
  @Schema(name = "errors", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("errors")
  public List<@Valid Model422ResponseErrorCreateConsentErrorsInner> getErrors() {
    return errors;
  }

  public void setErrors(List<@Valid Model422ResponseErrorCreateConsentErrorsInner> errors) {
    this.errors = errors;
  }

  public Model422ResponseErrorCreateConsent meta(Meta meta) {
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
    Model422ResponseErrorCreateConsent _422responseErrorCreateConsent = (Model422ResponseErrorCreateConsent) o;
    return Objects.equals(this.errors, _422responseErrorCreateConsent.errors) &&
        Objects.equals(this.meta, _422responseErrorCreateConsent.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errors, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Model422ResponseErrorCreateConsent {\n");
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

    private Model422ResponseErrorCreateConsent instance;

    public Builder() {
      this(new Model422ResponseErrorCreateConsent());
    }

    protected Builder(Model422ResponseErrorCreateConsent instance) {
      this.instance = instance;
    }

    protected Builder copyOf(Model422ResponseErrorCreateConsent value) { 
      this.instance.setErrors(value.errors);
      this.instance.setMeta(value.meta);
      return this;
    }

    public Model422ResponseErrorCreateConsent.Builder errors(List<Model422ResponseErrorCreateConsentErrorsInner> errors) {
      this.instance.errors(errors);
      return this;
    }
    
    public Model422ResponseErrorCreateConsent.Builder meta(Meta meta) {
      this.instance.meta(meta);
      return this;
    }
    
    /**
    * returns a built Model422ResponseErrorCreateConsent instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public Model422ResponseErrorCreateConsent build() {
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
  public static Model422ResponseErrorCreateConsent.Builder builder() {
    return new Model422ResponseErrorCreateConsent.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public Model422ResponseErrorCreateConsent.Builder toBuilder() {
    Model422ResponseErrorCreateConsent.Builder builder = new Model422ResponseErrorCreateConsent.Builder();
    return builder.copyOf(this);
  }

}

