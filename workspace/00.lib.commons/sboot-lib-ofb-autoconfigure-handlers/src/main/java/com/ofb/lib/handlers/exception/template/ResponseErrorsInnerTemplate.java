package com.ofb.lib.handlers.exception.template;

import com.fasterxml.jackson.annotation.*;
import java.util.Objects;

public class ResponseErrorsInnerTemplate {

  public static final String JSON_PROPERTY_CODE = "code";
  @javax.annotation.Nonnull
  private String code;

  public static final String JSON_PROPERTY_TITLE = "title";
  @javax.annotation.Nonnull
  private String title;

  public static final String JSON_PROPERTY_DETAIL = "detail";
  @javax.annotation.Nonnull
  private String detail;

  public ResponseErrorsInnerTemplate() {
  }

  public ResponseErrorsInnerTemplate(@JsonProperty(JSON_PROPERTY_CODE) String code,
                                     @JsonProperty(JSON_PROPERTY_TITLE) String title,
                                     @JsonProperty(JSON_PROPERTY_DETAIL) String detail) {
    this.code = code;
    this.title = title;
    this.detail = detail;
  }

  public ResponseErrorsInnerTemplate code(@javax.annotation.Nonnull String code) {
    this.code = code;
    return this;
  }

  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_CODE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getCode() {
    return code;
  }

  @JsonProperty(JSON_PROPERTY_CODE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCode(@javax.annotation.Nonnull String code) {
    this.code = code;
  }
  public ResponseErrorsInnerTemplate title(@javax.annotation.Nonnull String title) {
    this.title = title;
    return this;
  }

  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_TITLE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getTitle() {
    return title;
  }

  @JsonProperty(JSON_PROPERTY_TITLE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTitle(@javax.annotation.Nonnull String title) {
    this.title = title;
  }

  public ResponseErrorsInnerTemplate detail(@javax.annotation.Nonnull String detail) {
    this.detail = detail;
    return this;
  }

  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_DETAIL)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getDetail() {
    return detail;
  }

  @JsonProperty(JSON_PROPERTY_DETAIL)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setDetail(@javax.annotation.Nonnull String detail) {
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
    ResponseErrorsInnerTemplate _422responseErrorCreateConsentErrorsInner = (ResponseErrorsInnerTemplate) o;
    return Objects.equals(this.code, _422responseErrorCreateConsentErrorsInner.code) &&
        Objects.equals(this.title, _422responseErrorCreateConsentErrorsInner.title) &&
        Objects.equals(this.detail, _422responseErrorCreateConsentErrorsInner.detail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, title, detail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Model422ResponseErrorCreateConsentErrorsInner {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  public static class Builder {
    private ResponseErrorsInnerTemplate instance;

    public Builder() {
      this(new ResponseErrorsInnerTemplate());
    }

    protected Builder(ResponseErrorsInnerTemplate instance) {
      this.instance = instance;
    }

    public ResponseErrorsInnerTemplate.Builder code(String code) {
      this.instance.code = code;
      return this;
    }
    public ResponseErrorsInnerTemplate.Builder title(String title) {
      this.instance.title = title;
      return this;
    }
    public ResponseErrorsInnerTemplate.Builder detail(String detail) {
      this.instance.detail = detail;
      return this;
    }

    public ResponseErrorsInnerTemplate build() {
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

  public static ResponseErrorsInnerTemplate.Builder builder() {
    return new ResponseErrorsInnerTemplate.Builder();
  }

  public ResponseErrorsInnerTemplate.Builder toBuilder() {
    return new ResponseErrorsInnerTemplate.Builder()
      .code(getCode())
      .title(getTitle())
      .detail(getDetail());
  }

}

