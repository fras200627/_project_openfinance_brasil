package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_customers_model.BusinessAccount;
import com.tican.open.finance.template_customers_model.BusinessProcurator;
import com.tican.open.finance.template_customers_model.EnumProductServiceType;
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
 * Objeto que reúne as informações relativas ao relacionamento do cliente junto à Instituição. Considera-se relacionamento as informações que permitam conhecer desde quando a pessoa consultada é cliente da instituição, bem como um indicador dos produtos e serviços que ela consome atualmente e seus representantes
 */

@Schema(name = "BusinessFinancialRelationData", description = "Objeto que reúne as informações relativas ao relacionamento do cliente junto à Instituição. Considera-se relacionamento as informações que permitam conhecer desde quando a pessoa consultada é cliente da instituição, bem como um indicador dos produtos e serviços que ela consome atualmente e seus representantes")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class BusinessFinancialRelationData {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updateDateTime;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime startDate;

  @Valid
  private List<EnumProductServiceType> productsServicesType = new ArrayList<>();

  @Valid
  private List<@Valid BusinessProcurator> procurators = new ArrayList<>();

  @Valid
  private List<@Valid BusinessAccount> accounts = new ArrayList<>();

  public BusinessFinancialRelationData() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public BusinessFinancialRelationData(OffsetDateTime updateDateTime, OffsetDateTime startDate, List<EnumProductServiceType> productsServicesType, List<@Valid BusinessProcurator> procurators, List<@Valid BusinessAccount> accounts) {
    this.updateDateTime = updateDateTime;
    this.startDate = startDate;
    this.productsServicesType = productsServicesType;
    this.procurators = procurators;
    this.accounts = accounts;
  }

  public BusinessFinancialRelationData updateDateTime(OffsetDateTime updateDateTime) {
    this.updateDateTime = updateDateTime;
    return this;
  }

  /**
   * Data e hora da atualização dos campos \\<_endpoint_\\>, conforme especificação RFC-3339, formato UTC. Quando não existente uma data vinculada especificamente ao bloco, assumir a data e hora de atualização do cadastro como um todo. 
   * @return updateDateTime
   */
  @NotNull @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])T(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)Z$") @Size(max = 20) 
  @Schema(name = "updateDateTime", example = "2020-07-21T08:30Z", description = "Data e hora da atualização dos campos \\<_endpoint_\\>, conforme especificação RFC-3339, formato UTC. Quando não existente uma data vinculada especificamente ao bloco, assumir a data e hora de atualização do cadastro como um todo. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("updateDateTime")
  public OffsetDateTime getUpdateDateTime() {
    return updateDateTime;
  }

  public void setUpdateDateTime(OffsetDateTime updateDateTime) {
    this.updateDateTime = updateDateTime;
  }

  public BusinessFinancialRelationData startDate(OffsetDateTime startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Data de início de relacionamento com a Instituição Financeira. Deve trazer o menor valor entre a informação reportada ao BACEN pelo DOC 3040 e CCS.
   * @return startDate
   */
  @NotNull @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])T(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)Z$") @Size(max = 20) 
  @Schema(name = "startDate", example = "2020-07-21T08:30Z", description = "Data de início de relacionamento com a Instituição Financeira. Deve trazer o menor valor entre a informação reportada ao BACEN pelo DOC 3040 e CCS.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("startDate")
  public OffsetDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(OffsetDateTime startDate) {
    this.startDate = startDate;
  }

  public BusinessFinancialRelationData productsServicesType(List<EnumProductServiceType> productsServicesType) {
    this.productsServicesType = productsServicesType;
    return this;
  }

  public BusinessFinancialRelationData addProductsServicesTypeItem(EnumProductServiceType productsServicesTypeItem) {
    if (this.productsServicesType == null) {
      this.productsServicesType = new ArrayList<>();
    }
    this.productsServicesType.add(productsServicesTypeItem);
    return this;
  }

  /**
   * Get productsServicesType
   * @return productsServicesType
   */
  @NotNull @Valid @Size(min = 1, max = 12) 
  @Schema(name = "productsServicesType", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("productsServicesType")
  public List<EnumProductServiceType> getProductsServicesType() {
    return productsServicesType;
  }

  public void setProductsServicesType(List<EnumProductServiceType> productsServicesType) {
    this.productsServicesType = productsServicesType;
  }

  public BusinessFinancialRelationData procurators(List<@Valid BusinessProcurator> procurators) {
    this.procurators = procurators;
    return this;
  }

  public BusinessFinancialRelationData addProcuratorsItem(BusinessProcurator procuratorsItem) {
    if (this.procurators == null) {
      this.procurators = new ArrayList<>();
    }
    this.procurators.add(procuratorsItem);
    return this;
  }

  /**
   * Lista dos representantes. De preenchimento obrigatório se houver representante.
   * @return procurators
   */
  @NotNull @Valid @Size(min = 0) 
  @Schema(name = "procurators", description = "Lista dos representantes. De preenchimento obrigatório se houver representante.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("procurators")
  public List<@Valid BusinessProcurator> getProcurators() {
    return procurators;
  }

  public void setProcurators(List<@Valid BusinessProcurator> procurators) {
    this.procurators = procurators;
  }

  public BusinessFinancialRelationData accounts(List<@Valid BusinessAccount> accounts) {
    this.accounts = accounts;
    return this;
  }

  public BusinessFinancialRelationData addAccountsItem(BusinessAccount accountsItem) {
    if (this.accounts == null) {
      this.accounts = new ArrayList<>();
    }
    this.accounts.add(accountsItem);
    return this;
  }

  /**
   * Lista de contas depósito à vista, poupança e pagamento pré-pagas mantidas pelo cliente na instituição transmissora. 
   * @return accounts
   */
  @NotNull @Valid @Size(min = 0) 
  @Schema(name = "accounts", description = "Lista de contas depósito à vista, poupança e pagamento pré-pagas mantidas pelo cliente na instituição transmissora. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("accounts")
  public List<@Valid BusinessAccount> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<@Valid BusinessAccount> accounts) {
    this.accounts = accounts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BusinessFinancialRelationData businessFinancialRelationData = (BusinessFinancialRelationData) o;
    return Objects.equals(this.updateDateTime, businessFinancialRelationData.updateDateTime) &&
        Objects.equals(this.startDate, businessFinancialRelationData.startDate) &&
        Objects.equals(this.productsServicesType, businessFinancialRelationData.productsServicesType) &&
        Objects.equals(this.procurators, businessFinancialRelationData.procurators) &&
        Objects.equals(this.accounts, businessFinancialRelationData.accounts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(updateDateTime, startDate, productsServicesType, procurators, accounts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BusinessFinancialRelationData {\n");
    sb.append("    updateDateTime: ").append(toIndentedString(updateDateTime)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    productsServicesType: ").append(toIndentedString(productsServicesType)).append("\n");
    sb.append("    procurators: ").append(toIndentedString(procurators)).append("\n");
    sb.append("    accounts: ").append(toIndentedString(accounts)).append("\n");
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

    private BusinessFinancialRelationData instance;

    public Builder() {
      this(new BusinessFinancialRelationData());
    }

    protected Builder(BusinessFinancialRelationData instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BusinessFinancialRelationData value) { 
      this.instance.setUpdateDateTime(value.updateDateTime);
      this.instance.setStartDate(value.startDate);
      this.instance.setProductsServicesType(value.productsServicesType);
      this.instance.setProcurators(value.procurators);
      this.instance.setAccounts(value.accounts);
      return this;
    }

    public BusinessFinancialRelationData.Builder updateDateTime(OffsetDateTime updateDateTime) {
      this.instance.updateDateTime(updateDateTime);
      return this;
    }
    
    public BusinessFinancialRelationData.Builder startDate(OffsetDateTime startDate) {
      this.instance.startDate(startDate);
      return this;
    }
    
    public BusinessFinancialRelationData.Builder productsServicesType(List<EnumProductServiceType> productsServicesType) {
      this.instance.productsServicesType(productsServicesType);
      return this;
    }
    
    public BusinessFinancialRelationData.Builder procurators(List<BusinessProcurator> procurators) {
      this.instance.procurators(procurators);
      return this;
    }
    
    public BusinessFinancialRelationData.Builder accounts(List<BusinessAccount> accounts) {
      this.instance.accounts(accounts);
      return this;
    }
    
    /**
    * returns a built BusinessFinancialRelationData instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BusinessFinancialRelationData build() {
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
  public static BusinessFinancialRelationData.Builder builder() {
    return new BusinessFinancialRelationData.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BusinessFinancialRelationData.Builder toBuilder() {
    BusinessFinancialRelationData.Builder builder = new BusinessFinancialRelationData.Builder();
    return builder.copyOf(this);
  }

}

