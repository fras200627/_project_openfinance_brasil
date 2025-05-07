package com.tican.open.finance.template_consents_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tican.open.finance.template_consents_model.EnumRejectedBy;
import com.tican.open.finance.template_consents_model.ResponseConsentReadDataRejectionReason;
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
 * Objeto a ser retornado caso o consentimento seja rejeitado.
 */

@Schema(name = "ResponseConsentRead_data_rejection", description = "Objeto a ser retornado caso o consentimento seja rejeitado.")
@JsonTypeName("ResponseConsentRead_data_rejection")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class ResponseConsentReadDataRejection {

  private EnumRejectedBy rejectedBy;

  private ResponseConsentReadDataRejectionReason reason;

  public ResponseConsentReadDataRejection() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public ResponseConsentReadDataRejection(EnumRejectedBy rejectedBy, ResponseConsentReadDataRejectionReason reason) {
    this.rejectedBy = rejectedBy;
    this.reason = reason;
  }

  public ResponseConsentReadDataRejection rejectedBy(EnumRejectedBy rejectedBy) {
    this.rejectedBy = rejectedBy;
    return this;
  }

  /**
   * Get rejectedBy
   * @return rejectedBy
   */
  @NotNull @Valid 
  @Schema(name = "rejectedBy", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("rejectedBy")
  public EnumRejectedBy getRejectedBy() {
    return rejectedBy;
  }

  public void setRejectedBy(EnumRejectedBy rejectedBy) {
    this.rejectedBy = rejectedBy;
  }

  public ResponseConsentReadDataRejection reason(ResponseConsentReadDataRejectionReason reason) {
    this.reason = reason;
    return this;
  }

  /**
   * Get reason
   * @return reason
   */
  @NotNull @Valid 
  @Schema(name = "reason", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("reason")
  public ResponseConsentReadDataRejectionReason getReason() {
    return reason;
  }

  public void setReason(ResponseConsentReadDataRejectionReason reason) {
    this.reason = reason;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseConsentReadDataRejection responseConsentReadDataRejection = (ResponseConsentReadDataRejection) o;
    return Objects.equals(this.rejectedBy, responseConsentReadDataRejection.rejectedBy) &&
        Objects.equals(this.reason, responseConsentReadDataRejection.reason);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rejectedBy, reason);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseConsentReadDataRejection {\n");
    sb.append("    rejectedBy: ").append(toIndentedString(rejectedBy)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
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

    private ResponseConsentReadDataRejection instance;

    public Builder() {
      this(new ResponseConsentReadDataRejection());
    }

    protected Builder(ResponseConsentReadDataRejection instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ResponseConsentReadDataRejection value) { 
      this.instance.setRejectedBy(value.rejectedBy);
      this.instance.setReason(value.reason);
      return this;
    }

    public ResponseConsentReadDataRejection.Builder rejectedBy(EnumRejectedBy rejectedBy) {
      this.instance.rejectedBy(rejectedBy);
      return this;
    }
    
    public ResponseConsentReadDataRejection.Builder reason(ResponseConsentReadDataRejectionReason reason) {
      this.instance.reason(reason);
      return this;
    }
    
    /**
    * returns a built ResponseConsentReadDataRejection instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ResponseConsentReadDataRejection build() {
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
  public static ResponseConsentReadDataRejection.Builder builder() {
    return new ResponseConsentReadDataRejection.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ResponseConsentReadDataRejection.Builder toBuilder() {
    ResponseConsentReadDataRejection.Builder builder = new ResponseConsentReadDataRejection.Builder();
    return builder.copyOf(this);
  }

}

