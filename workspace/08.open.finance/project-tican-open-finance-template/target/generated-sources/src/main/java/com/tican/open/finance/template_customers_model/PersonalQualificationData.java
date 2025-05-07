package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tican.open.finance.template_customers_model.EnumOccupationMainCodeType;
import com.tican.open.finance.template_customers_model.InformedIncome;
import com.tican.open.finance.template_customers_model.PersonalInformedPatrimony;
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
 * Conjunto de informações relativas ao processo de qualificação. Considera-se qualificação as informações que permitam as instituições apreciar, avaliar, caracterizar e classificar o cliente com a finalidade de conhecer o seu perfil de risco e sua capacidade econômico-financeira
 */

@Schema(name = "PersonalQualificationData", description = "Conjunto de informações relativas ao processo de qualificação. Considera-se qualificação as informações que permitam as instituições apreciar, avaliar, caracterizar e classificar o cliente com a finalidade de conhecer o seu perfil de risco e sua capacidade econômico-financeira")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class PersonalQualificationData {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updateDateTime;

  private String companyCnpj;

  private @Nullable EnumOccupationMainCodeType occupationCode;

  private @Nullable String occupationDescription;

  private @Nullable InformedIncome informedIncome;

  private @Nullable PersonalInformedPatrimony informedPatrimony;

  public PersonalQualificationData() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PersonalQualificationData(OffsetDateTime updateDateTime, String companyCnpj) {
    this.updateDateTime = updateDateTime;
    this.companyCnpj = companyCnpj;
  }

  /**
   * Constructor with all args parameters
   */
  public PersonalQualificationData(OffsetDateTime updateDateTime, String companyCnpj, @Nullable EnumOccupationMainCodeType occupationCode, @Nullable String occupationDescription, @Nullable InformedIncome informedIncome, @Nullable PersonalInformedPatrimony informedPatrimony) {
      this.updateDateTime = updateDateTime;
      this.companyCnpj = companyCnpj;
      this.occupationCode = occupationCode;
      this.occupationDescription = occupationDescription;
      this.informedIncome = informedIncome;
      this.informedPatrimony = informedPatrimony;
  }

  public PersonalQualificationData updateDateTime(OffsetDateTime updateDateTime) {
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

  public PersonalQualificationData companyCnpj(String companyCnpj) {
    this.companyCnpj = companyCnpj;
    return this;
  }

  /**
   * Número completo do CNPJ da instituição responsável pelo Cadastro - o CNPJ corresponde ao número de inscrição no Cadastro de Pessoa Jurídica.  Deve-se ter apenas os números do CNPJ, sem máscara 
   * @return companyCnpj
   */
  @NotNull @Pattern(regexp = "^\\d{14}$") @Size(max = 14) 
  @Schema(name = "companyCnpj", example = "50685362000135", description = "Número completo do CNPJ da instituição responsável pelo Cadastro - o CNPJ corresponde ao número de inscrição no Cadastro de Pessoa Jurídica.  Deve-se ter apenas os números do CNPJ, sem máscara ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("companyCnpj")
  public String getCompanyCnpj() {
    return companyCnpj;
  }

  public void setCompanyCnpj(String companyCnpj) {
    this.companyCnpj = companyCnpj;
  }

  public PersonalQualificationData occupationCode(EnumOccupationMainCodeType occupationCode) {
    this.occupationCode = occupationCode;
    return this;
  }

  /**
   * Get occupationCode
   * @return occupationCode
   */
  @Valid 
  @Schema(name = "occupationCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("occupationCode")
  public EnumOccupationMainCodeType getOccupationCode() {
    return occupationCode;
  }

  public void setOccupationCode(EnumOccupationMainCodeType occupationCode) {
    this.occupationCode = occupationCode;
  }

  public PersonalQualificationData occupationDescription(String occupationDescription) {
    this.occupationDescription = occupationDescription;
    return this;
  }

  /**
   * Campo livre, de preenchimento obrigatório. Se selecionada a opção *occupationCode* \"RECEITA_FEDERAL\" ou \"CBO\", informar o código desta lista padronizada.    Se selecionada *occupationCode* \"OUTRO\", informar o descritivo da ocupação quando a IF não segue a lista padronizada da Receita Federal e nem da CBO. 
   * @return occupationDescription
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 100) 
  @Schema(name = "occupationDescription", example = "01", description = "Campo livre, de preenchimento obrigatório. Se selecionada a opção *occupationCode* \"RECEITA_FEDERAL\" ou \"CBO\", informar o código desta lista padronizada.    Se selecionada *occupationCode* \"OUTRO\", informar o descritivo da ocupação quando a IF não segue a lista padronizada da Receita Federal e nem da CBO. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("occupationDescription")
  public String getOccupationDescription() {
    return occupationDescription;
  }

  public void setOccupationDescription(String occupationDescription) {
    this.occupationDescription = occupationDescription;
  }

  public PersonalQualificationData informedIncome(InformedIncome informedIncome) {
    this.informedIncome = informedIncome;
    return this;
  }

  /**
   * Get informedIncome
   * @return informedIncome
   */
  @Valid 
  @Schema(name = "informedIncome", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("informedIncome")
  public InformedIncome getInformedIncome() {
    return informedIncome;
  }

  public void setInformedIncome(InformedIncome informedIncome) {
    this.informedIncome = informedIncome;
  }

  public PersonalQualificationData informedPatrimony(PersonalInformedPatrimony informedPatrimony) {
    this.informedPatrimony = informedPatrimony;
    return this;
  }

  /**
   * Get informedPatrimony
   * @return informedPatrimony
   */
  @Valid 
  @Schema(name = "informedPatrimony", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("informedPatrimony")
  public PersonalInformedPatrimony getInformedPatrimony() {
    return informedPatrimony;
  }

  public void setInformedPatrimony(PersonalInformedPatrimony informedPatrimony) {
    this.informedPatrimony = informedPatrimony;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonalQualificationData personalQualificationData = (PersonalQualificationData) o;
    return Objects.equals(this.updateDateTime, personalQualificationData.updateDateTime) &&
        Objects.equals(this.companyCnpj, personalQualificationData.companyCnpj) &&
        Objects.equals(this.occupationCode, personalQualificationData.occupationCode) &&
        Objects.equals(this.occupationDescription, personalQualificationData.occupationDescription) &&
        Objects.equals(this.informedIncome, personalQualificationData.informedIncome) &&
        Objects.equals(this.informedPatrimony, personalQualificationData.informedPatrimony);
  }

  @Override
  public int hashCode() {
    return Objects.hash(updateDateTime, companyCnpj, occupationCode, occupationDescription, informedIncome, informedPatrimony);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalQualificationData {\n");
    sb.append("    updateDateTime: ").append(toIndentedString(updateDateTime)).append("\n");
    sb.append("    companyCnpj: ").append(toIndentedString(companyCnpj)).append("\n");
    sb.append("    occupationCode: ").append(toIndentedString(occupationCode)).append("\n");
    sb.append("    occupationDescription: ").append(toIndentedString(occupationDescription)).append("\n");
    sb.append("    informedIncome: ").append(toIndentedString(informedIncome)).append("\n");
    sb.append("    informedPatrimony: ").append(toIndentedString(informedPatrimony)).append("\n");
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

    private PersonalQualificationData instance;

    public Builder() {
      this(new PersonalQualificationData());
    }

    protected Builder(PersonalQualificationData instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PersonalQualificationData value) { 
      this.instance.setUpdateDateTime(value.updateDateTime);
      this.instance.setCompanyCnpj(value.companyCnpj);
      this.instance.setOccupationCode(value.occupationCode);
      this.instance.setOccupationDescription(value.occupationDescription);
      this.instance.setInformedIncome(value.informedIncome);
      this.instance.setInformedPatrimony(value.informedPatrimony);
      return this;
    }

    public PersonalQualificationData.Builder updateDateTime(OffsetDateTime updateDateTime) {
      this.instance.updateDateTime(updateDateTime);
      return this;
    }
    
    public PersonalQualificationData.Builder companyCnpj(String companyCnpj) {
      this.instance.companyCnpj(companyCnpj);
      return this;
    }
    
    public PersonalQualificationData.Builder occupationCode(EnumOccupationMainCodeType occupationCode) {
      this.instance.occupationCode(occupationCode);
      return this;
    }
    
    public PersonalQualificationData.Builder occupationDescription(String occupationDescription) {
      this.instance.occupationDescription(occupationDescription);
      return this;
    }
    
    public PersonalQualificationData.Builder informedIncome(InformedIncome informedIncome) {
      this.instance.informedIncome(informedIncome);
      return this;
    }
    
    public PersonalQualificationData.Builder informedPatrimony(PersonalInformedPatrimony informedPatrimony) {
      this.instance.informedPatrimony(informedPatrimony);
      return this;
    }
    
    /**
    * returns a built PersonalQualificationData instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PersonalQualificationData build() {
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
  public static PersonalQualificationData.Builder builder() {
    return new PersonalQualificationData.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PersonalQualificationData.Builder toBuilder() {
    PersonalQualificationData.Builder builder = new PersonalQualificationData.Builder();
    return builder.copyOf(this);
  }

}

