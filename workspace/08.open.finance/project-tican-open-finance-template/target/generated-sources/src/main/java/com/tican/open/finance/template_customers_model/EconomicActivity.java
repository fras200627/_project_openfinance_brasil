package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * EconomicActivity
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class EconomicActivity {

  private String code;

  private Boolean isMain;

  public EconomicActivity() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public EconomicActivity(String code, Boolean isMain) {
    this.code = code;
    this.isMain = isMain;
  }

  public EconomicActivity code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Traz o código do ramo da atividade principal da empresa consultada, segundo padrão CNAE (Classificação Nacional de Atividades Econômicas)  [Observação] O campo sempre deve ser enviado com 7 caracteres, seguindo a classificação “CNAE-Subclasse 2.3”. Em casos em que o valor inicie com zeros, ele deve conter todos os caracteres, incluindo os zeros. 
   * @return code
   */
  @NotNull @Pattern(regexp = "^\\d{7}$") 
  @Schema(name = "code", example = "0600001", description = "Traz o código do ramo da atividade principal da empresa consultada, segundo padrão CNAE (Classificação Nacional de Atividades Econômicas)  [Observação] O campo sempre deve ser enviado com 7 caracteres, seguindo a classificação “CNAE-Subclasse 2.3”. Em casos em que o valor inicie com zeros, ele deve conter todos os caracteres, incluindo os zeros. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("code")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public EconomicActivity isMain(Boolean isMain) {
    this.isMain = isMain;
    return this;
  }

  /**
   * Indica se é o ramo principal de atividade da empresa quando true e se é o ramo secundário quando false. [Restrição] Somente uma ocorrência relativa ao código da atividade econômica principal deve trazer o valor true.
   * @return isMain
   */
  @NotNull 
  @Schema(name = "isMain", example = "true", description = "Indica se é o ramo principal de atividade da empresa quando true e se é o ramo secundário quando false. [Restrição] Somente uma ocorrência relativa ao código da atividade econômica principal deve trazer o valor true.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("isMain")
  public Boolean getIsMain() {
    return isMain;
  }

  public void setIsMain(Boolean isMain) {
    this.isMain = isMain;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EconomicActivity economicActivity = (EconomicActivity) o;
    return Objects.equals(this.code, economicActivity.code) &&
        Objects.equals(this.isMain, economicActivity.isMain);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, isMain);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EconomicActivity {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    isMain: ").append(toIndentedString(isMain)).append("\n");
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

    private EconomicActivity instance;

    public Builder() {
      this(new EconomicActivity());
    }

    protected Builder(EconomicActivity instance) {
      this.instance = instance;
    }

    protected Builder copyOf(EconomicActivity value) { 
      this.instance.setCode(value.code);
      this.instance.setIsMain(value.isMain);
      return this;
    }

    public EconomicActivity.Builder code(String code) {
      this.instance.code(code);
      return this;
    }
    
    public EconomicActivity.Builder isMain(Boolean isMain) {
      this.instance.isMain(isMain);
      return this;
    }
    
    /**
    * returns a built EconomicActivity instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public EconomicActivity build() {
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
  public static EconomicActivity.Builder builder() {
    return new EconomicActivity.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public EconomicActivity.Builder toBuilder() {
    EconomicActivity.Builder builder = new EconomicActivity.Builder();
    return builder.copyOf(this);
  }

}

