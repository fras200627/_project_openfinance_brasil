package com.tican.open.finance.template_accounts_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tican.open.finance.template_accounts_model.AccountTransactionsDataAmount;
import com.tican.open.finance.template_accounts_model.EnumCompletedAuthorisedPaymentIndicator;
import com.tican.open.finance.template_accounts_model.EnumCreditDebitIndicator;
import com.tican.open.finance.template_accounts_model.EnumPartiePersonType;
import com.tican.open.finance.template_accounts_model.EnumTransactionTypes;
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
 * AccountTransactionsData
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:09.506997400-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class AccountTransactionsData {

  private String transactionId;

  private EnumCompletedAuthorisedPaymentIndicator completedAuthorisedPaymentType;

  private EnumCreditDebitIndicator creditDebitType;

  private String transactionName;

  private EnumTransactionTypes type;

  private AccountTransactionsDataAmount transactionAmount;

  private String transactionDateTime;

  private @Nullable String partieCnpjCpf;

  private @Nullable EnumPartiePersonType partiePersonType;

  private @Nullable String partieCompeCode;

  private @Nullable String partieBranchCode;

  private @Nullable String partieNumber;

  private @Nullable String partieCheckDigit;

  public AccountTransactionsData() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AccountTransactionsData(String transactionId, EnumCompletedAuthorisedPaymentIndicator completedAuthorisedPaymentType, EnumCreditDebitIndicator creditDebitType, String transactionName, EnumTransactionTypes type, AccountTransactionsDataAmount transactionAmount, String transactionDateTime) {
    this.transactionId = transactionId;
    this.completedAuthorisedPaymentType = completedAuthorisedPaymentType;
    this.creditDebitType = creditDebitType;
    this.transactionName = transactionName;
    this.type = type;
    this.transactionAmount = transactionAmount;
    this.transactionDateTime = transactionDateTime;
  }

  /**
   * Constructor with all args parameters
   */
  public AccountTransactionsData(String transactionId, EnumCompletedAuthorisedPaymentIndicator completedAuthorisedPaymentType, EnumCreditDebitIndicator creditDebitType, String transactionName, EnumTransactionTypes type, AccountTransactionsDataAmount transactionAmount, String transactionDateTime, @Nullable String partieCnpjCpf, @Nullable EnumPartiePersonType partiePersonType, @Nullable String partieCompeCode, @Nullable String partieBranchCode, @Nullable String partieNumber, @Nullable String partieCheckDigit) {
      this.transactionId = transactionId;
      this.completedAuthorisedPaymentType = completedAuthorisedPaymentType;
      this.creditDebitType = creditDebitType;
      this.transactionName = transactionName;
      this.type = type;
      this.transactionAmount = transactionAmount;
      this.transactionDateTime = transactionDateTime;
      this.partieCnpjCpf = partieCnpjCpf;
      this.partiePersonType = partiePersonType;
      this.partieCompeCode = partieCompeCode;
      this.partieBranchCode = partieBranchCode;
      this.partieNumber = partieNumber;
      this.partieCheckDigit = partieCheckDigit;
  }

  public AccountTransactionsData transactionId(String transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  /**
   * Código ou identificador único prestado pela instituição que mantém a conta para representar a transação individual.  O ideal é que o `transactionId` seja imutável.  No entanto, o `transactionId` deve obedecer, no mínimo, as regras de imutabilidade propostas conforme tabela “Data de imutabilidade por tipo de transação” presente nas orientações desta API. 
   * @return transactionId
   */
  @NotNull @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9-]{0,99}$") @Size(min = 1, max = 100) 
  @Schema(name = "transactionId", example = "TXpRMU9UQTROMWhZV2xSU1FUazJSMDl", description = "Código ou identificador único prestado pela instituição que mantém a conta para representar a transação individual.  O ideal é que o `transactionId` seja imutável.  No entanto, o `transactionId` deve obedecer, no mínimo, as regras de imutabilidade propostas conforme tabela “Data de imutabilidade por tipo de transação” presente nas orientações desta API. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("transactionId")
  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public AccountTransactionsData completedAuthorisedPaymentType(EnumCompletedAuthorisedPaymentIndicator completedAuthorisedPaymentType) {
    this.completedAuthorisedPaymentType = completedAuthorisedPaymentType;
    return this;
  }

  /**
   * Get completedAuthorisedPaymentType
   * @return completedAuthorisedPaymentType
   */
  @NotNull @Valid 
  @Schema(name = "completedAuthorisedPaymentType", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("completedAuthorisedPaymentType")
  public EnumCompletedAuthorisedPaymentIndicator getCompletedAuthorisedPaymentType() {
    return completedAuthorisedPaymentType;
  }

  public void setCompletedAuthorisedPaymentType(EnumCompletedAuthorisedPaymentIndicator completedAuthorisedPaymentType) {
    this.completedAuthorisedPaymentType = completedAuthorisedPaymentType;
  }

  public AccountTransactionsData creditDebitType(EnumCreditDebitIndicator creditDebitType) {
    this.creditDebitType = creditDebitType;
    return this;
  }

  /**
   * Get creditDebitType
   * @return creditDebitType
   */
  @NotNull @Valid 
  @Schema(name = "creditDebitType", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("creditDebitType")
  public EnumCreditDebitIndicator getCreditDebitType() {
    return creditDebitType;
  }

  public void setCreditDebitType(EnumCreditDebitIndicator creditDebitType) {
    this.creditDebitType = creditDebitType;
  }

  public AccountTransactionsData transactionName(String transactionName) {
    this.transactionName = transactionName;
    return this;
  }

  /**
   * Literal usada na instituição financeira para identificar a transação. A informação apresentada precisa ser a mesma utilizada nos canais eletrônicos da instituição (extrato). Em casos onde a descrição da transação é apresentada com múltiplas linhas, todas as linhas devem ser enviadas (concatenadas) neste atributo, não sendo obrigatória a concatenação das informações já enviadas em outros atributos (ex: valor, data) do mesmo endpoint.
   * @return transactionName
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 200) 
  @Schema(name = "transactionName", example = "TRANSFCWAR5TXHCX5I9IDBHML8082N8NEO30M6LNNG7ANAYIJYRM00ZBZPU8", description = "Literal usada na instituição financeira para identificar a transação. A informação apresentada precisa ser a mesma utilizada nos canais eletrônicos da instituição (extrato). Em casos onde a descrição da transação é apresentada com múltiplas linhas, todas as linhas devem ser enviadas (concatenadas) neste atributo, não sendo obrigatória a concatenação das informações já enviadas em outros atributos (ex: valor, data) do mesmo endpoint.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("transactionName")
  public String getTransactionName() {
    return transactionName;
  }

  public void setTransactionName(String transactionName) {
    this.transactionName = transactionName;
  }

  public AccountTransactionsData type(EnumTransactionTypes type) {
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
  public EnumTransactionTypes getType() {
    return type;
  }

  public void setType(EnumTransactionTypes type) {
    this.type = type;
  }

  public AccountTransactionsData transactionAmount(AccountTransactionsDataAmount transactionAmount) {
    this.transactionAmount = transactionAmount;
    return this;
  }

  /**
   * Get transactionAmount
   * @return transactionAmount
   */
  @NotNull @Valid 
  @Schema(name = "transactionAmount", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("transactionAmount")
  public AccountTransactionsDataAmount getTransactionAmount() {
    return transactionAmount;
  }

  public void setTransactionAmount(AccountTransactionsDataAmount transactionAmount) {
    this.transactionAmount = transactionAmount;
  }

  public AccountTransactionsData transactionDateTime(String transactionDateTime) {
    this.transactionDateTime = transactionDateTime;
    return this;
  }

  /**
   * Data e hora original da transação. 
   * @return transactionDateTime
   */
  @NotNull @Pattern(regexp = "(^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])T(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)\\.(?:[0-9]){3}Z$)") @Size(max = 24) 
  @Schema(name = "transactionDateTime", example = "2016-01-29T12:29:03.374Z", description = "Data e hora original da transação. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("transactionDateTime")
  public String getTransactionDateTime() {
    return transactionDateTime;
  }

  public void setTransactionDateTime(String transactionDateTime) {
    this.transactionDateTime = transactionDateTime;
  }

  public AccountTransactionsData partieCnpjCpf(String partieCnpjCpf) {
    this.partieCnpjCpf = partieCnpjCpf;
    return this;
  }

  /**
   * Identificação da pessoa envolvida na transação: pagador ou recebedor (Preencher com o CPF ou CNPJ, sem formatação). Com a IN BCB nº 371, a partir de 02/05/23, o envio das informações de identificação de contraparte tornou-se obrigatória para transações de pagamento. Para maiores detalhes, favor consultar a página `Orientações - Contas`.  [Restrição] Quando o \"type“ for preenchido com valor FOLHA_PAGAMENTO e a transmissora for a responsável pelo pagamento de salário (banco-folha), o partieCnpjCpf informado deve ser do empregador relacionado. 
   * @return partieCnpjCpf
   */
  @Pattern(regexp = "^\\d{11}$|^\\d{14}$") @Size(max = 14) 
  @Schema(name = "partieCnpjCpf", example = "43908445778", description = "Identificação da pessoa envolvida na transação: pagador ou recebedor (Preencher com o CPF ou CNPJ, sem formatação). Com a IN BCB nº 371, a partir de 02/05/23, o envio das informações de identificação de contraparte tornou-se obrigatória para transações de pagamento. Para maiores detalhes, favor consultar a página `Orientações - Contas`.  [Restrição] Quando o \"type“ for preenchido com valor FOLHA_PAGAMENTO e a transmissora for a responsável pelo pagamento de salário (banco-folha), o partieCnpjCpf informado deve ser do empregador relacionado. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("partieCnpjCpf")
  public String getPartieCnpjCpf() {
    return partieCnpjCpf;
  }

  public void setPartieCnpjCpf(String partieCnpjCpf) {
    this.partieCnpjCpf = partieCnpjCpf;
  }

  public AccountTransactionsData partiePersonType(EnumPartiePersonType partiePersonType) {
    this.partiePersonType = partiePersonType;
    return this;
  }

  /**
   * Get partiePersonType
   * @return partiePersonType
   */
  @Valid 
  @Schema(name = "partiePersonType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("partiePersonType")
  public EnumPartiePersonType getPartiePersonType() {
    return partiePersonType;
  }

  public void setPartiePersonType(EnumPartiePersonType partiePersonType) {
    this.partiePersonType = partiePersonType;
  }

  public AccountTransactionsData partieCompeCode(String partieCompeCode) {
    this.partieCompeCode = partieCompeCode;
    return this;
  }

  /**
   * Código identificador atribuído pelo Banco Central do Brasil às instituições participantes do STR (Sistema de Transferência de reservas) referente à pessoa envolvida na transação. O número-código substituiu o antigo código COMPE. Todos os participantes do STR, exceto as Infraestruturas do Mercado Financeiro (IMF) e a Secretaria do Tesouro Nacional, possuem um número-código independentemente de participarem da Centralizadora da Compensação de Cheques (Compe).
   * @return partieCompeCode
   */
  @Pattern(regexp = "^\\d{3}$") @Size(max = 3) 
  @Schema(name = "partieCompeCode", example = "001", description = "Código identificador atribuído pelo Banco Central do Brasil às instituições participantes do STR (Sistema de Transferência de reservas) referente à pessoa envolvida na transação. O número-código substituiu o antigo código COMPE. Todos os participantes do STR, exceto as Infraestruturas do Mercado Financeiro (IMF) e a Secretaria do Tesouro Nacional, possuem um número-código independentemente de participarem da Centralizadora da Compensação de Cheques (Compe).", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("partieCompeCode")
  public String getPartieCompeCode() {
    return partieCompeCode;
  }

  public void setPartieCompeCode(String partieCompeCode) {
    this.partieCompeCode = partieCompeCode;
  }

  public AccountTransactionsData partieBranchCode(String partieBranchCode) {
    this.partieBranchCode = partieBranchCode;
    return this;
  }

  /**
   * Código da Agência detentora da conta da pessoa envolvida na transação. (Agência é a dependência destinada ao atendimento aos clientes, ao público em geral e aos associados de cooperativas de crédito, no exercício de atividades da instituição, não podendo ser móvel ou transitória)
   * @return partieBranchCode
   */
  @Pattern(regexp = "^\\d{4}$") @Size(max = 4) 
  @Schema(name = "partieBranchCode", example = "6272", description = "Código da Agência detentora da conta da pessoa envolvida na transação. (Agência é a dependência destinada ao atendimento aos clientes, ao público em geral e aos associados de cooperativas de crédito, no exercício de atividades da instituição, não podendo ser móvel ou transitória)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("partieBranchCode")
  public String getPartieBranchCode() {
    return partieBranchCode;
  }

  public void setPartieBranchCode(String partieBranchCode) {
    this.partieBranchCode = partieBranchCode;
  }

  public AccountTransactionsData partieNumber(String partieNumber) {
    this.partieNumber = partieNumber;
    return this;
  }

  /**
   * Número da conta da pessoa envolvida na transação
   * @return partieNumber
   */
  @Pattern(regexp = "^\\d{8,20}$") @Size(max = 20) 
  @Schema(name = "partieNumber", example = "67890854360", description = "Número da conta da pessoa envolvida na transação", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("partieNumber")
  public String getPartieNumber() {
    return partieNumber;
  }

  public void setPartieNumber(String partieNumber) {
    this.partieNumber = partieNumber;
  }

  public AccountTransactionsData partieCheckDigit(String partieCheckDigit) {
    this.partieCheckDigit = partieCheckDigit;
    return this;
  }

  /**
   * Dígito da conta da pessoa envolvida na transação
   * @return partieCheckDigit
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 1) 
  @Schema(name = "partieCheckDigit", example = "4", description = "Dígito da conta da pessoa envolvida na transação", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("partieCheckDigit")
  public String getPartieCheckDigit() {
    return partieCheckDigit;
  }

  public void setPartieCheckDigit(String partieCheckDigit) {
    this.partieCheckDigit = partieCheckDigit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountTransactionsData accountTransactionsData = (AccountTransactionsData) o;
    return Objects.equals(this.transactionId, accountTransactionsData.transactionId) &&
        Objects.equals(this.completedAuthorisedPaymentType, accountTransactionsData.completedAuthorisedPaymentType) &&
        Objects.equals(this.creditDebitType, accountTransactionsData.creditDebitType) &&
        Objects.equals(this.transactionName, accountTransactionsData.transactionName) &&
        Objects.equals(this.type, accountTransactionsData.type) &&
        Objects.equals(this.transactionAmount, accountTransactionsData.transactionAmount) &&
        Objects.equals(this.transactionDateTime, accountTransactionsData.transactionDateTime) &&
        Objects.equals(this.partieCnpjCpf, accountTransactionsData.partieCnpjCpf) &&
        Objects.equals(this.partiePersonType, accountTransactionsData.partiePersonType) &&
        Objects.equals(this.partieCompeCode, accountTransactionsData.partieCompeCode) &&
        Objects.equals(this.partieBranchCode, accountTransactionsData.partieBranchCode) &&
        Objects.equals(this.partieNumber, accountTransactionsData.partieNumber) &&
        Objects.equals(this.partieCheckDigit, accountTransactionsData.partieCheckDigit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionId, completedAuthorisedPaymentType, creditDebitType, transactionName, type, transactionAmount, transactionDateTime, partieCnpjCpf, partiePersonType, partieCompeCode, partieBranchCode, partieNumber, partieCheckDigit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountTransactionsData {\n");
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    completedAuthorisedPaymentType: ").append(toIndentedString(completedAuthorisedPaymentType)).append("\n");
    sb.append("    creditDebitType: ").append(toIndentedString(creditDebitType)).append("\n");
    sb.append("    transactionName: ").append(toIndentedString(transactionName)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    transactionAmount: ").append(toIndentedString(transactionAmount)).append("\n");
    sb.append("    transactionDateTime: ").append(toIndentedString(transactionDateTime)).append("\n");
    sb.append("    partieCnpjCpf: ").append(toIndentedString(partieCnpjCpf)).append("\n");
    sb.append("    partiePersonType: ").append(toIndentedString(partiePersonType)).append("\n");
    sb.append("    partieCompeCode: ").append(toIndentedString(partieCompeCode)).append("\n");
    sb.append("    partieBranchCode: ").append(toIndentedString(partieBranchCode)).append("\n");
    sb.append("    partieNumber: ").append(toIndentedString(partieNumber)).append("\n");
    sb.append("    partieCheckDigit: ").append(toIndentedString(partieCheckDigit)).append("\n");
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

    private AccountTransactionsData instance;

    public Builder() {
      this(new AccountTransactionsData());
    }

    protected Builder(AccountTransactionsData instance) {
      this.instance = instance;
    }

    protected Builder copyOf(AccountTransactionsData value) { 
      this.instance.setTransactionId(value.transactionId);
      this.instance.setCompletedAuthorisedPaymentType(value.completedAuthorisedPaymentType);
      this.instance.setCreditDebitType(value.creditDebitType);
      this.instance.setTransactionName(value.transactionName);
      this.instance.setType(value.type);
      this.instance.setTransactionAmount(value.transactionAmount);
      this.instance.setTransactionDateTime(value.transactionDateTime);
      this.instance.setPartieCnpjCpf(value.partieCnpjCpf);
      this.instance.setPartiePersonType(value.partiePersonType);
      this.instance.setPartieCompeCode(value.partieCompeCode);
      this.instance.setPartieBranchCode(value.partieBranchCode);
      this.instance.setPartieNumber(value.partieNumber);
      this.instance.setPartieCheckDigit(value.partieCheckDigit);
      return this;
    }

    public AccountTransactionsData.Builder transactionId(String transactionId) {
      this.instance.transactionId(transactionId);
      return this;
    }
    
    public AccountTransactionsData.Builder completedAuthorisedPaymentType(EnumCompletedAuthorisedPaymentIndicator completedAuthorisedPaymentType) {
      this.instance.completedAuthorisedPaymentType(completedAuthorisedPaymentType);
      return this;
    }
    
    public AccountTransactionsData.Builder creditDebitType(EnumCreditDebitIndicator creditDebitType) {
      this.instance.creditDebitType(creditDebitType);
      return this;
    }
    
    public AccountTransactionsData.Builder transactionName(String transactionName) {
      this.instance.transactionName(transactionName);
      return this;
    }
    
    public AccountTransactionsData.Builder type(EnumTransactionTypes type) {
      this.instance.type(type);
      return this;
    }
    
    public AccountTransactionsData.Builder transactionAmount(AccountTransactionsDataAmount transactionAmount) {
      this.instance.transactionAmount(transactionAmount);
      return this;
    }
    
    public AccountTransactionsData.Builder transactionDateTime(String transactionDateTime) {
      this.instance.transactionDateTime(transactionDateTime);
      return this;
    }
    
    public AccountTransactionsData.Builder partieCnpjCpf(String partieCnpjCpf) {
      this.instance.partieCnpjCpf(partieCnpjCpf);
      return this;
    }
    
    public AccountTransactionsData.Builder partiePersonType(EnumPartiePersonType partiePersonType) {
      this.instance.partiePersonType(partiePersonType);
      return this;
    }
    
    public AccountTransactionsData.Builder partieCompeCode(String partieCompeCode) {
      this.instance.partieCompeCode(partieCompeCode);
      return this;
    }
    
    public AccountTransactionsData.Builder partieBranchCode(String partieBranchCode) {
      this.instance.partieBranchCode(partieBranchCode);
      return this;
    }
    
    public AccountTransactionsData.Builder partieNumber(String partieNumber) {
      this.instance.partieNumber(partieNumber);
      return this;
    }
    
    public AccountTransactionsData.Builder partieCheckDigit(String partieCheckDigit) {
      this.instance.partieCheckDigit(partieCheckDigit);
      return this;
    }
    
    /**
    * returns a built AccountTransactionsData instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public AccountTransactionsData build() {
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
  public static AccountTransactionsData.Builder builder() {
    return new AccountTransactionsData.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public AccountTransactionsData.Builder toBuilder() {
    AccountTransactionsData.Builder builder = new AccountTransactionsData.Builder();
    return builder.copyOf(this);
  }

}

