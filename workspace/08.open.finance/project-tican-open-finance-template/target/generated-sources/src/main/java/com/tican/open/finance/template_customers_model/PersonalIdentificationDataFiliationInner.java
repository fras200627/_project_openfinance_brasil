package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tican.open.finance.template_customers_model.EnumFiliationType;
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
 * PersonalIdentificationDataFiliationInner
 */

@JsonTypeName("PersonalIdentificationData_filiation_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class PersonalIdentificationDataFiliationInner {

  private EnumFiliationType type;

  private String civilName;

  private @Nullable String socialName;

  public PersonalIdentificationDataFiliationInner() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PersonalIdentificationDataFiliationInner(EnumFiliationType type, String civilName) {
    this.type = type;
    this.civilName = civilName;
  }

  /**
   * Constructor with all args parameters
   */
  public PersonalIdentificationDataFiliationInner(EnumFiliationType type, String civilName, @Nullable String socialName) {
      this.type = type;
      this.civilName = civilName;
      this.socialName = socialName;
  }

  public PersonalIdentificationDataFiliationInner type(EnumFiliationType type) {
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
  public EnumFiliationType getType() {
    return type;
  }

  public void setType(EnumFiliationType type) {
    this.type = type;
  }

  public PersonalIdentificationDataFiliationInner civilName(String civilName) {
    this.civilName = civilName;
    return this;
  }

  /**
   * Nome civil completo da pessoa relativa à filiação. (Direito fundamental da pessoa, o nome civil é aquele atribuído à pessoa natural desde o registro de seu nascimento,  com o qual será identificada por toda a sua vida, bem como após a sua morte). 
   * @return civilName
   */
  @NotNull @Pattern(regexp = "^[\\w\\W]*$") @Size(max = 70) 
  @Schema(name = "civilName", example = "Marcelo Cláudio Fernandes", description = "Nome civil completo da pessoa relativa à filiação. (Direito fundamental da pessoa, o nome civil é aquele atribuído à pessoa natural desde o registro de seu nascimento,  com o qual será identificada por toda a sua vida, bem como após a sua morte). ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("civilName")
  public String getCivilName() {
    return civilName;
  }

  public void setCivilName(String civilName) {
    this.civilName = civilName;
  }

  public PersonalIdentificationDataFiliationInner socialName(String socialName) {
    this.socialName = socialName;
    return this;
  }

  /**
   * Nome social da pessoa natural, se houver.  (aquele pelo qual travestis e transexuais se reconhecem,  bem como são identificados por sua comunidade e em seu meio social, conforme Decreto Local). 
   * @return socialName
   */
  @Pattern(regexp = "^[\\w\\W]*$") @Size(max = 70) 
  @Schema(name = "socialName", description = "Nome social da pessoa natural, se houver.  (aquele pelo qual travestis e transexuais se reconhecem,  bem como são identificados por sua comunidade e em seu meio social, conforme Decreto Local). ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    PersonalIdentificationDataFiliationInner personalIdentificationDataFiliationInner = (PersonalIdentificationDataFiliationInner) o;
    return Objects.equals(this.type, personalIdentificationDataFiliationInner.type) &&
        Objects.equals(this.civilName, personalIdentificationDataFiliationInner.civilName) &&
        Objects.equals(this.socialName, personalIdentificationDataFiliationInner.socialName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, civilName, socialName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalIdentificationDataFiliationInner {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

    private PersonalIdentificationDataFiliationInner instance;

    public Builder() {
      this(new PersonalIdentificationDataFiliationInner());
    }

    protected Builder(PersonalIdentificationDataFiliationInner instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PersonalIdentificationDataFiliationInner value) { 
      this.instance.setType(value.type);
      this.instance.setCivilName(value.civilName);
      this.instance.setSocialName(value.socialName);
      return this;
    }

    public PersonalIdentificationDataFiliationInner.Builder type(EnumFiliationType type) {
      this.instance.type(type);
      return this;
    }
    
    public PersonalIdentificationDataFiliationInner.Builder civilName(String civilName) {
      this.instance.civilName(civilName);
      return this;
    }
    
    public PersonalIdentificationDataFiliationInner.Builder socialName(String socialName) {
      this.instance.socialName(socialName);
      return this;
    }
    
    /**
    * returns a built PersonalIdentificationDataFiliationInner instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PersonalIdentificationDataFiliationInner build() {
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
  public static PersonalIdentificationDataFiliationInner.Builder builder() {
    return new PersonalIdentificationDataFiliationInner.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PersonalIdentificationDataFiliationInner.Builder toBuilder() {
    PersonalIdentificationDataFiliationInner.Builder builder = new PersonalIdentificationDataFiliationInner.Builder();
    return builder.copyOf(this);
  }

}

