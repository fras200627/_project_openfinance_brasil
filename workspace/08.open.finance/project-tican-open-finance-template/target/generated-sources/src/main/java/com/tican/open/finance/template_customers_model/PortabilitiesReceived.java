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
 * PortabilitiesReceived
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class PortabilitiesReceived {

  private String employerName;

  private String employerCnpjCpf;

  private String paycheckBankDetainerCnpj;

  private String paycheckBankDetainerIspb;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate portabilityApprovalDate;

  public PortabilitiesReceived() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public PortabilitiesReceived(String employerName, String employerCnpjCpf, String paycheckBankDetainerCnpj, String paycheckBankDetainerIspb, LocalDate portabilityApprovalDate) {
    this.employerName = employerName;
    this.employerCnpjCpf = employerCnpjCpf;
    this.paycheckBankDetainerCnpj = paycheckBankDetainerCnpj;
    this.paycheckBankDetainerIspb = paycheckBankDetainerIspb;
    this.portabilityApprovalDate = portabilityApprovalDate;
  }

  public PortabilitiesReceived employerName(String employerName) {
    this.employerName = employerName;
    return this;
  }

  /**
   * Nome do empregador conforme recebido pela comunicação de portabilidade. O empregador pode ser pessoa natural ou pessoa jurídica, quando se tratar de pessoa jurídica, deve haver o envio da razão social. 
   * @return employerName
   */
  @NotNull @Pattern(regexp = "^(?!\\s)[\\w\\W\\s]*[^\\s]$") @Size(max = 80) 
  @Schema(name = "employerName", description = "Nome do empregador conforme recebido pela comunicação de portabilidade. O empregador pode ser pessoa natural ou pessoa jurídica, quando se tratar de pessoa jurídica, deve haver o envio da razão social. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("employerName")
  public String getEmployerName() {
    return employerName;
  }

  public void setEmployerName(String employerName) {
    this.employerName = employerName;
  }

  public PortabilitiesReceived employerCnpjCpf(String employerCnpjCpf) {
    this.employerCnpjCpf = employerCnpjCpf;
    return this;
  }

  /**
   * Número de inscrição (CPF/CNPJ) do empregador (contratante dos serviços de pagamento), conforme recebido pela comunicação de portabilidade. 
   * @return employerCnpjCpf
   */
  @NotNull @Pattern(regexp = "^\\d{14}$|^\\d{11}$") @Size(max = 14) 
  @Schema(name = "employerCnpjCpf", description = "Número de inscrição (CPF/CNPJ) do empregador (contratante dos serviços de pagamento), conforme recebido pela comunicação de portabilidade. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("employerCnpjCpf")
  public String getEmployerCnpjCpf() {
    return employerCnpjCpf;
  }

  public void setEmployerCnpjCpf(String employerCnpjCpf) {
    this.employerCnpjCpf = employerCnpjCpf;
  }

  public PortabilitiesReceived paycheckBankDetainerCnpj(String paycheckBankDetainerCnpj) {
    this.paycheckBankDetainerCnpj = paycheckBankDetainerCnpj;
    return this;
  }

  /**
   * Número de inscrição no Cadastro Nacional da Pessoa Jurídica (CNPJ) do banco folha (instituição financeira detentora da conta salário) conforme recebido pela comunicação de portabilidade. 
   * @return paycheckBankDetainerCnpj
   */
  @NotNull @Pattern(regexp = "^\\d{14}$") @Size(max = 14) 
  @Schema(name = "paycheckBankDetainerCnpj", description = "Número de inscrição no Cadastro Nacional da Pessoa Jurídica (CNPJ) do banco folha (instituição financeira detentora da conta salário) conforme recebido pela comunicação de portabilidade. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("paycheckBankDetainerCnpj")
  public String getPaycheckBankDetainerCnpj() {
    return paycheckBankDetainerCnpj;
  }

  public void setPaycheckBankDetainerCnpj(String paycheckBankDetainerCnpj) {
    this.paycheckBankDetainerCnpj = paycheckBankDetainerCnpj;
  }

  public PortabilitiesReceived paycheckBankDetainerIspb(String paycheckBankDetainerIspb) {
    this.paycheckBankDetainerIspb = paycheckBankDetainerIspb;
    return this;
  }

  /**
   * Número do ISPB do Banco Folha (instituição financeira detentora da conta salário) conforme recebido pela comunicação de portabilidade. 
   * @return paycheckBankDetainerIspb
   */
  @NotNull @Pattern(regexp = "^[0-9]{8}$") @Size(max = 8) 
  @Schema(name = "paycheckBankDetainerIspb", description = "Número do ISPB do Banco Folha (instituição financeira detentora da conta salário) conforme recebido pela comunicação de portabilidade. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("paycheckBankDetainerIspb")
  public String getPaycheckBankDetainerIspb() {
    return paycheckBankDetainerIspb;
  }

  public void setPaycheckBankDetainerIspb(String paycheckBankDetainerIspb) {
    this.paycheckBankDetainerIspb = paycheckBankDetainerIspb;
  }

  public PortabilitiesReceived portabilityApprovalDate(LocalDate portabilityApprovalDate) {
    this.portabilityApprovalDate = portabilityApprovalDate;
    return this;
  }

  /**
   * Data de aprovação da portabilidade, conforme recebido pela comunicação de portabilidade.  Obs.: somente devem ser compartilhadas solicitações aprovadas, mesmo que de forma compulsória. 
   * @return portabilityApprovalDate
   */
  @NotNull @Valid @Pattern(regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])$") @Size(max = 10) 
  @Schema(name = "portabilityApprovalDate", description = "Data de aprovação da portabilidade, conforme recebido pela comunicação de portabilidade.  Obs.: somente devem ser compartilhadas solicitações aprovadas, mesmo que de forma compulsória. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("portabilityApprovalDate")
  public LocalDate getPortabilityApprovalDate() {
    return portabilityApprovalDate;
  }

  public void setPortabilityApprovalDate(LocalDate portabilityApprovalDate) {
    this.portabilityApprovalDate = portabilityApprovalDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PortabilitiesReceived portabilitiesReceived = (PortabilitiesReceived) o;
    return Objects.equals(this.employerName, portabilitiesReceived.employerName) &&
        Objects.equals(this.employerCnpjCpf, portabilitiesReceived.employerCnpjCpf) &&
        Objects.equals(this.paycheckBankDetainerCnpj, portabilitiesReceived.paycheckBankDetainerCnpj) &&
        Objects.equals(this.paycheckBankDetainerIspb, portabilitiesReceived.paycheckBankDetainerIspb) &&
        Objects.equals(this.portabilityApprovalDate, portabilitiesReceived.portabilityApprovalDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(employerName, employerCnpjCpf, paycheckBankDetainerCnpj, paycheckBankDetainerIspb, portabilityApprovalDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PortabilitiesReceived {\n");
    sb.append("    employerName: ").append(toIndentedString(employerName)).append("\n");
    sb.append("    employerCnpjCpf: ").append(toIndentedString(employerCnpjCpf)).append("\n");
    sb.append("    paycheckBankDetainerCnpj: ").append(toIndentedString(paycheckBankDetainerCnpj)).append("\n");
    sb.append("    paycheckBankDetainerIspb: ").append(toIndentedString(paycheckBankDetainerIspb)).append("\n");
    sb.append("    portabilityApprovalDate: ").append(toIndentedString(portabilityApprovalDate)).append("\n");
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

    private PortabilitiesReceived instance;

    public Builder() {
      this(new PortabilitiesReceived());
    }

    protected Builder(PortabilitiesReceived instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PortabilitiesReceived value) { 
      this.instance.setEmployerName(value.employerName);
      this.instance.setEmployerCnpjCpf(value.employerCnpjCpf);
      this.instance.setPaycheckBankDetainerCnpj(value.paycheckBankDetainerCnpj);
      this.instance.setPaycheckBankDetainerIspb(value.paycheckBankDetainerIspb);
      this.instance.setPortabilityApprovalDate(value.portabilityApprovalDate);
      return this;
    }

    public PortabilitiesReceived.Builder employerName(String employerName) {
      this.instance.employerName(employerName);
      return this;
    }
    
    public PortabilitiesReceived.Builder employerCnpjCpf(String employerCnpjCpf) {
      this.instance.employerCnpjCpf(employerCnpjCpf);
      return this;
    }
    
    public PortabilitiesReceived.Builder paycheckBankDetainerCnpj(String paycheckBankDetainerCnpj) {
      this.instance.paycheckBankDetainerCnpj(paycheckBankDetainerCnpj);
      return this;
    }
    
    public PortabilitiesReceived.Builder paycheckBankDetainerIspb(String paycheckBankDetainerIspb) {
      this.instance.paycheckBankDetainerIspb(paycheckBankDetainerIspb);
      return this;
    }
    
    public PortabilitiesReceived.Builder portabilityApprovalDate(LocalDate portabilityApprovalDate) {
      this.instance.portabilityApprovalDate(portabilityApprovalDate);
      return this;
    }
    
    /**
    * returns a built PortabilitiesReceived instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PortabilitiesReceived build() {
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
  public static PortabilitiesReceived.Builder builder() {
    return new PortabilitiesReceived.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PortabilitiesReceived.Builder toBuilder() {
    PortabilitiesReceived.Builder builder = new PortabilitiesReceived.Builder();
    return builder.copyOf(this);
  }

}

