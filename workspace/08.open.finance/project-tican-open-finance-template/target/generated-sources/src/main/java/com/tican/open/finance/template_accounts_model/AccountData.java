package com.tican.open.finance.template_accounts_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
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
 * AccountData
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:09.506997400-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class AccountData {

  private String brandName;

  private String companyCnpj;

  private EnumAccountType type;

  private String compeCode;

  private @Nullable String branchCode;

  private String number;

  private String checkDigit;

  private String accountId;

  public AccountData() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AccountData(String brandName, String companyCnpj, EnumAccountType type, String compeCode, String number, String checkDigit, String accountId) {
    this.brandName = brandName;
    this.companyCnpj = companyCnpj;
    this.type = type;
    this.compeCode = compeCode;
    this.number = number;
    this.checkDigit = checkDigit;
    this.accountId = accountId;
  }

  /**
   * Constructor with all args parameters
   */
  public AccountData(String brandName, String companyCnpj, EnumAccountType type, String compeCode, @Nullable String branchCode, String number, String checkDigit, String accountId) {
      this.brandName = brandName;
      this.companyCnpj = companyCnpj;
      this.type = type;
      this.compeCode = compeCode;
      this.branchCode = branchCode;
      this.number = number;
      this.checkDigit = checkDigit;
      this.accountId = accountId;
  }

  public AccountData brandName(String brandName) {
    this.brandName = brandName;
    return this;
  }

  /**
   * Nome da Marca reportada pelo participante no Open Finance. Recomenda-se utilizar, sempre que possível, o mesmo nome de marca atribuído no campo do diretório Customer Friendly Server Name (Authorisation Server).
   * @return brandName
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 80) 
  @Schema(name = "brandName", example = "Organização A", description = "Nome da Marca reportada pelo participante no Open Finance. Recomenda-se utilizar, sempre que possível, o mesmo nome de marca atribuído no campo do diretório Customer Friendly Server Name (Authorisation Server).", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("brandName")
  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public AccountData companyCnpj(String companyCnpj) {
    this.companyCnpj = companyCnpj;
    return this;
  }

  /**
   * Número completo do CNPJ da instituição responsável pelo Cadastro - o CNPJ corresponde ao número de inscrição no Cadastro de Pessoa Jurídica. Deve-se ter apenas os números do CNPJ, sem máscara
   * @return companyCnpj
   */
  @NotNull @Pattern(regexp = "^\\d{14}$") @Size(max = 14) 
  @Schema(name = "companyCnpj", example = "21128159000166", description = "Número completo do CNPJ da instituição responsável pelo Cadastro - o CNPJ corresponde ao número de inscrição no Cadastro de Pessoa Jurídica. Deve-se ter apenas os números do CNPJ, sem máscara", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("companyCnpj")
  public String getCompanyCnpj() {
    return companyCnpj;
  }

  public void setCompanyCnpj(String companyCnpj) {
    this.companyCnpj = companyCnpj;
  }

  public AccountData type(EnumAccountType type) {
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

  public AccountData compeCode(String compeCode) {
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

  public AccountData branchCode(String branchCode) {
    this.branchCode = branchCode;
    return this;
  }

  /**
   * Código da Agência detentora da conta. (Agência é a dependência destinada ao atendimento aos clientes, ao público em geral e aos associados de cooperativas de crédito, no exercício de atividades da instituição, não podendo ser móvel ou transitória)  [Restrição] Obrigatoriamente deve ser preenchido quando o campo \"type\" for diferente de CONTA_PAGAMENTO_PRE_PAGA. 
   * @return branchCode
   */
  @Pattern(regexp = "^\\d{4}$") @Size(max = 4) 
  @Schema(name = "branchCode", example = "6272", description = "Código da Agência detentora da conta. (Agência é a dependência destinada ao atendimento aos clientes, ao público em geral e aos associados de cooperativas de crédito, no exercício de atividades da instituição, não podendo ser móvel ou transitória)  [Restrição] Obrigatoriamente deve ser preenchido quando o campo \"type\" for diferente de CONTA_PAGAMENTO_PRE_PAGA. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("branchCode")
  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  public AccountData number(String number) {
    this.number = number;
    return this;
  }

  /**
   * Número da conta
   * @return number
   */
  @NotNull @Pattern(regexp = "^\\d{8,20}$") @Size(max = 20) 
  @Schema(name = "number", example = "94088392", description = "Número da conta", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("number")
  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public AccountData checkDigit(String checkDigit) {
    this.checkDigit = checkDigit;
    return this;
  }

  /**
   * Dígito da conta
   * @return checkDigit
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 1) 
  @Schema(name = "checkDigit", example = "4", description = "Dígito da conta", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("checkDigit")
  public String getCheckDigit() {
    return checkDigit;
  }

  public void setCheckDigit(String checkDigit) {
    this.checkDigit = checkDigit;
  }

  public AccountData accountId(String accountId) {
    this.accountId = accountId;
    return this;
  }

  /**
   * Identifica de forma única  a conta do cliente, mantendo as regras de imutabilidade dentro da instituição transmissora.
   * @return accountId
   */
  @NotNull @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9-]{0,99}$") @Size(min = 1, max = 100) 
  @Schema(name = "accountId", example = "92792126019929279212650822221989319252576", description = "Identifica de forma única  a conta do cliente, mantendo as regras de imutabilidade dentro da instituição transmissora.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("accountId")
  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountData accountData = (AccountData) o;
    return Objects.equals(this.brandName, accountData.brandName) &&
        Objects.equals(this.companyCnpj, accountData.companyCnpj) &&
        Objects.equals(this.type, accountData.type) &&
        Objects.equals(this.compeCode, accountData.compeCode) &&
        Objects.equals(this.branchCode, accountData.branchCode) &&
        Objects.equals(this.number, accountData.number) &&
        Objects.equals(this.checkDigit, accountData.checkDigit) &&
        Objects.equals(this.accountId, accountData.accountId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(brandName, companyCnpj, type, compeCode, branchCode, number, checkDigit, accountId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountData {\n");
    sb.append("    brandName: ").append(toIndentedString(brandName)).append("\n");
    sb.append("    companyCnpj: ").append(toIndentedString(companyCnpj)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    compeCode: ").append(toIndentedString(compeCode)).append("\n");
    sb.append("    branchCode: ").append(toIndentedString(branchCode)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    checkDigit: ").append(toIndentedString(checkDigit)).append("\n");
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
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

    private AccountData instance;

    public Builder() {
      this(new AccountData());
    }

    protected Builder(AccountData instance) {
      this.instance = instance;
    }

    protected Builder copyOf(AccountData value) { 
      this.instance.setBrandName(value.brandName);
      this.instance.setCompanyCnpj(value.companyCnpj);
      this.instance.setType(value.type);
      this.instance.setCompeCode(value.compeCode);
      this.instance.setBranchCode(value.branchCode);
      this.instance.setNumber(value.number);
      this.instance.setCheckDigit(value.checkDigit);
      this.instance.setAccountId(value.accountId);
      return this;
    }

    public AccountData.Builder brandName(String brandName) {
      this.instance.brandName(brandName);
      return this;
    }
    
    public AccountData.Builder companyCnpj(String companyCnpj) {
      this.instance.companyCnpj(companyCnpj);
      return this;
    }
    
    public AccountData.Builder type(EnumAccountType type) {
      this.instance.type(type);
      return this;
    }
    
    public AccountData.Builder compeCode(String compeCode) {
      this.instance.compeCode(compeCode);
      return this;
    }
    
    public AccountData.Builder branchCode(String branchCode) {
      this.instance.branchCode(branchCode);
      return this;
    }
    
    public AccountData.Builder number(String number) {
      this.instance.number(number);
      return this;
    }
    
    public AccountData.Builder checkDigit(String checkDigit) {
      this.instance.checkDigit(checkDigit);
      return this;
    }
    
    public AccountData.Builder accountId(String accountId) {
      this.instance.accountId(accountId);
      return this;
    }
    
    /**
    * returns a built AccountData instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public AccountData build() {
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
  public static AccountData.Builder builder() {
    return new AccountData.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public AccountData.Builder toBuilder() {
    AccountData.Builder builder = new AccountData.Builder();
    return builder.copyOf(this);
  }

}

