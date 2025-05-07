package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_consents_model.LinksConsents;
import com.tican.open.finance.template_consents_model.Meta;
import com.tican.open.finance.template_consents_model.ResponseConsentReadData;
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
 * ResponseConsentRead
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseConsentRead {

  private ResponseConsentReadData data;

  private @Nullable LinksConsents links;

  private @Nullable Meta meta;

  public ResponseConsentRead() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ResponseConsentRead(ResponseConsentReadData data) {
    this.data = data;
  }

  /**
   * Constructor with all args parameters
   */
  public ResponseConsentRead(ResponseConsentReadData data, @Nullable LinksConsents links, @Nullable Meta meta) {
      this.data = data;
      this.links = links;
      this.meta = meta;
  }

  public ResponseConsentRead data(ResponseConsentReadData data) {
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
  public ResponseConsentReadData getData() {
    return data;
  }

  public void setData(ResponseConsentReadData data) {
    this.data = data;
  }

  public ResponseConsentRead links(LinksConsents links) {
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

  public ResponseConsentRead meta(Meta meta) {
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
    ResponseConsentRead responseConsentRead = (ResponseConsentRead) o;
    return Objects.equals(this.data, responseConsentRead.data) &&
        Objects.equals(this.links, responseConsentRead.links) &&
        Objects.equals(this.meta, responseConsentRead.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, links, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseConsentRead {\n");
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

    private ResponseConsentRead instance;

    public Builder() {
      this(new ResponseConsentRead());
    }

    protected Builder(ResponseConsentRead instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseConsentRead value) { 
      this.instance.setData(value.data);
      this.instance.setLinks(value.links);
      this.instance.setMeta(value.meta);
      return this;
    }

    public ResponseConsentRead.Builder data(ResponseConsentReadData data) {
      this.instance.data(data);
      return this;
    }
    
    public ResponseConsentRead.Builder links(LinksConsents links) {
      this.instance.links(links);
      return this;
    }
    
    public ResponseConsentRead.Builder meta(Meta meta) {
      this.instance.meta(meta);
      return this;
    }
    
    /**
    * returns a built ResponseConsentRead instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseConsentRead build() {
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
  public static ResponseConsentRead.Builder builder() {
    return new ResponseConsentRead.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseConsentRead.Builder toBuilder() {
    ResponseConsentRead.Builder builder = new ResponseConsentRead.Builder();
    return builder.copyOf(this);
  }

}

