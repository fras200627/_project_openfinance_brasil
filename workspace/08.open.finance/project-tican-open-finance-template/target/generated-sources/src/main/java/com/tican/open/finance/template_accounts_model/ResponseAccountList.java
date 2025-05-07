package com.tican.open.finance.template_accounts_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_accounts_model.AccountData;
import com.tican.open.finance.template_accounts_model.Links;
import com.tican.open.finance.template_accounts_model.Meta;
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
 * ResponseAccountList
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:09.506997400-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseAccountList {

  @Valid
  private List<@Valid AccountData> data = new ArrayList<>();

  private Links links;

  private Meta meta;

  public ResponseAccountList() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public ResponseAccountList(List<@Valid AccountData> data, Links links, Meta meta) {
    this.data = data;
    this.links = links;
    this.meta = meta;
  }

  public ResponseAccountList data(List<@Valid AccountData> data) {
    this.data = data;
    return this;
  }

  public ResponseAccountList addDataItem(AccountData dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<>();
    }
    this.data.add(dataItem);
    return this;
  }

  /**
   * Lista de contas depósito à vista, poupança e pagamento pré-pagas mantidas pelo cliente na instituição transmissora e para as quais ele tenha fornecido consentimento
   * @return data
   */
  @NotNull @Valid @Size(min = 0) 
  @Schema(name = "data", description = "Lista de contas depósito à vista, poupança e pagamento pré-pagas mantidas pelo cliente na instituição transmissora e para as quais ele tenha fornecido consentimento", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("data")
  public List<@Valid AccountData> getData() {
    return data;
  }

  public void setData(List<@Valid AccountData> data) {
    this.data = data;
  }

  public ResponseAccountList links(Links links) {
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

  public ResponseAccountList meta(Meta meta) {
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
    ResponseAccountList responseAccountList = (ResponseAccountList) o;
    return Objects.equals(this.data, responseAccountList.data) &&
        Objects.equals(this.links, responseAccountList.links) &&
        Objects.equals(this.meta, responseAccountList.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, links, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseAccountList {\n");
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

    private ResponseAccountList instance;

    public Builder() {
      this(new ResponseAccountList());
    }

    protected Builder(ResponseAccountList instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseAccountList value) { 
      this.instance.setData(value.data);
      this.instance.setLinks(value.links);
      this.instance.setMeta(value.meta);
      return this;
    }

    public ResponseAccountList.Builder data(List<AccountData> data) {
      this.instance.data(data);
      return this;
    }
    
    public ResponseAccountList.Builder links(Links links) {
      this.instance.links(links);
      return this;
    }
    
    public ResponseAccountList.Builder meta(Meta meta) {
      this.instance.meta(meta);
      return this;
    }
    
    /**
    * returns a built ResponseAccountList instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseAccountList build() {
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
  public static ResponseAccountList.Builder builder() {
    return new ResponseAccountList.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseAccountList.Builder toBuilder() {
    ResponseAccountList.Builder builder = new ResponseAccountList.Builder();
    return builder.copyOf(this);
  }

}

