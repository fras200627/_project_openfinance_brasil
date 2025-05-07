package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.tican.open.finance.template_consents_model.LoggedUserExtensions;
import java.time.OffsetDateTime;
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
 * ResponseConsentReadExtensionsDataInner
 */

@JsonTypeName("ResponseConsentReadExtensions_data_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseConsentReadExtensionsDataInner {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime expirationDateTime;

  private LoggedUserExtensions loggedUser;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime requestDateTime;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime previousExpirationDateTime;

  private @Nullable String xFapiCustomerIpAddress;

  private @Nullable String xCustomerUserAgent;

  public ResponseConsentReadExtensionsDataInner() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ResponseConsentReadExtensionsDataInner(LoggedUserExtensions loggedUser, OffsetDateTime requestDateTime) {
    this.loggedUser = loggedUser;
    this.requestDateTime = requestDateTime;
  }

  /**
   * Constructor with all args parameters
   */
  public ResponseConsentReadExtensionsDataInner(@Nullable OffsetDateTime expirationDateTime, LoggedUserExtensions loggedUser, OffsetDateTime requestDateTime, @Nullable OffsetDateTime previousExpirationDateTime, @Nullable String xFapiCustomerIpAddress, @Nullable String xCustomerUserAgent) {
      this.expirationDateTime = expirationDateTime;
      this.loggedUser = loggedUser;
      this.requestDateTime = requestDateTime;
      this.previousExpirationDateTime = previousExpirationDateTime;
      this.xFapiCustomerIpAddress = xFapiCustomerIpAddress;
      this.xCustomerUserAgent = xCustomerUserAgent;
  }

  public ResponseConsentReadExtensionsDataInner expirationDateTime(OffsetDateTime expirationDateTime) {
    this.expirationDateTime = expirationDateTime;
    return this;
  }

  /**
   * Data e hora de expiração da permissão. Reflete a data limite de validade do consentimento. Uma string com data e hora conforme especificação RFC-3339, sempre com a utilização de timezone UTC(UTC time format), utilizado apenas para consulta de alterações históricas de extensão do consentimento.  [Restrição] De preenchimento obrigatório nos casos em que houver validade determinada.  Em casos de consentimento com prazo indeterminada o campo não deve ser preenchido. 
   * @return expirationDateTime
   */
  @Valid @Pattern(regexp = "(^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])T(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)Z$)") @Size(max = 20) 
  @Schema(name = "expirationDateTime", example = "2021-05-21T08:30Z", description = "Data e hora de expiração da permissão. Reflete a data limite de validade do consentimento. Uma string com data e hora conforme especificação RFC-3339, sempre com a utilização de timezone UTC(UTC time format), utilizado apenas para consulta de alterações históricas de extensão do consentimento.  [Restrição] De preenchimento obrigatório nos casos em que houver validade determinada.  Em casos de consentimento com prazo indeterminada o campo não deve ser preenchido. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("expirationDateTime")
  public OffsetDateTime getExpirationDateTime() {
    return expirationDateTime;
  }

  public void setExpirationDateTime(OffsetDateTime expirationDateTime) {
    this.expirationDateTime = expirationDateTime;
  }

  public ResponseConsentReadExtensionsDataInner loggedUser(LoggedUserExtensions loggedUser) {
    this.loggedUser = loggedUser;
    return this;
  }

  /**
   * Get loggedUser
   * @return loggedUser
   */
  @NotNull @Valid 
  @Schema(name = "loggedUser", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("loggedUser")
  public LoggedUserExtensions getLoggedUser() {
    return loggedUser;
  }

  public void setLoggedUser(LoggedUserExtensions loggedUser) {
    this.loggedUser = loggedUser;
  }

  public ResponseConsentReadExtensionsDataInner requestDateTime(OffsetDateTime requestDateTime) {
    this.requestDateTime = requestDateTime;
    return this;
  }

  /**
   * Data e hora em que o recurso foi criado. Uma string com data e hora conforme especificação RFC-3339, sempre com a utilização de timezone UTC(UTC time format).
   * @return requestDateTime
   */
  @NotNull @Valid @Size(max = 20) 
  @Schema(name = "requestDateTime", example = "2021-05-21T08:30Z", description = "Data e hora em que o recurso foi criado. Uma string com data e hora conforme especificação RFC-3339, sempre com a utilização de timezone UTC(UTC time format).", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("requestDateTime")
  public OffsetDateTime getRequestDateTime() {
    return requestDateTime;
  }

  public void setRequestDateTime(OffsetDateTime requestDateTime) {
    this.requestDateTime = requestDateTime;
  }

  public ResponseConsentReadExtensionsDataInner previousExpirationDateTime(OffsetDateTime previousExpirationDateTime) {
    this.previousExpirationDateTime = previousExpirationDateTime;
    return this;
  }

  /**
   * Data e hora de expiração anteriores a renovação. Reflete a data limite anterior de validade do consentimento. Uma string com data e hora conforme especificação RFC-3339, sempre com a utilização de timezone UTC (UTC time format).  [Restrição] De preenchimento obrigatório nos casos em que houver validade determinada. Em casos de consentimento com prazo indeterminado, ou renovações feitas com a v2.2.0 em que não exista persistência dessa informação, o campo não deve ser preenchido. 
   * @return previousExpirationDateTime
   */
  @Valid @Pattern(regexp = "(^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])T(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)Z$)") @Size(max = 20) 
  @Schema(name = "previousExpirationDateTime", example = "2023-10-18T18:30Z", description = "Data e hora de expiração anteriores a renovação. Reflete a data limite anterior de validade do consentimento. Uma string com data e hora conforme especificação RFC-3339, sempre com a utilização de timezone UTC (UTC time format).  [Restrição] De preenchimento obrigatório nos casos em que houver validade determinada. Em casos de consentimento com prazo indeterminado, ou renovações feitas com a v2.2.0 em que não exista persistência dessa informação, o campo não deve ser preenchido. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("previousExpirationDateTime")
  public OffsetDateTime getPreviousExpirationDateTime() {
    return previousExpirationDateTime;
  }

  public void setPreviousExpirationDateTime(OffsetDateTime previousExpirationDateTime) {
    this.previousExpirationDateTime = previousExpirationDateTime;
  }

  public ResponseConsentReadExtensionsDataInner xFapiCustomerIpAddress(String xFapiCustomerIpAddress) {
    this.xFapiCustomerIpAddress = xFapiCustomerIpAddress;
    return this;
  }

  /**
   * O endereço IP do usuário logado com o receptor que solicitou a renovação sem redirecionamento.  [Restrição] De preenchimento obrigatório a partir da v3.0.0. Opcional para renovações feitas com a v2.2.0 quando não existir persistência dessa informação. 
   * @return xFapiCustomerIpAddress
   */
  @Pattern(regexp = "^(?!\\s)[\\w\\W\\s]*[^\\s]$") @Size(min = 1, max = 100) 
  @Schema(name = "xFapiCustomerIpAddress", example = "172.217.22.14", description = "O endereço IP do usuário logado com o receptor que solicitou a renovação sem redirecionamento.  [Restrição] De preenchimento obrigatório a partir da v3.0.0. Opcional para renovações feitas com a v2.2.0 quando não existir persistência dessa informação. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("xFapiCustomerIpAddress")
  public String getxFapiCustomerIpAddress() {
    return xFapiCustomerIpAddress;
  }

  public void setxFapiCustomerIpAddress(String xFapiCustomerIpAddress) {
    this.xFapiCustomerIpAddress = xFapiCustomerIpAddress;
  }

  public ResponseConsentReadExtensionsDataInner xCustomerUserAgent(String xCustomerUserAgent) {
    this.xCustomerUserAgent = xCustomerUserAgent;
    return this;
  }

  /**
   * Indica o user-agent que o usuário utilizou quando solicitou a renovação sem redirecionamento.  [Restrição] De preenchimento obrigatório a partir da v3.0.0. Opcional para renovações feitas com a v2.2.0 quando não existir persistência dessa informação. 
   * @return xCustomerUserAgent
   */
  @Pattern(regexp = "^(?!\\s)[\\w\\W\\s]*[^\\s]$") @Size(min = 1, max = 100) 
  @Schema(name = "xCustomerUserAgent", example = "Mozilla/5.0 (iPhone14,6; U; CPU iPhone OS 15_4 like Mac OS X)", description = "Indica o user-agent que o usuário utilizou quando solicitou a renovação sem redirecionamento.  [Restrição] De preenchimento obrigatório a partir da v3.0.0. Opcional para renovações feitas com a v2.2.0 quando não existir persistência dessa informação. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("xCustomerUserAgent")
  public String getxCustomerUserAgent() {
    return xCustomerUserAgent;
  }

  public void setxCustomerUserAgent(String xCustomerUserAgent) {
    this.xCustomerUserAgent = xCustomerUserAgent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseConsentReadExtensionsDataInner responseConsentReadExtensionsDataInner = (ResponseConsentReadExtensionsDataInner) o;
    return Objects.equals(this.expirationDateTime, responseConsentReadExtensionsDataInner.expirationDateTime) &&
        Objects.equals(this.loggedUser, responseConsentReadExtensionsDataInner.loggedUser) &&
        Objects.equals(this.requestDateTime, responseConsentReadExtensionsDataInner.requestDateTime) &&
        Objects.equals(this.previousExpirationDateTime, responseConsentReadExtensionsDataInner.previousExpirationDateTime) &&
        Objects.equals(this.xFapiCustomerIpAddress, responseConsentReadExtensionsDataInner.xFapiCustomerIpAddress) &&
        Objects.equals(this.xCustomerUserAgent, responseConsentReadExtensionsDataInner.xCustomerUserAgent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(expirationDateTime, loggedUser, requestDateTime, previousExpirationDateTime, xFapiCustomerIpAddress, xCustomerUserAgent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseConsentReadExtensionsDataInner {\n");
    sb.append("    expirationDateTime: ").append(toIndentedString(expirationDateTime)).append("\n");
    sb.append("    loggedUser: ").append(toIndentedString(loggedUser)).append("\n");
    sb.append("    requestDateTime: ").append(toIndentedString(requestDateTime)).append("\n");
    sb.append("    previousExpirationDateTime: ").append(toIndentedString(previousExpirationDateTime)).append("\n");
    sb.append("    xFapiCustomerIpAddress: ").append(toIndentedString(xFapiCustomerIpAddress)).append("\n");
    sb.append("    xCustomerUserAgent: ").append(toIndentedString(xCustomerUserAgent)).append("\n");
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

    private ResponseConsentReadExtensionsDataInner instance;

    public Builder() {
      this(new ResponseConsentReadExtensionsDataInner());
    }

    protected Builder(ResponseConsentReadExtensionsDataInner instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseConsentReadExtensionsDataInner value) { 
      this.instance.setExpirationDateTime(value.expirationDateTime);
      this.instance.setLoggedUser(value.loggedUser);
      this.instance.setRequestDateTime(value.requestDateTime);
      this.instance.setPreviousExpirationDateTime(value.previousExpirationDateTime);
      this.instance.setxFapiCustomerIpAddress(value.xFapiCustomerIpAddress);
      this.instance.setxCustomerUserAgent(value.xCustomerUserAgent);
      return this;
    }

    public ResponseConsentReadExtensionsDataInner.Builder expirationDateTime(OffsetDateTime expirationDateTime) {
      this.instance.expirationDateTime(expirationDateTime);
      return this;
    }
    
    public ResponseConsentReadExtensionsDataInner.Builder loggedUser(LoggedUserExtensions loggedUser) {
      this.instance.loggedUser(loggedUser);
      return this;
    }
    
    public ResponseConsentReadExtensionsDataInner.Builder requestDateTime(OffsetDateTime requestDateTime) {
      this.instance.requestDateTime(requestDateTime);
      return this;
    }
    
    public ResponseConsentReadExtensionsDataInner.Builder previousExpirationDateTime(OffsetDateTime previousExpirationDateTime) {
      this.instance.previousExpirationDateTime(previousExpirationDateTime);
      return this;
    }
    
    public ResponseConsentReadExtensionsDataInner.Builder xFapiCustomerIpAddress(String xFapiCustomerIpAddress) {
      this.instance.xFapiCustomerIpAddress(xFapiCustomerIpAddress);
      return this;
    }
    
    public ResponseConsentReadExtensionsDataInner.Builder xCustomerUserAgent(String xCustomerUserAgent) {
      this.instance.xCustomerUserAgent(xCustomerUserAgent);
      return this;
    }
    
    /**
    * returns a built ResponseConsentReadExtensionsDataInner instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseConsentReadExtensionsDataInner build() {
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
  public static ResponseConsentReadExtensionsDataInner.Builder builder() {
    return new ResponseConsentReadExtensionsDataInner.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseConsentReadExtensionsDataInner.Builder toBuilder() {
    ResponseConsentReadExtensionsDataInner.Builder builder = new ResponseConsentReadExtensionsDataInner.Builder();
    return builder.copyOf(this);
  }

}

