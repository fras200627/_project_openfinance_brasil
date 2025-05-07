package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
 * ResponseConsentData
 */

@JsonTypeName("ResponseConsent_data")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseConsentData {

  private String consentId;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime creationDateTime;

  /**
   * Estado atual do consentimento cadastrado.
   */
  public enum StatusEnum {
    AUTHORISED("AUTHORISED"),
    
    AWAITING_AUTHORISATION("AWAITING_AUTHORISATION"),
    
    REJECTED("REJECTED");

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

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime statusUpdateDateTime;

  /**
   * Gets or Sets permissions
   */
  public enum PermissionsEnum {
    ACCOUNTS_READ("ACCOUNTS_READ"),
    
    ACCOUNTS_BALANCES_READ("ACCOUNTS_BALANCES_READ"),
    
    ACCOUNTS_TRANSACTIONS_READ("ACCOUNTS_TRANSACTIONS_READ"),
    
    ACCOUNTS_OVERDRAFT_LIMITS_READ("ACCOUNTS_OVERDRAFT_LIMITS_READ"),
    
    CREDIT_CARDS_ACCOUNTS_READ("CREDIT_CARDS_ACCOUNTS_READ"),
    
    CREDIT_CARDS_ACCOUNTS_BILLS_READ("CREDIT_CARDS_ACCOUNTS_BILLS_READ"),
    
    CREDIT_CARDS_ACCOUNTS_BILLS_TRANSACTIONS_READ("CREDIT_CARDS_ACCOUNTS_BILLS_TRANSACTIONS_READ"),
    
    CREDIT_CARDS_ACCOUNTS_LIMITS_READ("CREDIT_CARDS_ACCOUNTS_LIMITS_READ"),
    
    CREDIT_CARDS_ACCOUNTS_TRANSACTIONS_READ("CREDIT_CARDS_ACCOUNTS_TRANSACTIONS_READ"),
    
    CUSTOMERS_PERSONAL_IDENTIFICATIONS_READ("CUSTOMERS_PERSONAL_IDENTIFICATIONS_READ"),
    
    CUSTOMERS_PERSONAL_ADITTIONALINFO_READ("CUSTOMERS_PERSONAL_ADITTIONALINFO_READ"),
    
    CUSTOMERS_BUSINESS_IDENTIFICATIONS_READ("CUSTOMERS_BUSINESS_IDENTIFICATIONS_READ"),
    
    CUSTOMERS_BUSINESS_ADITTIONALINFO_READ("CUSTOMERS_BUSINESS_ADITTIONALINFO_READ"),
    
    FINANCINGS_READ("FINANCINGS_READ"),
    
    FINANCINGS_SCHEDULED_INSTALMENTS_READ("FINANCINGS_SCHEDULED_INSTALMENTS_READ"),
    
    FINANCINGS_PAYMENTS_READ("FINANCINGS_PAYMENTS_READ"),
    
    FINANCINGS_WARRANTIES_READ("FINANCINGS_WARRANTIES_READ"),
    
    INVOICE_FINANCINGS_READ("INVOICE_FINANCINGS_READ"),
    
    INVOICE_FINANCINGS_SCHEDULED_INSTALMENTS_READ("INVOICE_FINANCINGS_SCHEDULED_INSTALMENTS_READ"),
    
    INVOICE_FINANCINGS_PAYMENTS_READ("INVOICE_FINANCINGS_PAYMENTS_READ"),
    
    INVOICE_FINANCINGS_WARRANTIES_READ("INVOICE_FINANCINGS_WARRANTIES_READ"),
    
    LOANS_READ("LOANS_READ"),
    
    LOANS_SCHEDULED_INSTALMENTS_READ("LOANS_SCHEDULED_INSTALMENTS_READ"),
    
    LOANS_PAYMENTS_READ("LOANS_PAYMENTS_READ"),
    
    LOANS_WARRANTIES_READ("LOANS_WARRANTIES_READ"),
    
    UNARRANGED_ACCOUNTS_OVERDRAFT_READ("UNARRANGED_ACCOUNTS_OVERDRAFT_READ"),
    
    UNARRANGED_ACCOUNTS_OVERDRAFT_SCHEDULED_INSTALMENTS_READ("UNARRANGED_ACCOUNTS_OVERDRAFT_SCHEDULED_INSTALMENTS_READ"),
    
    UNARRANGED_ACCOUNTS_OVERDRAFT_PAYMENTS_READ("UNARRANGED_ACCOUNTS_OVERDRAFT_PAYMENTS_READ"),
    
    UNARRANGED_ACCOUNTS_OVERDRAFT_WARRANTIES_READ("UNARRANGED_ACCOUNTS_OVERDRAFT_WARRANTIES_READ"),
    
    RESOURCES_READ("RESOURCES_READ"),
    
    BANK_FIXED_INCOMES_READ("BANK_FIXED_INCOMES_READ"),
    
    CREDIT_FIXED_INCOMES_READ("CREDIT_FIXED_INCOMES_READ"),
    
    FUNDS_READ("FUNDS_READ"),
    
    VARIABLE_INCOMES_READ("VARIABLE_INCOMES_READ"),
    
    TREASURE_TITLES_READ("TREASURE_TITLES_READ"),
    
    EXCHANGES_READ("EXCHANGES_READ");

    private String value;

    PermissionsEnum(String value) {
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
    public static PermissionsEnum fromValue(String value) {
      for (PermissionsEnum b : PermissionsEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @Valid
  private List<PermissionsEnum> permissions = new ArrayList<>();

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime expirationDateTime;

  public ResponseConsentData() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ResponseConsentData(String consentId, OffsetDateTime creationDateTime, StatusEnum status, OffsetDateTime statusUpdateDateTime, List<PermissionsEnum> permissions) {
    this.consentId = consentId;
    this.creationDateTime = creationDateTime;
    this.status = status;
    this.statusUpdateDateTime = statusUpdateDateTime;
    this.permissions = permissions;
  }

  /**
   * Constructor with all args parameters
   */
  public ResponseConsentData(String consentId, OffsetDateTime creationDateTime, StatusEnum status, OffsetDateTime statusUpdateDateTime, List<PermissionsEnum> permissions, @Nullable OffsetDateTime expirationDateTime) {
      this.consentId = consentId;
      this.creationDateTime = creationDateTime;
      this.status = status;
      this.statusUpdateDateTime = statusUpdateDateTime;
      this.permissions = permissions;
      this.expirationDateTime = expirationDateTime;
  }

  public ResponseConsentData consentId(String consentId) {
    this.consentId = consentId;
    return this;
  }

  /**
   * O consentId é o identificador único do consentimento e deverá ser um URN - Uniform Resource Name.   Um URN, conforme definido na [RFC8141](https://tools.ietf.org/html/rfc8141) é um Uniform Resource  Identifier - URI - que é atribuído sob o URI scheme \"urn\" e um namespace URN específico, com a intenção de que o URN  seja um identificador de recurso persistente e independente da localização.   Considerando a string urn:bancoex:C1DD33123 como exemplo para consentId temos: - o namespace(urn) - o identificador associado ao namespace da instituição transnmissora (bancoex)  - o identificador específico dentro do namespace (C1DD33123).   Informações mais detalhadas sobre a construção de namespaces devem ser consultadas na [RFC8141](https://tools.ietf.org/html/rfc8141). 
   * @return consentId
   */
  @NotNull @Pattern(regexp = "^urn:[a-zA-Z0-9][a-zA-Z0-9-]{0,31}:[a-zA-Z0-9()+,\\-.:=@;$_!*'%/?#]+$") @Size(max = 256) 
  @Schema(name = "consentId", example = "urn:bancoex:C1DD33123", description = "O consentId é o identificador único do consentimento e deverá ser um URN - Uniform Resource Name.   Um URN, conforme definido na [RFC8141](https://tools.ietf.org/html/rfc8141) é um Uniform Resource  Identifier - URI - que é atribuído sob o URI scheme \"urn\" e um namespace URN específico, com a intenção de que o URN  seja um identificador de recurso persistente e independente da localização.   Considerando a string urn:bancoex:C1DD33123 como exemplo para consentId temos: - o namespace(urn) - o identificador associado ao namespace da instituição transnmissora (bancoex)  - o identificador específico dentro do namespace (C1DD33123).   Informações mais detalhadas sobre a construção de namespaces devem ser consultadas na [RFC8141](https://tools.ietf.org/html/rfc8141). ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("consentId")
  public String getConsentId() {
    return consentId;
  }

  public void setConsentId(String consentId) {
    this.consentId = consentId;
  }

  public ResponseConsentData creationDateTime(OffsetDateTime creationDateTime) {
    this.creationDateTime = creationDateTime;
    return this;
  }

  /**
   * Data e hora em que o recurso foi criado. Uma string com data e hora conforme especificação RFC-3339, sempre com a utilização de timezone UTC(UTC time format).
   * @return creationDateTime
   */
  @NotNull @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])T(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)Z$") @Size(max = 20) 
  @Schema(name = "creationDateTime", example = "2021-05-21T08:30Z", description = "Data e hora em que o recurso foi criado. Uma string com data e hora conforme especificação RFC-3339, sempre com a utilização de timezone UTC(UTC time format).", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("creationDateTime")
  public OffsetDateTime getCreationDateTime() {
    return creationDateTime;
  }

  public void setCreationDateTime(OffsetDateTime creationDateTime) {
    this.creationDateTime = creationDateTime;
  }

  public ResponseConsentData status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Estado atual do consentimento cadastrado.
   * @return status
   */
  @NotNull 
  @Schema(name = "status", example = "AWAITING_AUTHORISATION", description = "Estado atual do consentimento cadastrado.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public ResponseConsentData statusUpdateDateTime(OffsetDateTime statusUpdateDateTime) {
    this.statusUpdateDateTime = statusUpdateDateTime;
    return this;
  }

  /**
   * Data e hora em que o recurso foi atualizado. Uma string com data e hora conforme especificação RFC-3339, sempre com a utilização de timezone UTC(UTC time format).
   * @return statusUpdateDateTime
   */
  @NotNull @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])T(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)Z$") @Size(max = 20) 
  @Schema(name = "statusUpdateDateTime", example = "2021-05-21T08:30Z", description = "Data e hora em que o recurso foi atualizado. Uma string com data e hora conforme especificação RFC-3339, sempre com a utilização de timezone UTC(UTC time format).", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("statusUpdateDateTime")
  public OffsetDateTime getStatusUpdateDateTime() {
    return statusUpdateDateTime;
  }

  public void setStatusUpdateDateTime(OffsetDateTime statusUpdateDateTime) {
    this.statusUpdateDateTime = statusUpdateDateTime;
  }

  public ResponseConsentData permissions(List<PermissionsEnum> permissions) {
    this.permissions = permissions;
    return this;
  }

  public ResponseConsentData addPermissionsItem(PermissionsEnum permissionsItem) {
    if (this.permissions == null) {
      this.permissions = new ArrayList<>();
    }
    this.permissions.add(permissionsItem);
    return this;
  }

  /**
   * Especifica os tipos de permissões de acesso às APIs no escopo do Open Finance Brasil - Dados cadastrais e transacionais, de acordo com os blocos de consentimento fornecidos pelo usuário e necessários ao acesso a cada endpoint das APIs. Esse array não deve ter duplicidade de itens.
   * @return permissions
   */
  @NotNull @Size(min = 1) 
  @Schema(name = "permissions", example = "[\"ACCOUNTS_READ\",\"ACCOUNTS_OVERDRAFT_LIMITS_READ\",\"RESOURCES_READ\"]", description = "Especifica os tipos de permissões de acesso às APIs no escopo do Open Finance Brasil - Dados cadastrais e transacionais, de acordo com os blocos de consentimento fornecidos pelo usuário e necessários ao acesso a cada endpoint das APIs. Esse array não deve ter duplicidade de itens.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("permissions")
  public List<PermissionsEnum> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<PermissionsEnum> permissions) {
    this.permissions = permissions;
  }

  public ResponseConsentData expirationDateTime(OffsetDateTime expirationDateTime) {
    this.expirationDateTime = expirationDateTime;
    return this;
  }

  /**
   * Data e hora de expiração da permissão. Reflete a data limite de validade do consentimento. Uma string com data e hora conforme especificação RFC-3339, sempre com a utilização de timezone UTC (UTC time format).  [Restrição] De preenchimento obrigatório nos casos em que houver validade determinada. Em casos de consentimento com prazo indeterminado o campo não deve ser enviado.  Quando preenchido, o valor do campo não pode ultrapassar 12 meses. 
   * @return expirationDateTime
   */
  @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])T(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)Z$") @Size(max = 20) 
  @Schema(name = "expirationDateTime", example = "2021-05-21T08:30Z", description = "Data e hora de expiração da permissão. Reflete a data limite de validade do consentimento. Uma string com data e hora conforme especificação RFC-3339, sempre com a utilização de timezone UTC (UTC time format).  [Restrição] De preenchimento obrigatório nos casos em que houver validade determinada. Em casos de consentimento com prazo indeterminado o campo não deve ser enviado.  Quando preenchido, o valor do campo não pode ultrapassar 12 meses. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("expirationDateTime")
  public OffsetDateTime getExpirationDateTime() {
    return expirationDateTime;
  }

  public void setExpirationDateTime(OffsetDateTime expirationDateTime) {
    this.expirationDateTime = expirationDateTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseConsentData responseConsentData = (ResponseConsentData) o;
    return Objects.equals(this.consentId, responseConsentData.consentId) &&
        Objects.equals(this.creationDateTime, responseConsentData.creationDateTime) &&
        Objects.equals(this.status, responseConsentData.status) &&
        Objects.equals(this.statusUpdateDateTime, responseConsentData.statusUpdateDateTime) &&
        Objects.equals(this.permissions, responseConsentData.permissions) &&
        Objects.equals(this.expirationDateTime, responseConsentData.expirationDateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(consentId, creationDateTime, status, statusUpdateDateTime, permissions, expirationDateTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseConsentData {\n");
    sb.append("    consentId: ").append(toIndentedString(consentId)).append("\n");
    sb.append("    creationDateTime: ").append(toIndentedString(creationDateTime)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    statusUpdateDateTime: ").append(toIndentedString(statusUpdateDateTime)).append("\n");
    sb.append("    permissions: ").append(toIndentedString(permissions)).append("\n");
    sb.append("    expirationDateTime: ").append(toIndentedString(expirationDateTime)).append("\n");
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

    private ResponseConsentData instance;

    public Builder() {
      this(new ResponseConsentData());
    }

    protected Builder(ResponseConsentData instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseConsentData value) { 
      this.instance.setConsentId(value.consentId);
      this.instance.setCreationDateTime(value.creationDateTime);
      this.instance.setStatus(value.status);
      this.instance.setStatusUpdateDateTime(value.statusUpdateDateTime);
      this.instance.setPermissions(value.permissions);
      this.instance.setExpirationDateTime(value.expirationDateTime);
      return this;
    }

    public ResponseConsentData.Builder consentId(String consentId) {
      this.instance.consentId(consentId);
      return this;
    }
    
    public ResponseConsentData.Builder creationDateTime(OffsetDateTime creationDateTime) {
      this.instance.creationDateTime(creationDateTime);
      return this;
    }
    
    public ResponseConsentData.Builder status(StatusEnum status) {
      this.instance.status(status);
      return this;
    }
    
    public ResponseConsentData.Builder statusUpdateDateTime(OffsetDateTime statusUpdateDateTime) {
      this.instance.statusUpdateDateTime(statusUpdateDateTime);
      return this;
    }
    
    public ResponseConsentData.Builder permissions(List<PermissionsEnum> permissions) {
      this.instance.permissions(permissions);
      return this;
    }
    
    public ResponseConsentData.Builder expirationDateTime(OffsetDateTime expirationDateTime) {
      this.instance.expirationDateTime(expirationDateTime);
      return this;
    }
    
    /**
    * returns a built ResponseConsentData instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseConsentData build() {
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
  public static ResponseConsentData.Builder builder() {
    return new ResponseConsentData.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseConsentData.Builder toBuilder() {
    ResponseConsentData.Builder builder = new ResponseConsentData.Builder();
    return builder.copyOf(this);
  }

}

