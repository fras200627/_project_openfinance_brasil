package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_consents_model.Links;
import com.tican.open.finance.template_consents_model.MetaExtensions;
import com.tican.open.finance.template_consents_model.ResponseConsentReadExtensionsDataInner;
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
 * ResponseConsentReadExtensions
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseConsentReadExtensions {

  @Valid
  private List<@Valid ResponseConsentReadExtensionsDataInner> data = new ArrayList<>();

  private @Nullable Links links;

  private @Nullable MetaExtensions meta;

  public ResponseConsentReadExtensions() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ResponseConsentReadExtensions(List<@Valid ResponseConsentReadExtensionsDataInner> data) {
    this.data = data;
  }

  /**
   * Constructor with all args parameters
   */
  public ResponseConsentReadExtensions(List<@Valid ResponseConsentReadExtensionsDataInner> data, @Nullable Links links, @Nullable MetaExtensions meta) {
      this.data = data;
      this.links = links;
      this.meta = meta;
  }

  public ResponseConsentReadExtensions data(List<@Valid ResponseConsentReadExtensionsDataInner> data) {
    this.data = data;
    return this;
  }

  public ResponseConsentReadExtensions addDataItem(ResponseConsentReadExtensionsDataInner dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<>();
    }
    this.data.add(dataItem);
    return this;
  }

  /**
   * Get data
   * @return data
   */
  @NotNull @Valid @Size(min = 0) 
  @Schema(name = "data", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("data")
  public List<@Valid ResponseConsentReadExtensionsDataInner> getData() {
    return data;
  }

  public void setData(List<@Valid ResponseConsentReadExtensionsDataInner> data) {
    this.data = data;
  }

  public ResponseConsentReadExtensions links(Links links) {
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
  public Links getLinks() {
    return links;
  }

  public void setLinks(Links links) {
    this.links = links;
  }

  public ResponseConsentReadExtensions meta(MetaExtensions meta) {
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
  public MetaExtensions getMeta() {
    return meta;
  }

  public void setMeta(MetaExtensions meta) {
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
    ResponseConsentReadExtensions responseConsentReadExtensions = (ResponseConsentReadExtensions) o;
    return Objects.equals(this.data, responseConsentReadExtensions.data) &&
        Objects.equals(this.links, responseConsentReadExtensions.links) &&
        Objects.equals(this.meta, responseConsentReadExtensions.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, links, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseConsentReadExtensions {\n");
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

    private ResponseConsentReadExtensions instance;

    public Builder() {
      this(new ResponseConsentReadExtensions());
    }

    protected Builder(ResponseConsentReadExtensions instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseConsentReadExtensions value) { 
      this.instance.setData(value.data);
      this.instance.setLinks(value.links);
      this.instance.setMeta(value.meta);
      return this;
    }

    public ResponseConsentReadExtensions.Builder data(List<ResponseConsentReadExtensionsDataInner> data) {
      this.instance.data(data);
      return this;
    }
    
    public ResponseConsentReadExtensions.Builder links(Links links) {
      this.instance.links(links);
      return this;
    }
    
    public ResponseConsentReadExtensions.Builder meta(MetaExtensions meta) {
      this.instance.meta(meta);
      return this;
    }
    
    /**
    * returns a built ResponseConsentReadExtensions instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseConsentReadExtensions build() {
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
  public static ResponseConsentReadExtensions.Builder builder() {
    return new ResponseConsentReadExtensions.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseConsentReadExtensions.Builder toBuilder() {
    ResponseConsentReadExtensions.Builder builder = new ResponseConsentReadExtensions.Builder();
    return builder.copyOf(this);
  }

}

