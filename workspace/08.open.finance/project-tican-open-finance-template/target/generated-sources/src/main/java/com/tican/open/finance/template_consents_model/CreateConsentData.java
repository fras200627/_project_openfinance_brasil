package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tican.open.finance.template_consents_model.BusinessEntity;
import com.tican.open.finance.template_consents_model.LoggedUser;
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
 * CreateConsentData
 */

@JsonTypeName("CreateConsent_data")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class CreateConsentData {

  private LoggedUser loggedUser;

  private @Nullable BusinessEntity businessEntity;

  /**
   * Especifica os tipos de permissões de acesso às APIs no escopo do Open Finance Brasil - Dados cadastrais e transacionais, de acordo com os blocos de consentimento fornecidos pelo usuário e necessários ao acesso a cada endpoint das APIs. Esse array não deve ter duplicidade de itens.
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

  public CreateConsentData() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateConsentData(LoggedUser loggedUser, List<PermissionsEnum> permissions) {
    this.loggedUser = loggedUser;
    this.permissions = permissions;
  }

  /**
   * Constructor with all args parameters
   */
  public CreateConsentData(LoggedUser loggedUser, @Nullable BusinessEntity businessEntity, List<PermissionsEnum> permissions, @Nullable OffsetDateTime expirationDateTime) {
      this.loggedUser = loggedUser;
      this.businessEntity = businessEntity;
      this.permissions = permissions;
      this.expirationDateTime = expirationDateTime;
  }

  public CreateConsentData loggedUser(LoggedUser loggedUser) {
    this.loggedUser = loggedUser;
    return this;
  }

  /**
   * Get loggedUser
   * @return loggedUser
   */
  @NotNull @Valid 
  @Schema(name = "loggedUser", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("loggedUser")
  public LoggedUser getLoggedUser() {
    return loggedUser;
  }

  public void setLoggedUser(LoggedUser loggedUser) {
    this.loggedUser = loggedUser;
  }

  public CreateConsentData businessEntity(BusinessEntity businessEntity) {
    this.businessEntity = businessEntity;
    return this;
  }

  /**
   * Get businessEntity
   * @return businessEntity
   */
  @Valid 
  @Schema(name = "businessEntity", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("businessEntity")
  public BusinessEntity getBusinessEntity() {
    return businessEntity;
  }

  public void setBusinessEntity(BusinessEntity businessEntity) {
    this.businessEntity = businessEntity;
  }

  public CreateConsentData permissions(List<PermissionsEnum> permissions) {
    this.permissions = permissions;
    return this;
  }

  public CreateConsentData addPermissionsItem(PermissionsEnum permissionsItem) {
    if (this.permissions == null) {
      this.permissions = new ArrayList<>();
    }
    this.permissions.add(permissionsItem);
    return this;
  }

  /**
   * Get permissions
   * @return permissions
   */
  @NotNull @Size(min = 1) 
  @Schema(name = "permissions", example = "[\"ACCOUNTS_READ\",\"ACCOUNTS_OVERDRAFT_LIMITS_READ\",\"RESOURCES_READ\"]", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("permissions")
  public List<PermissionsEnum> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<PermissionsEnum> permissions) {
    this.permissions = permissions;
  }

  public CreateConsentData expirationDateTime(OffsetDateTime expirationDateTime) {
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
    CreateConsentData createConsentData = (CreateConsentData) o;
    return Objects.equals(this.loggedUser, createConsentData.loggedUser) &&
        Objects.equals(this.businessEntity, createConsentData.businessEntity) &&
        Objects.equals(this.permissions, createConsentData.permissions) &&
        Objects.equals(this.expirationDateTime, createConsentData.expirationDateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(loggedUser, businessEntity, permissions, expirationDateTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateConsentData {\n");
    sb.append("    loggedUser: ").append(toIndentedString(loggedUser)).append("\n");
    sb.append("    businessEntity: ").append(toIndentedString(businessEntity)).append("\n");
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

    private CreateConsentData instance;

    public Builder() {
      this(new CreateConsentData());
    }

    protected Builder(CreateConsentData instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CreateConsentData value) { 
      this.instance.setLoggedUser(value.loggedUser);
      this.instance.setBusinessEntity(value.businessEntity);
      this.instance.setPermissions(value.permissions);
      this.instance.setExpirationDateTime(value.expirationDateTime);
      return this;
    }

    public CreateConsentData.Builder loggedUser(LoggedUser loggedUser) {
      this.instance.loggedUser(loggedUser);
      return this;
    }
    
    public CreateConsentData.Builder businessEntity(BusinessEntity businessEntity) {
      this.instance.businessEntity(businessEntity);
      return this;
    }
    
    public CreateConsentData.Builder permissions(List<PermissionsEnum> permissions) {
      this.instance.permissions(permissions);
      return this;
    }
    
    public CreateConsentData.Builder expirationDateTime(OffsetDateTime expirationDateTime) {
      this.instance.expirationDateTime(expirationDateTime);
      return this;
    }
    
    /**
    * returns a built CreateConsentData instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CreateConsentData build() {
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
  public static CreateConsentData.Builder builder() {
    return new CreateConsentData.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CreateConsentData.Builder toBuilder() {
    CreateConsentData.Builder builder = new CreateConsentData.Builder();
    return builder.copyOf(this);
  }

}

