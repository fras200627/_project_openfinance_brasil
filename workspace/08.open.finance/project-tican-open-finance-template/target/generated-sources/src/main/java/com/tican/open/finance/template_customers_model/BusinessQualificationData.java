package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_customers_model.BusinessInformedPatrimony;
import com.tican.open.finance.template_customers_model.EconomicActivity;
import com.tican.open.finance.template_customers_model.InformedRevenue;
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
 * Objeto que reúne as informações relativas ao processo de qualificação. Considera-se qualificação as informações que permitam as instituições apreciar, avaliar, caracterizar e classificar o cliente com a finalidade de conhecer o seu perfil de risco e sua capacidade econômico-financeira
 */

@Schema(name = "BusinessQualificationData", description = "Objeto que reúne as informações relativas ao processo de qualificação. Considera-se qualificação as informações que permitam as instituições apreciar, avaliar, caracterizar e classificar o cliente com a finalidade de conhecer o seu perfil de risco e sua capacidade econômico-financeira")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class BusinessQualificationData {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updateDateTime;

  @Valid
  private List<@Valid EconomicActivity> economicActivities = new ArrayList<>();

  private @Nullable InformedRevenue informedRevenue;

  private @Nullable BusinessInformedPatrimony informedPatrimony;

  public BusinessQualificationData() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public BusinessQualificationData(OffsetDateTime updateDateTime) {
    this.updateDateTime = updateDateTime;
  }

  /**
   * Constructor with all args parameters
   */
  public BusinessQualificationData(OffsetDateTime updateDateTime, List<@Valid EconomicActivity> economicActivities, @Nullable InformedRevenue informedRevenue, @Nullable BusinessInformedPatrimony informedPatrimony) {
      this.updateDateTime = updateDateTime;
      this.economicActivities = economicActivities;
      this.informedRevenue = informedRevenue;
      this.informedPatrimony = informedPatrimony;
  }

  public BusinessQualificationData updateDateTime(OffsetDateTime updateDateTime) {
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

  public BusinessQualificationData economicActivities(List<@Valid EconomicActivity> economicActivities) {
    this.economicActivities = economicActivities;
    return this;
  }

  public BusinessQualificationData addEconomicActivitiesItem(EconomicActivity economicActivitiesItem) {
    if (this.economicActivities == null) {
      this.economicActivities = new ArrayList<>();
    }
    this.economicActivities.add(economicActivitiesItem);
    return this;
  }

  /**
   * Lista dos demais códigos relativos às demais atividades econômicas da empresa, segundo padrão CNAE (Classificação Nacional de Atividades Econômicas). De preenchimento obrigatório, se houver
   * @return economicActivities
   */
  @Valid @Size(min = 0) 
  @Schema(name = "economicActivities", description = "Lista dos demais códigos relativos às demais atividades econômicas da empresa, segundo padrão CNAE (Classificação Nacional de Atividades Econômicas). De preenchimento obrigatório, se houver", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("economicActivities")
  public List<@Valid EconomicActivity> getEconomicActivities() {
    return economicActivities;
  }

  public void setEconomicActivities(List<@Valid EconomicActivity> economicActivities) {
    this.economicActivities = economicActivities;
  }

  public BusinessQualificationData informedRevenue(InformedRevenue informedRevenue) {
    this.informedRevenue = informedRevenue;
    return this;
  }

  /**
   * Get informedRevenue
   * @return informedRevenue
   */
  @Valid 
  @Schema(name = "informedRevenue", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("informedRevenue")
  public InformedRevenue getInformedRevenue() {
    return informedRevenue;
  }

  public void setInformedRevenue(InformedRevenue informedRevenue) {
    this.informedRevenue = informedRevenue;
  }

  public BusinessQualificationData informedPatrimony(BusinessInformedPatrimony informedPatrimony) {
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
  public BusinessInformedPatrimony getInformedPatrimony() {
    return informedPatrimony;
  }

  public void setInformedPatrimony(BusinessInformedPatrimony informedPatrimony) {
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
    BusinessQualificationData businessQualificationData = (BusinessQualificationData) o;
    return Objects.equals(this.updateDateTime, businessQualificationData.updateDateTime) &&
        Objects.equals(this.economicActivities, businessQualificationData.economicActivities) &&
        Objects.equals(this.informedRevenue, businessQualificationData.informedRevenue) &&
        Objects.equals(this.informedPatrimony, businessQualificationData.informedPatrimony);
  }

  @Override
  public int hashCode() {
    return Objects.hash(updateDateTime, economicActivities, informedRevenue, informedPatrimony);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BusinessQualificationData {\n");
    sb.append("    updateDateTime: ").append(toIndentedString(updateDateTime)).append("\n");
    sb.append("    economicActivities: ").append(toIndentedString(economicActivities)).append("\n");
    sb.append("    informedRevenue: ").append(toIndentedString(informedRevenue)).append("\n");
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

    private BusinessQualificationData instance;

    public Builder() {
      this(new BusinessQualificationData());
    }

    protected Builder(BusinessQualificationData instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BusinessQualificationData value) { 
      this.instance.setUpdateDateTime(value.updateDateTime);
      this.instance.setEconomicActivities(value.economicActivities);
      this.instance.setInformedRevenue(value.informedRevenue);
      this.instance.setInformedPatrimony(value.informedPatrimony);
      return this;
    }

    public BusinessQualificationData.Builder updateDateTime(OffsetDateTime updateDateTime) {
      this.instance.updateDateTime(updateDateTime);
      return this;
    }
    
    public BusinessQualificationData.Builder economicActivities(List<EconomicActivity> economicActivities) {
      this.instance.economicActivities(economicActivities);
      return this;
    }
    
    public BusinessQualificationData.Builder informedRevenue(InformedRevenue informedRevenue) {
      this.instance.informedRevenue(informedRevenue);
      return this;
    }
    
    public BusinessQualificationData.Builder informedPatrimony(BusinessInformedPatrimony informedPatrimony) {
      this.instance.informedPatrimony(informedPatrimony);
      return this;
    }
    
    /**
    * returns a built BusinessQualificationData instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BusinessQualificationData build() {
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
  public static BusinessQualificationData.Builder builder() {
    return new BusinessQualificationData.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BusinessQualificationData.Builder toBuilder() {
    BusinessQualificationData.Builder builder = new BusinessQualificationData.Builder();
    return builder.copyOf(this);
  }

}

