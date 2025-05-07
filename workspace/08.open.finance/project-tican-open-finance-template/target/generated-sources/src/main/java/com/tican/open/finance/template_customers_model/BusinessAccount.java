package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tican.open.finance.template_customers_model.EnumAccountTypeCustomers;
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
 * BusinessAccount
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class BusinessAccount {

  private String compeCode;

  private @Nullable String branchCode;

  private String number;

  private String checkDigit;

  private EnumAccountTypeCustomers type;

  public BusinessAccount() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public BusinessAccount(String compeCode, String number, String checkDigit, EnumAccountTypeCustomers type) {
    this.compeCode = compeCode;
    this.number = number;
    this.checkDigit = checkDigit;
    this.type = type;
  }

  /**
   * Constructor with all args parameters
   */
  public BusinessAccount(String compeCode, @Nullable String branchCode, String number, String checkDigit, EnumAccountTypeCustomers type) {
      this.compeCode = compeCode;
      this.branchCode = branchCode;
      this.number = number;
      this.checkDigit = checkDigit;
      this.type = type;
  }

  public BusinessAccount compeCode(String compeCode) {
    this.compeCode = compeCode;
    return this;
  }

  /**
   * Código identificador atribuído pelo Banco Central do Brasil às instituições participantes do STR (Sistema de Transferência de reservas).O Compe (Sistema de Compensação de Cheques e Outros Papéis) é um sistema que identifica e processa as compensações bancárias. Ele é representado por um código de três dígitos que serve como identificador de bancos, sendo assim, cada instituição bancária possui um número exclusivo
   * @return compeCode
   */
  @NotNull @Pattern(regexp = "^\\d{3}$") @Size(max = 3) 
  @Schema(name = "compeCode", example = "001", description = "Código identificador atribuído pelo Banco Central do Brasil às instituições participantes do STR (Sistema de Transferência de reservas).O Compe (Sistema de Compensação de Cheques e Outros Papéis) é um sistema que identifica e processa as compensações bancárias. Ele é representado por um código de três dígitos que serve como identificador de bancos, sendo assim, cada instituição bancária possui um número exclusivo", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("compeCode")
  public String getCompeCode() {
    return compeCode;
  }

  public void setCompeCode(String compeCode) {
    this.compeCode = compeCode;
  }

  public BusinessAccount branchCode(String branchCode) {
    this.branchCode = branchCode;
    return this;
  }

  /**
   * Código da Agência detentora da conta. (Agência é a dependência destinada ao atendimento aos clientes, ao público em geral e aos associados de cooperativas de crédito, no exercício de atividades da instituição, não podendo ser móvel ou transitória)    [Restrição] Obrigatoriamente deve ser preenchido quando o campo \"type\" for diferente de conta pré paga. 
   * @return branchCode
   */
  @Pattern(regexp = "^\\d{4}$") @Size(max = 4) 
  @Schema(name = "branchCode", example = "6272", description = "Código da Agência detentora da conta. (Agência é a dependência destinada ao atendimento aos clientes, ao público em geral e aos associados de cooperativas de crédito, no exercício de atividades da instituição, não podendo ser móvel ou transitória)    [Restrição] Obrigatoriamente deve ser preenchido quando o campo \"type\" for diferente de conta pré paga. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("branchCode")
  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  public BusinessAccount number(String number) {
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

  public BusinessAccount checkDigit(String checkDigit) {
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

  public BusinessAccount type(EnumAccountTypeCustomers type) {
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
  public EnumAccountTypeCustomers getType() {
    return type;
  }

  public void setType(EnumAccountTypeCustomers type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BusinessAccount businessAccount = (BusinessAccount) o;
    return Objects.equals(this.compeCode, businessAccount.compeCode) &&
        Objects.equals(this.branchCode, businessAccount.branchCode) &&
        Objects.equals(this.number, businessAccount.number) &&
        Objects.equals(this.checkDigit, businessAccount.checkDigit) &&
        Objects.equals(this.type, businessAccount.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(compeCode, branchCode, number, checkDigit, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BusinessAccount {\n");
    sb.append("    compeCode: ").append(toIndentedString(compeCode)).append("\n");
    sb.append("    branchCode: ").append(toIndentedString(branchCode)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    checkDigit: ").append(toIndentedString(checkDigit)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

    private BusinessAccount instance;

    public Builder() {
      this(new BusinessAccount());
    }

    protected Builder(BusinessAccount instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BusinessAccount value) { 
      this.instance.setCompeCode(value.compeCode);
      this.instance.setBranchCode(value.branchCode);
      this.instance.setNumber(value.number);
      this.instance.setCheckDigit(value.checkDigit);
      this.instance.setType(value.type);
      return this;
    }

    public BusinessAccount.Builder compeCode(String compeCode) {
      this.instance.compeCode(compeCode);
      return this;
    }
    
    public BusinessAccount.Builder branchCode(String branchCode) {
      this.instance.branchCode(branchCode);
      return this;
    }
    
    public BusinessAccount.Builder number(String number) {
      this.instance.number(number);
      return this;
    }
    
    public BusinessAccount.Builder checkDigit(String checkDigit) {
      this.instance.checkDigit(checkDigit);
      return this;
    }
    
    public BusinessAccount.Builder type(EnumAccountTypeCustomers type) {
      this.instance.type(type);
      return this;
    }
    
    /**
    * returns a built BusinessAccount instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BusinessAccount build() {
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
  public static BusinessAccount.Builder builder() {
    return new BusinessAccount.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BusinessAccount.Builder toBuilder() {
    BusinessAccount.Builder builder = new BusinessAccount.Builder();
    return builder.copyOf(this);
  }

}

