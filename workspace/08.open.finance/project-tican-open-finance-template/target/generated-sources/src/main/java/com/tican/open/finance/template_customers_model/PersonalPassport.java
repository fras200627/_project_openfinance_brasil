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
 * Documento concedido aos viajantes por uma autoridade administrativa nacional a fim de certificar sua identidade perante autoridades estrangeiras.   [Restrição] Aplicável somente à Pessoa natural residente no exterior desobrigada de inscrição no CPF.  [Restrição] Preenchimento obrigatório quando não for informado o cpfNumber. 
 */

@Schema(name = "PersonalPassport", description = "Documento concedido aos viajantes por uma autoridade administrativa nacional a fim de certificar sua identidade perante autoridades estrangeiras.   [Restrição] Aplicável somente à Pessoa natural residente no exterior desobrigada de inscrição no CPF.  [Restrição] Preenchimento obrigatório quando não for informado o cpfNumber. ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class PersonalPassport {

  private String number;

  private String country;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate expirationDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate issueDate;

  public PersonalPassport() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PersonalPassport(String number, String country) {
    this.number = number;
    this.country = country;
  }

  /**
   * Constructor with all args parameters
   */
  public PersonalPassport(String number, String country, @Nullable LocalDate expirationDate, @Nullable LocalDate issueDate) {
      this.number = number;
      this.country = country;
      this.expirationDate = expirationDate;
      this.issueDate = issueDate;
  }

  public PersonalPassport number(String number) {
    this.number = number;
    return this;
  }

  /**
   * Número do Passaporte. 
   * @return number
   */
  @NotNull @Pattern(regexp = "^[\\w\\W]*$") @Size(max = 20) 
  @Schema(name = "number", example = "75253468744594820620", description = "Número do Passaporte. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("number")
  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public PersonalPassport country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Pais de emissão do passaporte. Código do pais de acordo com o código 'alpha3' do ISO-3166. 
   * @return country
   */
  @NotNull @Pattern(regexp = "^(\\w{3}){1}$") @Size(max = 3) 
  @Schema(name = "country", example = "CAN", description = "Pais de emissão do passaporte. Código do pais de acordo com o código 'alpha3' do ISO-3166. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("country")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public PersonalPassport expirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
    return this;
  }

  /**
   * Data vigência do Passaporte, conforme especificação RFC-3339. 
   * @return expirationDate
   */
  @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])$") @Size(max = 10) 
  @Schema(name = "expirationDate", example = "2021-05-21", description = "Data vigência do Passaporte, conforme especificação RFC-3339. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("expirationDate")
  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }

  public PersonalPassport issueDate(LocalDate issueDate) {
    this.issueDate = issueDate;
    return this;
  }

  /**
   * Data de emissão do passaporte, conforme especificação RFC-3339. 
   * @return issueDate
   */
  @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])$") @Size(max = 10) 
  @Schema(name = "issueDate", example = "2021-05-21", description = "Data de emissão do passaporte, conforme especificação RFC-3339. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("issueDate")
  public LocalDate getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(LocalDate issueDate) {
    this.issueDate = issueDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonalPassport personalPassport = (PersonalPassport) o;
    return Objects.equals(this.number, personalPassport.number) &&
        Objects.equals(this.country, personalPassport.country) &&
        Objects.equals(this.expirationDate, personalPassport.expirationDate) &&
        Objects.equals(this.issueDate, personalPassport.issueDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, country, expirationDate, issueDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalPassport {\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    expirationDate: ").append(toIndentedString(expirationDate)).append("\n");
    sb.append("    issueDate: ").append(toIndentedString(issueDate)).append("\n");
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

    private PersonalPassport instance;

    public Builder() {
      this(new PersonalPassport());
    }

    protected Builder(PersonalPassport instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PersonalPassport value) { 
      this.instance.setNumber(value.number);
      this.instance.setCountry(value.country);
      this.instance.setExpirationDate(value.expirationDate);
      this.instance.setIssueDate(value.issueDate);
      return this;
    }

    public PersonalPassport.Builder number(String number) {
      this.instance.number(number);
      return this;
    }
    
    public PersonalPassport.Builder country(String country) {
      this.instance.country(country);
      return this;
    }
    
    public PersonalPassport.Builder expirationDate(LocalDate expirationDate) {
      this.instance.expirationDate(expirationDate);
      return this;
    }
    
    public PersonalPassport.Builder issueDate(LocalDate issueDate) {
      this.instance.issueDate(issueDate);
      return this;
    }
    
    /**
    * returns a built PersonalPassport instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PersonalPassport build() {
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
  public static PersonalPassport.Builder builder() {
    return new PersonalPassport.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PersonalPassport.Builder toBuilder() {
    PersonalPassport.Builder builder = new PersonalPassport.Builder();
    return builder.copyOf(this);
  }

}

