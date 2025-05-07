package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tican.open.finance.template_customers_model.EnumPartiesParticipationDocumentType;
import java.time.LocalDate;
import java.time.OffsetDateTime;
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
 * Lista relativa às informações das partes envolvidas, como: sócio e /ou administrador 
 */

@Schema(name = "PartiesParticipation", description = "Lista relativa às informações das partes envolvidas, como: sócio e /ou administrador ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class PartiesParticipation {

  /**
   * Indica se a pessoa da parte envolvida é uma pessoa natural ou juridica
   */
  public enum PersonTypeEnum {
    PESSOA_NATURAL("PESSOA_NATURAL"),
    
    PESSOA_JURIDICA("PESSOA_JURIDICA");

    private String value;

    PersonTypeEnum(String value) {
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
    public static PersonTypeEnum fromValue(String value) {
      for (PersonTypeEnum b : PersonTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private PersonTypeEnum personType;

  /**
   * Indica o perfil de atuação na empresa. Vide Enum O administrador é o responsável por desempenhar todas as funções administrativas da empresa. É ele quem conduz o dia a dia do negócio, assinando documentos, respondendo legalmente pela sociedade, realizando empréstimos e outras ações gerenciais. Apesar de estar na linha de frente da empresa, ele é denominado sócio por também possuir sua parcela de participação no Capital Social. Sócio não tem qualquer envolvimento nas atividades administrativas da sociedade. 
   */
  public enum TypeEnum {
    SOCIO("SOCIO"),
    
    ADMINISTRADOR("ADMINISTRADOR");

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

  private @Nullable String civilName;

  private @Nullable String socialName;

  private @Nullable String companyName;

  private @Nullable String tradeName;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime startDate;

  private @Nullable Double shareholding;

  private EnumPartiesParticipationDocumentType documentType;

  private String documentNumber;

  private @Nullable String documentAdditionalInfo;

  private @Nullable String documentCountry;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate documentExpirationDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate documentIssueDate;

  public PartiesParticipation() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PartiesParticipation(PersonTypeEnum personType, TypeEnum type, EnumPartiesParticipationDocumentType documentType, String documentNumber) {
    this.personType = personType;
    this.type = type;
    this.documentType = documentType;
    this.documentNumber = documentNumber;
  }

  /**
   * Constructor with all args parameters
   */
  public PartiesParticipation(PersonTypeEnum personType, TypeEnum type, @Nullable String civilName, @Nullable String socialName, @Nullable String companyName, @Nullable String tradeName, @Nullable OffsetDateTime startDate, @Nullable Double shareholding, EnumPartiesParticipationDocumentType documentType, String documentNumber, @Nullable String documentAdditionalInfo, @Nullable String documentCountry, @Nullable LocalDate documentExpirationDate, @Nullable LocalDate documentIssueDate) {
      this.personType = personType;
      this.type = type;
      this.civilName = civilName;
      this.socialName = socialName;
      this.companyName = companyName;
      this.tradeName = tradeName;
      this.startDate = startDate;
      this.shareholding = shareholding;
      this.documentType = documentType;
      this.documentNumber = documentNumber;
      this.documentAdditionalInfo = documentAdditionalInfo;
      this.documentCountry = documentCountry;
      this.documentExpirationDate = documentExpirationDate;
      this.documentIssueDate = documentIssueDate;
  }

  public PartiesParticipation personType(PersonTypeEnum personType) {
    this.personType = personType;
    return this;
  }

  /**
   * Indica se a pessoa da parte envolvida é uma pessoa natural ou juridica
   * @return personType
   */
  @NotNull 
  @Schema(name = "personType", description = "Indica se a pessoa da parte envolvida é uma pessoa natural ou juridica", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("personType")
  public PersonTypeEnum getPersonType() {
    return personType;
  }

  public void setPersonType(PersonTypeEnum personType) {
    this.personType = personType;
  }

  public PartiesParticipation type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Indica o perfil de atuação na empresa. Vide Enum O administrador é o responsável por desempenhar todas as funções administrativas da empresa. É ele quem conduz o dia a dia do negócio, assinando documentos, respondendo legalmente pela sociedade, realizando empréstimos e outras ações gerenciais. Apesar de estar na linha de frente da empresa, ele é denominado sócio por também possuir sua parcela de participação no Capital Social. Sócio não tem qualquer envolvimento nas atividades administrativas da sociedade. 
   * @return type
   */
  @NotNull 
  @Schema(name = "type", description = "Indica o perfil de atuação na empresa. Vide Enum O administrador é o responsável por desempenhar todas as funções administrativas da empresa. É ele quem conduz o dia a dia do negócio, assinando documentos, respondendo legalmente pela sociedade, realizando empréstimos e outras ações gerenciais. Apesar de estar na linha de frente da empresa, ele é denominado sócio por também possuir sua parcela de participação no Capital Social. Sócio não tem qualquer envolvimento nas atividades administrativas da sociedade. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public PartiesParticipation civilName(String civilName) {
    this.civilName = civilName;
    return this;
  }

  /**
   * Nome civil completo da pessoa natural (Direito fundamental da pessoa, o nome civil é aquele atribuído à pessoa natural desde o registro de seu nascimento, com o qual será identificada por toda a sua vida, bem como após a sua morte)  [Restrição] O campo civilName deve ser obrigatoriamente preenchido quando personType for PESSOA_NATURAL. 
   * @return civilName
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 70) 
  @Schema(name = "civilName", example = "Juan Kaique Cláudio Fernandes", description = "Nome civil completo da pessoa natural (Direito fundamental da pessoa, o nome civil é aquele atribuído à pessoa natural desde o registro de seu nascimento, com o qual será identificada por toda a sua vida, bem como após a sua morte)  [Restrição] O campo civilName deve ser obrigatoriamente preenchido quando personType for PESSOA_NATURAL. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("civilName")
  public String getCivilName() {
    return civilName;
  }

  public void setCivilName(String civilName) {
    this.civilName = civilName;
  }

  public PartiesParticipation socialName(String socialName) {
    this.socialName = socialName;
    return this;
  }

  /**
   * Nome social da pessoa natural, se houver. (aquele pelo qual travestis e transexuais se reconhecem, bem como são identificados por sua comunidade e em seu meio social, conforme Decreto Local). 
   * @return socialName
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 70) 
  @Schema(name = "socialName", example = "Karina", description = "Nome social da pessoa natural, se houver. (aquele pelo qual travestis e transexuais se reconhecem, bem como são identificados por sua comunidade e em seu meio social, conforme Decreto Local). ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("socialName")
  public String getSocialName() {
    return socialName;
  }

  public void setSocialName(String socialName) {
    this.socialName = socialName;
  }

  public PartiesParticipation companyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

  /**
   * Razão social da empresa consultada é o termo registrado sob o qual uma pessoa jurídica (PJ) se individualiza e exerce suas atividades. Também pode ser chamada por denominação social ou firma empresarial  [Restrição] o campo companyName deve ser obrigatoriamente preenchido quando personType for PESSOA_JURIDICA. 
   * @return companyName
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 70) 
  @Schema(name = "companyName", example = "Luiza e Benjamin Assessoria Jurídica Ltda", description = "Razão social da empresa consultada é o termo registrado sob o qual uma pessoa jurídica (PJ) se individualiza e exerce suas atividades. Também pode ser chamada por denominação social ou firma empresarial  [Restrição] o campo companyName deve ser obrigatoriamente preenchido quando personType for PESSOA_JURIDICA. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("companyName")
  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public PartiesParticipation tradeName(String tradeName) {
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

  public PartiesParticipation startDate(OffsetDateTime startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Data de início da participação, conforme especificação RFC-3339.
   * @return startDate
   */
  @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])T(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)Z$") @Size(max = 20) 
  @Schema(name = "startDate", example = "2021-05-21T08:30Z", description = "Data de início da participação, conforme especificação RFC-3339.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("startDate")
  public OffsetDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(OffsetDateTime startDate) {
    this.startDate = startDate;
  }

  public PartiesParticipation shareholding(Double shareholding) {
    this.shareholding = shareholding;
    return this;
  }

  /**
   * Percentual de participação societária (informar com 6 casas decimais). O Sócio só deve ser informado se sua participação societária for igual ou superior a 25%. p.ex: 0.250000 (Este valor  representa 25%. O valor '1 'representa 100%).  [Restrição]: Campo obrigatório caso o type for igual a SOCIO e este tiver participação societária maior que 25%. 
   * @return shareholding
   */
  @Pattern(regexp = "^[01]\\.\\d{6}$") @Size(min = 8, max = 8) 
  @Schema(name = "shareholding", example = "0.510000", description = "Percentual de participação societária (informar com 6 casas decimais). O Sócio só deve ser informado se sua participação societária for igual ou superior a 25%. p.ex: 0.250000 (Este valor  representa 25%. O valor '1 'representa 100%).  [Restrição]: Campo obrigatório caso o type for igual a SOCIO e este tiver participação societária maior que 25%. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("shareholding")
  public Double getShareholding() {
    return shareholding;
  }

  public void setShareholding(Double shareholding) {
    this.shareholding = shareholding;
  }

  public PartiesParticipation documentType(EnumPartiesParticipationDocumentType documentType) {
    this.documentType = documentType;
    return this;
  }

  /**
   * Get documentType
   * @return documentType
   */
  @NotNull @Valid 
  @Schema(name = "documentType", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("documentType")
  public EnumPartiesParticipationDocumentType getDocumentType() {
    return documentType;
  }

  public void setDocumentType(EnumPartiesParticipationDocumentType documentType) {
    this.documentType = documentType;
  }

  public PartiesParticipation documentNumber(String documentNumber) {
    this.documentNumber = documentNumber;
    return this;
  }

  /**
   * Número do documento informado. Campo Texto Livre para preencher número e dígito do documento se houver
   * @return documentNumber
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 20) 
  @Schema(name = "documentNumber", example = "73677831148", description = "Número do documento informado. Campo Texto Livre para preencher número e dígito do documento se houver", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("documentNumber")
  public String getDocumentNumber() {
    return documentNumber;
  }

  public void setDocumentNumber(String documentNumber) {
    this.documentNumber = documentNumber;
  }

  public PartiesParticipation documentAdditionalInfo(String documentAdditionalInfo) {
    this.documentAdditionalInfo = documentAdditionalInfo;
    return this;
  }

  /**
   * Campo livre, de preenchimento obrigatório quando o documento informado tiver informações complementares relevantes para a sua identificação
   * @return documentAdditionalInfo
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 100) 
  @Schema(name = "documentAdditionalInfo", example = "CNH", description = "Campo livre, de preenchimento obrigatório quando o documento informado tiver informações complementares relevantes para a sua identificação", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("documentAdditionalInfo")
  public String getDocumentAdditionalInfo() {
    return documentAdditionalInfo;
  }

  public void setDocumentAdditionalInfo(String documentAdditionalInfo) {
    this.documentAdditionalInfo = documentAdditionalInfo;
  }

  public PartiesParticipation documentCountry(String documentCountry) {
    this.documentCountry = documentCountry;
    return this;
  }

  /**
   * País de emissão do documento. Código do pais de acordo com o código alpha3 do ISO-3166. 
   * @return documentCountry
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 3) 
  @Schema(name = "documentCountry", example = "CAN", description = "País de emissão do documento. Código do pais de acordo com o código alpha3 do ISO-3166. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("documentCountry")
  public String getDocumentCountry() {
    return documentCountry;
  }

  public void setDocumentCountry(String documentCountry) {
    this.documentCountry = documentCountry;
  }

  public PartiesParticipation documentExpirationDate(LocalDate documentExpirationDate) {
    this.documentExpirationDate = documentExpirationDate;
    return this;
  }

  /**
   * Data de validade do documento informado, conforme especificação RFC-3339.
   * @return documentExpirationDate
   */
  @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])$") @Size(max = 10) 
  @Schema(name = "documentExpirationDate", example = "2021-05-21", description = "Data de validade do documento informado, conforme especificação RFC-3339.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("documentExpirationDate")
  public LocalDate getDocumentExpirationDate() {
    return documentExpirationDate;
  }

  public void setDocumentExpirationDate(LocalDate documentExpirationDate) {
    this.documentExpirationDate = documentExpirationDate;
  }

  public PartiesParticipation documentIssueDate(LocalDate documentIssueDate) {
    this.documentIssueDate = documentIssueDate;
    return this;
  }

  /**
   * Data de emissão do documento, conforme especificação RFC-3339.
   * @return documentIssueDate
   */
  @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])$") @Size(max = 10) 
  @Schema(name = "documentIssueDate", example = "2021-05-21", description = "Data de emissão do documento, conforme especificação RFC-3339.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("documentIssueDate")
  public LocalDate getDocumentIssueDate() {
    return documentIssueDate;
  }

  public void setDocumentIssueDate(LocalDate documentIssueDate) {
    this.documentIssueDate = documentIssueDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PartiesParticipation partiesParticipation = (PartiesParticipation) o;
    return Objects.equals(this.personType, partiesParticipation.personType) &&
        Objects.equals(this.type, partiesParticipation.type) &&
        Objects.equals(this.civilName, partiesParticipation.civilName) &&
        Objects.equals(this.socialName, partiesParticipation.socialName) &&
        Objects.equals(this.companyName, partiesParticipation.companyName) &&
        Objects.equals(this.tradeName, partiesParticipation.tradeName) &&
        Objects.equals(this.startDate, partiesParticipation.startDate) &&
        Objects.equals(this.shareholding, partiesParticipation.shareholding) &&
        Objects.equals(this.documentType, partiesParticipation.documentType) &&
        Objects.equals(this.documentNumber, partiesParticipation.documentNumber) &&
        Objects.equals(this.documentAdditionalInfo, partiesParticipation.documentAdditionalInfo) &&
        Objects.equals(this.documentCountry, partiesParticipation.documentCountry) &&
        Objects.equals(this.documentExpirationDate, partiesParticipation.documentExpirationDate) &&
        Objects.equals(this.documentIssueDate, partiesParticipation.documentIssueDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(personType, type, civilName, socialName, companyName, tradeName, startDate, shareholding, documentType, documentNumber, documentAdditionalInfo, documentCountry, documentExpirationDate, documentIssueDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PartiesParticipation {\n");
    sb.append("    personType: ").append(toIndentedString(personType)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    civilName: ").append(toIndentedString(civilName)).append("\n");
    sb.append("    socialName: ").append(toIndentedString(socialName)).append("\n");
    sb.append("    companyName: ").append(toIndentedString(companyName)).append("\n");
    sb.append("    tradeName: ").append(toIndentedString(tradeName)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    shareholding: ").append(toIndentedString(shareholding)).append("\n");
    sb.append("    documentType: ").append(toIndentedString(documentType)).append("\n");
    sb.append("    documentNumber: ").append(toIndentedString(documentNumber)).append("\n");
    sb.append("    documentAdditionalInfo: ").append(toIndentedString(documentAdditionalInfo)).append("\n");
    sb.append("    documentCountry: ").append(toIndentedString(documentCountry)).append("\n");
    sb.append("    documentExpirationDate: ").append(toIndentedString(documentExpirationDate)).append("\n");
    sb.append("    documentIssueDate: ").append(toIndentedString(documentIssueDate)).append("\n");
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

    private PartiesParticipation instance;

    public Builder() {
      this(new PartiesParticipation());
    }

    protected Builder(PartiesParticipation instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PartiesParticipation value) { 
      this.instance.setPersonType(value.personType);
      this.instance.setType(value.type);
      this.instance.setCivilName(value.civilName);
      this.instance.setSocialName(value.socialName);
      this.instance.setCompanyName(value.companyName);
      this.instance.setTradeName(value.tradeName);
      this.instance.setStartDate(value.startDate);
      this.instance.setShareholding(value.shareholding);
      this.instance.setDocumentType(value.documentType);
      this.instance.setDocumentNumber(value.documentNumber);
      this.instance.setDocumentAdditionalInfo(value.documentAdditionalInfo);
      this.instance.setDocumentCountry(value.documentCountry);
      this.instance.setDocumentExpirationDate(value.documentExpirationDate);
      this.instance.setDocumentIssueDate(value.documentIssueDate);
      return this;
    }

    public PartiesParticipation.Builder personType(PersonTypeEnum personType) {
      this.instance.personType(personType);
      return this;
    }
    
    public PartiesParticipation.Builder type(TypeEnum type) {
      this.instance.type(type);
      return this;
    }
    
    public PartiesParticipation.Builder civilName(String civilName) {
      this.instance.civilName(civilName);
      return this;
    }
    
    public PartiesParticipation.Builder socialName(String socialName) {
      this.instance.socialName(socialName);
      return this;
    }
    
    public PartiesParticipation.Builder companyName(String companyName) {
      this.instance.companyName(companyName);
      return this;
    }
    
    public PartiesParticipation.Builder tradeName(String tradeName) {
      this.instance.tradeName(tradeName);
      return this;
    }
    
    public PartiesParticipation.Builder startDate(OffsetDateTime startDate) {
      this.instance.startDate(startDate);
      return this;
    }
    
    public PartiesParticipation.Builder shareholding(Double shareholding) {
      this.instance.shareholding(shareholding);
      return this;
    }
    
    public PartiesParticipation.Builder documentType(EnumPartiesParticipationDocumentType documentType) {
      this.instance.documentType(documentType);
      return this;
    }
    
    public PartiesParticipation.Builder documentNumber(String documentNumber) {
      this.instance.documentNumber(documentNumber);
      return this;
    }
    
    public PartiesParticipation.Builder documentAdditionalInfo(String documentAdditionalInfo) {
      this.instance.documentAdditionalInfo(documentAdditionalInfo);
      return this;
    }
    
    public PartiesParticipation.Builder documentCountry(String documentCountry) {
      this.instance.documentCountry(documentCountry);
      return this;
    }
    
    public PartiesParticipation.Builder documentExpirationDate(LocalDate documentExpirationDate) {
      this.instance.documentExpirationDate(documentExpirationDate);
      return this;
    }
    
    public PartiesParticipation.Builder documentIssueDate(LocalDate documentIssueDate) {
      this.instance.documentIssueDate(documentIssueDate);
      return this;
    }
    
    /**
    * returns a built PartiesParticipation instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PartiesParticipation build() {
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
  public static PartiesParticipation.Builder builder() {
    return new PartiesParticipation.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PartiesParticipation.Builder toBuilder() {
    PartiesParticipation.Builder builder = new PartiesParticipation.Builder();
    return builder.copyOf(this);
  }

}

