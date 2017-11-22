package com.tenpines.advancetdd;

import java.util.List;

public interface CustomerService {

  Long persist(CustomerDTO newCustomer);

  List<CustomerDTO> list();

  List<CustomerDTO> customerIdentifiedAs(String idType, String idNumber);

  public void finalize();

}
