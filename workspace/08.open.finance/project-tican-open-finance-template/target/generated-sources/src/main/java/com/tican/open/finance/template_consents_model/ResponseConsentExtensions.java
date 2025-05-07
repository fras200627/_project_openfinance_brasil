package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_consents_model.LinksConsents;
import com.tican.open.finance.template_consents_model.Meta;
import com.tican.open.finance.template_consents_model.ResponseConsentExtensionsData;
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
 * ResponseConsentExtensions
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseConsentExtensions {

  private ResponseConsentExtensionsData data;

  private @Nullable LinksConsents links;

  private @Nullable Meta meta;

  public ResponseConsentExtensions() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ResponseConsentExtensions(ResponseConsentExtensionsData data) {
    this.data = data;
  }

  /**
   * Constructor with all args parameters
   */
  public ResponseConsentExtensions(ResponseConsentExtensionsData data, @Nullable LinksConsents links, @Nullable Meta meta) {
      this.data = data;
      this.links = links;
      this.meta = meta;
  }

  public ResponseConsentExtensions data(ResponseConsentExtensionsData data) {
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
  public ResponseConsentExtensionsData getData() {
    return data;
  }

  public void setData(ResponseConsentExtensionsData data) {
    this.data = data;
  }

  public ResponseConsentExtensions links(LinksConsents links) {
    this.links = links;
    return this;
  }

  /**
   * Get links
   * @return links
   */
  @Valid 
  @Schema(name = "links", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("links")
  public LinksConsents getLinks() {
    return links;
  }

  public void setLinks(LinksConsents links) {
    this.links = links;
  }

  public ResponseConsentExtensions meta(Meta meta) {
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
    ResponseConsentExtensions responseConsentExtensions = (ResponseConsentExtensions) o;
    return Objects.equals(this.data, responseConsentExtensions.data) &&
        Objects.equals(this.links, responseConsentExtensions.links) &&
        Objects.equals(this.meta, responseConsentExtensions.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, links, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseConsentExtensions {\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
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

    private ResponseConsentExtensions instance;

    public Builder() {
      this(new ResponseConsentExtensions());
    }

    protected Builder(ResponseConsentExtensions instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseConsentExtensions value) { 
      this.instance.setData(value.data);
      this.instance.setLinks(value.links);
      this.instance.setMeta(value.meta);
      return this;
    }

    public ResponseConsentExtensions.Builder data(ResponseConsentExtensionsData data) {
      this.instance.data(data);
      return this;
    }
    
    public ResponseConsentExtensions.Builder links(LinksConsents links) {
      this.instance.links(links);
      return this;
    }
    
    public ResponseConsentExtensions.Builder meta(Meta meta) {
      this.instance.meta(meta);
      return this;
    }
    
    /**
    * returns a built ResponseConsentExtensions instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseConsentExtensions build() {
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
  public static ResponseConsentExtensions.Builder builder() {
    return new ResponseConsentExtensions.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseConsentExtensions.Builder toBuilder() {
    ResponseConsentExtensions.Builder builder = new ResponseConsentExtensions.Builder();
    return builder.copyOf(this);
  }

}

