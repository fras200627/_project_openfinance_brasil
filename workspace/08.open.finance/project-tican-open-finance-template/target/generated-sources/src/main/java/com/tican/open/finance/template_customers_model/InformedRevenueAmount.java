package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * Valor do patrimônio informado
 */

@Schema(name = "InformedRevenueAmount", description = "Valor do patrimônio informado")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class InformedRevenueAmount {

  private String amount;

  private String currency;

  public InformedRevenueAmount() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public InformedRevenueAmount(String amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public InformedRevenueAmount amount(String amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Valor do patrimônio informado. Expresso em valor monetário com no mínimo 2 casas e no máximo 4 casas decimais. Patrimônio é o conjunto de bens vinculado a uma pessoa ou a uma entidade. 
   * @return amount
   */
  @NotNull @Pattern(regexp = "^-?\\d{1,15}\\.\\d{2,4}$") @Size(min = 4, max = 21) 
  @Schema(name = "amount", example = "100000.0400", description = "Valor do patrimônio informado. Expresso em valor monetário com no mínimo 2 casas e no máximo 4 casas decimais. Patrimônio é o conjunto de bens vinculado a uma pessoa ou a uma entidade. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("amount")
  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public InformedRevenueAmount currency(String currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Moeda referente ao valor monetário, seguindo o modelo ISO-4217.
   * @return currency
   */
  @NotNull @Pattern(regexp = "^[A-Z]{3}$") @Size(max = 3) 
  @Schema(name = "currency", example = "BRL", description = "Moeda referente ao valor monetário, seguindo o modelo ISO-4217.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("currency")
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InformedRevenueAmount informedRevenueAmount = (InformedRevenueAmount) o;
    return Objects.equals(this.amount, informedRevenueAmount.amount) &&
        Objects.equals(this.currency, informedRevenueAmount.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, currency);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InformedRevenueAmount {\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
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

    private InformedRevenueAmount instance;

    public Builder() {
      this(new InformedRevenueAmount());
    }

    protected Builder(InformedRevenueAmount instance) {
      this.instance = instance;
    }

    protected Builder copyOf(InformedRevenueAmount value) { 
      this.instance.setAmount(value.amount);
      this.instance.setCurrency(value.currency);
      return this;
    }

    public InformedRevenueAmount.Builder amount(String amount) {
      this.instance.amount(amount);
      return this;
    }
    
    public InformedRevenueAmount.Builder currency(String currency) {
      this.instance.currency(currency);
      return this;
    }
    
    /**
    * returns a built InformedRevenueAmount instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public InformedRevenueAmount build() {
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
  public static InformedRevenueAmount.Builder builder() {
    return new InformedRevenueAmount.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public InformedRevenueAmount.Builder toBuilder() {
    InformedRevenueAmount.Builder builder = new InformedRevenueAmount.Builder();
    return builder.copyOf(this);
  }

}

