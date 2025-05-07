package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
 * ResponseErrorUnprocessableEntityErrorsInner
 */

@JsonTypeName("ResponseErrorUnprocessableEntity_errors_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseErrorUnprocessableEntityErrorsInner {

  /**
   * - SEM_PERMISSOES_FUNCIONAIS_RESTANTES - INFORMACOES_PJ_NAO_INFORMADAS - PERMISSOES_PJ_INCORRETAS - PERMISSAO_PF_PJ_EM_CONJUNTO - COMBINACAO_PERMISSOES_INCORRETA - DATA_EXPIRACAO_INVALIDA - ERRO_NAO_MAPEADO  A opção “ERRO_NAO_MAPEADO” só deve ser utilizada caso não haja um code especifico mapeado para o erro. 
   */
  public enum CodeEnum {
    SEM_PERMISSOES_FUNCIONAIS_RESTANTES("SEM_PERMISSOES_FUNCIONAIS_RESTANTES"),
    
    INFORMACOES_PJ_NAO_INFORMADAS("INFORMACOES_PJ_NAO_INFORMADAS"),
    
    PERMISSOES_PJ_INCORRETAS("PERMISSOES_PJ_INCORRETAS"),
    
    PERMISSAO_PF_PJ_EM_CONJUNTO("PERMISSAO_PF_PJ_EM_CONJUNTO"),
    
    COMBINACAO_PERMISSOES_INCORRETA("COMBINACAO_PERMISSOES_INCORRETA"),
    
    DATA_EXPIRACAO_INVALIDA("DATA_EXPIRACAO_INVALIDA"),
    
    ERRO_NAO_MAPEADO("ERRO_NAO_MAPEADO");

    private String value;

    CodeEnum(String value) {
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
    public static CodeEnum fromValue(String value) {
      for (CodeEnum b : CodeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private CodeEnum code;

  private String title;

  private String detail;

  public ResponseErrorUnprocessableEntityErrorsInner() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public ResponseErrorUnprocessableEntityErrorsInner(CodeEnum code, String title, String detail) {
    this.code = code;
    this.title = title;
    this.detail = detail;
  }

  public ResponseErrorUnprocessableEntityErrorsInner code(CodeEnum code) {
    this.code = code;
    return this;
  }

  /**
   * - SEM_PERMISSOES_FUNCIONAIS_RESTANTES - INFORMACOES_PJ_NAO_INFORMADAS - PERMISSOES_PJ_INCORRETAS - PERMISSAO_PF_PJ_EM_CONJUNTO - COMBINACAO_PERMISSOES_INCORRETA - DATA_EXPIRACAO_INVALIDA - ERRO_NAO_MAPEADO  A opção “ERRO_NAO_MAPEADO” só deve ser utilizada caso não haja um code especifico mapeado para o erro. 
   * @return code
   */
  @NotNull 
  @Schema(name = "code", example = "SEM_PERMISSOES_FUNCIONAIS_RESTANTES", description = "- SEM_PERMISSOES_FUNCIONAIS_RESTANTES - INFORMACOES_PJ_NAO_INFORMADAS - PERMISSOES_PJ_INCORRETAS - PERMISSAO_PF_PJ_EM_CONJUNTO - COMBINACAO_PERMISSOES_INCORRETA - DATA_EXPIRACAO_INVALIDA - ERRO_NAO_MAPEADO  A opção “ERRO_NAO_MAPEADO” só deve ser utilizada caso não haja um code especifico mapeado para o erro. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("code")
  public CodeEnum getCode() {
    return code;
  }

  public void setCode(CodeEnum code) {
    this.code = code;
  }

  public ResponseErrorUnprocessableEntityErrorsInner title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Título legível por humanos deste erro específico.
   * @return title
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 255) 
  @Schema(name = "title", description = "Título legível por humanos deste erro específico.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ResponseErrorUnprocessableEntityErrorsInner detail(String detail) {
    this.detail = detail;
    return this;
  }

  /**
   * Descrição legível por humanos deste erro específico.
   * @return detail
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 2048) 
  @Schema(name = "detail", description = "Descrição legível por humanos deste erro específico.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("detail")
  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseErrorUnprocessableEntityErrorsInner responseErrorUnprocessableEntityErrorsInner = (ResponseErrorUnprocessableEntityErrorsInner) o;
    return Objects.equals(this.code, responseErrorUnprocessableEntityErrorsInner.code) &&
        Objects.equals(this.title, responseErrorUnprocessableEntityErrorsInner.title) &&
        Objects.equals(this.detail, responseErrorUnprocessableEntityErrorsInner.detail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, title, detail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseErrorUnprocessableEntityErrorsInner {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
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

    private ResponseErrorUnprocessableEntityErrorsInner instance;

    public Builder() {
      this(new ResponseErrorUnprocessableEntityErrorsInner());
    }

    protected Builder(ResponseErrorUnprocessableEntityErrorsInner instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseErrorUnprocessableEntityErrorsInner value) { 
      this.instance.setCode(value.code);
      this.instance.setTitle(value.title);
      this.instance.setDetail(value.detail);
      return this;
    }

    public ResponseErrorUnprocessableEntityErrorsInner.Builder code(CodeEnum code) {
      this.instance.code(code);
      return this;
    }
    
    public ResponseErrorUnprocessableEntityErrorsInner.Builder title(String title) {
      this.instance.title(title);
      return this;
    }
    
    public ResponseErrorUnprocessableEntityErrorsInner.Builder detail(String detail) {
      this.instance.detail(detail);
      return this;
    }
    
    /**
    * returns a built ResponseErrorUnprocessableEntityErrorsInner instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseErrorUnprocessableEntityErrorsInner build() {
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
  public static ResponseErrorUnprocessableEntityErrorsInner.Builder builder() {
    return new ResponseErrorUnprocessableEntityErrorsInner.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseErrorUnprocessableEntityErrorsInner.Builder toBuilder() {
    ResponseErrorUnprocessableEntityErrorsInner.Builder builder = new ResponseErrorUnprocessableEntityErrorsInner.Builder();
    return builder.copyOf(this);
  }

}

