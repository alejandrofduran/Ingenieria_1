package com.tenpines.advancetdd;

public class AddressDTO {

  private String streetName;
  private int streetNumber;
  private String town;
  private int zipCode;
  private String province;

  public String getStreetName() {
    return streetName;
  }

  public AddressDTO setStreetName(String streetName) {
    this.streetName = streetName;
    return this;
  }

  public int getStreetNumber() {
    return streetNumber;
  }

  public AddressDTO setStreetNumber(int streetNumber) {
    this.streetNumber = streetNumber;
    return this;
  }

  public String getTown() {
    return town;
  }

  public AddressDTO setTown(String town) {
    this.town = town;
    return this;
  }

  public int getZipCode() {
    return zipCode;
  }

  public AddressDTO setZipCode(int zipCode) {
    this.zipCode = zipCode;
    return this;
  }

  public String getProvince() {
    return province;
  }

  public AddressDTO setProvince(String province) {
    this.province = province;
    return this;
  }

  public boolean isAt(String aStreetName) {
    return streetName.equals(aStreetName);
  }
}
