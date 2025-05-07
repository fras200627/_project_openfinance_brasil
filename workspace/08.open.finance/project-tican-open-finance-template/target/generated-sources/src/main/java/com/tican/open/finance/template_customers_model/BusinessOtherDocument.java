package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
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
 * BusinessOtherDocument
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class BusinessOtherDocument {

  private String type;

  private String number;

  private String country;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate expirationDate;

  public BusinessOtherDocument() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public BusinessOtherDocument(String type, String number, String country) {
    this.type = type;
    this.number = number;
    this.country = country;
  }

  /**
   * Constructor with all args parameters
   */
  public BusinessOtherDocument(String type, String number, String country, @Nullable LocalDate expirationDate) {
      this.type = type;
      this.number = number;
      this.country = country;
      this.expirationDate = expirationDate;
  }

  public BusinessOtherDocument type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Número do Tipo de documento informado. De preenchimento obrigatório, para a Pessoa jurídica com domicílio ou sede no exterior, desobrigada de inscrição no CNPJ
   * @return type
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 20) 
  @Schema(name = "type", example = "EIN", description = "Número do Tipo de documento informado. De preenchimento obrigatório, para a Pessoa jurídica com domicílio ou sede no exterior, desobrigada de inscrição no CNPJ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BusinessOtherDocument number(String number) {
    this.number = number;
    return this;
  }

  /**
   * Número do outro documento. De preenchimento obrigatório, para a Pessoa jurídica com domicílio ou sede no exterior, desobrigada de inscrição no CNPJ
   * @return number
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 20) 
  @Schema(name = "number", example = "128328453", description = "Número do outro documento. De preenchimento obrigatório, para a Pessoa jurídica com domicílio ou sede no exterior, desobrigada de inscrição no CNPJ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("number")
  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public BusinessOtherDocument country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Pais de emissão do tipo de documento informado. Código do pais de acordo com o código alpha3 do ISO-3166
   * @return country
   */
  @NotNull @Pattern(regexp = "^(\\w{3}){1}$") @Size(max = 3) 
  @Schema(name = "country", example = "CAN", description = "Pais de emissão do tipo de documento informado. Código do pais de acordo com o código alpha3 do ISO-3166", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("country")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public BusinessOtherDocument expirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
    return this;
  }

  /**
   * Data vigência do tipo de  documento informado, conforme especificação RFC-3339.
   * @return expirationDate
   */
  @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])$") @Size(max = 10) 
  @Schema(name = "expirationDate", example = "2021-05-21", description = "Data vigência do tipo de  documento informado, conforme especificação RFC-3339.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("expirationDate")
  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BusinessOtherDocument businessOtherDocument = (BusinessOtherDocument) o;
    return Objects.equals(this.type, businessOtherDocument.type) &&
        Objects.equals(this.number, businessOtherDocument.number) &&
        Objects.equals(this.country, businessOtherDocument.country) &&
        Objects.equals(this.expirationDate, businessOtherDocument.expirationDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, number, country, expirationDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BusinessOtherDocument {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    expirationDate: ").append(toIndentedString(expirationDate)).append("\n");
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

    private BusinessOtherDocument instance;

    public Builder() {
      this(new BusinessOtherDocument());
    }

    protected Builder(BusinessOtherDocument instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BusinessOtherDocument value) { 
      this.instance.setType(value.type);
      this.instance.setNumber(value.number);
      this.instance.setCountry(value.country);
      this.instance.setExpirationDate(value.expirationDate);
      return this;
    }

    public BusinessOtherDocument.Builder type(String type) {
      this.instance.type(type);
      return this;
    }
    
    public BusinessOtherDocument.Builder number(String number) {
      this.instance.number(number);
      return this;
    }
    
    public BusinessOtherDocument.Builder country(String country) {
      this.instance.country(country);
      return this;
    }
    
    public BusinessOtherDocument.Builder expirationDate(LocalDate expirationDate) {
      this.instance.expirationDate(expirationDate);
      return this;
    }
    
    /**
    * returns a built BusinessOtherDocument instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BusinessOtherDocument build() {
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
  public static BusinessOtherDocument.Builder builder() {
    return new BusinessOtherDocument.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BusinessOtherDocument.Builder toBuilder() {
    BusinessOtherDocument.Builder builder = new BusinessOtherDocument.Builder();
    return builder.copyOf(this);
  }

}

