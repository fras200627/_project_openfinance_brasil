package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_customers_model.BusinessPostalAddress;
import com.tican.open.finance.template_customers_model.CustomerEmail;
import com.tican.open.finance.template_customers_model.CustomerPhone;
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
 * Conjunto de informações referentes às formas para contatar o cliente.
 */

@Schema(name = "BusinessContacts", description = "Conjunto de informações referentes às formas para contatar o cliente.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class BusinessContacts {

  @Valid
  private List<@Valid BusinessPostalAddress> postalAddresses = new ArrayList<>();

  @Valid
  private List<@Valid CustomerPhone> phones = new ArrayList<>();

  @Valid
  private List<@Valid CustomerEmail> emails = new ArrayList<>();

  public BusinessContacts() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public BusinessContacts(List<@Valid BusinessPostalAddress> postalAddresses, List<@Valid CustomerPhone> phones, List<@Valid CustomerEmail> emails) {
    this.postalAddresses = postalAddresses;
    this.phones = phones;
    this.emails = emails;
  }

  public BusinessContacts postalAddresses(List<@Valid BusinessPostalAddress> postalAddresses) {
    this.postalAddresses = postalAddresses;
    return this;
  }

  public BusinessContacts addPostalAddressesItem(BusinessPostalAddress postalAddressesItem) {
    if (this.postalAddresses == null) {
      this.postalAddresses = new ArrayList<>();
    }
    this.postalAddresses.add(postalAddressesItem);
    return this;
  }

  /**
   * Lista de endereços da pessoa jurídica
   * @return postalAddresses
   */
  @NotNull @Valid @Size(min = 0) 
  @Schema(name = "postalAddresses", description = "Lista de endereços da pessoa jurídica", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("postalAddresses")
  public List<@Valid BusinessPostalAddress> getPostalAddresses() {
    return postalAddresses;
  }

  public void setPostalAddresses(List<@Valid BusinessPostalAddress> postalAddresses) {
    this.postalAddresses = postalAddresses;
  }

  public BusinessContacts phones(List<@Valid CustomerPhone> phones) {
    this.phones = phones;
    return this;
  }

  public BusinessContacts addPhonesItem(CustomerPhone phonesItem) {
    if (this.phones == null) {
      this.phones = new ArrayList<>();
    }
    this.phones.add(phonesItem);
    return this;
  }

  /**
   * Lista com telefones de contato da pessoa jurídica
   * @return phones
   */
  @NotNull @Valid @Size(min = 0) 
  @Schema(name = "phones", description = "Lista com telefones de contato da pessoa jurídica", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("phones")
  public List<@Valid CustomerPhone> getPhones() {
    return phones;
  }

  public void setPhones(List<@Valid CustomerPhone> phones) {
    this.phones = phones;
  }

  public BusinessContacts emails(List<@Valid CustomerEmail> emails) {
    this.emails = emails;
    return this;
  }

  public BusinessContacts addEmailsItem(CustomerEmail emailsItem) {
    if (this.emails == null) {
      this.emails = new ArrayList<>();
    }
    this.emails.add(emailsItem);
    return this;
  }

  /**
   * Lista e-mails de contato
   * @return emails
   */
  @NotNull @Valid @Size(min = 0) 
  @Schema(name = "emails", description = "Lista e-mails de contato", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("emails")
  public List<@Valid CustomerEmail> getEmails() {
    return emails;
  }

  public void setEmails(List<@Valid CustomerEmail> emails) {
    this.emails = emails;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BusinessContacts businessContacts = (BusinessContacts) o;
    return Objects.equals(this.postalAddresses, businessContacts.postalAddresses) &&
        Objects.equals(this.phones, businessContacts.phones) &&
        Objects.equals(this.emails, businessContacts.emails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(postalAddresses, phones, emails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BusinessContacts {\n");
    sb.append("    postalAddresses: ").append(toIndentedString(postalAddresses)).append("\n");
    sb.append("    phones: ").append(toIndentedString(phones)).append("\n");
    sb.append("    emails: ").append(toIndentedString(emails)).append("\n");
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

    private BusinessContacts instance;

    public Builder() {
      this(new BusinessContacts());
    }

    protected Builder(BusinessContacts instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BusinessContacts value) { 
      this.instance.setPostalAddresses(value.postalAddresses);
      this.instance.setPhones(value.phones);
      this.instance.setEmails(value.emails);
      return this;
    }

    public BusinessContacts.Builder postalAddresses(List<BusinessPostalAddress> postalAddresses) {
      this.instance.postalAddresses(postalAddresses);
      return this;
    }
    
    public BusinessContacts.Builder phones(List<CustomerPhone> phones) {
      this.instance.phones(phones);
      return this;
    }
    
    public BusinessContacts.Builder emails(List<CustomerEmail> emails) {
      this.instance.emails(emails);
      return this;
    }
    
    /**
    * returns a built BusinessContacts instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BusinessContacts build() {
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
  public static BusinessContacts.Builder builder() {
    return new BusinessContacts.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BusinessContacts.Builder toBuilder() {
    BusinessContacts.Builder builder = new BusinessContacts.Builder();
    return builder.copyOf(this);
  }

}

