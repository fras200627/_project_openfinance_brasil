package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_customers_model.PersonalPassport;
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
 * Objeto agrupador de informações relativas a Documentos da pessoa natural
 */

@Schema(name = "PersonalDocument", description = "Objeto agrupador de informações relativas a Documentos da pessoa natural")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class PersonalDocument {

  private @Nullable String cpfNumber;

  private @Nullable PersonalPassport passport;

  public PersonalDocument() {
    super();
  }

  /**
   * Constructor with all args parameters
   */
  public PersonalDocument(@Nullable String cpfNumber, @Nullable PersonalPassport passport) {
      this.cpfNumber = cpfNumber;
      this.passport = passport;
  }

  public PersonalDocument cpfNumber(String cpfNumber) {
    this.cpfNumber = cpfNumber;
    return this;
  }

  /**
   * Número completo do CPF.  Atributo que corresponde às informações mínimas exigidas pela Regulamentação em vigor.  O CPF é o Cadastro de Pessoa natural. Ele é um documento feito pela Receita Federal e serve para identificar os contribuintes. O CPF é uma numeração com 11 dígitos, que só mudam por decisão judicial. O documento é emitido pela receita federal.  [Restrição] Preenchimento obrigatório quando não for informado o passport. 
   * @return cpfNumber
   */
  @Pattern(regexp = "^\\d{11}$") @Size(max = 11) 
  @Schema(name = "cpfNumber", example = "25872252137", description = "Número completo do CPF.  Atributo que corresponde às informações mínimas exigidas pela Regulamentação em vigor.  O CPF é o Cadastro de Pessoa natural. Ele é um documento feito pela Receita Federal e serve para identificar os contribuintes. O CPF é uma numeração com 11 dígitos, que só mudam por decisão judicial. O documento é emitido pela receita federal.  [Restrição] Preenchimento obrigatório quando não for informado o passport. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cpfNumber")
  public String getCpfNumber() {
    return cpfNumber;
  }

  public void setCpfNumber(String cpfNumber) {
    this.cpfNumber = cpfNumber;
  }

  public PersonalDocument passport(PersonalPassport passport) {
    this.passport = passport;
    return this;
  }

  /**
   * Get passport
   * @return passport
   */
  @Valid 
  @Schema(name = "passport", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("passport")
  public PersonalPassport getPassport() {
    return passport;
  }

  public void setPassport(PersonalPassport passport) {
    this.passport = passport;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonalDocument personalDocument = (PersonalDocument) o;
    return Objects.equals(this.cpfNumber, personalDocument.cpfNumber) &&
        Objects.equals(this.passport, personalDocument.passport);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cpfNumber, passport);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalDocument {\n");
    sb.append("    cpfNumber: ").append(toIndentedString(cpfNumber)).append("\n");
    sb.append("    passport: ").append(toIndentedString(passport)).append("\n");
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

    private PersonalDocument instance;

    public Builder() {
      this(new PersonalDocument());
    }

    protected Builder(PersonalDocument instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PersonalDocument value) { 
      this.instance.setCpfNumber(value.cpfNumber);
      this.instance.setPassport(value.passport);
      return this;
    }

    public PersonalDocument.Builder cpfNumber(String cpfNumber) {
      this.instance.cpfNumber(cpfNumber);
      return this;
    }
    
    public PersonalDocument.Builder passport(PersonalPassport passport) {
      this.instance.passport(passport);
      return this;
    }
    
    /**
    * returns a built PersonalDocument instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PersonalDocument build() {
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
  public static PersonalDocument.Builder builder() {
    return new PersonalDocument.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PersonalDocument.Builder toBuilder() {
    PersonalDocument.Builder builder = new PersonalDocument.Builder();
    return builder.copyOf(this);
  }

}

