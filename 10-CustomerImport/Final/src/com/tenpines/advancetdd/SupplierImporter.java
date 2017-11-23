package com.tenpines.advancetdd;

public class SupplierImporter extends Importer {

  public SupplierImporter(Enviroment customerService) {
    super(customerService);
  }

  @Override
  protected void processCurrent() {
    switch (record[0]) {
      case "A":
        if (hasNotImportedSupplier()) {
          throw new RuntimeException("Direccion sin supplier");
        }
        newSupplier.getAddresses().add(addressParser.parseRecord(record));
        enviroment.persistSupplier(newSupplier);
        break;
      case "NC":
        newCustomer = customerParser.parseRecord(record);
        newSupplier.getCustomers().add(newCustomer);
        enviroment.persistSupplier(newSupplier);
        break;
      case "EC":
        newCustomer = enviroment.customerIdentifiedAs(record[1], record[2]).get(0);
        newSupplier.getCustomers().add(newCustomer);
        enviroment.persistSupplier(newSupplier);
        break;
      case "S":
        newSupplier = supplierParser.parseRecord(record);
        newSupplier.setId(enviroment.persistSupplier(newSupplier));
        break;
      default:
        throw new RuntimeException(Importer.INVALID_RECORD_TYPE);
    }
  }
}
