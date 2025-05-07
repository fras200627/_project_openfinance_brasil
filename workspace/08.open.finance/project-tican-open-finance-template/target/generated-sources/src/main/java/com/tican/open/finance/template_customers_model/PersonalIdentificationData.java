package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tican.open.finance.template_customers_model.EnumMaritalStatusCode;
import com.tican.open.finance.template_customers_model.EnumSex;
import com.tican.open.finance.template_customers_model.Nationality;
import com.tican.open.finance.template_customers_model.PersonalContacts;
import com.tican.open.finance.template_customers_model.PersonalDocument;
import com.tican.open.finance.template_customers_model.PersonalIdentificationDataFiliationInner;
import com.tican.open.finance.template_customers_model.PersonalOtherDocument;
import java.time.LocalDate;
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
 * Conjunto de informações relativas a Identificação ou seja a ação e o efeito de identificar de forma única a pessoa natural através de seus dados cadastrais.
 */

@Schema(name = "PersonalIdentificationData", description = "Conjunto de informações relativas a Identificação ou seja a ação e o efeito de identificar de forma única a pessoa natural através de seus dados cadastrais.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class PersonalIdentificationData {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updateDateTime;

  private String personalId;

  private String brandName;

  private String civilName;

  private @Nullable String socialName;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate birthDate;

  private @Nullable EnumMaritalStatusCode maritalStatusCode;

  private @Nullable String maritalStatusAdditionalInfo;

  private @Nullable EnumSex sex;

  @Valid
  private List<@Pattern(regexp = "^\\d{14}$")@Size(max = 14)String> companiesCnpj = new ArrayList<>();

  private PersonalDocument documents;

  @Valid
  private List<@Valid PersonalOtherDocument> otherDocuments = new ArrayList<>();

  private Boolean hasBrazilianNationality;

  @Valid
  private List<@Valid Nationality> nationality = new ArrayList<>();

  @Valid
  private List<@Valid PersonalIdentificationDataFiliationInner> filiation = new ArrayList<>();

  private PersonalContacts contacts;

  public PersonalIdentificationData() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PersonalIdentificationData(OffsetDateTime updateDateTime, String personalId, String brandName, String civilName, LocalDate birthDate, List<@Pattern(regexp = "^\\d{14}$")@Size(max = 14)String> companiesCnpj, PersonalDocument documents, Boolean hasBrazilianNationality, PersonalContacts contacts) {
    this.updateDateTime = updateDateTime;
    this.personalId = personalId;
    this.brandName = brandName;
    this.civilName = civilName;
    this.birthDate = birthDate;
    this.companiesCnpj = companiesCnpj;
    this.documents = documents;
    this.hasBrazilianNationality = hasBrazilianNationality;
    this.contacts = contacts;
  }

  /**
   * Constructor with all args parameters
   */
  public PersonalIdentificationData(OffsetDateTime updateDateTime, String personalId, String brandName, String civilName, @Nullable String socialName, LocalDate birthDate, @Nullable EnumMaritalStatusCode maritalStatusCode, @Nullable String maritalStatusAdditionalInfo, @Nullable EnumSex sex, List<@Pattern(regexp = "^\\d{14}$")@Size(max = 14)String> companiesCnpj, PersonalDocument documents, List<@Valid PersonalOtherDocument> otherDocuments, Boolean hasBrazilianNationality, List<@Valid Nationality> nationality, List<@Valid PersonalIdentificationDataFiliationInner> filiation, PersonalContacts contacts) {
      this.updateDateTime = updateDateTime;
      this.personalId = personalId;
      this.brandName = brandName;
      this.civilName = civilName;
      this.socialName = socialName;
      this.birthDate = birthDate;
      this.maritalStatusCode = maritalStatusCode;
      this.maritalStatusAdditionalInfo = maritalStatusAdditionalInfo;
      this.sex = sex;
      this.companiesCnpj = companiesCnpj;
      this.documents = documents;
      this.otherDocuments = otherDocuments;
      this.hasBrazilianNationality = hasBrazilianNationality;
      this.nationality = nationality;
      this.filiation = filiation;
      this.contacts = contacts;
  }

  public PersonalIdentificationData updateDateTime(OffsetDateTime updateDateTime) {
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

  public PersonalIdentificationData personalId(String personalId) {
    this.personalId = personalId;
    return this;
  }

  /**
   * Um identificador único e imutável usado para identificar o recurso cliente pessoa natural. Este identificador não tem significado para o cliente que deu o consentimento
   * @return personalId
   */
  @NotNull @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9-]{0,99}$") @Size(min = 1, max = 100) 
  @Schema(name = "personalId", example = "578-psd-71md6971kjh-2d414", description = "Um identificador único e imutável usado para identificar o recurso cliente pessoa natural. Este identificador não tem significado para o cliente que deu o consentimento", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("personalId")
  public String getPersonalId() {
    return personalId;
  }

  public void setPersonalId(String personalId) {
    this.personalId = personalId;
  }

  public PersonalIdentificationData brandName(String brandName) {
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

  public PersonalIdentificationData civilName(String civilName) {
    this.civilName = civilName;
    return this;
  }

  /**
   * Nome civil completo da pessoa natural (Direito fundamental da pessoa, o nome civil é aquele atribuído à pessoa natural desde o registro de seu nascimento, com o qual será identificada por toda a sua vida, bem como após a sua morte)
   * @return civilName
   */
  @NotNull @Pattern(regexp = "^[\\w\\W]*$") @Size(max = 70) 
  @Schema(name = "civilName", example = "Juan Kaique Cláudio Fernandes", description = "Nome civil completo da pessoa natural (Direito fundamental da pessoa, o nome civil é aquele atribuído à pessoa natural desde o registro de seu nascimento, com o qual será identificada por toda a sua vida, bem como após a sua morte)", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("civilName")
  public String getCivilName() {
    return civilName;
  }

  public void setCivilName(String civilName) {
    this.civilName = civilName;
  }

  public PersonalIdentificationData socialName(String socialName) {
    this.socialName = socialName;
    return this;
  }

  /**
   * Nome social da pessoa natural, se houver. (aquele pelo qual travestis e transexuais se reconhecem, bem como são identificados por sua comunidade e em seu meio social, conforme Decreto Local)
   * @return socialName
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 70) 
  @Schema(name = "socialName", example = "Jaqueline de Freitas", description = "Nome social da pessoa natural, se houver. (aquele pelo qual travestis e transexuais se reconhecem, bem como são identificados por sua comunidade e em seu meio social, conforme Decreto Local)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("socialName")
  public String getSocialName() {
    return socialName;
  }

  public void setSocialName(String socialName) {
    this.socialName = socialName;
  }

  public PersonalIdentificationData birthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  /**
   * Data de nascimento, conforme especificação RFC-3339
   * @return birthDate
   */
  @NotNull @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])$") @Size(max = 10) 
  @Schema(name = "birthDate", example = "1989-03-23", description = "Data de nascimento, conforme especificação RFC-3339", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("birthDate")
  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public PersonalIdentificationData maritalStatusCode(EnumMaritalStatusCode maritalStatusCode) {
    this.maritalStatusCode = maritalStatusCode;
    return this;
  }

  /**
   * Get maritalStatusCode
   * @return maritalStatusCode
   */
  @Valid 
  @Schema(name = "maritalStatusCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("maritalStatusCode")
  public EnumMaritalStatusCode getMaritalStatusCode() {
    return maritalStatusCode;
  }

  public void setMaritalStatusCode(EnumMaritalStatusCode maritalStatusCode) {
    this.maritalStatusCode = maritalStatusCode;
  }

  public PersonalIdentificationData maritalStatusAdditionalInfo(String maritalStatusAdditionalInfo) {
    this.maritalStatusAdditionalInfo = maritalStatusAdditionalInfo;
    return this;
  }

  /**
   * Campo livre para complementar a informação relativa ao estado marital.  [Restrição] Preenchimento obrigatório quando selecionado o tipo 'OUTRO'. 
   * @return maritalStatusAdditionalInfo
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 50) 
  @Schema(name = "maritalStatusAdditionalInfo", example = "Amasiado", description = "Campo livre para complementar a informação relativa ao estado marital.  [Restrição] Preenchimento obrigatório quando selecionado o tipo 'OUTRO'. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("maritalStatusAdditionalInfo")
  public String getMaritalStatusAdditionalInfo() {
    return maritalStatusAdditionalInfo;
  }

  public void setMaritalStatusAdditionalInfo(String maritalStatusAdditionalInfo) {
    this.maritalStatusAdditionalInfo = maritalStatusAdditionalInfo;
  }

  public PersonalIdentificationData sex(EnumSex sex) {
    this.sex = sex;
    return this;
  }

  /**
   * Get sex
   * @return sex
   */
  @Valid 
  @Schema(name = "sex", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("sex")
  public EnumSex getSex() {
    return sex;
  }

  public void setSex(EnumSex sex) {
    this.sex = sex;
  }

  public PersonalIdentificationData companiesCnpj(List<@Pattern(regexp = "^\\d{14}$")@Size(max = 14)String> companiesCnpj) {
    this.companiesCnpj = companiesCnpj;
    return this;
  }

  public PersonalIdentificationData addCompaniesCnpjItem(String companiesCnpjItem) {
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
  @Schema(name = "companiesCnpj", example = "[\"01773247000103\",\"01773247000563\"]", description = "Número completo do CNPJ da instituição responsável pelo Cadastro - o CNPJ corresponde ao número de inscrição no Cadastro de Pessoa Jurídica.  Deve-se ter apenas os números do CNPJ, sem máscara ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("companiesCnpj")
  public List<@Pattern(regexp = "^\\d{14}$")@Size(max = 14)String> getCompaniesCnpj() {
    return companiesCnpj;
  }

  public void setCompaniesCnpj(List<@Pattern(regexp = "^\\d{14}$")@Size(max = 14)String> companiesCnpj) {
    this.companiesCnpj = companiesCnpj;
  }

  public PersonalIdentificationData documents(PersonalDocument documents) {
    this.documents = documents;
    return this;
  }

  /**
   * Get documents
   * @return documents
   */
  @NotNull @Valid 
  @Schema(name = "documents", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("documents")
  public PersonalDocument getDocuments() {
    return documents;
  }

  public void setDocuments(PersonalDocument documents) {
    this.documents = documents;
  }

  public PersonalIdentificationData otherDocuments(List<@Valid PersonalOtherDocument> otherDocuments) {
    this.otherDocuments = otherDocuments;
    return this;
  }

  public PersonalIdentificationData addOtherDocumentsItem(PersonalOtherDocument otherDocumentsItem) {
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
  public List<@Valid PersonalOtherDocument> getOtherDocuments() {
    return otherDocuments;
  }

  public void setOtherDocuments(List<@Valid PersonalOtherDocument> otherDocuments) {
    this.otherDocuments = otherDocuments;
  }

  public PersonalIdentificationData hasBrazilianNationality(Boolean hasBrazilianNationality) {
    this.hasBrazilianNationality = hasBrazilianNationality;
    return this;
  }

  /**
   * Informa se o Cliente tem nacionalidade brasileira.
   * @return hasBrazilianNationality
   */
  @NotNull 
  @Schema(name = "hasBrazilianNationality", example = "false", description = "Informa se o Cliente tem nacionalidade brasileira.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("hasBrazilianNationality")
  public Boolean getHasBrazilianNationality() {
    return hasBrazilianNationality;
  }

  public void setHasBrazilianNationality(Boolean hasBrazilianNationality) {
    this.hasBrazilianNationality = hasBrazilianNationality;
  }

  public PersonalIdentificationData nationality(List<@Valid Nationality> nationality) {
    this.nationality = nationality;
    return this;
  }

  public PersonalIdentificationData addNationalityItem(Nationality nationalityItem) {
    if (this.nationality == null) {
      this.nationality = new ArrayList<>();
    }
    this.nationality.add(nationalityItem);
    return this;
  }

  /**
   * Get nationality
   * @return nationality
   */
  @Valid @Size(min = 1) 
  @Schema(name = "nationality", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nationality")
  public List<@Valid Nationality> getNationality() {
    return nationality;
  }

  public void setNationality(List<@Valid Nationality> nationality) {
    this.nationality = nationality;
  }

  public PersonalIdentificationData filiation(List<@Valid PersonalIdentificationDataFiliationInner> filiation) {
    this.filiation = filiation;
    return this;
  }

  public PersonalIdentificationData addFiliationItem(PersonalIdentificationDataFiliationInner filiationItem) {
    if (this.filiation == null) {
      this.filiation = new ArrayList<>();
    }
    this.filiation.add(filiationItem);
    return this;
  }

  /**
   * Get filiation
   * @return filiation
   */
  @Valid @Size(min = 1) 
  @Schema(name = "filiation", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("filiation")
  public List<@Valid PersonalIdentificationDataFiliationInner> getFiliation() {
    return filiation;
  }

  public void setFiliation(List<@Valid PersonalIdentificationDataFiliationInner> filiation) {
    this.filiation = filiation;
  }

  public PersonalIdentificationData contacts(PersonalContacts contacts) {
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
  public PersonalContacts getContacts() {
    return contacts;
  }

  public void setContacts(PersonalContacts contacts) {
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
    PersonalIdentificationData personalIdentificationData = (PersonalIdentificationData) o;
    return Objects.equals(this.updateDateTime, personalIdentificationData.updateDateTime) &&
        Objects.equals(this.personalId, personalIdentificationData.personalId) &&
        Objects.equals(this.brandName, personalIdentificationData.brandName) &&
        Objects.equals(this.civilName, personalIdentificationData.civilName) &&
        Objects.equals(this.socialName, personalIdentificationData.socialName) &&
        Objects.equals(this.birthDate, personalIdentificationData.birthDate) &&
        Objects.equals(this.maritalStatusCode, personalIdentificationData.maritalStatusCode) &&
        Objects.equals(this.maritalStatusAdditionalInfo, personalIdentificationData.maritalStatusAdditionalInfo) &&
        Objects.equals(this.sex, personalIdentificationData.sex) &&
        Objects.equals(this.companiesCnpj, personalIdentificationData.companiesCnpj) &&
        Objects.equals(this.documents, personalIdentificationData.documents) &&
        Objects.equals(this.otherDocuments, personalIdentificationData.otherDocuments) &&
        Objects.equals(this.hasBrazilianNationality, personalIdentificationData.hasBrazilianNationality) &&
        Objects.equals(this.nationality, personalIdentificationData.nationality) &&
        Objects.equals(this.filiation, personalIdentificationData.filiation) &&
        Objects.equals(this.contacts, personalIdentificationData.contacts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(updateDateTime, personalId, brandName, civilName, socialName, birthDate, maritalStatusCode, maritalStatusAdditionalInfo, sex, companiesCnpj, documents, otherDocuments, hasBrazilianNationality, nationality, filiation, contacts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalIdentificationData {\n");
    sb.append("    updateDateTime: ").append(toIndentedString(updateDateTime)).append("\n");
    sb.append("    personalId: ").append(toIndentedString(personalId)).append("\n");
    sb.append("    brandName: ").append(toIndentedString(brandName)).append("\n");
    sb.append("    civilName: ").append(toIndentedString(civilName)).append("\n");
    sb.append("    socialName: ").append(toIndentedString(socialName)).append("\n");
    sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
    sb.append("    maritalStatusCode: ").append(toIndentedString(maritalStatusCode)).append("\n");
    sb.append("    maritalStatusAdditionalInfo: ").append(toIndentedString(maritalStatusAdditionalInfo)).append("\n");
    sb.append("    sex: ").append(toIndentedString(sex)).append("\n");
    sb.append("    companiesCnpj: ").append(toIndentedString(companiesCnpj)).append("\n");
    sb.append("    documents: ").append(toIndentedString(documents)).append("\n");
    sb.append("    otherDocuments: ").append(toIndentedString(otherDocuments)).append("\n");
    sb.append("    hasBrazilianNationality: ").append(toIndentedString(hasBrazilianNationality)).append("\n");
    sb.append("    nationality: ").append(toIndentedString(nationality)).append("\n");
    sb.append("    filiation: ").append(toIndentedString(filiation)).append("\n");
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

    private PersonalIdentificationData instance;

    public Builder() {
      this(new PersonalIdentificationData());
    }

    protected Builder(PersonalIdentificationData instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PersonalIdentificationData value) { 
      this.instance.setUpdateDateTime(value.updateDateTime);
      this.instance.setPersonalId(value.personalId);
      this.instance.setBrandName(value.brandName);
      this.instance.setCivilName(value.civilName);
      this.instance.setSocialName(value.socialName);
      this.instance.setBirthDate(value.birthDate);
      this.instance.setMaritalStatusCode(value.maritalStatusCode);
      this.instance.setMaritalStatusAdditionalInfo(value.maritalStatusAdditionalInfo);
      this.instance.setSex(value.sex);
      this.instance.setCompaniesCnpj(value.companiesCnpj);
      this.instance.setDocuments(value.documents);
      this.instance.setOtherDocuments(value.otherDocuments);
      this.instance.setHasBrazilianNationality(value.hasBrazilianNationality);
      this.instance.setNationality(value.nationality);
      this.instance.setFiliation(value.filiation);
      this.instance.setContacts(value.contacts);
      return this;
    }

    public PersonalIdentificationData.Builder updateDateTime(OffsetDateTime updateDateTime) {
      this.instance.updateDateTime(updateDateTime);
      return this;
    }
    
    public PersonalIdentificationData.Builder personalId(String personalId) {
      this.instance.personalId(personalId);
      return this;
    }
    
    public PersonalIdentificationData.Builder brandName(String brandName) {
      this.instance.brandName(brandName);
      return this;
    }
    
    public PersonalIdentificationData.Builder civilName(String civilName) {
      this.instance.civilName(civilName);
      return this;
    }
    
    public PersonalIdentificationData.Builder socialName(String socialName) {
      this.instance.socialName(socialName);
      return this;
    }
    
    public PersonalIdentificationData.Builder birthDate(LocalDate birthDate) {
      this.instance.birthDate(birthDate);
      return this;
    }
    
    public PersonalIdentificationData.Builder maritalStatusCode(EnumMaritalStatusCode maritalStatusCode) {
      this.instance.maritalStatusCode(maritalStatusCode);
      return this;
    }
    
    public PersonalIdentificationData.Builder maritalStatusAdditionalInfo(String maritalStatusAdditionalInfo) {
      this.instance.maritalStatusAdditionalInfo(maritalStatusAdditionalInfo);
      return this;
    }
    
    public PersonalIdentificationData.Builder sex(EnumSex sex) {
      this.instance.sex(sex);
      return this;
    }
    
    public PersonalIdentificationData.Builder companiesCnpj(List<String> companiesCnpj) {
      this.instance.companiesCnpj(companiesCnpj);
      return this;
    }
    
    public PersonalIdentificationData.Builder documents(PersonalDocument documents) {
      this.instance.documents(documents);
      return this;
    }
    
    public PersonalIdentificationData.Builder otherDocuments(List<PersonalOtherDocument> otherDocuments) {
      this.instance.otherDocuments(otherDocuments);
      return this;
    }
    
    public PersonalIdentificationData.Builder hasBrazilianNationality(Boolean hasBrazilianNationality) {
      this.instance.hasBrazilianNationality(hasBrazilianNationality);
      return this;
    }
    
    public PersonalIdentificationData.Builder nationality(List<Nationality> nationality) {
      this.instance.nationality(nationality);
      return this;
    }
    
    public PersonalIdentificationData.Builder filiation(List<PersonalIdentificationDataFiliationInner> filiation) {
      this.instance.filiation(filiation);
      return this;
    }
    
    public PersonalIdentificationData.Builder contacts(PersonalContacts contacts) {
      this.instance.contacts(contacts);
      return this;
    }
    
    /**
    * returns a built PersonalIdentificationData instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PersonalIdentificationData build() {
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
  public static PersonalIdentificationData.Builder builder() {
    return new PersonalIdentificationData.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PersonalIdentificationData.Builder toBuilder() {
    PersonalIdentificationData.Builder builder = new PersonalIdentificationData.Builder();
    return builder.copyOf(this);
  }

}

