package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tican.open.finance.template_customers_model.EnumInformedRevenueFrequency;
import com.tican.open.finance.template_customers_model.InformedRevenueAmount;
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
 * InformedRevenue
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class InformedRevenue {

  private @Nullable EnumInformedRevenueFrequency frequency;

  private @Nullable String frequencyAdditionalInfo;

  private InformedRevenueAmount amount;

  private @Nullable BigDecimal year;

  public InformedRevenue() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public InformedRevenue(InformedRevenueAmount amount) {
    this.amount = amount;
  }

  /**
   * Constructor with all args parameters
   */
  public InformedRevenue(@Nullable EnumInformedRevenueFrequency frequency, @Nullable String frequencyAdditionalInfo, InformedRevenueAmount amount, @Nullable BigDecimal year) {
      this.frequency = frequency;
      this.frequencyAdditionalInfo = frequencyAdditionalInfo;
      this.amount = amount;
      this.year = year;
  }

  public InformedRevenue frequency(EnumInformedRevenueFrequency frequency) {
    this.frequency = frequency;
    return this;
  }

  /**
   * Get frequency
   * @return frequency
   */
  @Valid 
  @Schema(name = "frequency", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("frequency")
  public EnumInformedRevenueFrequency getFrequency() {
    return frequency;
  }

  public void setFrequency(EnumInformedRevenueFrequency frequency) {
    this.frequency = frequency;
  }

  public InformedRevenue frequencyAdditionalInfo(String frequencyAdditionalInfo) {
    this.frequencyAdditionalInfo = frequencyAdditionalInfo;
    return this;
  }

  /**
   * Texto livre para complementar informação relativa ao patrimonio.  [Restrição] Preencher quando frequency for igual OUTROS. 
   * @return frequencyAdditionalInfo
   */
  @Pattern(regexp = "[\\w\\W\\s]*") @Size(max = 100) 
  @Schema(name = "frequencyAdditionalInfo", example = "Informações adicionais", description = "Texto livre para complementar informação relativa ao patrimonio.  [Restrição] Preencher quando frequency for igual OUTROS. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("frequencyAdditionalInfo")
  public String getFrequencyAdditionalInfo() {
    return frequencyAdditionalInfo;
  }

  public void setFrequencyAdditionalInfo(String frequencyAdditionalInfo) {
    this.frequencyAdditionalInfo = frequencyAdditionalInfo;
  }

  public InformedRevenue amount(InformedRevenueAmount amount) {
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
  public InformedRevenueAmount getAmount() {
    return amount;
  }

  public void setAmount(InformedRevenueAmount amount) {
    this.amount = amount;
  }

  public InformedRevenue year(BigDecimal year) {
    this.year = year;
    return this;
  }

  /**
   * Ano de referência da Renda, conforme especificação RFC-3339.
   * maximum: 9999
   * @return year
   */
  @Valid @DecimalMax("9999") 
  @Schema(name = "year", example = "2010", description = "Ano de referência da Renda, conforme especificação RFC-3339.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    InformedRevenue informedRevenue = (InformedRevenue) o;
    return Objects.equals(this.frequency, informedRevenue.frequency) &&
        Objects.equals(this.frequencyAdditionalInfo, informedRevenue.frequencyAdditionalInfo) &&
        Objects.equals(this.amount, informedRevenue.amount) &&
        Objects.equals(this.year, informedRevenue.year);
  }

  @Override
  public int hashCode() {
    return Objects.hash(frequency, frequencyAdditionalInfo, amount, year);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InformedRevenue {\n");
    sb.append("    frequency: ").append(toIndentedString(frequency)).append("\n");
    sb.append("    frequencyAdditionalInfo: ").append(toIndentedString(frequencyAdditionalInfo)).append("\n");
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

    private InformedRevenue instance;

    public Builder() {
      this(new InformedRevenue());
    }

    protected Builder(InformedRevenue instance) {
      this.instance = instance;
    }

    protected Builder copyOf(InformedRevenue value) { 
      this.instance.setFrequency(value.frequency);
      this.instance.setFrequencyAdditionalInfo(value.frequencyAdditionalInfo);
      this.instance.setAmount(value.amount);
      this.instance.setYear(value.year);
      return this;
    }

    public InformedRevenue.Builder frequency(EnumInformedRevenueFrequency frequency) {
      this.instance.frequency(frequency);
      return this;
    }
    
    public InformedRevenue.Builder frequencyAdditionalInfo(String frequencyAdditionalInfo) {
      this.instance.frequencyAdditionalInfo(frequencyAdditionalInfo);
      return this;
    }
    
    public InformedRevenue.Builder amount(InformedRevenueAmount amount) {
      this.instance.amount(amount);
      return this;
    }
    
    public InformedRevenue.Builder year(BigDecimal year) {
      this.instance.year(year);
      return this;
    }
    
    /**
    * returns a built InformedRevenue instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public InformedRevenue build() {
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
  public static InformedRevenue.Builder builder() {
    return new InformedRevenue.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public InformedRevenue.Builder toBuilder() {
    InformedRevenue.Builder builder = new InformedRevenue.Builder();
    return builder.copyOf(this);
  }

}

