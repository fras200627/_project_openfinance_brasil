package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tican.open.finance.template_customers_model.EnumCountrySubDivision;
import com.tican.open.finance.template_customers_model.GeographicCoordinates;
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
 * PersonalPostalAddress
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class PersonalPostalAddress {

  private Boolean isMain;

  private String address;

  private @Nullable String additionalInfo;

  private @Nullable String districtName;

  private String townName;

  private @Nullable String ibgeTownCode;

  private @Nullable EnumCountrySubDivision countrySubDivision;

  private String postCode;

  private String country;

  private @Nullable String countryCode;

  private @Nullable GeographicCoordinates geographicCoordinates;

  public PersonalPostalAddress() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PersonalPostalAddress(Boolean isMain, String address, String townName, String postCode, String country) {
    this.isMain = isMain;
    this.address = address;
    this.townName = townName;
    this.postCode = postCode;
    this.country = country;
  }

  /**
   * Constructor with all args parameters
   */
  public PersonalPostalAddress(Boolean isMain, String address, @Nullable String additionalInfo, @Nullable String districtName, String townName, @Nullable String ibgeTownCode, @Nullable EnumCountrySubDivision countrySubDivision, String postCode, String country, @Nullable String countryCode, @Nullable GeographicCoordinates geographicCoordinates) {
      this.isMain = isMain;
      this.address = address;
      this.additionalInfo = additionalInfo;
      this.districtName = districtName;
      this.townName = townName;
      this.ibgeTownCode = ibgeTownCode;
      this.countrySubDivision = countrySubDivision;
      this.postCode = postCode;
      this.country = country;
      this.countryCode = countryCode;
      this.geographicCoordinates = geographicCoordinates;
  }

  public PersonalPostalAddress isMain(Boolean isMain) {
    this.isMain = isMain;
    return this;
  }

  /**
   * Indica se o endereço informado é o principal.
   * @return isMain
   */
  @NotNull 
  @Schema(name = "isMain", example = "true", description = "Indica se o endereço informado é o principal.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("isMain")
  public Boolean getIsMain() {
    return isMain;
  }

  public void setIsMain(Boolean isMain) {
    this.isMain = isMain;
  }

  public PersonalPostalAddress address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Corresponde ao endereço residencial do cliente.
   * @return address
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 150) 
  @Schema(name = "address", example = "Av Naburo Ykesaki, 1270", description = "Corresponde ao endereço residencial do cliente.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("address")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public PersonalPostalAddress additionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
    return this;
  }

  /**
   * Alguns logradouros ainda necessitam ser especificados por meio de complemento.
   * @return additionalInfo
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 30) 
  @Schema(name = "additionalInfo", example = "Fundos", description = "Alguns logradouros ainda necessitam ser especificados por meio de complemento.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("additionalInfo")
  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  public PersonalPostalAddress districtName(String districtName) {
    this.districtName = districtName;
    return this;
  }

  /**
   * Bairro é uma comunidade ou região localizada em uma cidade ou município de acordo com as suas subdivisões geográficas. [Restrição] De preenchimento obrigatório, se houver. 
   * @return districtName
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 50) 
  @Schema(name = "districtName", example = "Centro", description = "Bairro é uma comunidade ou região localizada em uma cidade ou município de acordo com as suas subdivisões geográficas. [Restrição] De preenchimento obrigatório, se houver. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("districtName")
  public String getDistrictName() {
    return districtName;
  }

  public void setDistrictName(String districtName) {
    this.districtName = districtName;
  }

  public PersonalPostalAddress townName(String townName) {
    this.townName = townName;
    return this;
  }

  /**
   * Localidade: O nome da localidade corresponde à designação da cidade ou município no qual o endereço está localizado. 
   * @return townName
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 50) 
  @Schema(name = "townName", example = "Marília", description = "Localidade: O nome da localidade corresponde à designação da cidade ou município no qual o endereço está localizado. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("townName")
  public String getTownName() {
    return townName;
  }

  public void setTownName(String townName) {
    this.townName = townName;
  }

  public PersonalPostalAddress ibgeTownCode(String ibgeTownCode) {
    this.ibgeTownCode = ibgeTownCode;
    return this;
  }

  /**
   * Código IBGE de Município. A Tabela de Códigos de Municípios do IBGE apresenta a lista dos municípios brasileiros associados a um código composto de 7 dígitos, sendo os dois primeiros referentes ao código da Unidade da Federação.
   * @return ibgeTownCode
   */
  @Pattern(regexp = "\\d{7}$") @Size(max = 7) 
  @Schema(name = "ibgeTownCode", example = "3550308", description = "Código IBGE de Município. A Tabela de Códigos de Municípios do IBGE apresenta a lista dos municípios brasileiros associados a um código composto de 7 dígitos, sendo os dois primeiros referentes ao código da Unidade da Federação.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ibgeTownCode")
  public String getIbgeTownCode() {
    return ibgeTownCode;
  }

  public void setIbgeTownCode(String ibgeTownCode) {
    this.ibgeTownCode = ibgeTownCode;
  }

  public PersonalPostalAddress countrySubDivision(EnumCountrySubDivision countrySubDivision) {
    this.countrySubDivision = countrySubDivision;
    return this;
  }

  /**
   * Get countrySubDivision
   * @return countrySubDivision
   */
  @Valid 
  @Schema(name = "countrySubDivision", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("countrySubDivision")
  public EnumCountrySubDivision getCountrySubDivision() {
    return countrySubDivision;
  }

  public void setCountrySubDivision(EnumCountrySubDivision countrySubDivision) {
    this.countrySubDivision = countrySubDivision;
  }

  public PersonalPostalAddress postCode(String postCode) {
    this.postCode = postCode;
    return this;
  }

  /**
   * Código de Endereçamento Postal: Composto por um conjunto numérico de oito dígitos, o objetivo principal do CEP é orientar e acelerar o encaminhamento, o tratamento e a entrega de objetos postados nos Correios, por meio da sua atribuição a localidades, logradouros, unidades dos Correios, serviços, órgãos públicos, empresas e edifícios. p.ex. '01311000'. 
   * @return postCode
   */
  @NotNull @Pattern(regexp = "^\\d{8}$") @Size(max = 8) 
  @Schema(name = "postCode", example = "17500001", description = "Código de Endereçamento Postal: Composto por um conjunto numérico de oito dígitos, o objetivo principal do CEP é orientar e acelerar o encaminhamento, o tratamento e a entrega de objetos postados nos Correios, por meio da sua atribuição a localidades, logradouros, unidades dos Correios, serviços, órgãos públicos, empresas e edifícios. p.ex. '01311000'. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("postCode")
  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public PersonalPostalAddress country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Nome do país
   * @return country
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 80) 
  @Schema(name = "country", example = "Brasil", description = "Nome do país", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("country")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public PersonalPostalAddress countryCode(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  /**
   * Código do país de acordo com o código alpha3 do ISO-3166.
   * @return countryCode
   */
  @Pattern(regexp = "^([A-Z]{3})$") @Size(max = 3) 
  @Schema(name = "countryCode", example = "BRA", description = "Código do país de acordo com o código alpha3 do ISO-3166.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("countryCode")
  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public PersonalPostalAddress geographicCoordinates(GeographicCoordinates geographicCoordinates) {
    this.geographicCoordinates = geographicCoordinates;
    return this;
  }

  /**
   * Get geographicCoordinates
   * @return geographicCoordinates
   */
  @Valid 
  @Schema(name = "geographicCoordinates", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("geographicCoordinates")
  public GeographicCoordinates getGeographicCoordinates() {
    return geographicCoordinates;
  }

  public void setGeographicCoordinates(GeographicCoordinates geographicCoordinates) {
    this.geographicCoordinates = geographicCoordinates;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonalPostalAddress personalPostalAddress = (PersonalPostalAddress) o;
    return Objects.equals(this.isMain, personalPostalAddress.isMain) &&
        Objects.equals(this.address, personalPostalAddress.address) &&
        Objects.equals(this.additionalInfo, personalPostalAddress.additionalInfo) &&
        Objects.equals(this.districtName, personalPostalAddress.districtName) &&
        Objects.equals(this.townName, personalPostalAddress.townName) &&
        Objects.equals(this.ibgeTownCode, personalPostalAddress.ibgeTownCode) &&
        Objects.equals(this.countrySubDivision, personalPostalAddress.countrySubDivision) &&
        Objects.equals(this.postCode, personalPostalAddress.postCode) &&
        Objects.equals(this.country, personalPostalAddress.country) &&
        Objects.equals(this.countryCode, personalPostalAddress.countryCode) &&
        Objects.equals(this.geographicCoordinates, personalPostalAddress.geographicCoordinates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isMain, address, additionalInfo, districtName, townName, ibgeTownCode, countrySubDivision, postCode, country, countryCode, geographicCoordinates);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalPostalAddress {\n");
    sb.append("    isMain: ").append(toIndentedString(isMain)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    additionalInfo: ").append(toIndentedString(additionalInfo)).append("\n");
    sb.append("    districtName: ").append(toIndentedString(districtName)).append("\n");
    sb.append("    townName: ").append(toIndentedString(townName)).append("\n");
    sb.append("    ibgeTownCode: ").append(toIndentedString(ibgeTownCode)).append("\n");
    sb.append("    countrySubDivision: ").append(toIndentedString(countrySubDivision)).append("\n");
    sb.append("    postCode: ").append(toIndentedString(postCode)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
    sb.append("    geographicCoordinates: ").append(toIndentedString(geographicCoordinates)).append("\n");
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

    private PersonalPostalAddress instance;

    public Builder() {
      this(new PersonalPostalAddress());
    }

    protected Builder(PersonalPostalAddress instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PersonalPostalAddress value) { 
      this.instance.setIsMain(value.isMain);
      this.instance.setAddress(value.address);
      this.instance.setAdditionalInfo(value.additionalInfo);
      this.instance.setDistrictName(value.districtName);
      this.instance.setTownName(value.townName);
      this.instance.setIbgeTownCode(value.ibgeTownCode);
      this.instance.setCountrySubDivision(value.countrySubDivision);
      this.instance.setPostCode(value.postCode);
      this.instance.setCountry(value.country);
      this.instance.setCountryCode(value.countryCode);
      this.instance.setGeographicCoordinates(value.geographicCoordinates);
      return this;
    }

    public PersonalPostalAddress.Builder isMain(Boolean isMain) {
      this.instance.isMain(isMain);
      return this;
    }
    
    public PersonalPostalAddress.Builder address(String address) {
      this.instance.address(address);
      return this;
    }
    
    public PersonalPostalAddress.Builder additionalInfo(String additionalInfo) {
      this.instance.additionalInfo(additionalInfo);
      return this;
    }
    
    public PersonalPostalAddress.Builder districtName(String districtName) {
      this.instance.districtName(districtName);
      return this;
    }
    
    public PersonalPostalAddress.Builder townName(String townName) {
      this.instance.townName(townName);
      return this;
    }
    
    public PersonalPostalAddress.Builder ibgeTownCode(String ibgeTownCode) {
      this.instance.ibgeTownCode(ibgeTownCode);
      return this;
    }
    
    public PersonalPostalAddress.Builder countrySubDivision(EnumCountrySubDivision countrySubDivision) {
      this.instance.countrySubDivision(countrySubDivision);
      return this;
    }
    
    public PersonalPostalAddress.Builder postCode(String postCode) {
      this.instance.postCode(postCode);
      return this;
    }
    
    public PersonalPostalAddress.Builder country(String country) {
      this.instance.country(country);
      return this;
    }
    
    public PersonalPostalAddress.Builder countryCode(String countryCode) {
      this.instance.countryCode(countryCode);
      return this;
    }
    
    public PersonalPostalAddress.Builder geographicCoordinates(GeographicCoordinates geographicCoordinates) {
      this.instance.geographicCoordinates(geographicCoordinates);
      return this;
    }
    
    /**
    * returns a built PersonalPostalAddress instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PersonalPostalAddress build() {
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
  public static PersonalPostalAddress.Builder builder() {
    return new PersonalPostalAddress.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PersonalPostalAddress.Builder toBuilder() {
    PersonalPostalAddress.Builder builder = new PersonalPostalAddress.Builder();
    return builder.copyOf(this);
  }

}

