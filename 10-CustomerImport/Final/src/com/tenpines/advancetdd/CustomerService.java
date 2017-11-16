package com.tenpines.advancetdd;

import java.util.List;

public interface CustomerService {

  void persist(Customer newCustomer);

  List<CustomerDTO> list();

  List<CustomerDTO> customerIdentifiedAs(String idType, String idNumber);

  public void finalize();

}
