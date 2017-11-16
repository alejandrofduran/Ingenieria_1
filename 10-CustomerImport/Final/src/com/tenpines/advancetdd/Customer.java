package com.tenpines.advancetdd;

import java.util.HashSet;
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
@Table(name = "CUSTOMERS")
public class Customer {

  @Id
  @GeneratedValue
  private long id;
  @NotEmpty
  private String firstName;
  @NotEmpty
  private String lastName;
  @Pattern(regexp = "D|C")
  private String identificationType;
  @NotEmpty
  private String identificationNumber;
  @OneToMany(cascade = CascadeType.ALL)
  private Set<Address> addresses;

  public Customer() {
    addresses = new HashSet<Address>();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public Customer setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public Customer setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getIdentificationType() {
    return identificationType;
  }

  public Customer setIdentificationType(String identificationType) {
    this.identificationType = identificationType;
    return this;
  }

  public String getIdentificationNumber() {
    return identificationNumber;
  }

  public Customer setIdentificationNumber(String identificationNumber) {
    this.identificationNumber = identificationNumber;
    return this;
  }

  public void addAddress(Address anAddress) {
    addresses.add(anAddress);
  }

  public Address firstAddress() {
    return addresses.iterator().next();
  }

  public int numberOfAddresses() {
    return addresses.size();
  }

  public Address addressAt(String aStreetName) {
    for (Address address : addresses) {
      if (address.isAt(aStreetName)) {
        return address;
      }
    }

    throw new RuntimeException("no se encontro...");
  }

  public boolean addressesIsEmpty() {
    return addresses.isEmpty();
  }


  public Set<Address> getAddresses() {
    return addresses;
  }

  public Customer setAddresses(Set<Address> addresses) {
    this.addresses = addresses;
    return this;
  }
}
