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
 * NationalityOtherDocument
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class NationalityOtherDocument {

  private String type;

  private String number;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate expirationDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate issueDate;

  private @Nullable String country;

  private @Nullable String additionalInfo;

  public NationalityOtherDocument() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public NationalityOtherDocument(String type, String number) {
    this.type = type;
    this.number = number;
  }

  /**
   * Constructor with all args parameters
   */
  public NationalityOtherDocument(String type, String number, @Nullable LocalDate expirationDate, @Nullable LocalDate issueDate, @Nullable String country, @Nullable String additionalInfo) {
      this.type = type;
      this.number = number;
      this.expirationDate = expirationDate;
      this.issueDate = issueDate;
      this.country = country;
      this.additionalInfo = additionalInfo;
  }

  public NationalityOtherDocument type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Tipo de documento. Campo livre, de preenchimento obrigatório quando a nacionalidade for diferente de brasileira. Informar tipo e número do documento, além da, vigência e demais informações complementares para se identificar o documento de pessoa estrangeira
   * @return type
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 10) 
  @Schema(name = "type", example = "SOCIAL SEC", description = "Tipo de documento. Campo livre, de preenchimento obrigatório quando a nacionalidade for diferente de brasileira. Informar tipo e número do documento, além da, vigência e demais informações complementares para se identificar o documento de pessoa estrangeira", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public NationalityOtherDocument number(String number) {
    this.number = number;
    return this;
  }

  /**
   * Número de identificação do documento. Campo livre, de preenchimento obrigatório quando a nacionalidade for diferente de brasileira. Informar o número do documento e demais informações complementares para se identificar o documento de pessoa estrangeira
   * @return number
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 40) 
  @Schema(name = "number", example = "423929299", description = "Número de identificação do documento. Campo livre, de preenchimento obrigatório quando a nacionalidade for diferente de brasileira. Informar o número do documento e demais informações complementares para se identificar o documento de pessoa estrangeira", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("number")
  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public NationalityOtherDocument expirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
    return this;
  }

  /**
   * Data de validade do documento informado, conforme especificação RFC-3339.
   * @return expirationDate
   */
  @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])$") @Size(max = 10) 
  @Schema(name = "expirationDate", example = "2021-05-21", description = "Data de validade do documento informado, conforme especificação RFC-3339.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("expirationDate")
  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }

  public NationalityOtherDocument issueDate(LocalDate issueDate) {
    this.issueDate = issueDate;
    return this;
  }

  /**
   * Data de emissão do documento, conforme especificação RFC-3339.
   * @return issueDate
   */
  @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])$") @Size(max = 10) 
  @Schema(name = "issueDate", example = "2021-05-21", description = "Data de emissão do documento, conforme especificação RFC-3339.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("issueDate")
  public LocalDate getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(LocalDate issueDate) {
    this.issueDate = issueDate;
  }

  public NationalityOtherDocument country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Nome do país. 
   * @return country
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 80) 
  @Schema(name = "country", example = "Brasil", description = "Nome do país. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("country")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public NationalityOtherDocument additionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
    return this;
  }

  /**
   * Campo livre de preenchimento quando necessário.
   * @return additionalInfo
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 70) 
  @Schema(name = "additionalInfo", example = "Informações adicionais.", description = "Campo livre de preenchimento quando necessário.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("additionalInfo")
  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NationalityOtherDocument nationalityOtherDocument = (NationalityOtherDocument) o;
    return Objects.equals(this.type, nationalityOtherDocument.type) &&
        Objects.equals(this.number, nationalityOtherDocument.number) &&
        Objects.equals(this.expirationDate, nationalityOtherDocument.expirationDate) &&
        Objects.equals(this.issueDate, nationalityOtherDocument.issueDate) &&
        Objects.equals(this.country, nationalityOtherDocument.country) &&
        Objects.equals(this.additionalInfo, nationalityOtherDocument.additionalInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, number, expirationDate, issueDate, country, additionalInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NationalityOtherDocument {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    expirationDate: ").append(toIndentedString(expirationDate)).append("\n");
    sb.append("    issueDate: ").append(toIndentedString(issueDate)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    additionalInfo: ").append(toIndentedString(additionalInfo)).append("\n");
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

    private NationalityOtherDocument instance;

    public Builder() {
      this(new NationalityOtherDocument());
    }

    protected Builder(NationalityOtherDocument instance) {
      this.instance = instance;
    }

    protected Builder copyOf(NationalityOtherDocument value) { 
      this.instance.setType(value.type);
      this.instance.setNumber(value.number);
      this.instance.setExpirationDate(value.expirationDate);
      this.instance.setIssueDate(value.issueDate);
      this.instance.setCountry(value.country);
      this.instance.setAdditionalInfo(value.additionalInfo);
      return this;
    }

    public NationalityOtherDocument.Builder type(String type) {
      this.instance.type(type);
      return this;
    }
    
    public NationalityOtherDocument.Builder number(String number) {
      this.instance.number(number);
      return this;
    }
    
    public NationalityOtherDocument.Builder expirationDate(LocalDate expirationDate) {
      this.instance.expirationDate(expirationDate);
      return this;
    }
    
    public NationalityOtherDocument.Builder issueDate(LocalDate issueDate) {
      this.instance.issueDate(issueDate);
      return this;
    }
    
    public NationalityOtherDocument.Builder country(String country) {
      this.instance.country(country);
      return this;
    }
    
    public NationalityOtherDocument.Builder additionalInfo(String additionalInfo) {
      this.instance.additionalInfo(additionalInfo);
      return this;
    }
    
    /**
    * returns a built NationalityOtherDocument instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public NationalityOtherDocument build() {
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
  public static NationalityOtherDocument.Builder builder() {
    return new NationalityOtherDocument.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public NationalityOtherDocument.Builder toBuilder() {
    NationalityOtherDocument.Builder builder = new NationalityOtherDocument.Builder();
    return builder.copyOf(this);
  }

}

