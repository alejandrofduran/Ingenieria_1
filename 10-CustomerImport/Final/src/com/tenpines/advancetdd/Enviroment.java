package com.tenpines.advancetdd;

import java.util.List;

public interface Enviroment {

  Long persistCustomer(CustomerDTO newCustomer);

  List<CustomerDTO> listCustomers();

  List<CustomerDTO> customerIdentifiedAs(String idType, String idNumber);

  public void finalize();

  Long persistSupplier(SupplierDTO supplierDTO);

  List<SupplierDTO> listSuppliers();

  List<SupplierDTO> supplierIdentifiedAs(String idType, String idNumber);
}
