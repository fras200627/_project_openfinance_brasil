package com.tican.open.finance.template_accounts_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_accounts_model.AccountIdentificationData;
import com.tican.open.finance.template_accounts_model.LinksAccountId;
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
 * ResponseAccountIdentification
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:09.506997400-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseAccountIdentification {

  private AccountIdentificationData data;

  private LinksAccountId links;

  private Meta meta;

  public ResponseAccountIdentification() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public ResponseAccountIdentification(AccountIdentificationData data, LinksAccountId links, Meta meta) {
    this.data = data;
    this.links = links;
    this.meta = meta;
  }

  public ResponseAccountIdentification data(AccountIdentificationData data) {
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
  public AccountIdentificationData getData() {
    return data;
  }

  public void setData(AccountIdentificationData data) {
    this.data = data;
  }

  public ResponseAccountIdentification links(LinksAccountId links) {
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
  public LinksAccountId getLinks() {
    return links;
  }

  public void setLinks(LinksAccountId links) {
    this.links = links;
  }

  public ResponseAccountIdentification meta(Meta meta) {
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
    ResponseAccountIdentification responseAccountIdentification = (ResponseAccountIdentification) o;
    return Objects.equals(this.data, responseAccountIdentification.data) &&
        Objects.equals(this.links, responseAccountIdentification.links) &&
        Objects.equals(this.meta, responseAccountIdentification.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, links, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseAccountIdentification {\n");
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

    private ResponseAccountIdentification instance;

    public Builder() {
      this(new ResponseAccountIdentification());
    }

    protected Builder(ResponseAccountIdentification instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseAccountIdentification value) { 
      this.instance.setData(value.data);
      this.instance.setLinks(value.links);
      this.instance.setMeta(value.meta);
      return this;
    }

    public ResponseAccountIdentification.Builder data(AccountIdentificationData data) {
      this.instance.data(data);
      return this;
    }
    
    public ResponseAccountIdentification.Builder links(LinksAccountId links) {
      this.instance.links(links);
      return this;
    }
    
    public ResponseAccountIdentification.Builder meta(Meta meta) {
      this.instance.meta(meta);
      return this;
    }
    
    /**
    * returns a built ResponseAccountIdentification instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseAccountIdentification build() {
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
  public static ResponseAccountIdentification.Builder builder() {
    return new ResponseAccountIdentification.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseAccountIdentification.Builder toBuilder() {
    ResponseAccountIdentification.Builder builder = new ResponseAccountIdentification.Builder();
    return builder.copyOf(this);
  }

}

