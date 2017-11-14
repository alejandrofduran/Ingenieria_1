package com.tenpines.advancetdd;

import java.util.List;
import org.hibernate.Session;

public class CustomerServiceImpl implements CustomerService {

  private Session session;

  public CustomerServiceImpl(Session session) {
    this.session = session;
  }

  @Override
  public void persist(Customer newCustomer) {
    session.persist(newCustomer);
  }

  @Override
  public List<Customer> list() {
    return session.createCriteria(Customer.class).list();
  }
}
