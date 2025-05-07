package com.tican.open.finance.template_accounts_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_accounts_model.AccountBalancesDataAutomaticallyInvestedAmount;
import com.tican.open.finance.template_accounts_model.AccountBalancesDataAvailableAmount;
import com.tican.open.finance.template_accounts_model.AccountBalancesDataBlockedAmount;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
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
 * Conjunto de informações das Contas de: depósito à vista, poupança e de pagamento pré-paga 
 */

@Schema(name = "AccountBalancesData", description = "Conjunto de informações das Contas de: depósito à vista, poupança e de pagamento pré-paga ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:09.506997400-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class AccountBalancesData {

  private AccountBalancesDataAvailableAmount availableAmount;

  private AccountBalancesDataBlockedAmount blockedAmount;

  private AccountBalancesDataAutomaticallyInvestedAmount automaticallyInvestedAmount;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updateDateTime;

  public AccountBalancesData() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public AccountBalancesData(AccountBalancesDataAvailableAmount availableAmount, AccountBalancesDataBlockedAmount blockedAmount, AccountBalancesDataAutomaticallyInvestedAmount automaticallyInvestedAmount, OffsetDateTime updateDateTime) {
    this.availableAmount = availableAmount;
    this.blockedAmount = blockedAmount;
    this.automaticallyInvestedAmount = automaticallyInvestedAmount;
    this.updateDateTime = updateDateTime;
  }

  public AccountBalancesData availableAmount(AccountBalancesDataAvailableAmount availableAmount) {
    this.availableAmount = availableAmount;
    return this;
  }

  /**
   * Get availableAmount
   * @return availableAmount
   */
  @NotNull @Valid 
  @Schema(name = "availableAmount", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("availableAmount")
  public AccountBalancesDataAvailableAmount getAvailableAmount() {
    return availableAmount;
  }

  public void setAvailableAmount(AccountBalancesDataAvailableAmount availableAmount) {
    this.availableAmount = availableAmount;
  }

  public AccountBalancesData blockedAmount(AccountBalancesDataBlockedAmount blockedAmount) {
    this.blockedAmount = blockedAmount;
    return this;
  }

  /**
   * Get blockedAmount
   * @return blockedAmount
   */
  @NotNull @Valid 
  @Schema(name = "blockedAmount", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("blockedAmount")
  public AccountBalancesDataBlockedAmount getBlockedAmount() {
    return blockedAmount;
  }

  public void setBlockedAmount(AccountBalancesDataBlockedAmount blockedAmount) {
    this.blockedAmount = blockedAmount;
  }

  public AccountBalancesData automaticallyInvestedAmount(AccountBalancesDataAutomaticallyInvestedAmount automaticallyInvestedAmount) {
    this.automaticallyInvestedAmount = automaticallyInvestedAmount;
    return this;
  }

  /**
   * Get automaticallyInvestedAmount
   * @return automaticallyInvestedAmount
   */
  @NotNull @Valid 
  @Schema(name = "automaticallyInvestedAmount", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("automaticallyInvestedAmount")
  public AccountBalancesDataAutomaticallyInvestedAmount getAutomaticallyInvestedAmount() {
    return automaticallyInvestedAmount;
  }

  public void setAutomaticallyInvestedAmount(AccountBalancesDataAutomaticallyInvestedAmount automaticallyInvestedAmount) {
    this.automaticallyInvestedAmount = automaticallyInvestedAmount;
  }

  public AccountBalancesData updateDateTime(OffsetDateTime updateDateTime) {
    this.updateDateTime = updateDateTime;
    return this;
  }

  /**
   * Data e hora da última atualização do saldo. É esperado que a instituição informe a última vez que capturou o saldo para compartilhamento no Open Finance. Dessa forma, é possível que: - Caso a instituição capture dados de forma síncrona essa informação seja de poucos momentos; - Caso a instituição capture dados de forma assíncrona essa informação seja de horas ou dias no passado; - Quando não existente uma data vinculada especificamente ao bloco, se assume a data e hora de atualização do cadastro como um todo.  De toda forma, é preciso continuar respeitando o prazo máximo de tempestividade da API de Contas. 
   * @return updateDateTime
   */
  @NotNull @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])T(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)Z$") @Size(max = 20) 
  @Schema(name = "updateDateTime", example = "2021-05-21T08:30Z", description = "Data e hora da última atualização do saldo. É esperado que a instituição informe a última vez que capturou o saldo para compartilhamento no Open Finance. Dessa forma, é possível que: - Caso a instituição capture dados de forma síncrona essa informação seja de poucos momentos; - Caso a instituição capture dados de forma assíncrona essa informação seja de horas ou dias no passado; - Quando não existente uma data vinculada especificamente ao bloco, se assume a data e hora de atualização do cadastro como um todo.  De toda forma, é preciso continuar respeitando o prazo máximo de tempestividade da API de Contas. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("updateDateTime")
  public OffsetDateTime getUpdateDateTime() {
    return updateDateTime;
  }

  public void setUpdateDateTime(OffsetDateTime updateDateTime) {
    this.updateDateTime = updateDateTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountBalancesData accountBalancesData = (AccountBalancesData) o;
    return Objects.equals(this.availableAmount, accountBalancesData.availableAmount) &&
        Objects.equals(this.blockedAmount, accountBalancesData.blockedAmount) &&
        Objects.equals(this.automaticallyInvestedAmount, accountBalancesData.automaticallyInvestedAmount) &&
        Objects.equals(this.updateDateTime, accountBalancesData.updateDateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(availableAmount, blockedAmount, automaticallyInvestedAmount, updateDateTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountBalancesData {\n");
    sb.append("    availableAmount: ").append(toIndentedString(availableAmount)).append("\n");
    sb.append("    blockedAmount: ").append(toIndentedString(blockedAmount)).append("\n");
    sb.append("    automaticallyInvestedAmount: ").append(toIndentedString(automaticallyInvestedAmount)).append("\n");
    sb.append("    updateDateTime: ").append(toIndentedString(updateDateTime)).append("\n");
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

    private AccountBalancesData instance;

    public Builder() {
      this(new AccountBalancesData());
    }

    protected Builder(AccountBalancesData instance) {
      this.instance = instance;
    }

    protected Builder copyOf(AccountBalancesData value) { 
      this.instance.setAvailableAmount(value.availableAmount);
      this.instance.setBlockedAmount(value.blockedAmount);
      this.instance.setAutomaticallyInvestedAmount(value.automaticallyInvestedAmount);
      this.instance.setUpdateDateTime(value.updateDateTime);
      return this;
    }

    public AccountBalancesData.Builder availableAmount(AccountBalancesDataAvailableAmount availableAmount) {
      this.instance.availableAmount(availableAmount);
      return this;
    }
    
    public AccountBalancesData.Builder blockedAmount(AccountBalancesDataBlockedAmount blockedAmount) {
      this.instance.blockedAmount(blockedAmount);
      return this;
    }
    
    public AccountBalancesData.Builder automaticallyInvestedAmount(AccountBalancesDataAutomaticallyInvestedAmount automaticallyInvestedAmount) {
      this.instance.automaticallyInvestedAmount(automaticallyInvestedAmount);
      return this;
    }
    
    public AccountBalancesData.Builder updateDateTime(OffsetDateTime updateDateTime) {
      this.instance.updateDateTime(updateDateTime);
      return this;
    }
    
    /**
    * returns a built AccountBalancesData instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public AccountBalancesData build() {
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
  public static AccountBalancesData.Builder builder() {
    return new AccountBalancesData.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public AccountBalancesData.Builder toBuilder() {
    AccountBalancesData.Builder builder = new AccountBalancesData.Builder();
    return builder.copyOf(this);
  }

}

