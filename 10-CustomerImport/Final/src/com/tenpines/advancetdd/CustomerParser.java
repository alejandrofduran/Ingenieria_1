package com.tenpines.advancetdd;

public class CustomerParser {

  public CustomerDTO parseRecord(String[] record) {
    if (invalidCustomerRecordSize(record)) {
      throw new RuntimeException(Importer.INVALID_CUSTOMER_RECORD);
    }
    CustomerDTO newCustomer = new CustomerDTO();
    newCustomer.setFirstName(record[1]);
    newCustomer.setLastName(record[2]);
    newCustomer.setIdentificationType(record[3]);
    newCustomer.setIdentificationNumber(record[4]);
    return newCustomer;
  }

  public boolean invalidCustomerRecordSize(String[] record) {
    return record.length != 5;
  }

}
