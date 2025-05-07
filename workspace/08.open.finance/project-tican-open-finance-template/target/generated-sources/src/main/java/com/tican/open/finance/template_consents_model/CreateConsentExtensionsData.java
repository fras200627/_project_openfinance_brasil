package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.tican.open.finance.template_consents_model.BusinessEntityExtensions;
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
 * CreateConsentExtensionsData
 */

@JsonTypeName("CreateConsentExtensions_data")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class CreateConsentExtensionsData {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime expirationDateTime;

  private LoggedUserExtensions loggedUser;

  private @Nullable BusinessEntityExtensions businessEntity;

  public CreateConsentExtensionsData() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateConsentExtensionsData(LoggedUserExtensions loggedUser) {
    this.loggedUser = loggedUser;
  }

  /**
   * Constructor with all args parameters
   */
  public CreateConsentExtensionsData(@Nullable OffsetDateTime expirationDateTime, LoggedUserExtensions loggedUser, @Nullable BusinessEntityExtensions businessEntity) {
      this.expirationDateTime = expirationDateTime;
      this.loggedUser = loggedUser;
      this.businessEntity = businessEntity;
  }

  public CreateConsentExtensionsData expirationDateTime(OffsetDateTime expirationDateTime) {
    this.expirationDateTime = expirationDateTime;
    return this;
  }

  /**
   * Data e hora de expiração da permissão. Reflete a data limite de validade do consentimento. Uma string com data e hora conforme especificação RFC-3339, sempre com a utilização de timezone UTC (UTC time format).  [Restrição] De preenchimento obrigatório nos casos em que houver validade determinada. Em casos de consentimento com prazo indeterminado o campo não deve ser enviado.  Quando preenchido, o valor do campo não pode ultrapassar 12 meses. 
   * @return expirationDateTime
   */
  @Valid @Pattern(regexp = "(^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])T(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)Z$)") @Size(max = 20) 
  @Schema(name = "expirationDateTime", example = "2021-05-21T08:30Z", description = "Data e hora de expiração da permissão. Reflete a data limite de validade do consentimento. Uma string com data e hora conforme especificação RFC-3339, sempre com a utilização de timezone UTC (UTC time format).  [Restrição] De preenchimento obrigatório nos casos em que houver validade determinada. Em casos de consentimento com prazo indeterminado o campo não deve ser enviado.  Quando preenchido, o valor do campo não pode ultrapassar 12 meses. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("expirationDateTime")
  public OffsetDateTime getExpirationDateTime() {
    return expirationDateTime;
  }

  public void setExpirationDateTime(OffsetDateTime expirationDateTime) {
    this.expirationDateTime = expirationDateTime;
  }

  public CreateConsentExtensionsData loggedUser(LoggedUserExtensions loggedUser) {
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

  public CreateConsentExtensionsData businessEntity(BusinessEntityExtensions businessEntity) {
    this.businessEntity = businessEntity;
    return this;
  }

  /**
   * Get businessEntity
   * @return businessEntity
   */
  @Valid 
  @Schema(name = "businessEntity", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("businessEntity")
  public BusinessEntityExtensions getBusinessEntity() {
    return businessEntity;
  }

  public void setBusinessEntity(BusinessEntityExtensions businessEntity) {
    this.businessEntity = businessEntity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateConsentExtensionsData createConsentExtensionsData = (CreateConsentExtensionsData) o;
    return Objects.equals(this.expirationDateTime, createConsentExtensionsData.expirationDateTime) &&
        Objects.equals(this.loggedUser, createConsentExtensionsData.loggedUser) &&
        Objects.equals(this.businessEntity, createConsentExtensionsData.businessEntity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(expirationDateTime, loggedUser, businessEntity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateConsentExtensionsData {\n");
    sb.append("    expirationDateTime: ").append(toIndentedString(expirationDateTime)).append("\n");
    sb.append("    loggedUser: ").append(toIndentedString(loggedUser)).append("\n");
    sb.append("    businessEntity: ").append(toIndentedString(businessEntity)).append("\n");
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

    private CreateConsentExtensionsData instance;

    public Builder() {
      this(new CreateConsentExtensionsData());
    }

    protected Builder(CreateConsentExtensionsData instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CreateConsentExtensionsData value) { 
      this.instance.setExpirationDateTime(value.expirationDateTime);
      this.instance.setLoggedUser(value.loggedUser);
      this.instance.setBusinessEntity(value.businessEntity);
      return this;
    }

    public CreateConsentExtensionsData.Builder expirationDateTime(OffsetDateTime expirationDateTime) {
      this.instance.expirationDateTime(expirationDateTime);
      return this;
    }
    
    public CreateConsentExtensionsData.Builder loggedUser(LoggedUserExtensions loggedUser) {
      this.instance.loggedUser(loggedUser);
      return this;
    }
    
    public CreateConsentExtensionsData.Builder businessEntity(BusinessEntityExtensions businessEntity) {
      this.instance.businessEntity(businessEntity);
      return this;
    }
    
    /**
    * returns a built CreateConsentExtensionsData instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CreateConsentExtensionsData build() {
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
  public static CreateConsentExtensionsData.Builder builder() {
    return new CreateConsentExtensionsData.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CreateConsentExtensionsData.Builder toBuilder() {
    CreateConsentExtensionsData.Builder builder = new CreateConsentExtensionsData.Builder();
    return builder.copyOf(this);
  }

}

