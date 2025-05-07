package com.tican.open.finance.template_accounts_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_accounts_model.AccountTransactionsData;
import com.tican.open.finance.template_accounts_model.MetaOnlyRequestDateTime;
import com.tican.open.finance.template_accounts_model.TransactionsLinks;
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
 * ResponseAccountTransactions
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:09.506997400-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseAccountTransactions {

  @Valid
  private List<@Valid AccountTransactionsData> data = new ArrayList<>();

  private TransactionsLinks links;

  private MetaOnlyRequestDateTime meta;

  public ResponseAccountTransactions() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public ResponseAccountTransactions(List<@Valid AccountTransactionsData> data, TransactionsLinks links, MetaOnlyRequestDateTime meta) {
    this.data = data;
    this.links = links;
    this.meta = meta;
  }

  public ResponseAccountTransactions data(List<@Valid AccountTransactionsData> data) {
    this.data = data;
    return this;
  }

  public ResponseAccountTransactions addDataItem(AccountTransactionsData dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<>();
    }
    this.data.add(dataItem);
    return this;
  }

  /**
   * Lista dos lançamentos referentes às transações realizadas e de lançamentos futuros para as contas de: depósito à vista, poupança e de pagamento pré-paga 
   * @return data
   */
  @NotNull @Valid @Size(min = 0) 
  @Schema(name = "data", description = "Lista dos lançamentos referentes às transações realizadas e de lançamentos futuros para as contas de: depósito à vista, poupança e de pagamento pré-paga ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("data")
  public List<@Valid AccountTransactionsData> getData() {
    return data;
  }

  public void setData(List<@Valid AccountTransactionsData> data) {
    this.data = data;
  }

  public ResponseAccountTransactions links(TransactionsLinks links) {
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
  public TransactionsLinks getLinks() {
    return links;
  }

  public void setLinks(TransactionsLinks links) {
    this.links = links;
  }

  public ResponseAccountTransactions meta(MetaOnlyRequestDateTime meta) {
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
  public MetaOnlyRequestDateTime getMeta() {
    return meta;
  }

  public void setMeta(MetaOnlyRequestDateTime meta) {
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
    ResponseAccountTransactions responseAccountTransactions = (ResponseAccountTransactions) o;
    return Objects.equals(this.data, responseAccountTransactions.data) &&
        Objects.equals(this.links, responseAccountTransactions.links) &&
        Objects.equals(this.meta, responseAccountTransactions.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, links, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseAccountTransactions {\n");
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

    private ResponseAccountTransactions instance;

    public Builder() {
      this(new ResponseAccountTransactions());
    }

    protected Builder(ResponseAccountTransactions instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseAccountTransactions value) { 
      this.instance.setData(value.data);
      this.instance.setLinks(value.links);
      this.instance.setMeta(value.meta);
      return this;
    }

    public ResponseAccountTransactions.Builder data(List<AccountTransactionsData> data) {
      this.instance.data(data);
      return this;
    }
    
    public ResponseAccountTransactions.Builder links(TransactionsLinks links) {
      this.instance.links(links);
      return this;
    }
    
    public ResponseAccountTransactions.Builder meta(MetaOnlyRequestDateTime meta) {
      this.instance.meta(meta);
      return this;
    }
    
    /**
    * returns a built ResponseAccountTransactions instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseAccountTransactions build() {
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
  public static ResponseAccountTransactions.Builder builder() {
    return new ResponseAccountTransactions.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseAccountTransactions.Builder toBuilder() {
    ResponseAccountTransactions.Builder builder = new ResponseAccountTransactions.Builder();
    return builder.copyOf(this);
  }

}

