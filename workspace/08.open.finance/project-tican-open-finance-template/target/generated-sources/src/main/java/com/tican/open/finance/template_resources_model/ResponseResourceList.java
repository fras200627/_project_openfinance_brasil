package com.tican.open.finance.template_resources_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_resources_model.Links;
import com.tican.open.finance.template_resources_model.MetaResponse;
import com.tican.open.finance.template_resources_model.ResponseResourceListDataInner;
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
 * ResponseResourceList
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:04.924183300-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseResourceList {

  @Valid
  private List<@Valid ResponseResourceListDataInner> data = new ArrayList<>();

  private Links links;

  private MetaResponse meta;

  public ResponseResourceList() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public ResponseResourceList(List<@Valid ResponseResourceListDataInner> data, Links links, MetaResponse meta) {
    this.data = data;
    this.links = links;
    this.meta = meta;
  }

  public ResponseResourceList data(List<@Valid ResponseResourceListDataInner> data) {
    this.data = data;
    return this;
  }

  public ResponseResourceList addDataItem(ResponseResourceListDataInner dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<>();
    }
    this.data.add(dataItem);
    return this;
  }

  /**
   * Lista de recursos e seus respectivos status.
   * @return data
   */
  @NotNull @Valid @Size(min = 0) 
  @Schema(name = "data", description = "Lista de recursos e seus respectivos status.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("data")
  public List<@Valid ResponseResourceListDataInner> getData() {
    return data;
  }

  public void setData(List<@Valid ResponseResourceListDataInner> data) {
    this.data = data;
  }

  public ResponseResourceList links(Links links) {
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

  public ResponseResourceList meta(MetaResponse meta) {
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
  public MetaResponse getMeta() {
    return meta;
  }

  public void setMeta(MetaResponse meta) {
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
    ResponseResourceList responseResourceList = (ResponseResourceList) o;
    return Objects.equals(this.data, responseResourceList.data) &&
        Objects.equals(this.links, responseResourceList.links) &&
        Objects.equals(this.meta, responseResourceList.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, links, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseResourceList {\n");
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

    private ResponseResourceList instance;

    public Builder() {
      this(new ResponseResourceList());
    }

    protected Builder(ResponseResourceList instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseResourceList value) { 
      this.instance.setData(value.data);
      this.instance.setLinks(value.links);
      this.instance.setMeta(value.meta);
      return this;
    }

    public ResponseResourceList.Builder data(List<ResponseResourceListDataInner> data) {
      this.instance.data(data);
      return this;
    }
    
    public ResponseResourceList.Builder links(Links links) {
      this.instance.links(links);
      return this;
    }
    
    public ResponseResourceList.Builder meta(MetaResponse meta) {
      this.instance.meta(meta);
      return this;
    }
    
    /**
    * returns a built ResponseResourceList instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseResourceList build() {
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
  public static ResponseResourceList.Builder builder() {
    return new ResponseResourceList.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseResourceList.Builder toBuilder() {
    ResponseResourceList.Builder builder = new ResponseResourceList.Builder();
    return builder.copyOf(this);
  }

}

