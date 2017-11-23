package com.tenpines.advancetdd;

public class CustomerImporter extends Importer {

  public CustomerImporter(Enviroment customerService) {
    super(customerService);
  }

  protected void processCurrent() {
    switch (record[0]) {
      case "A":
        if (hasNotImportedCustomer()) {
          throw new RuntimeException(Importer.ADDRESS_WITHOUT_CUSTOMER_ERROR_DESCRIPTION);
        }
        newCustomer.addAddress(addressParser.parseRecord(record));
        enviroment.persistCustomer(newCustomer);
        break;
      case "C":
        newCustomer = customerParser.parseRecord(record);
        newCustomer.setId(enviroment.persistCustomer(newCustomer));
        break;
      default:
        throw new RuntimeException(Importer.INVALID_RECORD_TYPE);
    }
  }
}
