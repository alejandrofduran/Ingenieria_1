package com.tenpines.advancetdd;

import java.util.List;

public class SupplierImporter extends Importer {

  public static final String CUSTOMER_NO_EXISTENTE = "customer no existente";

  public SupplierImporter(Enviroment enviroment) {
    super(enviroment);
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
        enviroment.persistCustomer(newCustomer);
        newSupplier.getCustomers().add(newCustomer);
        enviroment.persistSupplier(newSupplier);
        break;
      case "EC":
        List<CustomerDTO> customerDTOS = enviroment.customerIdentifiedAs(record[1], record[2]);
        if (customerDTOS == null || customerDTOS.isEmpty()) {
          throw new RuntimeException(CUSTOMER_NO_EXISTENTE);
        }
        newCustomer = customerDTOS.get(0);
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
