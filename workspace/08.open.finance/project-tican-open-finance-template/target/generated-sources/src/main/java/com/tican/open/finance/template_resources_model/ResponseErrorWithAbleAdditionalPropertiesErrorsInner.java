package com.tican.open.finance.template_resources_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
 * ResponseErrorWithAbleAdditionalPropertiesErrorsInner
 */

@JsonTypeName("ResponseErrorWithAbleAdditionalProperties_errors_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:04.924183300-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseErrorWithAbleAdditionalPropertiesErrorsInner {

  private String code;

  private String title;

  private String detail;

  public ResponseErrorWithAbleAdditionalPropertiesErrorsInner() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public ResponseErrorWithAbleAdditionalPropertiesErrorsInner(String code, String title, String detail) {
    this.code = code;
    this.title = title;
    this.detail = detail;
  }

  public ResponseErrorWithAbleAdditionalPropertiesErrorsInner code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Código de erro específico do endpoint
   * @return code
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 255) 
  @Schema(name = "code", description = "Código de erro específico do endpoint", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("code")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ResponseErrorWithAbleAdditionalPropertiesErrorsInner title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Título legível por humanos deste erro específico
   * @return title
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 255) 
  @Schema(name = "title", description = "Título legível por humanos deste erro específico", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ResponseErrorWithAbleAdditionalPropertiesErrorsInner detail(String detail) {
    this.detail = detail;
    return this;
  }

  /**
   * Descrição legível por humanos deste erro específico
   * @return detail
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 2048) 
  @Schema(name = "detail", description = "Descrição legível por humanos deste erro específico", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("detail")
  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseErrorWithAbleAdditionalPropertiesErrorsInner responseErrorWithAbleAdditionalPropertiesErrorsInner = (ResponseErrorWithAbleAdditionalPropertiesErrorsInner) o;
    return Objects.equals(this.code, responseErrorWithAbleAdditionalPropertiesErrorsInner.code) &&
        Objects.equals(this.title, responseErrorWithAbleAdditionalPropertiesErrorsInner.title) &&
        Objects.equals(this.detail, responseErrorWithAbleAdditionalPropertiesErrorsInner.detail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, title, detail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseErrorWithAbleAdditionalPropertiesErrorsInner {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
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

    private ResponseErrorWithAbleAdditionalPropertiesErrorsInner instance;

    public Builder() {
      this(new ResponseErrorWithAbleAdditionalPropertiesErrorsInner());
    }

    protected Builder(ResponseErrorWithAbleAdditionalPropertiesErrorsInner instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseErrorWithAbleAdditionalPropertiesErrorsInner value) { 
      this.instance.setCode(value.code);
      this.instance.setTitle(value.title);
      this.instance.setDetail(value.detail);
      return this;
    }

    public ResponseErrorWithAbleAdditionalPropertiesErrorsInner.Builder code(String code) {
      this.instance.code(code);
      return this;
    }
    
    public ResponseErrorWithAbleAdditionalPropertiesErrorsInner.Builder title(String title) {
      this.instance.title(title);
      return this;
    }
    
    public ResponseErrorWithAbleAdditionalPropertiesErrorsInner.Builder detail(String detail) {
      this.instance.detail(detail);
      return this;
    }
    
    /**
    * returns a built ResponseErrorWithAbleAdditionalPropertiesErrorsInner instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseErrorWithAbleAdditionalPropertiesErrorsInner build() {
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
  public static ResponseErrorWithAbleAdditionalPropertiesErrorsInner.Builder builder() {
    return new ResponseErrorWithAbleAdditionalPropertiesErrorsInner.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseErrorWithAbleAdditionalPropertiesErrorsInner.Builder toBuilder() {
    ResponseErrorWithAbleAdditionalPropertiesErrorsInner.Builder builder = new ResponseErrorWithAbleAdditionalPropertiesErrorsInner.Builder();
    return builder.copyOf(this);
  }

}

