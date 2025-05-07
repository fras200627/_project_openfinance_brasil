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
 * Define a razão pela qual o consentimento foi rejeitado.
 */

@Schema(name = "ResponseConsentRead_data_rejection_reason", description = "Define a razão pela qual o consentimento foi rejeitado.")
@JsonTypeName("ResponseConsentRead_data_rejection_reason")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseConsentReadDataRejectionReason {

  /**
   * Define o código da razão pela qual o consentimento foi rejeitado.  - CONSENT_EXPIRED – consentimento que ultrapassou o tempo limite para autorização.    - CUSTOMER_MANUALLY_REJECTED – cliente efetuou a rejeição do consentimento manualmente através de interação nas instituições participantes.    - CUSTOMER_MANUALLY_REVOKED – cliente efetuou a revogação após a autorização do consentimento.    - CONSENT_MAX_DATE_REACHED – consentimento que ultrapassou o tempo limite de compartilhamento.    - CONSENT_TECHNICAL_ISSUE – consentimento que foi rejeitado devido a um problema técnico que impossibilita seu uso pela instituição receptora, por exemplo: falha associada a troca do AuthCode pelo AccessToken, durante o processo de Hybrid Flow.    - INTERNAL_SECURITY_REASON – consentimento que foi rejeitado devido as políticas de segurança aplicada pela instituição transmissora. 
   */
  public enum CodeEnum {
    CONSENT_EXPIRED("CONSENT_EXPIRED"),
    
    CUSTOMER_MANUALLY_REJECTED("CUSTOMER_MANUALLY_REJECTED"),
    
    CUSTOMER_MANUALLY_REVOKED("CUSTOMER_MANUALLY_REVOKED"),
    
    CONSENT_MAX_DATE_REACHED("CONSENT_MAX_DATE_REACHED"),
    
    CONSENT_TECHNICAL_ISSUE("CONSENT_TECHNICAL_ISSUE"),
    
    INTERNAL_SECURITY_REASON("INTERNAL_SECURITY_REASON");

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

  private @Nullable String additionalInformation;

  public ResponseConsentReadDataRejectionReason() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ResponseConsentReadDataRejectionReason(CodeEnum code) {
    this.code = code;
  }

  /**
   * Constructor with all args parameters
   */
  public ResponseConsentReadDataRejectionReason(CodeEnum code, @Nullable String additionalInformation) {
      this.code = code;
      this.additionalInformation = additionalInformation;
  }

  public ResponseConsentReadDataRejectionReason code(CodeEnum code) {
    this.code = code;
    return this;
  }

  /**
   * Define o código da razão pela qual o consentimento foi rejeitado.  - CONSENT_EXPIRED – consentimento que ultrapassou o tempo limite para autorização.    - CUSTOMER_MANUALLY_REJECTED – cliente efetuou a rejeição do consentimento manualmente através de interação nas instituições participantes.    - CUSTOMER_MANUALLY_REVOKED – cliente efetuou a revogação após a autorização do consentimento.    - CONSENT_MAX_DATE_REACHED – consentimento que ultrapassou o tempo limite de compartilhamento.    - CONSENT_TECHNICAL_ISSUE – consentimento que foi rejeitado devido a um problema técnico que impossibilita seu uso pela instituição receptora, por exemplo: falha associada a troca do AuthCode pelo AccessToken, durante o processo de Hybrid Flow.    - INTERNAL_SECURITY_REASON – consentimento que foi rejeitado devido as políticas de segurança aplicada pela instituição transmissora. 
   * @return code
   */
  @NotNull 
  @Schema(name = "code", example = "CONSENT_EXPIRED", description = "Define o código da razão pela qual o consentimento foi rejeitado.  - CONSENT_EXPIRED – consentimento que ultrapassou o tempo limite para autorização.    - CUSTOMER_MANUALLY_REJECTED – cliente efetuou a rejeição do consentimento manualmente através de interação nas instituições participantes.    - CUSTOMER_MANUALLY_REVOKED – cliente efetuou a revogação após a autorização do consentimento.    - CONSENT_MAX_DATE_REACHED – consentimento que ultrapassou o tempo limite de compartilhamento.    - CONSENT_TECHNICAL_ISSUE – consentimento que foi rejeitado devido a um problema técnico que impossibilita seu uso pela instituição receptora, por exemplo: falha associada a troca do AuthCode pelo AccessToken, durante o processo de Hybrid Flow.    - INTERNAL_SECURITY_REASON – consentimento que foi rejeitado devido as políticas de segurança aplicada pela instituição transmissora. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("code")
  public CodeEnum getCode() {
    return code;
  }

  public void setCode(CodeEnum code) {
    this.code = code;
  }

  public ResponseConsentReadDataRejectionReason additionalInformation(String additionalInformation) {
    this.additionalInformation = additionalInformation;
    return this;
  }

  /**
   * Contém informações adicionais a critério da transmissora.
   * @return additionalInformation
   */
  @Pattern(regexp = "^(?!\\s)[\\w\\W\\s]*[^\\s]$") @Size(max = 140) 
  @Schema(name = "additionalInformation", example = "Tempo de confirmação da múltipla alçada excedido.", description = "Contém informações adicionais a critério da transmissora.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("additionalInformation")
  public String getAdditionalInformation() {
    return additionalInformation;
  }

  public void setAdditionalInformation(String additionalInformation) {
    this.additionalInformation = additionalInformation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseConsentReadDataRejectionReason responseConsentReadDataRejectionReason = (ResponseConsentReadDataRejectionReason) o;
    return Objects.equals(this.code, responseConsentReadDataRejectionReason.code) &&
        Objects.equals(this.additionalInformation, responseConsentReadDataRejectionReason.additionalInformation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, additionalInformation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseConsentReadDataRejectionReason {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    additionalInformation: ").append(toIndentedString(additionalInformation)).append("\n");
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

    private ResponseConsentReadDataRejectionReason instance;

    public Builder() {
      this(new ResponseConsentReadDataRejectionReason());
    }

    protected Builder(ResponseConsentReadDataRejectionReason instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseConsentReadDataRejectionReason value) { 
      this.instance.setCode(value.code);
      this.instance.setAdditionalInformation(value.additionalInformation);
      return this;
    }

    public ResponseConsentReadDataRejectionReason.Builder code(CodeEnum code) {
      this.instance.code(code);
      return this;
    }
    
    public ResponseConsentReadDataRejectionReason.Builder additionalInformation(String additionalInformation) {
      this.instance.additionalInformation(additionalInformation);
      return this;
    }
    
    /**
    * returns a built ResponseConsentReadDataRejectionReason instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseConsentReadDataRejectionReason build() {
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
  public static ResponseConsentReadDataRejectionReason.Builder builder() {
    return new ResponseConsentReadDataRejectionReason.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseConsentReadDataRejectionReason.Builder toBuilder() {
    ResponseConsentReadDataRejectionReason.Builder builder = new ResponseConsentReadDataRejectionReason.Builder();
    return builder.copyOf(this);
  }

}

