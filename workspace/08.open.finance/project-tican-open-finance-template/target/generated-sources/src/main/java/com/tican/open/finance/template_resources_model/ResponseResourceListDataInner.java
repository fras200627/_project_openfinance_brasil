package com.tican.open.finance.template_resources_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
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
 * ResponseResourceListDataInner
 */

@JsonTypeName("ResponseResourceList_data_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:04.924183300-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseResourceListDataInner {

  private String resourceId;

  /**
   * Tipo de recurso (vide Enum):   - Account - Conta de depósito à vista, poupança ou pagamento pré-paga   - Credit Card Account - Conta de pagamento pós-paga (Cartão de Crédito)   - Loan - Empréstimo   - Financing - Financiamento   - Unarranged Account Overdraft - Cheque Especial   - Invoice Financing - Financiamento de Fatura   - Bank Fixed Income - Renda Fixa Bancária   - Credit Fixed Income - Renda Fixa Crédito   - Variabel Income - Renda Variável   - Treasure Title - Título do Tesouro Direto   - Fund - Fundo de Investimento   - Exchange - Câmbio 
   */
  public enum TypeEnum {
    ACCOUNT("ACCOUNT"),
    
    CREDIT_CARD_ACCOUNT("CREDIT_CARD_ACCOUNT"),
    
    LOAN("LOAN"),
    
    FINANCING("FINANCING"),
    
    UNARRANGED_ACCOUNT_OVERDRAFT("UNARRANGED_ACCOUNT_OVERDRAFT"),
    
    INVOICE_FINANCING("INVOICE_FINANCING"),
    
    BANK_FIXED_INCOME("BANK_FIXED_INCOME"),
    
    CREDIT_FIXED_INCOME("CREDIT_FIXED_INCOME"),
    
    VARIABLE_INCOME("VARIABLE_INCOME"),
    
    TREASURE_TITLE("TREASURE_TITLE"),
    
    FUND("FUND"),
    
    EXCHANGE("EXCHANGE");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private TypeEnum type;

  /**
   * Tipo de status de recurso (vide Enum): Available - Disponível Unavailable - Indisponível Temporarily Unavailable - Temporariamente Indisponível Pending Authorisation - Pendente de Autorização 
   */
  public enum StatusEnum {
    AVAILABLE("AVAILABLE"),
    
    UNAVAILABLE("UNAVAILABLE"),
    
    TEMPORARILY_UNAVAILABLE("TEMPORARILY_UNAVAILABLE"),
    
    PENDING_AUTHORISATION("PENDING_AUTHORISATION");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private StatusEnum status;

  public ResponseResourceListDataInner() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public ResponseResourceListDataInner(String resourceId, TypeEnum type, StatusEnum status) {
    this.resourceId = resourceId;
    this.type = type;
    this.status = status;
  }

  public ResponseResourceListDataInner resourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  /**
   * Identifica o recurso reportado pelo participante do Open Finance, no caso de: - Contas de depósito à vista, de poupança ou de pagamento pré-paga : corresponde ao accountId; - Conta de pagamento pós-paga: corresponde ao  creditCardAccountId; - Empréstimos, Financiamentos, Direitos creditórios descontados e Adiantamento a depositantes: corresponde ao contractId - Renda Fixa Bancária, Renda Fixa Crédito, Renda Variável, Título do Tesouro Direto e Fundo de Investimento: corresponde ao investmentId; - Câmbio: corresponde ao operationId. 
   * @return resourceId
   */
  @NotNull @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9-]{0,99}$") @Size(min = 1, max = 100) 
  @Schema(name = "resourceId", example = "25cac914-d8ae-6789-b215-650a6215820d", description = "Identifica o recurso reportado pelo participante do Open Finance, no caso de: - Contas de depósito à vista, de poupança ou de pagamento pré-paga : corresponde ao accountId; - Conta de pagamento pós-paga: corresponde ao  creditCardAccountId; - Empréstimos, Financiamentos, Direitos creditórios descontados e Adiantamento a depositantes: corresponde ao contractId - Renda Fixa Bancária, Renda Fixa Crédito, Renda Variável, Título do Tesouro Direto e Fundo de Investimento: corresponde ao investmentId; - Câmbio: corresponde ao operationId. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("resourceId")
  public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  public ResponseResourceListDataInner type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Tipo de recurso (vide Enum):   - Account - Conta de depósito à vista, poupança ou pagamento pré-paga   - Credit Card Account - Conta de pagamento pós-paga (Cartão de Crédito)   - Loan - Empréstimo   - Financing - Financiamento   - Unarranged Account Overdraft - Cheque Especial   - Invoice Financing - Financiamento de Fatura   - Bank Fixed Income - Renda Fixa Bancária   - Credit Fixed Income - Renda Fixa Crédito   - Variabel Income - Renda Variável   - Treasure Title - Título do Tesouro Direto   - Fund - Fundo de Investimento   - Exchange - Câmbio 
   * @return type
   */
  @NotNull 
  @Schema(name = "type", example = "ACCOUNT", description = "Tipo de recurso (vide Enum):   - Account - Conta de depósito à vista, poupança ou pagamento pré-paga   - Credit Card Account - Conta de pagamento pós-paga (Cartão de Crédito)   - Loan - Empréstimo   - Financing - Financiamento   - Unarranged Account Overdraft - Cheque Especial   - Invoice Financing - Financiamento de Fatura   - Bank Fixed Income - Renda Fixa Bancária   - Credit Fixed Income - Renda Fixa Crédito   - Variabel Income - Renda Variável   - Treasure Title - Título do Tesouro Direto   - Fund - Fundo de Investimento   - Exchange - Câmbio ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public ResponseResourceListDataInner status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Tipo de status de recurso (vide Enum): Available - Disponível Unavailable - Indisponível Temporarily Unavailable - Temporariamente Indisponível Pending Authorisation - Pendente de Autorização 
   * @return status
   */
  @NotNull 
  @Schema(name = "status", example = "AVAILABLE", description = "Tipo de status de recurso (vide Enum): Available - Disponível Unavailable - Indisponível Temporarily Unavailable - Temporariamente Indisponível Pending Authorisation - Pendente de Autorização ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseResourceListDataInner responseResourceListDataInner = (ResponseResourceListDataInner) o;
    return Objects.equals(this.resourceId, responseResourceListDataInner.resourceId) &&
        Objects.equals(this.type, responseResourceListDataInner.type) &&
        Objects.equals(this.status, responseResourceListDataInner.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resourceId, type, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseResourceListDataInner {\n");
    sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

    private ResponseResourceListDataInner instance;

    public Builder() {
      this(new ResponseResourceListDataInner());
    }

    protected Builder(ResponseResourceListDataInner instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseResourceListDataInner value) { 
      this.instance.setResourceId(value.resourceId);
      this.instance.setType(value.type);
      this.instance.setStatus(value.status);
      return this;
    }

    public ResponseResourceListDataInner.Builder resourceId(String resourceId) {
      this.instance.resourceId(resourceId);
      return this;
    }
    
    public ResponseResourceListDataInner.Builder type(TypeEnum type) {
      this.instance.type(type);
      return this;
    }
    
    public ResponseResourceListDataInner.Builder status(StatusEnum status) {
      this.instance.status(status);
      return this;
    }
    
    /**
    * returns a built ResponseResourceListDataInner instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseResourceListDataInner build() {
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
  public static ResponseResourceListDataInner.Builder builder() {
    return new ResponseResourceListDataInner.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseResourceListDataInner.Builder toBuilder() {
    ResponseResourceListDataInner.Builder builder = new ResponseResourceListDataInner.Builder();
    return builder.copyOf(this);
  }

}

