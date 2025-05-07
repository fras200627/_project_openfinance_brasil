package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tican.open.finance.template_customers_model.EnumInformedIncomeFrequency;
import com.tican.open.finance.template_customers_model.InformedIncomeAmount;
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
 * InformedIncome
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class InformedIncome {

  private EnumInformedIncomeFrequency frequency;

  private InformedIncomeAmount amount;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate date;

  public InformedIncome() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public InformedIncome(EnumInformedIncomeFrequency frequency, InformedIncomeAmount amount, LocalDate date) {
    this.frequency = frequency;
    this.amount = amount;
    this.date = date;
  }

  public InformedIncome frequency(EnumInformedIncomeFrequency frequency) {
    this.frequency = frequency;
    return this;
  }

  /**
   * Get frequency
   * @return frequency
   */
  @NotNull @Valid 
  @Schema(name = "frequency", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("frequency")
  public EnumInformedIncomeFrequency getFrequency() {
    return frequency;
  }

  public void setFrequency(EnumInformedIncomeFrequency frequency) {
    this.frequency = frequency;
  }

  public InformedIncome amount(InformedIncomeAmount amount) {
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
  public InformedIncomeAmount getAmount() {
    return amount;
  }

  public void setAmount(InformedIncomeAmount amount) {
    this.amount = amount;
  }

  public InformedIncome date(LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Data da renda, conforme especificação RFC-3339.
   * @return date
   */
  @NotNull @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])$") @Size(max = 10) 
  @Schema(name = "date", example = "2021-05-21", description = "Data da renda, conforme especificação RFC-3339.", requiredMode = Schema.RequiredMode.REQUIRED)
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
    InformedIncome informedIncome = (InformedIncome) o;
    return Objects.equals(this.frequency, informedIncome.frequency) &&
        Objects.equals(this.amount, informedIncome.amount) &&
        Objects.equals(this.date, informedIncome.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(frequency, amount, date);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InformedIncome {\n");
    sb.append("    frequency: ").append(toIndentedString(frequency)).append("\n");
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

    private InformedIncome instance;

    public Builder() {
      this(new InformedIncome());
    }

    protected Builder(InformedIncome instance) {
      this.instance = instance;
    }

    protected Builder copyOf(InformedIncome value) { 
      this.instance.setFrequency(value.frequency);
      this.instance.setAmount(value.amount);
      this.instance.setDate(value.date);
      return this;
    }

    public InformedIncome.Builder frequency(EnumInformedIncomeFrequency frequency) {
      this.instance.frequency(frequency);
      return this;
    }
    
    public InformedIncome.Builder amount(InformedIncomeAmount amount) {
      this.instance.amount(amount);
      return this;
    }
    
    public InformedIncome.Builder date(LocalDate date) {
      this.instance.date(date);
      return this;
    }
    
    /**
    * returns a built InformedIncome instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public InformedIncome build() {
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
  public static InformedIncome.Builder builder() {
    return new InformedIncome.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public InformedIncome.Builder toBuilder() {
    InformedIncome.Builder builder = new InformedIncome.Builder();
    return builder.copyOf(this);
  }

}

