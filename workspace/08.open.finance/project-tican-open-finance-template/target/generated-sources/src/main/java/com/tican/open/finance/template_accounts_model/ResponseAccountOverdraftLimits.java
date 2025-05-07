package com.tican.open.finance.template_accounts_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_accounts_model.AccountOverdraftLimitsData;
import com.tican.open.finance.template_accounts_model.Links;
import com.tican.open.finance.template_accounts_model.Meta;
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
 * ResponseAccountOverdraftLimits
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:09.506997400-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseAccountOverdraftLimits {

  private AccountOverdraftLimitsData data;

  private Links links;

  private Meta meta;

  public ResponseAccountOverdraftLimits() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public ResponseAccountOverdraftLimits(AccountOverdraftLimitsData data, Links links, Meta meta) {
    this.data = data;
    this.links = links;
    this.meta = meta;
  }

  public ResponseAccountOverdraftLimits data(AccountOverdraftLimitsData data) {
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
  public AccountOverdraftLimitsData getData() {
    return data;
  }

  public void setData(AccountOverdraftLimitsData data) {
    this.data = data;
  }

  public ResponseAccountOverdraftLimits links(Links links) {
    this.links = links;
    return this;
  }

  /**
   * Get links
   * @return links
   */
  @NotNull @Valid 
  @Schema(name = "links", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("links")
  public Links getLinks() {
    return links;
  }

  public void setLinks(Links links) {
    this.links = links;
  }

  public ResponseAccountOverdraftLimits meta(Meta meta) {
    this.meta = meta;
    return this;
  }

  /**
   * Get meta
   * @return meta
   */
  @NotNull @Valid 
  @Schema(name = "meta", requiredMode = Schema.RequiredMode.REQUIRED)
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
    ResponseAccountOverdraftLimits responseAccountOverdraftLimits = (ResponseAccountOverdraftLimits) o;
    return Objects.equals(this.data, responseAccountOverdraftLimits.data) &&
        Objects.equals(this.links, responseAccountOverdraftLimits.links) &&
        Objects.equals(this.meta, responseAccountOverdraftLimits.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, links, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseAccountOverdraftLimits {\n");
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

    private ResponseAccountOverdraftLimits instance;

    public Builder() {
      this(new ResponseAccountOverdraftLimits());
    }

    protected Builder(ResponseAccountOverdraftLimits instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseAccountOverdraftLimits value) { 
      this.instance.setData(value.data);
      this.instance.setLinks(value.links);
      this.instance.setMeta(value.meta);
      return this;
    }

    public ResponseAccountOverdraftLimits.Builder data(AccountOverdraftLimitsData data) {
      this.instance.data(data);
      return this;
    }
    
    public ResponseAccountOverdraftLimits.Builder links(Links links) {
      this.instance.links(links);
      return this;
    }
    
    public ResponseAccountOverdraftLimits.Builder meta(Meta meta) {
      this.instance.meta(meta);
      return this;
    }
    
    /**
    * returns a built ResponseAccountOverdraftLimits instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseAccountOverdraftLimits build() {
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
  public static ResponseAccountOverdraftLimits.Builder builder() {
    return new ResponseAccountOverdraftLimits.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseAccountOverdraftLimits.Builder toBuilder() {
    ResponseAccountOverdraftLimits.Builder builder = new ResponseAccountOverdraftLimits.Builder();
    return builder.copyOf(this);
  }

}

