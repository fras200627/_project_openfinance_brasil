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
 * Model422ResponseErrorCreateConsentErrorsInner
 */

@JsonTypeName("_422ResponseErrorCreateConsent_errors_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class Model422ResponseErrorCreateConsentErrorsInner {

  /**
   * Códigos de erros previstos na durante o processo de extensão do consentimento:  - DEPENDE_MULTIPLA_ALCADA: Necessário aprovação de múltipla alçada.  - ESTADO_CONSENTIMENTO_INVALIDO: Estado inválido do consentimento.  - DATA_EXPIRACAO_INVALIDA: Nova data para expiração do consentimento é inválida.  - ERRO_NAO_MAPEADO: Utilizado quando não houver um code de erro definido. 
   */
  public enum CodeEnum {
    DEPENDE_MULTIPLA_ALCADA("DEPENDE_MULTIPLA_ALCADA"),
    
    ESTADO_CONSENTIMENTO_INVALIDO("ESTADO_CONSENTIMENTO_INVALIDO"),
    
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

  public Model422ResponseErrorCreateConsentErrorsInner() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public Model422ResponseErrorCreateConsentErrorsInner(CodeEnum code, String title, String detail) {
    this.code = code;
    this.title = title;
    this.detail = detail;
  }

  public Model422ResponseErrorCreateConsentErrorsInner code(CodeEnum code) {
    this.code = code;
    return this;
  }

  /**
   * Códigos de erros previstos na durante o processo de extensão do consentimento:  - DEPENDE_MULTIPLA_ALCADA: Necessário aprovação de múltipla alçada.  - ESTADO_CONSENTIMENTO_INVALIDO: Estado inválido do consentimento.  - DATA_EXPIRACAO_INVALIDA: Nova data para expiração do consentimento é inválida.  - ERRO_NAO_MAPEADO: Utilizado quando não houver um code de erro definido. 
   * @return code
   */
  @NotNull 
  @Schema(name = "code", example = "DEPENDE_MULTIPLA_ALCADA", description = "Códigos de erros previstos na durante o processo de extensão do consentimento:  - DEPENDE_MULTIPLA_ALCADA: Necessário aprovação de múltipla alçada.  - ESTADO_CONSENTIMENTO_INVALIDO: Estado inválido do consentimento.  - DATA_EXPIRACAO_INVALIDA: Nova data para expiração do consentimento é inválida.  - ERRO_NAO_MAPEADO: Utilizado quando não houver um code de erro definido. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("code")
  public CodeEnum getCode() {
    return code;
  }

  public void setCode(CodeEnum code) {
    this.code = code;
  }

  public Model422ResponseErrorCreateConsentErrorsInner title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Título específico do erro reportado, de acordo com o código enviado: - DEPENDE_MULTIPLA_ALCADA: Necessário aprovação de múltipla alçada. - ESTADO_CONSENTIMENTO_INVALIDO: Estado inválido do consentimento. - DATA_EXPIRACAO_INVALIDA: Nova data para expiração do consentimento é inválida. - ERRO_NAO_MAPEADO: Utilizado quando não houver um code de erro definido. O texto deve deixar claro o motivo do erro ocorrido. 
   * @return title
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 255) 
  @Schema(name = "title", example = "Necessário aprovação de múltipla alçada.", description = "Título específico do erro reportado, de acordo com o código enviado: - DEPENDE_MULTIPLA_ALCADA: Necessário aprovação de múltipla alçada. - ESTADO_CONSENTIMENTO_INVALIDO: Estado inválido do consentimento. - DATA_EXPIRACAO_INVALIDA: Nova data para expiração do consentimento é inválida. - ERRO_NAO_MAPEADO: Utilizado quando não houver um code de erro definido. O texto deve deixar claro o motivo do erro ocorrido. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Model422ResponseErrorCreateConsentErrorsInner detail(String detail) {
    this.detail = detail;
    return this;
  }

  /**
   * Título específico do erro reportado, de acordo com o código enviado: - DEPENDE_MULTIPLA_ALCADA: O consentimento informado não pode ser renovado sem redirecionamento porque depende de múltipla alçada para aprovação. - ESTADO_CONSENTIMENTO_INVALIDO: O consentimento informado não pode ser renovado sem redirecionamento porque está em um estado que não permite a renovação. - DATA_EXPIRACAO_INVALIDA: O consentimento informado não pode ser renovado pois a nova data de expiração não segue a convenção do ecossistema. - ERRO_NAO_MAPEADO: Utilizado quando não houver um code de erro definido. O texto deve deixar claro o motivo do erro ocorrido. 
   * @return detail
   */
  @NotNull @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 2048) 
  @Schema(name = "detail", example = "O consentimento informado não pode ser renovado sem redirecionamento porque depende de múltipla alçada para aprovação.", description = "Título específico do erro reportado, de acordo com o código enviado: - DEPENDE_MULTIPLA_ALCADA: O consentimento informado não pode ser renovado sem redirecionamento porque depende de múltipla alçada para aprovação. - ESTADO_CONSENTIMENTO_INVALIDO: O consentimento informado não pode ser renovado sem redirecionamento porque está em um estado que não permite a renovação. - DATA_EXPIRACAO_INVALIDA: O consentimento informado não pode ser renovado pois a nova data de expiração não segue a convenção do ecossistema. - ERRO_NAO_MAPEADO: Utilizado quando não houver um code de erro definido. O texto deve deixar claro o motivo do erro ocorrido. ", requiredMode = Schema.RequiredMode.REQUIRED)
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
    Model422ResponseErrorCreateConsentErrorsInner _422responseErrorCreateConsentErrorsInner = (Model422ResponseErrorCreateConsentErrorsInner) o;
    return Objects.equals(this.code, _422responseErrorCreateConsentErrorsInner.code) &&
        Objects.equals(this.title, _422responseErrorCreateConsentErrorsInner.title) &&
        Objects.equals(this.detail, _422responseErrorCreateConsentErrorsInner.detail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, title, detail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Model422ResponseErrorCreateConsentErrorsInner {\n");
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

    private Model422ResponseErrorCreateConsentErrorsInner instance;

    public Builder() {
      this(new Model422ResponseErrorCreateConsentErrorsInner());
    }

    protected Builder(Model422ResponseErrorCreateConsentErrorsInner instance) {
      this.instance = instance;
    }

    protected Builder copyOf(Model422ResponseErrorCreateConsentErrorsInner value) { 
      this.instance.setCode(value.code);
      this.instance.setTitle(value.title);
      this.instance.setDetail(value.detail);
      return this;
    }

    public Model422ResponseErrorCreateConsentErrorsInner.Builder code(CodeEnum code) {
      this.instance.code(code);
      return this;
    }
    
    public Model422ResponseErrorCreateConsentErrorsInner.Builder title(String title) {
      this.instance.title(title);
      return this;
    }
    
    public Model422ResponseErrorCreateConsentErrorsInner.Builder detail(String detail) {
      this.instance.detail(detail);
      return this;
    }
    
    /**
    * returns a built Model422ResponseErrorCreateConsentErrorsInner instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public Model422ResponseErrorCreateConsentErrorsInner build() {
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
  public static Model422ResponseErrorCreateConsentErrorsInner.Builder builder() {
    return new Model422ResponseErrorCreateConsentErrorsInner.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public Model422ResponseErrorCreateConsentErrorsInner.Builder toBuilder() {
    Model422ResponseErrorCreateConsentErrorsInner.Builder builder = new Model422ResponseErrorCreateConsentErrorsInner.Builder();
    return builder.copyOf(this);
  }

}

