package com.tenpines.advancetdd;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "SUPPLIERS")
public class Supplier {

  @OneToMany(cascade = CascadeType.ALL)
  private Set<Customer> customers;
  @Id
  @GeneratedValue
  private long id;
  @Pattern(regexp = "D|C")
  private String identificationType;
  @NotEmpty
  private String identificationNumber;
  @OneToMany(cascade = CascadeType.ALL)
  private Set<Address> addresses;
  @NotEmpty
  private String name;


  public String getName() {
    return name;
  }

  public Supplier setName(String name) {
    this.name = name;
    return this;
  }

  public Set<Customer> getCustomers() {
    return customers;
  }

  public Supplier setCustomers(Set<Customer> customers) {
    this.customers = customers;
    return this;
  }

  public long getId() {
    return id;
  }

  public Supplier setId(long id) {
    this.id = id;
    return this;
  }

  public String getIdentificationType() {
    return identificationType;
  }

  public Supplier setIdentificationType(String identificationType) {
    this.identificationType = identificationType;
    return this;
  }

  public String getIdentificationNumber() {
    return identificationNumber;
  }

  public Supplier setIdentificationNumber(String identificationNumber) {
    this.identificationNumber = identificationNumber;
    return this;
  }

  public Set<Address> getAddresses() {
    return addresses;
  }

  public Supplier setAddresses(Set<Address> addresses) {
    this.addresses = addresses;
    return this;
  }
}
