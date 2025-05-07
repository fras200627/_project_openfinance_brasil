package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_customers_model.Links;
import com.tican.open.finance.template_customers_model.Meta;
import com.tican.open.finance.template_customers_model.PersonalFinancialRelationData;
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
 * ResponsePersonalCustomersFinancialRelation
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponsePersonalCustomersFinancialRelation {

  private PersonalFinancialRelationData data;

  private Links links;

  private Meta meta;

  public ResponsePersonalCustomersFinancialRelation() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public ResponsePersonalCustomersFinancialRelation(PersonalFinancialRelationData data, Links links, Meta meta) {
    this.data = data;
    this.links = links;
    this.meta = meta;
  }

  public ResponsePersonalCustomersFinancialRelation data(PersonalFinancialRelationData data) {
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
  public PersonalFinancialRelationData getData() {
    return data;
  }

  public void setData(PersonalFinancialRelationData data) {
    this.data = data;
  }

  public ResponsePersonalCustomersFinancialRelation links(Links links) {
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

  public ResponsePersonalCustomersFinancialRelation meta(Meta meta) {
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
    ResponsePersonalCustomersFinancialRelation responsePersonalCustomersFinancialRelation = (ResponsePersonalCustomersFinancialRelation) o;
    return Objects.equals(this.data, responsePersonalCustomersFinancialRelation.data) &&
        Objects.equals(this.links, responsePersonalCustomersFinancialRelation.links) &&
        Objects.equals(this.meta, responsePersonalCustomersFinancialRelation.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, links, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponsePersonalCustomersFinancialRelation {\n");
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

    private ResponsePersonalCustomersFinancialRelation instance;

    public Builder() {
      this(new ResponsePersonalCustomersFinancialRelation());
    }

    protected Builder(ResponsePersonalCustomersFinancialRelation instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponsePersonalCustomersFinancialRelation value) { 
      this.instance.setData(value.data);
      this.instance.setLinks(value.links);
      this.instance.setMeta(value.meta);
      return this;
    }

    public ResponsePersonalCustomersFinancialRelation.Builder data(PersonalFinancialRelationData data) {
      this.instance.data(data);
      return this;
    }
    
    public ResponsePersonalCustomersFinancialRelation.Builder links(Links links) {
      this.instance.links(links);
      return this;
    }
    
    public ResponsePersonalCustomersFinancialRelation.Builder meta(Meta meta) {
      this.instance.meta(meta);
      return this;
    }
    
    /**
    * returns a built ResponsePersonalCustomersFinancialRelation instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponsePersonalCustomersFinancialRelation build() {
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
  public static ResponsePersonalCustomersFinancialRelation.Builder builder() {
    return new ResponsePersonalCustomersFinancialRelation.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponsePersonalCustomersFinancialRelation.Builder toBuilder() {
    ResponsePersonalCustomersFinancialRelation.Builder builder = new ResponsePersonalCustomersFinancialRelation.Builder();
    return builder.copyOf(this);
  }

}

