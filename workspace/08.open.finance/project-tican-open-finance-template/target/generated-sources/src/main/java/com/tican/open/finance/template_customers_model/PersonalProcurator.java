package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tican.open.finance.template_customers_model.EnumProcuratorsTypePersonal;
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
 * PersonalProcurator
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class PersonalProcurator {

  private EnumProcuratorsTypePersonal type;

  private String cpfNumber;

  private String civilName;

  private @Nullable String socialName;

  public PersonalProcurator() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PersonalProcurator(EnumProcuratorsTypePersonal type, String cpfNumber, String civilName) {
    this.type = type;
    this.cpfNumber = cpfNumber;
    this.civilName = civilName;
  }

  /**
   * Constructor with all args parameters
   */
  public PersonalProcurator(EnumProcuratorsTypePersonal type, String cpfNumber, String civilName, @Nullable String socialName) {
      this.type = type;
      this.cpfNumber = cpfNumber;
      this.civilName = civilName;
      this.socialName = socialName;
  }

  public PersonalProcurator type(EnumProcuratorsTypePersonal type) {
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
  public EnumProcuratorsTypePersonal getType() {
    return type;
  }

  public void setType(EnumProcuratorsTypePersonal type) {
    this.type = type;
  }

  public PersonalProcurator cpfNumber(String cpfNumber) {
    this.cpfNumber = cpfNumber;
    return this;
  }

  /**
   * Número completo do CPF. O CPF é o Cadastro de Pessoa natural. Ele é um documento feito pela Receita Federal e serve para identificar os contribuintes. O CPF é uma numeração com 11 dígitos, que só mudam por decisão judicial. O documento é emitido pela receita federal
   * @return cpfNumber
   */
  @NotNull @Pattern(regexp = "^\\d{11}$") @Size(max = 11) 
  @Schema(name = "cpfNumber", example = "73677831148", description = "Número completo do CPF. O CPF é o Cadastro de Pessoa natural. Ele é um documento feito pela Receita Federal e serve para identificar os contribuintes. O CPF é uma numeração com 11 dígitos, que só mudam por decisão judicial. O documento é emitido pela receita federal", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("cpfNumber")
  public String getCpfNumber() {
    return cpfNumber;
  }

  public void setCpfNumber(String cpfNumber) {
    this.cpfNumber = cpfNumber;
  }

  public PersonalProcurator civilName(String civilName) {
    this.civilName = civilName;
    return this;
  }

  /**
   * Nome civil completo da pessoa natural. (Direito fundamental da pessoa, o nome civil é aquele atribuído à pessoa natural desde o registro de seu nascimento, com o qual será identificada por toda a sua vida, bem como após a sua morte)
   * @return civilName
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 70) 
  @Schema(name = "civilName", example = "Elza Milena Stefany Teixeira", description = "Nome civil completo da pessoa natural. (Direito fundamental da pessoa, o nome civil é aquele atribuído à pessoa natural desde o registro de seu nascimento, com o qual será identificada por toda a sua vida, bem como após a sua morte)", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("civilName")
  public String getCivilName() {
    return civilName;
  }

  public void setCivilName(String civilName) {
    this.civilName = civilName;
  }

  public PersonalProcurator socialName(String socialName) {
    this.socialName = socialName;
    return this;
  }

  /**
   * Nome social da pessoa natural, se houver. (aquele pelo qual travestis e transexuais se reconhecem, bem como são identificados por sua comunidade e em seu meio social, conforme Decreto Nº 51.180, de 14 de janeiro de 2010)
   * @return socialName
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 70) 
  @Schema(name = "socialName", example = "Carlos", description = "Nome social da pessoa natural, se houver. (aquele pelo qual travestis e transexuais se reconhecem, bem como são identificados por sua comunidade e em seu meio social, conforme Decreto Nº 51.180, de 14 de janeiro de 2010)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("socialName")
  public String getSocialName() {
    return socialName;
  }

  public void setSocialName(String socialName) {
    this.socialName = socialName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonalProcurator personalProcurator = (PersonalProcurator) o;
    return Objects.equals(this.type, personalProcurator.type) &&
        Objects.equals(this.cpfNumber, personalProcurator.cpfNumber) &&
        Objects.equals(this.civilName, personalProcurator.civilName) &&
        Objects.equals(this.socialName, personalProcurator.socialName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, cpfNumber, civilName, socialName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalProcurator {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    cpfNumber: ").append(toIndentedString(cpfNumber)).append("\n");
    sb.append("    civilName: ").append(toIndentedString(civilName)).append("\n");
    sb.append("    socialName: ").append(toIndentedString(socialName)).append("\n");
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

    private PersonalProcurator instance;

    public Builder() {
      this(new PersonalProcurator());
    }

    protected Builder(PersonalProcurator instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PersonalProcurator value) { 
      this.instance.setType(value.type);
      this.instance.setCpfNumber(value.cpfNumber);
      this.instance.setCivilName(value.civilName);
      this.instance.setSocialName(value.socialName);
      return this;
    }

    public PersonalProcurator.Builder type(EnumProcuratorsTypePersonal type) {
      this.instance.type(type);
      return this;
    }
    
    public PersonalProcurator.Builder cpfNumber(String cpfNumber) {
      this.instance.cpfNumber(cpfNumber);
      return this;
    }
    
    public PersonalProcurator.Builder civilName(String civilName) {
      this.instance.civilName(civilName);
      return this;
    }
    
    public PersonalProcurator.Builder socialName(String socialName) {
      this.instance.socialName(socialName);
      return this;
    }
    
    /**
    * returns a built PersonalProcurator instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PersonalProcurator build() {
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
  public static PersonalProcurator.Builder builder() {
    return new PersonalProcurator.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PersonalProcurator.Builder toBuilder() {
    PersonalProcurator.Builder builder = new PersonalProcurator.Builder();
    return builder.copyOf(this);
  }

}

