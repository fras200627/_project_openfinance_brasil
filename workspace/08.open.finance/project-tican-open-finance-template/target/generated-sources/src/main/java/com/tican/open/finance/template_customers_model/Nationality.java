package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_customers_model.NationalityOtherDocument;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
 * Objeto que agrupa informações relativas à nacionalidade da Pessoa Natural
 */

@Schema(name = "Nationality", description = "Objeto que agrupa informações relativas à nacionalidade da Pessoa Natural")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class Nationality {

  private String otherNationalitiesInfo;

  @Valid
  private List<@Valid NationalityOtherDocument> documents = new ArrayList<>();

  public Nationality() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public Nationality(String otherNationalitiesInfo, List<@Valid NationalityOtherDocument> documents) {
    this.otherNationalitiesInfo = otherNationalitiesInfo;
    this.documents = documents;
  }

  public Nationality otherNationalitiesInfo(String otherNationalitiesInfo) {
    this.otherNationalitiesInfo = otherNationalitiesInfo;
    return this;
  }

  /**
   * Campo de preenchimento obrigatório caso o cliente não possua nacionalidade brasileira. Preencher indicando todas suas demais nacionalidades utilizando o código de pais de acordo com o código alpha3 do ISO-3166.p.ex.'CAN' 
   * @return otherNationalitiesInfo
   */
  @NotNull @Pattern(regexp = "^\\S[\\s\\S]*$") @Size(max = 40) 
  @Schema(name = "otherNationalitiesInfo", example = "CAN", description = "Campo de preenchimento obrigatório caso o cliente não possua nacionalidade brasileira. Preencher indicando todas suas demais nacionalidades utilizando o código de pais de acordo com o código alpha3 do ISO-3166.p.ex.'CAN' ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("otherNationalitiesInfo")
  public String getOtherNationalitiesInfo() {
    return otherNationalitiesInfo;
  }

  public void setOtherNationalitiesInfo(String otherNationalitiesInfo) {
    this.otherNationalitiesInfo = otherNationalitiesInfo;
  }

  public Nationality documents(List<@Valid NationalityOtherDocument> documents) {
    this.documents = documents;
    return this;
  }

  public Nationality addDocumentsItem(NationalityOtherDocument documentsItem) {
    if (this.documents == null) {
      this.documents = new ArrayList<>();
    }
    this.documents.add(documentsItem);
    return this;
  }

  /**
   * Lista que traz relação de documentos complementares de pessoas com nacionalidade diferente de brasileira
   * @return documents
   */
  @NotNull @Valid 
  @Schema(name = "documents", description = "Lista que traz relação de documentos complementares de pessoas com nacionalidade diferente de brasileira", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("documents")
  public List<@Valid NationalityOtherDocument> getDocuments() {
    return documents;
  }

  public void setDocuments(List<@Valid NationalityOtherDocument> documents) {
    this.documents = documents;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Nationality nationality = (Nationality) o;
    return Objects.equals(this.otherNationalitiesInfo, nationality.otherNationalitiesInfo) &&
        Objects.equals(this.documents, nationality.documents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(otherNationalitiesInfo, documents);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Nationality {\n");
    sb.append("    otherNationalitiesInfo: ").append(toIndentedString(otherNationalitiesInfo)).append("\n");
    sb.append("    documents: ").append(toIndentedString(documents)).append("\n");
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

    private Nationality instance;

    public Builder() {
      this(new Nationality());
    }

    protected Builder(Nationality instance) {
      this.instance = instance;
    }

    protected Builder copyOf(Nationality value) { 
      this.instance.setOtherNationalitiesInfo(value.otherNationalitiesInfo);
      this.instance.setDocuments(value.documents);
      return this;
    }

    public Nationality.Builder otherNationalitiesInfo(String otherNationalitiesInfo) {
      this.instance.otherNationalitiesInfo(otherNationalitiesInfo);
      return this;
    }
    
    public Nationality.Builder documents(List<NationalityOtherDocument> documents) {
      this.instance.documents(documents);
      return this;
    }
    
    /**
    * returns a built Nationality instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public Nationality build() {
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
  public static Nationality.Builder builder() {
    return new Nationality.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public Nationality.Builder toBuilder() {
    Nationality.Builder builder = new Nationality.Builder();
    return builder.copyOf(this);
  }

}

