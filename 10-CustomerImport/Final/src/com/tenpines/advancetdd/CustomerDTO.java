package com.tenpines.advancetdd;

import java.util.HashSet;
import java.util.Set;

public class CustomerDTO {

  private Long id;
  private String firstName;
  private String lastName;
  private String identificationType;
  private String identificationNumber;
  private Set<AddressDTO> addresses = new HashSet<>();

  public Long getId() {
    return id;
  }

  public CustomerDTO setId(long id) {
    this.id = id;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public CustomerDTO setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public CustomerDTO setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getIdentificationType() {
    return identificationType;
  }

  public CustomerDTO setIdentificationType(String identificationType) {
    this.identificationType = identificationType;
    return this;
  }

  public String getIdentificationNumber() {
    return identificationNumber;
  }

  public CustomerDTO setIdentificationNumber(String identificationNumber) {
    this.identificationNumber = identificationNumber;
    return this;
  }

  public Set<AddressDTO> getAddresses() {
    return addresses;
  }

  public CustomerDTO setAddresses(Set<AddressDTO> addresses) {
    this.addresses = addresses;
    return this;
  }

  public int numberOfAddresses() {
    return addresses.size();
  }

  public AddressDTO addressAt(String aStreetName) {
    for (AddressDTO address : addresses) {
      if (address.isAt(aStreetName)) {
        return address;
      }
    }
    throw new RuntimeException("no se encontro...");
  }

  public boolean addressesIsEmpty() {
    return addresses.isEmpty();
  }

  public void addAddress(AddressDTO newAddress) {
    addresses.add(newAddress);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CustomerDTO dto = (CustomerDTO) o;

    return id != null ? id.equals(dto.id) : dto.id == null;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}
