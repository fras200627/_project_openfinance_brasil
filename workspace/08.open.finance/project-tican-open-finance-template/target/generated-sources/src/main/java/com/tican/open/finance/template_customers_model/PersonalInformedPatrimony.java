package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_customers_model.InformedPatrimonyAmount;
import java.math.BigDecimal;
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
 * PersonalInformedPatrimony
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class PersonalInformedPatrimony {

  private InformedPatrimonyAmount amount;

  private BigDecimal year;

  public PersonalInformedPatrimony() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public PersonalInformedPatrimony(InformedPatrimonyAmount amount, BigDecimal year) {
    this.amount = amount;
    this.year = year;
  }

  public PersonalInformedPatrimony amount(InformedPatrimonyAmount amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
   */
  @NotNull @Valid 
  @Schema(name = "amount", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("amount")
  public InformedPatrimonyAmount getAmount() {
    return amount;
  }

  public void setAmount(InformedPatrimonyAmount amount) {
    this.amount = amount;
  }

  public PersonalInformedPatrimony year(BigDecimal year) {
    this.year = year;
    return this;
  }

  /**
   * Ano de referência da Renda, conforme especificação RFC-3339.
   * maximum: 9999
   * @return year
   */
  @NotNull @Valid @DecimalMax("9999") 
  @Schema(name = "year", example = "2010", description = "Ano de referência da Renda, conforme especificação RFC-3339.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("year")
  public BigDecimal getYear() {
    return year;
  }

  public void setYear(BigDecimal year) {
    this.year = year;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonalInformedPatrimony personalInformedPatrimony = (PersonalInformedPatrimony) o;
    return Objects.equals(this.amount, personalInformedPatrimony.amount) &&
        Objects.equals(this.year, personalInformedPatrimony.year);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, year);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalInformedPatrimony {\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
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

    private PersonalInformedPatrimony instance;

    public Builder() {
      this(new PersonalInformedPatrimony());
    }

    protected Builder(PersonalInformedPatrimony instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PersonalInformedPatrimony value) { 
      this.instance.setAmount(value.amount);
      this.instance.setYear(value.year);
      return this;
    }

    public PersonalInformedPatrimony.Builder amount(InformedPatrimonyAmount amount) {
      this.instance.amount(amount);
      return this;
    }
    
    public PersonalInformedPatrimony.Builder year(BigDecimal year) {
      this.instance.year(year);
      return this;
    }
    
    /**
    * returns a built PersonalInformedPatrimony instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PersonalInformedPatrimony build() {
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
  public static PersonalInformedPatrimony.Builder builder() {
    return new PersonalInformedPatrimony.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PersonalInformedPatrimony.Builder toBuilder() {
    PersonalInformedPatrimony.Builder builder = new PersonalInformedPatrimony.Builder();
    return builder.copyOf(this);
  }

}

