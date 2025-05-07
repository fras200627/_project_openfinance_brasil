package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_customers_model.EnumProductServiceType;
import com.tican.open.finance.template_customers_model.PaychecksBankLink;
import com.tican.open.finance.template_customers_model.PersonalAccount;
import com.tican.open.finance.template_customers_model.PersonalProcurator;
import com.tican.open.finance.template_customers_model.PortabilitiesReceived;
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

@Schema(name = "PersonalFinancialRelationData", description = "Objeto que reúne as informações relativas ao relacionamento do cliente junto à Instituição. Considera-se relacionamento as informações que permitam conhecer desde quando a pessoa consultada é cliente da instituição, bem como um indicador dos produtos e serviços que ela consome atualmente e seus representantes")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class PersonalFinancialRelationData {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updateDateTime;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime startDate;

  @Valid
  private List<EnumProductServiceType> productsServicesType = new ArrayList<>();

  private @Nullable String productsServicesTypeAdditionalInfo;

  @Valid
  private List<@Valid PersonalProcurator> procurators = new ArrayList<>();

  @Valid
  private List<@Valid PersonalAccount> accounts = new ArrayList<>();

  @Valid
  private List<@Valid PortabilitiesReceived> portabilitiesReceived = new ArrayList<>();

  @Valid
  private List<@Valid PaychecksBankLink> paychecksBankLink = new ArrayList<>();

  public PersonalFinancialRelationData() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PersonalFinancialRelationData(OffsetDateTime updateDateTime, OffsetDateTime startDate, List<EnumProductServiceType> productsServicesType, List<@Valid PersonalProcurator> procurators, List<@Valid PersonalAccount> accounts) {
    this.updateDateTime = updateDateTime;
    this.startDate = startDate;
    this.productsServicesType = productsServicesType;
    this.procurators = procurators;
    this.accounts = accounts;
  }

  /**
   * Constructor with all args parameters
   */
  public PersonalFinancialRelationData(OffsetDateTime updateDateTime, OffsetDateTime startDate, List<EnumProductServiceType> productsServicesType, @Nullable String productsServicesTypeAdditionalInfo, List<@Valid PersonalProcurator> procurators, List<@Valid PersonalAccount> accounts, List<@Valid PortabilitiesReceived> portabilitiesReceived, List<@Valid PaychecksBankLink> paychecksBankLink) {
      this.updateDateTime = updateDateTime;
      this.startDate = startDate;
      this.productsServicesType = productsServicesType;
      this.productsServicesTypeAdditionalInfo = productsServicesTypeAdditionalInfo;
      this.procurators = procurators;
      this.accounts = accounts;
      this.portabilitiesReceived = portabilitiesReceived;
      this.paychecksBankLink = paychecksBankLink;
  }

  public PersonalFinancialRelationData updateDateTime(OffsetDateTime updateDateTime) {
    this.updateDateTime = updateDateTime;
    return this;
  }

  /**
   * Data e hora da atualização dos campos \\<_endpoint_\\>, conforme especificação RFC-3339, formato UTC. Quando não existente uma data vinculada especificamente ao bloco, assumir a data e hora de atualização do cadastro como um todo. 
   * @return updateDateTime
   */
  @NotNull @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])T(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)Z$") @Size(max = 20) 
  @Schema(name = "updateDateTime", example = "2021-05-21T08:30Z", description = "Data e hora da atualização dos campos \\<_endpoint_\\>, conforme especificação RFC-3339, formato UTC. Quando não existente uma data vinculada especificamente ao bloco, assumir a data e hora de atualização do cadastro como um todo. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("updateDateTime")
  public OffsetDateTime getUpdateDateTime() {
    return updateDateTime;
  }

  public void setUpdateDateTime(OffsetDateTime updateDateTime) {
    this.updateDateTime = updateDateTime;
  }

  public PersonalFinancialRelationData startDate(OffsetDateTime startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Data de início de relacionamento com a Instituição Financeira. Deve trazer o menor valor entre a informação reportada ao BACEN pelo DOC 3040 e CCS.
   * @return startDate
   */
  @NotNull @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])T(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)Z$") @Size(max = 20) 
  @Schema(name = "startDate", example = "2021-05-21T08:30Z", description = "Data de início de relacionamento com a Instituição Financeira. Deve trazer o menor valor entre a informação reportada ao BACEN pelo DOC 3040 e CCS.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("startDate")
  public OffsetDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(OffsetDateTime startDate) {
    this.startDate = startDate;
  }

  public PersonalFinancialRelationData productsServicesType(List<EnumProductServiceType> productsServicesType) {
    this.productsServicesType = productsServicesType;
    return this;
  }

  public PersonalFinancialRelationData addProductsServicesTypeItem(EnumProductServiceType productsServicesTypeItem) {
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

  public PersonalFinancialRelationData productsServicesTypeAdditionalInfo(String productsServicesTypeAdditionalInfo) {
    this.productsServicesTypeAdditionalInfo = productsServicesTypeAdditionalInfo;
    return this;
  }

  /**
   * Informações adicionais do tipo de serviço. [Restrição] Campo obrigatório quando productsServicesType for 'OUTROS'. 
   * @return productsServicesTypeAdditionalInfo
   */
  @Pattern(regexp = "^[\\w\\W]*$") @Size(max = 100) 
  @Schema(name = "productsServicesTypeAdditionalInfo", example = "Informações adicionais do tipo de serviço.", description = "Informações adicionais do tipo de serviço. [Restrição] Campo obrigatório quando productsServicesType for 'OUTROS'. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("productsServicesTypeAdditionalInfo")
  public String getProductsServicesTypeAdditionalInfo() {
    return productsServicesTypeAdditionalInfo;
  }

  public void setProductsServicesTypeAdditionalInfo(String productsServicesTypeAdditionalInfo) {
    this.productsServicesTypeAdditionalInfo = productsServicesTypeAdditionalInfo;
  }

  public PersonalFinancialRelationData procurators(List<@Valid PersonalProcurator> procurators) {
    this.procurators = procurators;
    return this;
  }

  public PersonalFinancialRelationData addProcuratorsItem(PersonalProcurator procuratorsItem) {
    if (this.procurators == null) {
      this.procurators = new ArrayList<>();
    }
    this.procurators.add(procuratorsItem);
    return this;
  }

  /**
   * Lista dos representantes.  [Restrição] De preenchimento obrigatório se houver representante. 
   * @return procurators
   */
  @NotNull @Valid @Size(min = 0) 
  @Schema(name = "procurators", description = "Lista dos representantes.  [Restrição] De preenchimento obrigatório se houver representante. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("procurators")
  public List<@Valid PersonalProcurator> getProcurators() {
    return procurators;
  }

  public void setProcurators(List<@Valid PersonalProcurator> procurators) {
    this.procurators = procurators;
  }

  public PersonalFinancialRelationData accounts(List<@Valid PersonalAccount> accounts) {
    this.accounts = accounts;
    return this;
  }

  public PersonalFinancialRelationData addAccountsItem(PersonalAccount accountsItem) {
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
  public List<@Valid PersonalAccount> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<@Valid PersonalAccount> accounts) {
    this.accounts = accounts;
  }

  public PersonalFinancialRelationData portabilitiesReceived(List<@Valid PortabilitiesReceived> portabilitiesReceived) {
    this.portabilitiesReceived = portabilitiesReceived;
    return this;
  }

  public PersonalFinancialRelationData addPortabilitiesReceivedItem(PortabilitiesReceived portabilitiesReceivedItem) {
    if (this.portabilitiesReceived == null) {
      this.portabilitiesReceived = new ArrayList<>();
    }
    this.portabilitiesReceived.add(portabilitiesReceivedItem);
    return this;
  }

  /**
   * Lista de informações de empregador recebidos através de portabilidade de salário solicitada pelo cliente da transmissora à instituição detentora(s) de sua conta salário, ativos ou que já estiveram ativos,. Cada vínculo é associado a uma portabilidade de salário recebida pela transmissora.  Obs.: a portabilidade não é explicitamente encerrada, ou seja, a IF para a qual o salário foi portado não é avisado quando a conta salário se encerra ou o salário é portado para outra IF. Não é possível garantir que os dados informados sejam de uma portabilidade ativa, nem que o vínculo com o banco folha ainda exista. A transmissora terá tais informações apenas quando o pedido da portabilidade tiver sido solicitado em seus canais. 
   * @return portabilitiesReceived
   */
  @Valid @Size(min = 1) 
  @Schema(name = "portabilitiesReceived", description = "Lista de informações de empregador recebidos através de portabilidade de salário solicitada pelo cliente da transmissora à instituição detentora(s) de sua conta salário, ativos ou que já estiveram ativos,. Cada vínculo é associado a uma portabilidade de salário recebida pela transmissora.  Obs.: a portabilidade não é explicitamente encerrada, ou seja, a IF para a qual o salário foi portado não é avisado quando a conta salário se encerra ou o salário é portado para outra IF. Não é possível garantir que os dados informados sejam de uma portabilidade ativa, nem que o vínculo com o banco folha ainda exista. A transmissora terá tais informações apenas quando o pedido da portabilidade tiver sido solicitado em seus canais. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("portabilitiesReceived")
  public List<@Valid PortabilitiesReceived> getPortabilitiesReceived() {
    return portabilitiesReceived;
  }

  public void setPortabilitiesReceived(List<@Valid PortabilitiesReceived> portabilitiesReceived) {
    this.portabilitiesReceived = portabilitiesReceived;
  }

  public PersonalFinancialRelationData paychecksBankLink(List<@Valid PaychecksBankLink> paychecksBankLink) {
    this.paychecksBankLink = paychecksBankLink;
    return this;
  }

  public PersonalFinancialRelationData addPaychecksBankLinkItem(PaychecksBankLink paychecksBankLinkItem) {
    if (this.paychecksBankLink == null) {
      this.paychecksBankLink = new ArrayList<>();
    }
    this.paychecksBankLink.add(paychecksBankLinkItem);
    return this;
  }

  /**
   * Lista de informações de contas salário relacionadas com vínculos empregatícios, existentes ou que já existiram, firmados entre o cliente pessoa natural e um ou mais empregadores. Cada vínculo é associado a uma conta salário aberta mantida no banco-folha (instituição transmissora).  Obs: como empregadores antigos podem não ter solicitado o fechamento da conta salário, não é possível garantir que os dados informados sejam do empregador atual. 
   * @return paychecksBankLink
   */
  @Valid @Size(min = 1) 
  @Schema(name = "paychecksBankLink", description = "Lista de informações de contas salário relacionadas com vínculos empregatícios, existentes ou que já existiram, firmados entre o cliente pessoa natural e um ou mais empregadores. Cada vínculo é associado a uma conta salário aberta mantida no banco-folha (instituição transmissora).  Obs: como empregadores antigos podem não ter solicitado o fechamento da conta salário, não é possível garantir que os dados informados sejam do empregador atual. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("paychecksBankLink")
  public List<@Valid PaychecksBankLink> getPaychecksBankLink() {
    return paychecksBankLink;
  }

  public void setPaychecksBankLink(List<@Valid PaychecksBankLink> paychecksBankLink) {
    this.paychecksBankLink = paychecksBankLink;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonalFinancialRelationData personalFinancialRelationData = (PersonalFinancialRelationData) o;
    return Objects.equals(this.updateDateTime, personalFinancialRelationData.updateDateTime) &&
        Objects.equals(this.startDate, personalFinancialRelationData.startDate) &&
        Objects.equals(this.productsServicesType, personalFinancialRelationData.productsServicesType) &&
        Objects.equals(this.productsServicesTypeAdditionalInfo, personalFinancialRelationData.productsServicesTypeAdditionalInfo) &&
        Objects.equals(this.procurators, personalFinancialRelationData.procurators) &&
        Objects.equals(this.accounts, personalFinancialRelationData.accounts) &&
        Objects.equals(this.portabilitiesReceived, personalFinancialRelationData.portabilitiesReceived) &&
        Objects.equals(this.paychecksBankLink, personalFinancialRelationData.paychecksBankLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(updateDateTime, startDate, productsServicesType, productsServicesTypeAdditionalInfo, procurators, accounts, portabilitiesReceived, paychecksBankLink);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalFinancialRelationData {\n");
    sb.append("    updateDateTime: ").append(toIndentedString(updateDateTime)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    productsServicesType: ").append(toIndentedString(productsServicesType)).append("\n");
    sb.append("    productsServicesTypeAdditionalInfo: ").append(toIndentedString(productsServicesTypeAdditionalInfo)).append("\n");
    sb.append("    procurators: ").append(toIndentedString(procurators)).append("\n");
    sb.append("    accounts: ").append(toIndentedString(accounts)).append("\n");
    sb.append("    portabilitiesReceived: ").append(toIndentedString(portabilitiesReceived)).append("\n");
    sb.append("    paychecksBankLink: ").append(toIndentedString(paychecksBankLink)).append("\n");
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

    private PersonalFinancialRelationData instance;

    public Builder() {
      this(new PersonalFinancialRelationData());
    }

    protected Builder(PersonalFinancialRelationData instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PersonalFinancialRelationData value) { 
      this.instance.setUpdateDateTime(value.updateDateTime);
      this.instance.setStartDate(value.startDate);
      this.instance.setProductsServicesType(value.productsServicesType);
      this.instance.setProductsServicesTypeAdditionalInfo(value.productsServicesTypeAdditionalInfo);
      this.instance.setProcurators(value.procurators);
      this.instance.setAccounts(value.accounts);
      this.instance.setPortabilitiesReceived(value.portabilitiesReceived);
      this.instance.setPaychecksBankLink(value.paychecksBankLink);
      return this;
    }

    public PersonalFinancialRelationData.Builder updateDateTime(OffsetDateTime updateDateTime) {
      this.instance.updateDateTime(updateDateTime);
      return this;
    }
    
    public PersonalFinancialRelationData.Builder startDate(OffsetDateTime startDate) {
      this.instance.startDate(startDate);
      return this;
    }
    
    public PersonalFinancialRelationData.Builder productsServicesType(List<EnumProductServiceType> productsServicesType) {
      this.instance.productsServicesType(productsServicesType);
      return this;
    }
    
    public PersonalFinancialRelationData.Builder productsServicesTypeAdditionalInfo(String productsServicesTypeAdditionalInfo) {
      this.instance.productsServicesTypeAdditionalInfo(productsServicesTypeAdditionalInfo);
      return this;
    }
    
    public PersonalFinancialRelationData.Builder procurators(List<PersonalProcurator> procurators) {
      this.instance.procurators(procurators);
      return this;
    }
    
    public PersonalFinancialRelationData.Builder accounts(List<PersonalAccount> accounts) {
      this.instance.accounts(accounts);
      return this;
    }
    
    public PersonalFinancialRelationData.Builder portabilitiesReceived(List<PortabilitiesReceived> portabilitiesReceived) {
      this.instance.portabilitiesReceived(portabilitiesReceived);
      return this;
    }
    
    public PersonalFinancialRelationData.Builder paychecksBankLink(List<PaychecksBankLink> paychecksBankLink) {
      this.instance.paychecksBankLink(paychecksBankLink);
      return this;
    }
    
    /**
    * returns a built PersonalFinancialRelationData instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PersonalFinancialRelationData build() {
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
  public static PersonalFinancialRelationData.Builder builder() {
    return new PersonalFinancialRelationData.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PersonalFinancialRelationData.Builder toBuilder() {
    PersonalFinancialRelationData.Builder builder = new PersonalFinancialRelationData.Builder();
    return builder.copyOf(this);
  }

}

