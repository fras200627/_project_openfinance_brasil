package com.tican.open.finance.template_accounts_model;

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
 * Saldo disponível com aplicação automática - corresponde a soma do saldo disponível acrescido do valor obtido a partir da aplicação automática. Expresso em valor monetário com no mínimo 2 casas e no máximo 4 casas decimais.
 */

@Schema(name = "AccountBalancesDataAutomaticallyInvestedAmount", description = "Saldo disponível com aplicação automática - corresponde a soma do saldo disponível acrescido do valor obtido a partir da aplicação automática. Expresso em valor monetário com no mínimo 2 casas e no máximo 4 casas decimais.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:09.506997400-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class AccountBalancesDataAutomaticallyInvestedAmount {

  private Double amount;

  private String currency;

  public AccountBalancesDataAutomaticallyInvestedAmount() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public AccountBalancesDataAutomaticallyInvestedAmount(Double amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public AccountBalancesDataAutomaticallyInvestedAmount amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Valor relacionado ao objeto.
   * @return amount
   */
  @NotNull @Pattern(regexp = "^-?\\d{1,15}\\.\\d{2,4}$") @Size(min = 4, max = 21) 
  @Schema(name = "amount", example = "1000.0400", description = "Valor relacionado ao objeto.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("amount")
  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public AccountBalancesDataAutomaticallyInvestedAmount currency(String currency) {
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
    AccountBalancesDataAutomaticallyInvestedAmount accountBalancesDataAutomaticallyInvestedAmount = (AccountBalancesDataAutomaticallyInvestedAmount) o;
    return Objects.equals(this.amount, accountBalancesDataAutomaticallyInvestedAmount.amount) &&
        Objects.equals(this.currency, accountBalancesDataAutomaticallyInvestedAmount.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, currency);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountBalancesDataAutomaticallyInvestedAmount {\n");
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

    private AccountBalancesDataAutomaticallyInvestedAmount instance;

    public Builder() {
      this(new AccountBalancesDataAutomaticallyInvestedAmount());
    }

    protected Builder(AccountBalancesDataAutomaticallyInvestedAmount instance) {
      this.instance = instance;
    }

    protected Builder copyOf(AccountBalancesDataAutomaticallyInvestedAmount value) { 
      this.instance.setAmount(value.amount);
      this.instance.setCurrency(value.currency);
      return this;
    }

    public AccountBalancesDataAutomaticallyInvestedAmount.Builder amount(Double amount) {
      this.instance.amount(amount);
      return this;
    }
    
    public AccountBalancesDataAutomaticallyInvestedAmount.Builder currency(String currency) {
      this.instance.currency(currency);
      return this;
    }
    
    /**
    * returns a built AccountBalancesDataAutomaticallyInvestedAmount instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public AccountBalancesDataAutomaticallyInvestedAmount build() {
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
  public static AccountBalancesDataAutomaticallyInvestedAmount.Builder builder() {
    return new AccountBalancesDataAutomaticallyInvestedAmount.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public AccountBalancesDataAutomaticallyInvestedAmount.Builder toBuilder() {
    AccountBalancesDataAutomaticallyInvestedAmount.Builder builder = new AccountBalancesDataAutomaticallyInvestedAmount.Builder();
    return builder.copyOf(this);
  }

}

