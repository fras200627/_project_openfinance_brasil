package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
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
 * BusinessProcurator
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class BusinessProcurator {

  /**
   * Tipo de representante. Representante legal - Nome Civil completo da Pessoa Natural que represente uma entidade ou uma empresa e é nomeado em seu ato constitutivo, ou seja, no contrato social ou estatuto social. Procurador - é qualquer pessoa que represente a Pessoa Natural em algum negócio, mediante autorização escrita do mesmo. 
   */
  public enum TypeEnum {
    REPRESENTANTE_LEGAL("REPRESENTANTE_LEGAL"),
    
    PROCURADOR("PROCURADOR");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private TypeEnum type;

  private String cnpjCpfNumber;

  private String civilName;

  private @Nullable String socialName;

  public BusinessProcurator() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public BusinessProcurator(TypeEnum type, String cnpjCpfNumber, String civilName) {
    this.type = type;
    this.cnpjCpfNumber = cnpjCpfNumber;
    this.civilName = civilName;
  }

  /**
   * Constructor with all args parameters
   */
  public BusinessProcurator(TypeEnum type, String cnpjCpfNumber, String civilName, @Nullable String socialName) {
      this.type = type;
      this.cnpjCpfNumber = cnpjCpfNumber;
      this.civilName = civilName;
      this.socialName = socialName;
  }

  public BusinessProcurator type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Tipo de representante. Representante legal - Nome Civil completo da Pessoa Natural que represente uma entidade ou uma empresa e é nomeado em seu ato constitutivo, ou seja, no contrato social ou estatuto social. Procurador - é qualquer pessoa que represente a Pessoa Natural em algum negócio, mediante autorização escrita do mesmo. 
   * @return type
   */
  @NotNull 
  @Schema(name = "type", example = "PROCURADOR", description = "Tipo de representante. Representante legal - Nome Civil completo da Pessoa Natural que represente uma entidade ou uma empresa e é nomeado em seu ato constitutivo, ou seja, no contrato social ou estatuto social. Procurador - é qualquer pessoa que represente a Pessoa Natural em algum negócio, mediante autorização escrita do mesmo. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public BusinessProcurator cnpjCpfNumber(String cnpjCpfNumber) {
    this.cnpjCpfNumber = cnpjCpfNumber;
    return this;
  }

  /**
   * Identificação do Representante Legal ou Procurador. Número do cadastro nas Receita Federal  (Preencher com CPF ou CNPJ sem formatação)
   * @return cnpjCpfNumber
   */
  @NotNull @Pattern(regexp = "^\\d{11}$|^\\d{14}$") @Size(max = 14) 
  @Schema(name = "cnpjCpfNumber", example = "73677831148", description = "Identificação do Representante Legal ou Procurador. Número do cadastro nas Receita Federal  (Preencher com CPF ou CNPJ sem formatação)", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("cnpjCpfNumber")
  public String getCnpjCpfNumber() {
    return cnpjCpfNumber;
  }

  public void setCnpjCpfNumber(String cnpjCpfNumber) {
    this.cnpjCpfNumber = cnpjCpfNumber;
  }

  public BusinessProcurator civilName(String civilName) {
    this.civilName = civilName;
    return this;
  }

  /**
   * Nome civil completo ou Razão Social
   * @return civilName
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 70) 
  @Schema(name = "civilName", example = "Elza Milena Stefany Teixeira", description = "Nome civil completo ou Razão Social", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("civilName")
  public String getCivilName() {
    return civilName;
  }

  public void setCivilName(String civilName) {
    this.civilName = civilName;
  }

  public BusinessProcurator socialName(String socialName) {
    this.socialName = socialName;
    return this;
  }

  /**
   * Nome social da pessoa natural, se houver. Aquele pelo qual travestis e transexuais se reconhecem,  bem como são identificados por sua comunidade e em seu meio social, conforme Decreto Local. 
   * @return socialName
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 70) 
  @Schema(name = "socialName", example = "Stefany Teixeirass", description = "Nome social da pessoa natural, se houver. Aquele pelo qual travestis e transexuais se reconhecem,  bem como são identificados por sua comunidade e em seu meio social, conforme Decreto Local. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    BusinessProcurator businessProcurator = (BusinessProcurator) o;
    return Objects.equals(this.type, businessProcurator.type) &&
        Objects.equals(this.cnpjCpfNumber, businessProcurator.cnpjCpfNumber) &&
        Objects.equals(this.civilName, businessProcurator.civilName) &&
        Objects.equals(this.socialName, businessProcurator.socialName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, cnpjCpfNumber, civilName, socialName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BusinessProcurator {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    cnpjCpfNumber: ").append(toIndentedString(cnpjCpfNumber)).append("\n");
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

    private BusinessProcurator instance;

    public Builder() {
      this(new BusinessProcurator());
    }

    protected Builder(BusinessProcurator instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BusinessProcurator value) { 
      this.instance.setType(value.type);
      this.instance.setCnpjCpfNumber(value.cnpjCpfNumber);
      this.instance.setCivilName(value.civilName);
      this.instance.setSocialName(value.socialName);
      return this;
    }

    public BusinessProcurator.Builder type(TypeEnum type) {
      this.instance.type(type);
      return this;
    }
    
    public BusinessProcurator.Builder cnpjCpfNumber(String cnpjCpfNumber) {
      this.instance.cnpjCpfNumber(cnpjCpfNumber);
      return this;
    }
    
    public BusinessProcurator.Builder civilName(String civilName) {
      this.instance.civilName(civilName);
      return this;
    }
    
    public BusinessProcurator.Builder socialName(String socialName) {
      this.instance.socialName(socialName);
      return this;
    }
    
    /**
    * returns a built BusinessProcurator instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BusinessProcurator build() {
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
  public static BusinessProcurator.Builder builder() {
    return new BusinessProcurator.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BusinessProcurator.Builder toBuilder() {
    BusinessProcurator.Builder builder = new BusinessProcurator.Builder();
    return builder.copyOf(this);
  }

}

