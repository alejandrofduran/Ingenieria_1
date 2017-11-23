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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AddressDTO that = (AddressDTO) o;

    if (streetNumber != that.streetNumber) {
      return false;
    }
    if (zipCode != that.zipCode) {
      return false;
    }
    if (streetName != null ? !streetName.equals(that.streetName) : that.streetName != null) {
      return false;
    }
    if (town != null ? !town.equals(that.town) : that.town != null) {
      return false;
    }
    return province != null ? province.equals(that.province) : that.province == null;
  }

  @Override
  public int hashCode() {
    int result = streetName != null ? streetName.hashCode() : 0;
    result = 31 * result + streetNumber;
    result = 31 * result + (town != null ? town.hashCode() : 0);
    result = 31 * result + zipCode;
    result = 31 * result + (province != null ? province.hashCode() : 0);
    return result;
  }
}
