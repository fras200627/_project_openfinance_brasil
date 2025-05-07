package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tican.open.finance.template_customers_model.CustomerEmail;
import com.tican.open.finance.template_customers_model.CustomerPhone;
import com.tican.open.finance.template_customers_model.PersonalPostalAddress;
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

@Schema(name = "PersonalContacts", description = "Conjunto de informações referentes às formas para contatar o cliente.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class PersonalContacts {

  @Valid
  private List<@Valid PersonalPostalAddress> postalAddresses = new ArrayList<>();

  @Valid
  private List<@Valid CustomerPhone> phones = new ArrayList<>();

  @Valid
  private List<@Valid CustomerEmail> emails = new ArrayList<>();

  public PersonalContacts() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public PersonalContacts(List<@Valid PersonalPostalAddress> postalAddresses, List<@Valid CustomerPhone> phones, List<@Valid CustomerEmail> emails) {
    this.postalAddresses = postalAddresses;
    this.phones = phones;
    this.emails = emails;
  }

  public PersonalContacts postalAddresses(List<@Valid PersonalPostalAddress> postalAddresses) {
    this.postalAddresses = postalAddresses;
    return this;
  }

  public PersonalContacts addPostalAddressesItem(PersonalPostalAddress postalAddressesItem) {
    if (this.postalAddresses == null) {
      this.postalAddresses = new ArrayList<>();
    }
    this.postalAddresses.add(postalAddressesItem);
    return this;
  }

  /**
   * Lista de endereços da pessoa natural
   * @return postalAddresses
   */
  @NotNull @Valid @Size(min = 0) 
  @Schema(name = "postalAddresses", description = "Lista de endereços da pessoa natural", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("postalAddresses")
  public List<@Valid PersonalPostalAddress> getPostalAddresses() {
    return postalAddresses;
  }

  public void setPostalAddresses(List<@Valid PersonalPostalAddress> postalAddresses) {
    this.postalAddresses = postalAddresses;
  }

  public PersonalContacts phones(List<@Valid CustomerPhone> phones) {
    this.phones = phones;
    return this;
  }

  public PersonalContacts addPhonesItem(CustomerPhone phonesItem) {
    if (this.phones == null) {
      this.phones = new ArrayList<>();
    }
    this.phones.add(phonesItem);
    return this;
  }

  /**
   * Lista com telefones de contato da pessoa natural
   * @return phones
   */
  @NotNull @Valid @Size(min = 0) 
  @Schema(name = "phones", description = "Lista com telefones de contato da pessoa natural", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("phones")
  public List<@Valid CustomerPhone> getPhones() {
    return phones;
  }

  public void setPhones(List<@Valid CustomerPhone> phones) {
    this.phones = phones;
  }

  public PersonalContacts emails(List<@Valid CustomerEmail> emails) {
    this.emails = emails;
    return this;
  }

  public PersonalContacts addEmailsItem(CustomerEmail emailsItem) {
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
    PersonalContacts personalContacts = (PersonalContacts) o;
    return Objects.equals(this.postalAddresses, personalContacts.postalAddresses) &&
        Objects.equals(this.phones, personalContacts.phones) &&
        Objects.equals(this.emails, personalContacts.emails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(postalAddresses, phones, emails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalContacts {\n");
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

    private PersonalContacts instance;

    public Builder() {
      this(new PersonalContacts());
    }

    protected Builder(PersonalContacts instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PersonalContacts value) { 
      this.instance.setPostalAddresses(value.postalAddresses);
      this.instance.setPhones(value.phones);
      this.instance.setEmails(value.emails);
      return this;
    }

    public PersonalContacts.Builder postalAddresses(List<PersonalPostalAddress> postalAddresses) {
      this.instance.postalAddresses(postalAddresses);
      return this;
    }
    
    public PersonalContacts.Builder phones(List<CustomerPhone> phones) {
      this.instance.phones(phones);
      return this;
    }
    
    public PersonalContacts.Builder emails(List<CustomerEmail> emails) {
      this.instance.emails(emails);
      return this;
    }
    
    /**
    * returns a built PersonalContacts instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PersonalContacts build() {
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
  public static PersonalContacts.Builder builder() {
    return new PersonalContacts.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PersonalContacts.Builder toBuilder() {
    PersonalContacts.Builder builder = new PersonalContacts.Builder();
    return builder.copyOf(this);
  }

}

