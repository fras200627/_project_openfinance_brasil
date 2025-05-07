package com.tican.open.finance.template_accounts_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tican.open.finance.template_accounts_model.EnumAccountSubType;
import com.tican.open.finance.template_accounts_model.EnumAccountType;
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
 * Conjunto dos atributos que caracterizam as Contas de: depósito à vista, poupança e de pagamento pré-paga 
 */

@Schema(name = "AccountIdentificationData", description = "Conjunto dos atributos que caracterizam as Contas de: depósito à vista, poupança e de pagamento pré-paga ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:09.506997400-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class AccountIdentificationData {

  private String compeCode;

  private @Nullable String branchCode;

  private String number;

  private String checkDigit;

  private EnumAccountType type;

  private EnumAccountSubType subtype;

  private String currency;

  public AccountIdentificationData() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AccountIdentificationData(String compeCode, String number, String checkDigit, EnumAccountType type, EnumAccountSubType subtype, String currency) {
    this.compeCode = compeCode;
    this.number = number;
    this.checkDigit = checkDigit;
    this.type = type;
    this.subtype = subtype;
    this.currency = currency;
  }

  /**
   * Constructor with all args parameters
   */
  public AccountIdentificationData(String compeCode, @Nullable String branchCode, String number, String checkDigit, EnumAccountType type, EnumAccountSubType subtype, String currency) {
      this.compeCode = compeCode;
      this.branchCode = branchCode;
      this.number = number;
      this.checkDigit = checkDigit;
      this.type = type;
      this.subtype = subtype;
      this.currency = currency;
  }

  public AccountIdentificationData compeCode(String compeCode) {
    this.compeCode = compeCode;
    return this;
  }

  /**
   * Código identificador atribuído pelo Banco Central do Brasil às instituições participantes do STR (Sistema de Transferência de reservas). O número-código substituiu o antigo código COMPE. Todos os participantes do STR, exceto as Infraestruturas do Mercado Financeiro (IMF) e a Secretaria do Tesouro Nacional, possuem um número-código independentemente de participarem da Centralizadora da Compensação de Cheques (Compe).
   * @return compeCode
   */
  @NotNull @Pattern(regexp = "^\\d{3}$") @Size(max = 3) 
  @Schema(name = "compeCode", example = "001", description = "Código identificador atribuído pelo Banco Central do Brasil às instituições participantes do STR (Sistema de Transferência de reservas). O número-código substituiu o antigo código COMPE. Todos os participantes do STR, exceto as Infraestruturas do Mercado Financeiro (IMF) e a Secretaria do Tesouro Nacional, possuem um número-código independentemente de participarem da Centralizadora da Compensação de Cheques (Compe).", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("compeCode")
  public String getCompeCode() {
    return compeCode;
  }

  public void setCompeCode(String compeCode) {
    this.compeCode = compeCode;
  }

  public AccountIdentificationData branchCode(String branchCode) {
    this.branchCode = branchCode;
    return this;
  }

  /**
   * Código da Agência detentora da conta. (Agência é a dependência destinada ao atendimento aos clientes, ao público em geral e aos associados de cooperativas de crédito, no exercício de atividades da instituição, não podendo ser móvel ou transitória)  [Restrição] Obrigatoriamente deve ser preenchido quando o campo \"type\" for diferente de conta pré-paga. 
   * @return branchCode
   */
  @Pattern(regexp = "^\\d{4}$") @Size(max = 4) 
  @Schema(name = "branchCode", example = "6272", description = "Código da Agência detentora da conta. (Agência é a dependência destinada ao atendimento aos clientes, ao público em geral e aos associados de cooperativas de crédito, no exercício de atividades da instituição, não podendo ser móvel ou transitória)  [Restrição] Obrigatoriamente deve ser preenchido quando o campo \"type\" for diferente de conta pré-paga. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("branchCode")
  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  public AccountIdentificationData number(String number) {
    this.number = number;
    return this;
  }

  /**
   * Número da conta 
   * @return number
   */
  @NotNull @Pattern(regexp = "^\\d{8,20}$") @Size(max = 20) 
  @Schema(name = "number", example = "24550245", description = "Número da conta ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("number")
  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public AccountIdentificationData checkDigit(String checkDigit) {
    this.checkDigit = checkDigit;
    return this;
  }

  /**
   * Dígito da conta 
   * @return checkDigit
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 1) 
  @Schema(name = "checkDigit", example = "4", description = "Dígito da conta ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("checkDigit")
  public String getCheckDigit() {
    return checkDigit;
  }

  public void setCheckDigit(String checkDigit) {
    this.checkDigit = checkDigit;
  }

  public AccountIdentificationData type(EnumAccountType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  @NotNull @Valid 
  @Schema(name = "type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public EnumAccountType getType() {
    return type;
  }

  public void setType(EnumAccountType type) {
    this.type = type;
  }

  public AccountIdentificationData subtype(EnumAccountSubType subtype) {
    this.subtype = subtype;
    return this;
  }

  /**
   * Get subtype
   * @return subtype
   */
  @NotNull @Valid 
  @Schema(name = "subtype", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("subtype")
  public EnumAccountSubType getSubtype() {
    return subtype;
  }

  public void setSubtype(EnumAccountSubType subtype) {
    this.subtype = subtype;
  }

  public AccountIdentificationData currency(String currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Moeda referente ao valor da transação, segundo modelo ISO-4217. p.ex. 'BRL'  Todos os saldos informados estão representados com a moeda vigente do Brasil 
   * @return currency
   */
  @NotNull @Pattern(regexp = "^(\\w{3}){1}$") @Size(max = 3) 
  @Schema(name = "currency", example = "BRL", description = "Moeda referente ao valor da transação, segundo modelo ISO-4217. p.ex. 'BRL'  Todos os saldos informados estão representados com a moeda vigente do Brasil ", requiredMode = Schema.RequiredMode.REQUIRED)
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
    AccountIdentificationData accountIdentificationData = (AccountIdentificationData) o;
    return Objects.equals(this.compeCode, accountIdentificationData.compeCode) &&
        Objects.equals(this.branchCode, accountIdentificationData.branchCode) &&
        Objects.equals(this.number, accountIdentificationData.number) &&
        Objects.equals(this.checkDigit, accountIdentificationData.checkDigit) &&
        Objects.equals(this.type, accountIdentificationData.type) &&
        Objects.equals(this.subtype, accountIdentificationData.subtype) &&
        Objects.equals(this.currency, accountIdentificationData.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(compeCode, branchCode, number, checkDigit, type, subtype, currency);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountIdentificationData {\n");
    sb.append("    compeCode: ").append(toIndentedString(compeCode)).append("\n");
    sb.append("    branchCode: ").append(toIndentedString(branchCode)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    checkDigit: ").append(toIndentedString(checkDigit)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    subtype: ").append(toIndentedString(subtype)).append("\n");
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

    private AccountIdentificationData instance;

    public Builder() {
      this(new AccountIdentificationData());
    }

    protected Builder(AccountIdentificationData instance) {
      this.instance = instance;
    }

    protected Builder copyOf(AccountIdentificationData value) { 
      this.instance.setCompeCode(value.compeCode);
      this.instance.setBranchCode(value.branchCode);
      this.instance.setNumber(value.number);
      this.instance.setCheckDigit(value.checkDigit);
      this.instance.setType(value.type);
      this.instance.setSubtype(value.subtype);
      this.instance.setCurrency(value.currency);
      return this;
    }

    public AccountIdentificationData.Builder compeCode(String compeCode) {
      this.instance.compeCode(compeCode);
      return this;
    }
    
    public AccountIdentificationData.Builder branchCode(String branchCode) {
      this.instance.branchCode(branchCode);
      return this;
    }
    
    public AccountIdentificationData.Builder number(String number) {
      this.instance.number(number);
      return this;
    }
    
    public AccountIdentificationData.Builder checkDigit(String checkDigit) {
      this.instance.checkDigit(checkDigit);
      return this;
    }
    
    public AccountIdentificationData.Builder type(EnumAccountType type) {
      this.instance.type(type);
      return this;
    }
    
    public AccountIdentificationData.Builder subtype(EnumAccountSubType subtype) {
      this.instance.subtype(subtype);
      return this;
    }
    
    public AccountIdentificationData.Builder currency(String currency) {
      this.instance.currency(currency);
      return this;
    }
    
    /**
    * returns a built AccountIdentificationData instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public AccountIdentificationData build() {
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
  public static AccountIdentificationData.Builder builder() {
    return new AccountIdentificationData.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public AccountIdentificationData.Builder toBuilder() {
    AccountIdentificationData.Builder builder = new AccountIdentificationData.Builder();
    return builder.copyOf(this);
  }

}

