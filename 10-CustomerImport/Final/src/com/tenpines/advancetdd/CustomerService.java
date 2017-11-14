package com.tenpines.advancetdd;

import java.util.List;

public interface CustomerService {

  void persist(Customer newCustomer);

  List<Customer> list();
}
