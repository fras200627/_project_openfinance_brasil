package com.tican.open.finance.template_accounts_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_accounts_model.AccountOverdraftLimitsDataOverdraftContractedLimit;
import com.tican.open.finance.template_accounts_model.AccountOverdraftLimitsDataOverdraftUsedLimit;
import com.tican.open.finance.template_accounts_model.AccountOverdraftLimitsDataUnarrangedOverdraftAmount;
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
 * Conjunto de informações da Conta de: depósito à vista 
 */

@Schema(name = "AccountOverdraftLimitsData", description = "Conjunto de informações da Conta de: depósito à vista ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:09.506997400-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class AccountOverdraftLimitsData {

  private @Nullable AccountOverdraftLimitsDataOverdraftContractedLimit overdraftContractedLimit;

  private @Nullable AccountOverdraftLimitsDataOverdraftUsedLimit overdraftUsedLimit;

  private @Nullable AccountOverdraftLimitsDataUnarrangedOverdraftAmount unarrangedOverdraftAmount;

  public AccountOverdraftLimitsData() {
    super();
  }

  /**
   * Constructor with all args parameters
   */
  public AccountOverdraftLimitsData(@Nullable AccountOverdraftLimitsDataOverdraftContractedLimit overdraftContractedLimit, @Nullable AccountOverdraftLimitsDataOverdraftUsedLimit overdraftUsedLimit, @Nullable AccountOverdraftLimitsDataUnarrangedOverdraftAmount unarrangedOverdraftAmount) {
      this.overdraftContractedLimit = overdraftContractedLimit;
      this.overdraftUsedLimit = overdraftUsedLimit;
      this.unarrangedOverdraftAmount = unarrangedOverdraftAmount;
  }

  public AccountOverdraftLimitsData overdraftContractedLimit(AccountOverdraftLimitsDataOverdraftContractedLimit overdraftContractedLimit) {
    this.overdraftContractedLimit = overdraftContractedLimit;
    return this;
  }

  /**
   * Get overdraftContractedLimit
   * @return overdraftContractedLimit
   */
  @Valid 
  @Schema(name = "overdraftContractedLimit", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("overdraftContractedLimit")
  public AccountOverdraftLimitsDataOverdraftContractedLimit getOverdraftContractedLimit() {
    return overdraftContractedLimit;
  }

  public void setOverdraftContractedLimit(AccountOverdraftLimitsDataOverdraftContractedLimit overdraftContractedLimit) {
    this.overdraftContractedLimit = overdraftContractedLimit;
  }

  public AccountOverdraftLimitsData overdraftUsedLimit(AccountOverdraftLimitsDataOverdraftUsedLimit overdraftUsedLimit) {
    this.overdraftUsedLimit = overdraftUsedLimit;
    return this;
  }

  /**
   * Get overdraftUsedLimit
   * @return overdraftUsedLimit
   */
  @Valid 
  @Schema(name = "overdraftUsedLimit", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("overdraftUsedLimit")
  public AccountOverdraftLimitsDataOverdraftUsedLimit getOverdraftUsedLimit() {
    return overdraftUsedLimit;
  }

  public void setOverdraftUsedLimit(AccountOverdraftLimitsDataOverdraftUsedLimit overdraftUsedLimit) {
    this.overdraftUsedLimit = overdraftUsedLimit;
  }

  public AccountOverdraftLimitsData unarrangedOverdraftAmount(AccountOverdraftLimitsDataUnarrangedOverdraftAmount unarrangedOverdraftAmount) {
    this.unarrangedOverdraftAmount = unarrangedOverdraftAmount;
    return this;
  }

  /**
   * Get unarrangedOverdraftAmount
   * @return unarrangedOverdraftAmount
   */
  @Valid 
  @Schema(name = "unarrangedOverdraftAmount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("unarrangedOverdraftAmount")
  public AccountOverdraftLimitsDataUnarrangedOverdraftAmount getUnarrangedOverdraftAmount() {
    return unarrangedOverdraftAmount;
  }

  public void setUnarrangedOverdraftAmount(AccountOverdraftLimitsDataUnarrangedOverdraftAmount unarrangedOverdraftAmount) {
    this.unarrangedOverdraftAmount = unarrangedOverdraftAmount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountOverdraftLimitsData accountOverdraftLimitsData = (AccountOverdraftLimitsData) o;
    return Objects.equals(this.overdraftContractedLimit, accountOverdraftLimitsData.overdraftContractedLimit) &&
        Objects.equals(this.overdraftUsedLimit, accountOverdraftLimitsData.overdraftUsedLimit) &&
        Objects.equals(this.unarrangedOverdraftAmount, accountOverdraftLimitsData.unarrangedOverdraftAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(overdraftContractedLimit, overdraftUsedLimit, unarrangedOverdraftAmount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountOverdraftLimitsData {\n");
    sb.append("    overdraftContractedLimit: ").append(toIndentedString(overdraftContractedLimit)).append("\n");
    sb.append("    overdraftUsedLimit: ").append(toIndentedString(overdraftUsedLimit)).append("\n");
    sb.append("    unarrangedOverdraftAmount: ").append(toIndentedString(unarrangedOverdraftAmount)).append("\n");
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

    private AccountOverdraftLimitsData instance;

    public Builder() {
      this(new AccountOverdraftLimitsData());
    }

    protected Builder(AccountOverdraftLimitsData instance) {
      this.instance = instance;
    }

    protected Builder copyOf(AccountOverdraftLimitsData value) { 
      this.instance.setOverdraftContractedLimit(value.overdraftContractedLimit);
      this.instance.setOverdraftUsedLimit(value.overdraftUsedLimit);
      this.instance.setUnarrangedOverdraftAmount(value.unarrangedOverdraftAmount);
      return this;
    }

    public AccountOverdraftLimitsData.Builder overdraftContractedLimit(AccountOverdraftLimitsDataOverdraftContractedLimit overdraftContractedLimit) {
      this.instance.overdraftContractedLimit(overdraftContractedLimit);
      return this;
    }
    
    public AccountOverdraftLimitsData.Builder overdraftUsedLimit(AccountOverdraftLimitsDataOverdraftUsedLimit overdraftUsedLimit) {
      this.instance.overdraftUsedLimit(overdraftUsedLimit);
      return this;
    }
    
    public AccountOverdraftLimitsData.Builder unarrangedOverdraftAmount(AccountOverdraftLimitsDataUnarrangedOverdraftAmount unarrangedOverdraftAmount) {
      this.instance.unarrangedOverdraftAmount(unarrangedOverdraftAmount);
      return this;
    }
    
    /**
    * returns a built AccountOverdraftLimitsData instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public AccountOverdraftLimitsData build() {
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
  public static AccountOverdraftLimitsData.Builder builder() {
    return new AccountOverdraftLimitsData.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public AccountOverdraftLimitsData.Builder toBuilder() {
    AccountOverdraftLimitsData.Builder builder = new AccountOverdraftLimitsData.Builder();
    return builder.copyOf(this);
  }

}

