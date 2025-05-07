package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * PaychecksBankLink
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class PaychecksBankLink {

  private String employerName;

  private String employerCnpjCpf;

  private String paycheckBankCnpj;

  private String paycheckBankIspb;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate accountOpeningDate;

  public PaychecksBankLink() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public PaychecksBankLink(String employerName, String employerCnpjCpf, String paycheckBankCnpj, String paycheckBankIspb, LocalDate accountOpeningDate) {
    this.employerName = employerName;
    this.employerCnpjCpf = employerCnpjCpf;
    this.paycheckBankCnpj = paycheckBankCnpj;
    this.paycheckBankIspb = paycheckBankIspb;
    this.accountOpeningDate = accountOpeningDate;
  }

  public PaychecksBankLink employerName(String employerName) {
    this.employerName = employerName;
    return this;
  }

  /**
   * Nome do empregador conforme registrado na abertura da conta salário. O empregador pode ser pessoa natural ou pessoa jurídica, quando se tratar de pessoa jurídica, deve haver o envio da razão social. 
   * @return employerName
   */
  @NotNull @Pattern(regexp = "^(?!\\s)[\\w\\W\\s]*[^\\s]$") @Size(max = 80) 
  @Schema(name = "employerName", description = "Nome do empregador conforme registrado na abertura da conta salário. O empregador pode ser pessoa natural ou pessoa jurídica, quando se tratar de pessoa jurídica, deve haver o envio da razão social. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("employerName")
  public String getEmployerName() {
    return employerName;
  }

  public void setEmployerName(String employerName) {
    this.employerName = employerName;
  }

  public PaychecksBankLink employerCnpjCpf(String employerCnpjCpf) {
    this.employerCnpjCpf = employerCnpjCpf;
    return this;
  }

  /**
   * Documento do empregador (CNPJ/CPF), conforme registrado na abertura da conta salário. 
   * @return employerCnpjCpf
   */
  @NotNull @Pattern(regexp = "^\\d{14}$|^\\d{11}$") @Size(max = 14) 
  @Schema(name = "employerCnpjCpf", description = "Documento do empregador (CNPJ/CPF), conforme registrado na abertura da conta salário. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("employerCnpjCpf")
  public String getEmployerCnpjCpf() {
    return employerCnpjCpf;
  }

  public void setEmployerCnpjCpf(String employerCnpjCpf) {
    this.employerCnpjCpf = employerCnpjCpf;
  }

  public PaychecksBankLink paycheckBankCnpj(String paycheckBankCnpj) {
    this.paycheckBankCnpj = paycheckBankCnpj;
    return this;
  }

  /**
   * CNPJ da instituição financeira contratada para prestar serviço de pagamento de salário (banco-folha). 
   * @return paycheckBankCnpj
   */
  @NotNull @Pattern(regexp = "^\\d{14}$") @Size(max = 14) 
  @Schema(name = "paycheckBankCnpj", description = "CNPJ da instituição financeira contratada para prestar serviço de pagamento de salário (banco-folha). ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("paycheckBankCnpj")
  public String getPaycheckBankCnpj() {
    return paycheckBankCnpj;
  }

  public void setPaycheckBankCnpj(String paycheckBankCnpj) {
    this.paycheckBankCnpj = paycheckBankCnpj;
  }

  public PaychecksBankLink paycheckBankIspb(String paycheckBankIspb) {
    this.paycheckBankIspb = paycheckBankIspb;
    return this;
  }

  /**
   * Número ISPB (Identificador do Sistema de Pagamentos Brasileiros) do instituição financeira contratada para prestar serviço de pagamento de salário (banco-folha). 
   * @return paycheckBankIspb
   */
  @NotNull @Pattern(regexp = "^[0-9]{8}$") @Size(max = 8) 
  @Schema(name = "paycheckBankIspb", description = "Número ISPB (Identificador do Sistema de Pagamentos Brasileiros) do instituição financeira contratada para prestar serviço de pagamento de salário (banco-folha). ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("paycheckBankIspb")
  public String getPaycheckBankIspb() {
    return paycheckBankIspb;
  }

  public void setPaycheckBankIspb(String paycheckBankIspb) {
    this.paycheckBankIspb = paycheckBankIspb;
  }

  public PaychecksBankLink accountOpeningDate(LocalDate accountOpeningDate) {
    this.accountOpeningDate = accountOpeningDate;
    return this;
  }

  /**
   * Data de abertura da conta salário. 
   * @return accountOpeningDate
   */
  @NotNull @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])$") @Size(max = 10) 
  @Schema(name = "accountOpeningDate", description = "Data de abertura da conta salário. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("accountOpeningDate")
  public LocalDate getAccountOpeningDate() {
    return accountOpeningDate;
  }

  public void setAccountOpeningDate(LocalDate accountOpeningDate) {
    this.accountOpeningDate = accountOpeningDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaychecksBankLink paychecksBankLink = (PaychecksBankLink) o;
    return Objects.equals(this.employerName, paychecksBankLink.employerName) &&
        Objects.equals(this.employerCnpjCpf, paychecksBankLink.employerCnpjCpf) &&
        Objects.equals(this.paycheckBankCnpj, paychecksBankLink.paycheckBankCnpj) &&
        Objects.equals(this.paycheckBankIspb, paychecksBankLink.paycheckBankIspb) &&
        Objects.equals(this.accountOpeningDate, paychecksBankLink.accountOpeningDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(employerName, employerCnpjCpf, paycheckBankCnpj, paycheckBankIspb, accountOpeningDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaychecksBankLink {\n");
    sb.append("    employerName: ").append(toIndentedString(employerName)).append("\n");
    sb.append("    employerCnpjCpf: ").append(toIndentedString(employerCnpjCpf)).append("\n");
    sb.append("    paycheckBankCnpj: ").append(toIndentedString(paycheckBankCnpj)).append("\n");
    sb.append("    paycheckBankIspb: ").append(toIndentedString(paycheckBankIspb)).append("\n");
    sb.append("    accountOpeningDate: ").append(toIndentedString(accountOpeningDate)).append("\n");
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

    private PaychecksBankLink instance;

    public Builder() {
      this(new PaychecksBankLink());
    }

    protected Builder(PaychecksBankLink instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PaychecksBankLink value) { 
      this.instance.setEmployerName(value.employerName);
      this.instance.setEmployerCnpjCpf(value.employerCnpjCpf);
      this.instance.setPaycheckBankCnpj(value.paycheckBankCnpj);
      this.instance.setPaycheckBankIspb(value.paycheckBankIspb);
      this.instance.setAccountOpeningDate(value.accountOpeningDate);
      return this;
    }

    public PaychecksBankLink.Builder employerName(String employerName) {
      this.instance.employerName(employerName);
      return this;
    }
    
    public PaychecksBankLink.Builder employerCnpjCpf(String employerCnpjCpf) {
      this.instance.employerCnpjCpf(employerCnpjCpf);
      return this;
    }
    
    public PaychecksBankLink.Builder paycheckBankCnpj(String paycheckBankCnpj) {
      this.instance.paycheckBankCnpj(paycheckBankCnpj);
      return this;
    }
    
    public PaychecksBankLink.Builder paycheckBankIspb(String paycheckBankIspb) {
      this.instance.paycheckBankIspb(paycheckBankIspb);
      return this;
    }
    
    public PaychecksBankLink.Builder accountOpeningDate(LocalDate accountOpeningDate) {
      this.instance.accountOpeningDate(accountOpeningDate);
      return this;
    }
    
    /**
    * returns a built PaychecksBankLink instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PaychecksBankLink build() {
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
  public static PaychecksBankLink.Builder builder() {
    return new PaychecksBankLink.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PaychecksBankLink.Builder toBuilder() {
    PaychecksBankLink.Builder builder = new PaychecksBankLink.Builder();
    return builder.copyOf(this);
  }

}

