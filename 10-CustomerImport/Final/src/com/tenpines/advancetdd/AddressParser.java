package com.tenpines.advancetdd;

public class AddressParser {

  public AddressDTO parseRecord(String[] record) {
    if (invalidAddressRecordSize(record)) {
      throw new RuntimeException(Importer.INVALID_ADDRESS_RECORD);
    }
    AddressDTO newAddress = new AddressDTO();
    newAddress.setStreetName(record[1]);
    newAddress.setStreetNumber(Integer.parseInt(record[2]));
    newAddress.setTown(record[3]);
    newAddress.setZipCode(Integer.parseInt(record[4]));
    newAddress.setProvince(record[5]);
    return newAddress;
  }

  public boolean invalidAddressRecordSize(String[] record) {
    return record.length != 6;
  }
}
