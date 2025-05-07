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
 * Referências para outros recusos da API requisitada.
 */

@Schema(name = "Links", description = "Referências para outros recusos da API requisitada.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class Links {

  private String self;

  private @Nullable String first;

  private @Nullable String prev;

  private @Nullable String next;

  private @Nullable String last;

  public Links() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Links(String self) {
    this.self = self;
  }

  /**
   * Constructor with all args parameters
   */
  public Links(String self, @Nullable String first, @Nullable String prev, @Nullable String next, @Nullable String last) {
      this.self = self;
      this.first = first;
      this.prev = prev;
      this.next = next;
      this.last = last;
  }

  public Links self(String self) {
    this.self = self;
    return this;
  }

  /**
   * URI completo que gerou a resposta atual.
   * @return self
   */
  @NotNull @Size(max = 2000) 
  @Schema(name = "self", example = "https://api.banco.com.br/open-banking/api/v1/resource", description = "URI completo que gerou a resposta atual.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("self")
  public String getSelf() {
    return self;
  }

  public void setSelf(String self) {
    this.self = self;
  }

  public Links first(String first) {
    this.first = first;
    return this;
  }

  /**
   * URI da primeira página que originou essa lista de resultados. Restrição - Obrigatório quando não for a primeira página da resposta
   * @return first
   */
  @Size(max = 2000) 
  @Schema(name = "first", example = "https://api.banco.com.br/open-banking/api/v1/resource", description = "URI da primeira página que originou essa lista de resultados. Restrição - Obrigatório quando não for a primeira página da resposta", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("first")
  public String getFirst() {
    return first;
  }

  public void setFirst(String first) {
    this.first = first;
  }

  public Links prev(String prev) {
    this.prev = prev;
    return this;
  }

  /**
   * URI da página anterior dessa lista de resultados. Restrição -  Obrigatório quando não for a primeira página da resposta
   * @return prev
   */
  @Size(max = 2000) 
  @Schema(name = "prev", example = "https://api.banco.com.br/open-banking/api/v1/resource", description = "URI da página anterior dessa lista de resultados. Restrição -  Obrigatório quando não for a primeira página da resposta", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prev")
  public String getPrev() {
    return prev;
  }

  public void setPrev(String prev) {
    this.prev = prev;
  }

  public Links next(String next) {
    this.next = next;
    return this;
  }

  /**
   * URI da próxima página dessa lista de resultados. Restrição - Obrigatório quando não for a última página da resposta
   * @return next
   */
  @Size(max = 2000) 
  @Schema(name = "next", example = "https://api.banco.com.br/open-banking/api/v1/resource", description = "URI da próxima página dessa lista de resultados. Restrição - Obrigatório quando não for a última página da resposta", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("next")
  public String getNext() {
    return next;
  }

  public void setNext(String next) {
    this.next = next;
  }

  public Links last(String last) {
    this.last = last;
    return this;
  }

  /**
   * URI da última página dessa lista de resultados. Restrição - Obrigatório quando não for a última página da resposta
   * @return last
   */
  @Size(max = 2000) 
  @Schema(name = "last", example = "https://api.banco.com.br/open-banking/api/v1/resource", description = "URI da última página dessa lista de resultados. Restrição - Obrigatório quando não for a última página da resposta", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("last")
  public String getLast() {
    return last;
  }

  public void setLast(String last) {
    this.last = last;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Links links = (Links) o;
    return Objects.equals(this.self, links.self) &&
        Objects.equals(this.first, links.first) &&
        Objects.equals(this.prev, links.prev) &&
        Objects.equals(this.next, links.next) &&
        Objects.equals(this.last, links.last);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, first, prev, next, last);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Links {\n");
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    first: ").append(toIndentedString(first)).append("\n");
    sb.append("    prev: ").append(toIndentedString(prev)).append("\n");
    sb.append("    next: ").append(toIndentedString(next)).append("\n");
    sb.append("    last: ").append(toIndentedString(last)).append("\n");
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

    private Links instance;

    public Builder() {
      this(new Links());
    }

    protected Builder(Links instance) {
      this.instance = instance;
    }

    protected Builder copyOf(Links value) { 
      this.instance.setSelf(value.self);
      this.instance.setFirst(value.first);
      this.instance.setPrev(value.prev);
      this.instance.setNext(value.next);
      this.instance.setLast(value.last);
      return this;
    }

    public Links.Builder self(String self) {
      this.instance.self(self);
      return this;
    }
    
    public Links.Builder first(String first) {
      this.instance.first(first);
      return this;
    }
    
    public Links.Builder prev(String prev) {
      this.instance.prev(prev);
      return this;
    }
    
    public Links.Builder next(String next) {
      this.instance.next(next);
      return this;
    }
    
    public Links.Builder last(String last) {
      this.instance.last(last);
      return this;
    }
    
    /**
    * returns a built Links instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public Links build() {
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
  public static Links.Builder builder() {
    return new Links.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public Links.Builder toBuilder() {
    Links.Builder builder = new Links.Builder();
    return builder.copyOf(this);
  }

}

