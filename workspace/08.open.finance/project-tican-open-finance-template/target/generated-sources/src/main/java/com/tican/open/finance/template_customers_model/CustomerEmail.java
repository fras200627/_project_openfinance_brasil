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
 * CustomerEmail
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class CustomerEmail {

  private Boolean isMain;

  private String email;

  public CustomerEmail() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public CustomerEmail(Boolean isMain, String email) {
    this.isMain = isMain;
    this.email = email;
  }

  public CustomerEmail isMain(Boolean isMain) {
    this.isMain = isMain;
    return this;
  }

  /**
   * Indica se o email informado é o principal
   * @return isMain
   */
  @NotNull 
  @Schema(name = "isMain", example = "true", description = "Indica se o email informado é o principal", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("isMain")
  public Boolean getIsMain() {
    return isMain;
  }

  public void setIsMain(Boolean isMain) {
    this.isMain = isMain;
  }

  public CustomerEmail email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Endereço de email
   * @return email
   */
  @NotNull @Size(max = 320) @javax.validation.constraints.Email 
  @Schema(name = "email", example = "karinafernandes-81@br.inter.net", description = "Endereço de email", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerEmail customerEmail = (CustomerEmail) o;
    return Objects.equals(this.isMain, customerEmail.isMain) &&
        Objects.equals(this.email, customerEmail.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isMain, email);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerEmail {\n");
    sb.append("    isMain: ").append(toIndentedString(isMain)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
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

    private CustomerEmail instance;

    public Builder() {
      this(new CustomerEmail());
    }

    protected Builder(CustomerEmail instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CustomerEmail value) { 
      this.instance.setIsMain(value.isMain);
      this.instance.setEmail(value.email);
      return this;
    }

    public CustomerEmail.Builder isMain(Boolean isMain) {
      this.instance.isMain(isMain);
      return this;
    }
    
    public CustomerEmail.Builder email(String email) {
      this.instance.email(email);
      return this;
    }
    
    /**
    * returns a built CustomerEmail instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CustomerEmail build() {
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
  public static CustomerEmail.Builder builder() {
    return new CustomerEmail.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CustomerEmail.Builder toBuilder() {
    CustomerEmail.Builder builder = new CustomerEmail.Builder();
    return builder.copyOf(this);
  }

}

