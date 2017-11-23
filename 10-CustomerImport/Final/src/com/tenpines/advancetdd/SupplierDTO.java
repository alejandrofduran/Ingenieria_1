package com.tenpines.advancetdd;

import java.util.Set;

public class SupplierDTO {

  private String name;
  private Set<CustomerDTO> customers;
  private Long id;
  private String identificationType;
  private String identificationNumber;
  private Set<AddressDTO> addresses;


  public Set<CustomerDTO> getCustomers() {
    return customers;
  }

  public SupplierDTO setCustomers(Set<CustomerDTO> customers) {
    this.customers = customers;
    return this;
  }

  public long getId() {
    return id;
  }

  public SupplierDTO setId(Long id) {
    this.id = id;
    return this;
  }

  public SupplierDTO setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public SupplierDTO setName(String name) {
    this.name = name;
    return this;
  }

  public String getIdentificationType() {
    return identificationType;
  }

  public SupplierDTO setIdentificationType(String identificationType) {
    this.identificationType = identificationType;
    return this;
  }

  public String getIdentificationNumber() {
    return identificationNumber;
  }

  public SupplierDTO setIdentificationNumber(String identificationNumber) {
    this.identificationNumber = identificationNumber;
    return this;
  }

  public Set<AddressDTO> getAddresses() {
    return addresses;
  }

  public SupplierDTO setAddresses(Set<AddressDTO> addresses) {
    this.addresses = addresses;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SupplierDTO dto = (SupplierDTO) o;

    return id != null ? id.equals(dto.id) : dto.id == null;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}
