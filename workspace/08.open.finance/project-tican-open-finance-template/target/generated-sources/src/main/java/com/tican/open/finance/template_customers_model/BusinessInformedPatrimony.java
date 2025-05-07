package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_customers_model.InformedPatrimonyAmount;
import java.time.LocalDate;
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
 * BusinessInformedPatrimony
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class BusinessInformedPatrimony {

  private InformedPatrimonyAmount amount;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate date;

  public BusinessInformedPatrimony() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public BusinessInformedPatrimony(InformedPatrimonyAmount amount, LocalDate date) {
    this.amount = amount;
    this.date = date;
  }

  public BusinessInformedPatrimony amount(InformedPatrimonyAmount amount) {
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

  public BusinessInformedPatrimony date(LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Data de referência do Patrimônio, conforme especificação RFC-3339.
   * @return date
   */
  @NotNull @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])$") @Size(max = 20) 
  @Schema(name = "date", example = "2021-05-21", description = "Data de referência do Patrimônio, conforme especificação RFC-3339.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("date")
  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BusinessInformedPatrimony businessInformedPatrimony = (BusinessInformedPatrimony) o;
    return Objects.equals(this.amount, businessInformedPatrimony.amount) &&
        Objects.equals(this.date, businessInformedPatrimony.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, date);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BusinessInformedPatrimony {\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
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

    private BusinessInformedPatrimony instance;

    public Builder() {
      this(new BusinessInformedPatrimony());
    }

    protected Builder(BusinessInformedPatrimony instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BusinessInformedPatrimony value) { 
      this.instance.setAmount(value.amount);
      this.instance.setDate(value.date);
      return this;
    }

    public BusinessInformedPatrimony.Builder amount(InformedPatrimonyAmount amount) {
      this.instance.amount(amount);
      return this;
    }
    
    public BusinessInformedPatrimony.Builder date(LocalDate date) {
      this.instance.date(date);
      return this;
    }
    
    /**
    * returns a built BusinessInformedPatrimony instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BusinessInformedPatrimony build() {
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
  public static BusinessInformedPatrimony.Builder builder() {
    return new BusinessInformedPatrimony.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BusinessInformedPatrimony.Builder toBuilder() {
    BusinessInformedPatrimony.Builder builder = new BusinessInformedPatrimony.Builder();
    return builder.copyOf(this);
  }

}

