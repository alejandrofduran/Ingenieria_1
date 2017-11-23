package com.tenpines.advancetdd;

import java.util.List;

public interface Enviroment {

  Long persistCustomer(CustomerDTO newCustomer);

  List<CustomerDTO> list();

  List<CustomerDTO> customerIdentifiedAs(String idType, String idNumber);

  public void finalize();

  Long persistSupplier(SupplierDTO supplierDTO);
}
