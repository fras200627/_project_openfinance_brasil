package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_customers_model.BusinessContacts;
import com.tican.open.finance.template_customers_model.BusinessOtherDocument;
import com.tican.open.finance.template_customers_model.PartiesParticipation;
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
 * Conjunto de informações relativas a Identificação ou seja a ação e o efeito de identificar de forma única a pessoa jurídica através de seus dados cadastrais
 */

@Schema(name = "BusinessIdentificationData", description = "Conjunto de informações relativas a Identificação ou seja a ação e o efeito de identificar de forma única a pessoa jurídica através de seus dados cadastrais")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class BusinessIdentificationData {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updateDateTime;

  private String businessId;

  private String brandName;

  private String companyName;

  private @Nullable String tradeName;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime incorporationDate;

  private String cnpjNumber;

  @Valid
  private List<@Pattern(regexp = "^\\d{14}$")@Size(max = 14)String> companiesCnpj = new ArrayList<>();

  @Valid
  private List<@Valid BusinessOtherDocument> otherDocuments = new ArrayList<>();

  @Valid
  private List<@Valid PartiesParticipation> parties = new ArrayList<>();

  private BusinessContacts contacts;

  public BusinessIdentificationData() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public BusinessIdentificationData(OffsetDateTime updateDateTime, String businessId, String brandName, String companyName, OffsetDateTime incorporationDate, String cnpjNumber, List<@Pattern(regexp = "^\\d{14}$")@Size(max = 14)String> companiesCnpj, List<@Valid PartiesParticipation> parties, BusinessContacts contacts) {
    this.updateDateTime = updateDateTime;
    this.businessId = businessId;
    this.brandName = brandName;
    this.companyName = companyName;
    this.incorporationDate = incorporationDate;
    this.cnpjNumber = cnpjNumber;
    this.companiesCnpj = companiesCnpj;
    this.parties = parties;
    this.contacts = contacts;
  }

  /**
   * Constructor with all args parameters
   */
  public BusinessIdentificationData(OffsetDateTime updateDateTime, String businessId, String brandName, String companyName, @Nullable String tradeName, OffsetDateTime incorporationDate, String cnpjNumber, List<@Pattern(regexp = "^\\d{14}$")@Size(max = 14)String> companiesCnpj, List<@Valid BusinessOtherDocument> otherDocuments, List<@Valid PartiesParticipation> parties, BusinessContacts contacts) {
      this.updateDateTime = updateDateTime;
      this.businessId = businessId;
      this.brandName = brandName;
      this.companyName = companyName;
      this.tradeName = tradeName;
      this.incorporationDate = incorporationDate;
      this.cnpjNumber = cnpjNumber;
      this.companiesCnpj = companiesCnpj;
      this.otherDocuments = otherDocuments;
      this.parties = parties;
      this.contacts = contacts;
  }

  public BusinessIdentificationData updateDateTime(OffsetDateTime updateDateTime) {
    this.updateDateTime = updateDateTime;
    return this;
  }

  /**
   * Data e hora da atualização dos campos \\<_endpoint_\\>, conforme especificação RFC-3339, formato UTC. Quando não existente uma data vinculada especificamente ao bloco, assumir a data e hora de atualização do cadastro como um todo. 
   * @return updateDateTime
   */
  @NotNull @Valid @Size(max = 20) 
  @Schema(name = "updateDateTime", example = "2021-05-21T08:30Z", description = "Data e hora da atualização dos campos \\<_endpoint_\\>, conforme especificação RFC-3339, formato UTC. Quando não existente uma data vinculada especificamente ao bloco, assumir a data e hora de atualização do cadastro como um todo. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("updateDateTime")
  public OffsetDateTime getUpdateDateTime() {
    return updateDateTime;
  }

  public void setUpdateDateTime(OffsetDateTime updateDateTime) {
    this.updateDateTime = updateDateTime;
  }

  public BusinessIdentificationData businessId(String businessId) {
    this.businessId = businessId;
    return this;
  }

  /**
   * Um identificador único e imutável usado para identificar o recurso cliente pessoa jurídica. Este identificador não tem significado para o cliente que deu o consentimento
   * @return businessId
   */
  @NotNull @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9-]{0,99}$") @Size(min = 1, max = 100) 
  @Schema(name = "businessId", example = "578-psd-71md6971kjh-2d414", description = "Um identificador único e imutável usado para identificar o recurso cliente pessoa jurídica. Este identificador não tem significado para o cliente que deu o consentimento", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("businessId")
  public String getBusinessId() {
    return businessId;
  }

  public void setBusinessId(String businessId) {
    this.businessId = businessId;
  }

  public BusinessIdentificationData brandName(String brandName) {
    this.brandName = brandName;
    return this;
  }

  /**
   * Nome da Marca reportada pelo participante no Open Finance. Recomenda-se utilizar, sempre que possível, o mesmo nome de marca atribuído no campo do diretório Customer Friendly Server Name (Authorisation Server). 
   * @return brandName
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 80) 
  @Schema(name = "brandName", example = "Organização A", description = "Nome da Marca reportada pelo participante no Open Finance. Recomenda-se utilizar, sempre que possível, o mesmo nome de marca atribuído no campo do diretório Customer Friendly Server Name (Authorisation Server). ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("brandName")
  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public BusinessIdentificationData companyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

  /**
   * Razão social da empresa consultada é o termo registrado sob o qual uma pessoa jurídica (PJ) se individualiza e exerce suas atividades. Também pode ser chamada por denominação social ou firma empresarial
   * @return companyName
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 70) 
  @Schema(name = "companyName", example = "Luiza e Benjamin Assessoria Jurídica Ltda", description = "Razão social da empresa consultada é o termo registrado sob o qual uma pessoa jurídica (PJ) se individualiza e exerce suas atividades. Também pode ser chamada por denominação social ou firma empresarial", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("companyName")
  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public BusinessIdentificationData tradeName(String tradeName) {
    this.tradeName = tradeName;
    return this;
  }

  /**
   * Nome fantasia da pessoa jurídica, se houver. (É o nome popular da empresa, utilizado para divulgação da empresa e melhor fixação com o público). De preenchimento obrigatório se houver
   * @return tradeName
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 70) 
  @Schema(name = "tradeName", example = "Mundo da Eletronica", description = "Nome fantasia da pessoa jurídica, se houver. (É o nome popular da empresa, utilizado para divulgação da empresa e melhor fixação com o público). De preenchimento obrigatório se houver", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tradeName")
  public String getTradeName() {
    return tradeName;
  }

  public void setTradeName(String tradeName) {
    this.tradeName = tradeName;
  }

  public BusinessIdentificationData incorporationDate(OffsetDateTime incorporationDate) {
    this.incorporationDate = incorporationDate;
    return this;
  }

  /**
   * Data de constituição da empresa, conforme especificação RFC-3339.
   * @return incorporationDate
   */
  @NotNull @Valid @Size(max = 20) 
  @Schema(name = "incorporationDate", example = "2021-05-21T08:30Z", description = "Data de constituição da empresa, conforme especificação RFC-3339.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("incorporationDate")
  public OffsetDateTime getIncorporationDate() {
    return incorporationDate;
  }

  public void setIncorporationDate(OffsetDateTime incorporationDate) {
    this.incorporationDate = incorporationDate;
  }

  public BusinessIdentificationData cnpjNumber(String cnpjNumber) {
    this.cnpjNumber = cnpjNumber;
    return this;
  }

  /**
   * Número completo do CNPJ da Empresa consultada  - o CNPJ corresponde ao número de inscrição no Cadastro de Pessoa Jurídica. Deve-se ter apenas os números do CNPJ, sem máscara
   * @return cnpjNumber
   */
  @NotNull @Pattern(regexp = "^\\d{14}$") @Size(max = 14) 
  @Schema(name = "cnpjNumber", example = "50685362006773", description = "Número completo do CNPJ da Empresa consultada  - o CNPJ corresponde ao número de inscrição no Cadastro de Pessoa Jurídica. Deve-se ter apenas os números do CNPJ, sem máscara", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("cnpjNumber")
  public String getCnpjNumber() {
    return cnpjNumber;
  }

  public void setCnpjNumber(String cnpjNumber) {
    this.cnpjNumber = cnpjNumber;
  }

  public BusinessIdentificationData companiesCnpj(List<@Pattern(regexp = "^\\d{14}$")@Size(max = 14)String> companiesCnpj) {
    this.companiesCnpj = companiesCnpj;
    return this;
  }

  public BusinessIdentificationData addCompaniesCnpjItem(String companiesCnpjItem) {
    if (this.companiesCnpj == null) {
      this.companiesCnpj = new ArrayList<>();
    }
    this.companiesCnpj.add(companiesCnpjItem);
    return this;
  }

  /**
   * Número completo do CNPJ da instituição responsável pelo Cadastro - o CNPJ corresponde ao número de inscrição no Cadastro de Pessoa Jurídica.  Deve-se ter apenas os números do CNPJ, sem máscara 
   * @return companiesCnpj
   */
  @NotNull @Size(min = 1) 
  @Schema(name = "companiesCnpj", example = "[\"50685362000135\",\"50685362006555\"]", description = "Número completo do CNPJ da instituição responsável pelo Cadastro - o CNPJ corresponde ao número de inscrição no Cadastro de Pessoa Jurídica.  Deve-se ter apenas os números do CNPJ, sem máscara ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("companiesCnpj")
  public List<@Pattern(regexp = "^\\d{14}$")@Size(max = 14)String> getCompaniesCnpj() {
    return companiesCnpj;
  }

  public void setCompaniesCnpj(List<@Pattern(regexp = "^\\d{14}$")@Size(max = 14)String> companiesCnpj) {
    this.companiesCnpj = companiesCnpj;
  }

  public BusinessIdentificationData otherDocuments(List<@Valid BusinessOtherDocument> otherDocuments) {
    this.otherDocuments = otherDocuments;
    return this;
  }

  public BusinessIdentificationData addOtherDocumentsItem(BusinessOtherDocument otherDocumentsItem) {
    if (this.otherDocuments == null) {
      this.otherDocuments = new ArrayList<>();
    }
    this.otherDocuments.add(otherDocumentsItem);
    return this;
  }

  /**
   * Relação dos demais documentos
   * @return otherDocuments
   */
  @Valid @Size(min = 1) 
  @Schema(name = "otherDocuments", description = "Relação dos demais documentos", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("otherDocuments")
  public List<@Valid BusinessOtherDocument> getOtherDocuments() {
    return otherDocuments;
  }

  public void setOtherDocuments(List<@Valid BusinessOtherDocument> otherDocuments) {
    this.otherDocuments = otherDocuments;
  }

  public BusinessIdentificationData parties(List<@Valid PartiesParticipation> parties) {
    this.parties = parties;
    return this;
  }

  public BusinessIdentificationData addPartiesItem(PartiesParticipation partiesItem) {
    if (this.parties == null) {
      this.parties = new ArrayList<>();
    }
    this.parties.add(partiesItem);
    return this;
  }

  /**
   * Lista relativa às informações das partes envolvidas, como: sócio e/ou administrador. Objeto de envio obrigatório para todos os CNPJs que possuam sócios e/ou administradores no cadastro do QSA (Quadro de Sócios e Administradores) 
   * @return parties
   */
  @NotNull @Valid @Size(min = 0) 
  @Schema(name = "parties", description = "Lista relativa às informações das partes envolvidas, como: sócio e/ou administrador. Objeto de envio obrigatório para todos os CNPJs que possuam sócios e/ou administradores no cadastro do QSA (Quadro de Sócios e Administradores) ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("parties")
  public List<@Valid PartiesParticipation> getParties() {
    return parties;
  }

  public void setParties(List<@Valid PartiesParticipation> parties) {
    this.parties = parties;
  }

  public BusinessIdentificationData contacts(BusinessContacts contacts) {
    this.contacts = contacts;
    return this;
  }

  /**
   * Get contacts
   * @return contacts
   */
  @NotNull @Valid 
  @Schema(name = "contacts", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("contacts")
  public BusinessContacts getContacts() {
    return contacts;
  }

  public void setContacts(BusinessContacts contacts) {
    this.contacts = contacts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BusinessIdentificationData businessIdentificationData = (BusinessIdentificationData) o;
    return Objects.equals(this.updateDateTime, businessIdentificationData.updateDateTime) &&
        Objects.equals(this.businessId, businessIdentificationData.businessId) &&
        Objects.equals(this.brandName, businessIdentificationData.brandName) &&
        Objects.equals(this.companyName, businessIdentificationData.companyName) &&
        Objects.equals(this.tradeName, businessIdentificationData.tradeName) &&
        Objects.equals(this.incorporationDate, businessIdentificationData.incorporationDate) &&
        Objects.equals(this.cnpjNumber, businessIdentificationData.cnpjNumber) &&
        Objects.equals(this.companiesCnpj, businessIdentificationData.companiesCnpj) &&
        Objects.equals(this.otherDocuments, businessIdentificationData.otherDocuments) &&
        Objects.equals(this.parties, businessIdentificationData.parties) &&
        Objects.equals(this.contacts, businessIdentificationData.contacts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(updateDateTime, businessId, brandName, companyName, tradeName, incorporationDate, cnpjNumber, companiesCnpj, otherDocuments, parties, contacts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BusinessIdentificationData {\n");
    sb.append("    updateDateTime: ").append(toIndentedString(updateDateTime)).append("\n");
    sb.append("    businessId: ").append(toIndentedString(businessId)).append("\n");
    sb.append("    brandName: ").append(toIndentedString(brandName)).append("\n");
    sb.append("    companyName: ").append(toIndentedString(companyName)).append("\n");
    sb.append("    tradeName: ").append(toIndentedString(tradeName)).append("\n");
    sb.append("    incorporationDate: ").append(toIndentedString(incorporationDate)).append("\n");
    sb.append("    cnpjNumber: ").append(toIndentedString(cnpjNumber)).append("\n");
    sb.append("    companiesCnpj: ").append(toIndentedString(companiesCnpj)).append("\n");
    sb.append("    otherDocuments: ").append(toIndentedString(otherDocuments)).append("\n");
    sb.append("    parties: ").append(toIndentedString(parties)).append("\n");
    sb.append("    contacts: ").append(toIndentedString(contacts)).append("\n");
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

    private BusinessIdentificationData instance;

    public Builder() {
      this(new BusinessIdentificationData());
    }

    protected Builder(BusinessIdentificationData instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BusinessIdentificationData value) { 
      this.instance.setUpdateDateTime(value.updateDateTime);
      this.instance.setBusinessId(value.businessId);
      this.instance.setBrandName(value.brandName);
      this.instance.setCompanyName(value.companyName);
      this.instance.setTradeName(value.tradeName);
      this.instance.setIncorporationDate(value.incorporationDate);
      this.instance.setCnpjNumber(value.cnpjNumber);
      this.instance.setCompaniesCnpj(value.companiesCnpj);
      this.instance.setOtherDocuments(value.otherDocuments);
      this.instance.setParties(value.parties);
      this.instance.setContacts(value.contacts);
      return this;
    }

    public BusinessIdentificationData.Builder updateDateTime(OffsetDateTime updateDateTime) {
      this.instance.updateDateTime(updateDateTime);
      return this;
    }
    
    public BusinessIdentificationData.Builder businessId(String businessId) {
      this.instance.businessId(businessId);
      return this;
    }
    
    public BusinessIdentificationData.Builder brandName(String brandName) {
      this.instance.brandName(brandName);
      return this;
    }
    
    public BusinessIdentificationData.Builder companyName(String companyName) {
      this.instance.companyName(companyName);
      return this;
    }
    
    public BusinessIdentificationData.Builder tradeName(String tradeName) {
      this.instance.tradeName(tradeName);
      return this;
    }
    
    public BusinessIdentificationData.Builder incorporationDate(OffsetDateTime incorporationDate) {
      this.instance.incorporationDate(incorporationDate);
      return this;
    }
    
    public BusinessIdentificationData.Builder cnpjNumber(String cnpjNumber) {
      this.instance.cnpjNumber(cnpjNumber);
      return this;
    }
    
    public BusinessIdentificationData.Builder companiesCnpj(List<String> companiesCnpj) {
      this.instance.companiesCnpj(companiesCnpj);
      return this;
    }
    
    public BusinessIdentificationData.Builder otherDocuments(List<BusinessOtherDocument> otherDocuments) {
      this.instance.otherDocuments(otherDocuments);
      return this;
    }
    
    public BusinessIdentificationData.Builder parties(List<PartiesParticipation> parties) {
      this.instance.parties(parties);
      return this;
    }
    
    public BusinessIdentificationData.Builder contacts(BusinessContacts contacts) {
      this.instance.contacts(contacts);
      return this;
    }
    
    /**
    * returns a built BusinessIdentificationData instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BusinessIdentificationData build() {
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
  public static BusinessIdentificationData.Builder builder() {
    return new BusinessIdentificationData.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BusinessIdentificationData.Builder toBuilder() {
    BusinessIdentificationData.Builder builder = new BusinessIdentificationData.Builder();
    return builder.copyOf(this);
  }

}

