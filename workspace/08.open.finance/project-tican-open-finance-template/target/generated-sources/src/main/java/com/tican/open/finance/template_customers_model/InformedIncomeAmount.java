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
 * Valor total da renda informada
 */

@Schema(name = "InformedIncomeAmount", description = "Valor total da renda informada")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class InformedIncomeAmount {

  private String amount;

  private String currency;

  public InformedIncomeAmount() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public InformedIncomeAmount(String amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public InformedIncomeAmount amount(String amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Valor total da renda informada. Expresso em valor monetário com no mínimo 2 casas e no máximo 4 casas decimais.  Renda primária indica os montantes a pagar ou a receber em troca do uso temporário de recursos financeiros, trabalho ou ativos não financeiros não produzidos, a saber, remuneração de trabalhadores, renda de investimentos e demais rendas primárias. Fazem parte da primeira a remuneração do trabalho assalariado (salários e ordenados); da segunda, renda de investimento direto, renda de investimento em carteira, renda de outros investimentos e renda de ativos de reserva; e da terceira, tributos sobre a produção e importação, subsídios e aluguéis. Fonte: Banco Central do Brasil – Departamento Econômico 
   * @return amount
   */
  @NotNull @Pattern(regexp = "^\\d{1,15}\\.\\d{2,4}$") @Size(min = 4, max = 20) 
  @Schema(name = "amount", example = "100000.0400", description = "Valor total da renda informada. Expresso em valor monetário com no mínimo 2 casas e no máximo 4 casas decimais.  Renda primária indica os montantes a pagar ou a receber em troca do uso temporário de recursos financeiros, trabalho ou ativos não financeiros não produzidos, a saber, remuneração de trabalhadores, renda de investimentos e demais rendas primárias. Fazem parte da primeira a remuneração do trabalho assalariado (salários e ordenados); da segunda, renda de investimento direto, renda de investimento em carteira, renda de outros investimentos e renda de ativos de reserva; e da terceira, tributos sobre a produção e importação, subsídios e aluguéis. Fonte: Banco Central do Brasil – Departamento Econômico ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("amount")
  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public InformedIncomeAmount currency(String currency) {
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
    InformedIncomeAmount informedIncomeAmount = (InformedIncomeAmount) o;
    return Objects.equals(this.amount, informedIncomeAmount.amount) &&
        Objects.equals(this.currency, informedIncomeAmount.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, currency);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InformedIncomeAmount {\n");
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

    private InformedIncomeAmount instance;

    public Builder() {
      this(new InformedIncomeAmount());
    }

    protected Builder(InformedIncomeAmount instance) {
      this.instance = instance;
    }

    protected Builder copyOf(InformedIncomeAmount value) { 
      this.instance.setAmount(value.amount);
      this.instance.setCurrency(value.currency);
      return this;
    }

    public InformedIncomeAmount.Builder amount(String amount) {
      this.instance.amount(amount);
      return this;
    }
    
    public InformedIncomeAmount.Builder currency(String currency) {
      this.instance.currency(currency);
      return this;
    }
    
    /**
    * returns a built InformedIncomeAmount instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public InformedIncomeAmount build() {
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
  public static InformedIncomeAmount.Builder builder() {
    return new InformedIncomeAmount.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public InformedIncomeAmount.Builder toBuilder() {
    InformedIncomeAmount.Builder builder = new InformedIncomeAmount.Builder();
    return builder.copyOf(this);
  }

}

