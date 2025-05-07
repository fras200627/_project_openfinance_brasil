package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tican.open.finance.template_customers_model.EnumCustomerPhoneType;
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
 * CustomerPhone
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class CustomerPhone {

  private Boolean isMain;

  private EnumCustomerPhoneType type;

  private @Nullable String additionalInfo;

  private @Nullable String countryCallingCode;

  private String areaCode;

  private String number;

  private @Nullable String phoneExtension;

  public CustomerPhone() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CustomerPhone(Boolean isMain, EnumCustomerPhoneType type, String areaCode, String number) {
    this.isMain = isMain;
    this.type = type;
    this.areaCode = areaCode;
    this.number = number;
  }

  /**
   * Constructor with all args parameters
   */
  public CustomerPhone(Boolean isMain, EnumCustomerPhoneType type, @Nullable String additionalInfo, @Nullable String countryCallingCode, String areaCode, String number, @Nullable String phoneExtension) {
      this.isMain = isMain;
      this.type = type;
      this.additionalInfo = additionalInfo;
      this.countryCallingCode = countryCallingCode;
      this.areaCode = areaCode;
      this.number = number;
      this.phoneExtension = phoneExtension;
  }

  public CustomerPhone isMain(Boolean isMain) {
    this.isMain = isMain;
    return this;
  }

  /**
   * Indica se o telefone informado é o principal
   * @return isMain
   */
  @NotNull 
  @Schema(name = "isMain", example = "true", description = "Indica se o telefone informado é o principal", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("isMain")
  public Boolean getIsMain() {
    return isMain;
  }

  public void setIsMain(Boolean isMain) {
    this.isMain = isMain;
  }

  public CustomerPhone type(EnumCustomerPhoneType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  @NotNull @Valid 
  @Schema(name = "type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public EnumCustomerPhoneType getType() {
    return type;
  }

  public void setType(EnumCustomerPhoneType type) {
    this.type = type;
  }

  public CustomerPhone additionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
    return this;
  }

  /**
   * Informação complementar relativa ao tipo de telefone selecionado. [Restrição] De preenchimento obrigatório quando selecionado o tipo 'OUTRO'.
   * @return additionalInfo
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 70) 
  @Schema(name = "additionalInfo", example = "Informações adicionais.", description = "Informação complementar relativa ao tipo de telefone selecionado. [Restrição] De preenchimento obrigatório quando selecionado o tipo 'OUTRO'.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("additionalInfo")
  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  public CustomerPhone countryCallingCode(String countryCallingCode) {
    this.countryCallingCode = countryCallingCode;
    return this;
  }

  /**
   * Número de DDI (Discagem Direta Internacional) para telefone de acesso ao Cliente - se houver  [Restrição] O preenchimento é obrigatório quando for diferente de 55. 
   * @return countryCallingCode
   */
  @Pattern(regexp = "^\\d{1,4}$") @Size(max = 4) 
  @Schema(name = "countryCallingCode", example = "55", description = "Número de DDI (Discagem Direta Internacional) para telefone de acesso ao Cliente - se houver  [Restrição] O preenchimento é obrigatório quando for diferente de 55. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("countryCallingCode")
  public String getCountryCallingCode() {
    return countryCallingCode;
  }

  public void setCountryCallingCode(String countryCallingCode) {
    this.countryCallingCode = countryCallingCode;
  }

  public CustomerPhone areaCode(String areaCode) {
    this.areaCode = areaCode;
    return this;
  }

  /**
   * Número de DDD (Discagem Direta à Distância) do telefone do cliente - se houver
   * @return areaCode
   */
  @NotNull @Pattern(regexp = "^(\\d{2,3})$") @Size(min = 2, max = 3) 
  @Schema(name = "areaCode", example = "19", description = "Número de DDD (Discagem Direta à Distância) do telefone do cliente - se houver", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("areaCode")
  public String getAreaCode() {
    return areaCode;
  }

  public void setAreaCode(String areaCode) {
    this.areaCode = areaCode;
  }

  public CustomerPhone number(String number) {
    this.number = number;
    return this;
  }

  /**
   * Número de telefone do cliente
   * @return number
   */
  @NotNull @Pattern(regexp = "^([0-9]{6,13})$") @Size(max = 13) 
  @Schema(name = "number", example = "29875132", description = "Número de telefone do cliente", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("number")
  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public CustomerPhone phoneExtension(String phoneExtension) {
    this.phoneExtension = phoneExtension;
    return this;
  }

  /**
   * Número do ramal. De preenchimento obrigatório se fizer parte da identificação do número do telefone informado
   * @return phoneExtension
   */
  @Pattern(regexp = "^\\d{1,5}$") @Size(max = 5) 
  @Schema(name = "phoneExtension", example = "932", description = "Número do ramal. De preenchimento obrigatório se fizer parte da identificação do número do telefone informado", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("phoneExtension")
  public String getPhoneExtension() {
    return phoneExtension;
  }

  public void setPhoneExtension(String phoneExtension) {
    this.phoneExtension = phoneExtension;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerPhone customerPhone = (CustomerPhone) o;
    return Objects.equals(this.isMain, customerPhone.isMain) &&
        Objects.equals(this.type, customerPhone.type) &&
        Objects.equals(this.additionalInfo, customerPhone.additionalInfo) &&
        Objects.equals(this.countryCallingCode, customerPhone.countryCallingCode) &&
        Objects.equals(this.areaCode, customerPhone.areaCode) &&
        Objects.equals(this.number, customerPhone.number) &&
        Objects.equals(this.phoneExtension, customerPhone.phoneExtension);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isMain, type, additionalInfo, countryCallingCode, areaCode, number, phoneExtension);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerPhone {\n");
    sb.append("    isMain: ").append(toIndentedString(isMain)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    additionalInfo: ").append(toIndentedString(additionalInfo)).append("\n");
    sb.append("    countryCallingCode: ").append(toIndentedString(countryCallingCode)).append("\n");
    sb.append("    areaCode: ").append(toIndentedString(areaCode)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    phoneExtension: ").append(toIndentedString(phoneExtension)).append("\n");
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

    private CustomerPhone instance;

    public Builder() {
      this(new CustomerPhone());
    }

    protected Builder(CustomerPhone instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CustomerPhone value) { 
      this.instance.setIsMain(value.isMain);
      this.instance.setType(value.type);
      this.instance.setAdditionalInfo(value.additionalInfo);
      this.instance.setCountryCallingCode(value.countryCallingCode);
      this.instance.setAreaCode(value.areaCode);
      this.instance.setNumber(value.number);
      this.instance.setPhoneExtension(value.phoneExtension);
      return this;
    }

    public CustomerPhone.Builder isMain(Boolean isMain) {
      this.instance.isMain(isMain);
      return this;
    }
    
    public CustomerPhone.Builder type(EnumCustomerPhoneType type) {
      this.instance.type(type);
      return this;
    }
    
    public CustomerPhone.Builder additionalInfo(String additionalInfo) {
      this.instance.additionalInfo(additionalInfo);
      return this;
    }
    
    public CustomerPhone.Builder countryCallingCode(String countryCallingCode) {
      this.instance.countryCallingCode(countryCallingCode);
      return this;
    }
    
    public CustomerPhone.Builder areaCode(String areaCode) {
      this.instance.areaCode(areaCode);
      return this;
    }
    
    public CustomerPhone.Builder number(String number) {
      this.instance.number(number);
      return this;
    }
    
    public CustomerPhone.Builder phoneExtension(String phoneExtension) {
      this.instance.phoneExtension(phoneExtension);
      return this;
    }
    
    /**
    * returns a built CustomerPhone instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CustomerPhone build() {
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
  public static CustomerPhone.Builder builder() {
    return new CustomerPhone.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CustomerPhone.Builder toBuilder() {
    CustomerPhone.Builder builder = new CustomerPhone.Builder();
    return builder.copyOf(this);
  }

}

