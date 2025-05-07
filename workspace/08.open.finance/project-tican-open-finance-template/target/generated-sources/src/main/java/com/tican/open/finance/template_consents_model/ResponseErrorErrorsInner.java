package com.tican.open.finance.template_consents_model;

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
 * ResponseErrorErrorsInner
 */

@JsonTypeName("ResponseError_errors_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseErrorErrorsInner {

  private String code;

  private String title;

  private String detail;

  public ResponseErrorErrorsInner() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public ResponseErrorErrorsInner(String code, String title, String detail) {
    this.code = code;
    this.title = title;
    this.detail = detail;
  }

  public ResponseErrorErrorsInner code(String code) {
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

  public ResponseErrorErrorsInner title(String title) {
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

  public ResponseErrorErrorsInner detail(String detail) {
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
    ResponseErrorErrorsInner responseErrorErrorsInner = (ResponseErrorErrorsInner) o;
    return Objects.equals(this.code, responseErrorErrorsInner.code) &&
        Objects.equals(this.title, responseErrorErrorsInner.title) &&
        Objects.equals(this.detail, responseErrorErrorsInner.detail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, title, detail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseErrorErrorsInner {\n");
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

    private ResponseErrorErrorsInner instance;

    public Builder() {
      this(new ResponseErrorErrorsInner());
    }

    protected Builder(ResponseErrorErrorsInner instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseErrorErrorsInner value) { 
      this.instance.setCode(value.code);
      this.instance.setTitle(value.title);
      this.instance.setDetail(value.detail);
      return this;
    }

    public ResponseErrorErrorsInner.Builder code(String code) {
      this.instance.code(code);
      return this;
    }
    
    public ResponseErrorErrorsInner.Builder title(String title) {
      this.instance.title(title);
      return this;
    }
    
    public ResponseErrorErrorsInner.Builder detail(String detail) {
      this.instance.detail(detail);
      return this;
    }
    
    /**
    * returns a built ResponseErrorErrorsInner instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseErrorErrorsInner build() {
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
  public static ResponseErrorErrorsInner.Builder builder() {
    return new ResponseErrorErrorsInner.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseErrorErrorsInner.Builder toBuilder() {
    ResponseErrorErrorsInner.Builder builder = new ResponseErrorErrorsInner.Builder();
    return builder.copyOf(this);
  }

}

