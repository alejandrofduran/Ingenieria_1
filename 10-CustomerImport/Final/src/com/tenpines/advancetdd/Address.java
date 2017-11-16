package com.tenpines.advancetdd;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "ADDRESSES")
public class Address {

  @Id
  @GeneratedValue
  private Long id;
  @NotEmpty
  private String streetName;
  @Min(1)
  private int streetNumber;
  @NotEmpty
  private String town;
  @Min(1000)
  private int zipCode;
  @NotEmpty
  private String province;

  public Address() {
  }

  public Address(String streetName) {
    this.streetName = streetName;
  }

  public String getStreetName() {
    return streetName;
  }

  public Address setStreetName(String streetName) {
    this.streetName = streetName;
    return this;
  }

  public int getStreetNumber() {
    return streetNumber;
  }

  public Address setStreetNumber(int streetNumber) {
    this.streetNumber = streetNumber;
    return this;
  }

  public String getTown() {
    return town;
  }

  public Address setTown(String town) {
    this.town = town;
    return this;
  }

  public int getZipCode() {
    return zipCode;
  }

  public Address setZipCode(int zipCode) {
    this.zipCode = zipCode;
    return this;
  }

  public String getProvince() {
    return province;
  }

  public Address setProvince(String province) {
    this.province = province;
    return this;
  }

  public boolean isAt(String aStreetName) {
    return streetName.equals(aStreetName);
  }

  public Address setId(Long id) {
    this.id = id;
    return this;
  }
}
