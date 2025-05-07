package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tican.open.finance.template_customers_model.EnumPersonalOtherDocumentType;
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
 * PersonalOtherDocument
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class PersonalOtherDocument {

  private EnumPersonalOtherDocumentType type;

  private @Nullable String typeAdditionalInfo;

  private String number;

  private @Nullable String checkDigit;

  private @Nullable String additionalInfo;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate expirationDate;

  public PersonalOtherDocument() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PersonalOtherDocument(EnumPersonalOtherDocumentType type, String number) {
    this.type = type;
    this.number = number;
  }

  /**
   * Constructor with all args parameters
   */
  public PersonalOtherDocument(EnumPersonalOtherDocumentType type, @Nullable String typeAdditionalInfo, String number, @Nullable String checkDigit, @Nullable String additionalInfo, @Nullable LocalDate expirationDate) {
      this.type = type;
      this.typeAdditionalInfo = typeAdditionalInfo;
      this.number = number;
      this.checkDigit = checkDigit;
      this.additionalInfo = additionalInfo;
      this.expirationDate = expirationDate;
  }

  public PersonalOtherDocument type(EnumPersonalOtherDocumentType type) {
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
  public EnumPersonalOtherDocumentType getType() {
    return type;
  }

  public void setType(EnumPersonalOtherDocumentType type) {
    this.type = type;
  }

  public PersonalOtherDocument typeAdditionalInfo(String typeAdditionalInfo) {
    this.typeAdditionalInfo = typeAdditionalInfo;
    return this;
  }

  /**
   * Campo livre de preenchimento obrigatório se selecionada a opção OUTROS tipos de documentos
   * @return typeAdditionalInfo
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 70) 
  @Schema(name = "typeAdditionalInfo", example = "CREA-RJ", description = "Campo livre de preenchimento obrigatório se selecionada a opção OUTROS tipos de documentos", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("typeAdditionalInfo")
  public String getTypeAdditionalInfo() {
    return typeAdditionalInfo;
  }

  public void setTypeAdditionalInfo(String typeAdditionalInfo) {
    this.typeAdditionalInfo = typeAdditionalInfo;
  }

  public PersonalOtherDocument number(String number) {
    this.number = number;
    return this;
  }

  /**
   * Identificação/Número do documento informado
   * @return number
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 40) 
  @Schema(name = "number", example = "15291908", description = "Identificação/Número do documento informado", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("number")
  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public PersonalOtherDocument checkDigit(String checkDigit) {
    this.checkDigit = checkDigit;
    return this;
  }

  /**
   * Dígito verificador do documento informado. De preenchimento obrigatório se o documento informado tiver dígito verificador
   * @return checkDigit
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 2) 
  @Schema(name = "checkDigit", example = "P", description = "Dígito verificador do documento informado. De preenchimento obrigatório se o documento informado tiver dígito verificador", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("checkDigit")
  public String getCheckDigit() {
    return checkDigit;
  }

  public void setCheckDigit(String checkDigit) {
    this.checkDigit = checkDigit;
  }

  public PersonalOtherDocument additionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
    return this;
  }

  /**
   * Para documentos em que se aplique o uso do local de emissão o mesmo deve ser enviado mandatoriamente, com a informação de órgão e UF. Exemplo: RG, local de emissão: SSP/RS. [Restrição] Obrigatório quando o Local de Emissão do Documento for relevante. 
   * @return additionalInfo
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 50) 
  @Schema(name = "additionalInfo", example = "SSP/SP", description = "Para documentos em que se aplique o uso do local de emissão o mesmo deve ser enviado mandatoriamente, com a informação de órgão e UF. Exemplo: RG, local de emissão: SSP/RS. [Restrição] Obrigatório quando o Local de Emissão do Documento for relevante. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("additionalInfo")
  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  public PersonalOtherDocument expirationDate(LocalDate expirationDate) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonalOtherDocument personalOtherDocument = (PersonalOtherDocument) o;
    return Objects.equals(this.type, personalOtherDocument.type) &&
        Objects.equals(this.typeAdditionalInfo, personalOtherDocument.typeAdditionalInfo) &&
        Objects.equals(this.number, personalOtherDocument.number) &&
        Objects.equals(this.checkDigit, personalOtherDocument.checkDigit) &&
        Objects.equals(this.additionalInfo, personalOtherDocument.additionalInfo) &&
        Objects.equals(this.expirationDate, personalOtherDocument.expirationDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, typeAdditionalInfo, number, checkDigit, additionalInfo, expirationDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalOtherDocument {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    typeAdditionalInfo: ").append(toIndentedString(typeAdditionalInfo)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    checkDigit: ").append(toIndentedString(checkDigit)).append("\n");
    sb.append("    additionalInfo: ").append(toIndentedString(additionalInfo)).append("\n");
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

    private PersonalOtherDocument instance;

    public Builder() {
      this(new PersonalOtherDocument());
    }

    protected Builder(PersonalOtherDocument instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PersonalOtherDocument value) { 
      this.instance.setType(value.type);
      this.instance.setTypeAdditionalInfo(value.typeAdditionalInfo);
      this.instance.setNumber(value.number);
      this.instance.setCheckDigit(value.checkDigit);
      this.instance.setAdditionalInfo(value.additionalInfo);
      this.instance.setExpirationDate(value.expirationDate);
      return this;
    }

    public PersonalOtherDocument.Builder type(EnumPersonalOtherDocumentType type) {
      this.instance.type(type);
      return this;
    }
    
    public PersonalOtherDocument.Builder typeAdditionalInfo(String typeAdditionalInfo) {
      this.instance.typeAdditionalInfo(typeAdditionalInfo);
      return this;
    }
    
    public PersonalOtherDocument.Builder number(String number) {
      this.instance.number(number);
      return this;
    }
    
    public PersonalOtherDocument.Builder checkDigit(String checkDigit) {
      this.instance.checkDigit(checkDigit);
      return this;
    }
    
    public PersonalOtherDocument.Builder additionalInfo(String additionalInfo) {
      this.instance.additionalInfo(additionalInfo);
      return this;
    }
    
    public PersonalOtherDocument.Builder expirationDate(LocalDate expirationDate) {
      this.instance.expirationDate(expirationDate);
      return this;
    }
    
    /**
    * returns a built PersonalOtherDocument instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PersonalOtherDocument build() {
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
  public static PersonalOtherDocument.Builder builder() {
    return new PersonalOtherDocument.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PersonalOtherDocument.Builder toBuilder() {
    PersonalOtherDocument.Builder builder = new PersonalOtherDocument.Builder();
    return builder.copyOf(this);
  }

}

