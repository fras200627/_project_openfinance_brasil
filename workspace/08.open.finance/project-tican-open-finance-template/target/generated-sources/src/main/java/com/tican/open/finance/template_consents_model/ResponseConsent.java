package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_consents_model.LinksConsents;
import com.tican.open.finance.template_consents_model.Meta;
import com.tican.open.finance.template_consents_model.ResponseConsentData;
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
 * ResponseConsent
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseConsent {

  private ResponseConsentData data;

  private @Nullable LinksConsents links;

  private @Nullable Meta meta;

  public ResponseConsent() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ResponseConsent(ResponseConsentData data) {
    this.data = data;
  }

  /**
   * Constructor with all args parameters
   */
  public ResponseConsent(ResponseConsentData data, @Nullable LinksConsents links, @Nullable Meta meta) {
      this.data = data;
      this.links = links;
      this.meta = meta;
  }

  public ResponseConsent data(ResponseConsentData data) {
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
  public ResponseConsentData getData() {
    return data;
  }

  public void setData(ResponseConsentData data) {
    this.data = data;
  }

  public ResponseConsent links(LinksConsents links) {
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

  public ResponseConsent meta(Meta meta) {
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
    ResponseConsent responseConsent = (ResponseConsent) o;
    return Objects.equals(this.data, responseConsent.data) &&
        Objects.equals(this.links, responseConsent.links) &&
        Objects.equals(this.meta, responseConsent.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, links, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseConsent {\n");
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

    private ResponseConsent instance;

    public Builder() {
      this(new ResponseConsent());
    }

    protected Builder(ResponseConsent instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseConsent value) { 
      this.instance.setData(value.data);
      this.instance.setLinks(value.links);
      this.instance.setMeta(value.meta);
      return this;
    }

    public ResponseConsent.Builder data(ResponseConsentData data) {
      this.instance.data(data);
      return this;
    }
    
    public ResponseConsent.Builder links(LinksConsents links) {
      this.instance.links(links);
      return this;
    }
    
    public ResponseConsent.Builder meta(Meta meta) {
      this.instance.meta(meta);
      return this;
    }
    
    /**
    * returns a built ResponseConsent instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseConsent build() {
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
  public static ResponseConsent.Builder builder() {
    return new ResponseConsent.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseConsent.Builder toBuilder() {
    ResponseConsent.Builder builder = new ResponseConsent.Builder();
    return builder.copyOf(this);
  }

}

