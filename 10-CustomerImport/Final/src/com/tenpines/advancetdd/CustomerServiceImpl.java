package com.tenpines.advancetdd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class CustomerServiceImpl implements CustomerService {

  private Session session;

  public CustomerServiceImpl() {
    Configuration configuration = new Configuration();
    configuration.configure();

    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
        .applySettings(configuration.getProperties()).buildServiceRegistry();
    SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    session = sessionFactory.openSession();
    session.beginTransaction();
  }

  @Override
  public void persist(Customer newCustomer) {
    session.persist(newCustomer);
  }

  private Customer assemblyCustomer(CustomerDTO dto) {
    Customer customer = new Customer().setFirstName(dto.getFirstName())
        .setIdentificationNumber(dto.getIdentificationNumber())
        .setIdentificationType(dto.getIdentificationType()).setLastName(dto.getLastName())
        .setAddresses(assemblyAdresses(dto.getAddresses()));
    return customer;
  }

  private Set<Address> assemblyAdresses(Set<AddressDTO> dtos) {
    Set<Address> addresses = new HashSet<>();
    for (AddressDTO dto : dtos) {
      Address address = new Address().setProvince(dto.getProvince())
          .setStreetName(dto.getStreetName()).setStreetNumber(dto.getStreetNumber())
          .setTown(dto.getTown()).setZipCode(dto.getZipCode()).setProvince(dto.getProvince());
      addresses.add(address);
    }
    return addresses;
  }

  @Override
  public List<CustomerDTO> list() {
    return new ArrayList<>(assemblyCustomerDTO(session.createCriteria(Customer.class).list()));
  }

  @Override
  public List<CustomerDTO> customerIdentifiedAs(String idType, String idNumber) {
    return new ArrayList<>(assemblyCustomerDTO(session.createCriteria(Customer.class).
        add(Restrictions.eq("identificationType", idType)).
        add(Restrictions.eq("identificationNumber", idNumber)).list()));
  }

  @Override
  public void finalize() {
    session.getTransaction().commit();
    session.close();
  }

  public CustomerDTO assemblyCustomerDTO(Customer customer) {
    CustomerDTO dto = new CustomerDTO().setFirstName(customer.getFirstName())
        .setId(customer.getId()).setIdentificationNumber(customer.getIdentificationNumber())
        .setIdentificationType(customer.getIdentificationType())
        .setLastName(customer.getLastName())
        .setAddresses(assemblyAddressDTO(customer.getAddresses()));
    return dto;
  }

  private Set<CustomerDTO> assemblyCustomerDTO(Collection<Customer> customers) {
    Set<CustomerDTO> dtos = new HashSet<>();
    if (customers != null) {
      for (Customer customer : customers) {
        dtos.add(assemblyCustomerDTO(customer));
      }
    }
    return dtos;
  }


  private Set<AddressDTO> assemblyAddressDTO(Collection<Address> addresses) {
    Set<AddressDTO> dtos = new HashSet<>();
    if (addresses != null) {
      for (Address address : addresses) {
        dtos.add(assemblyAddressDTO(address));
      }
    }
    return dtos;
  }

  private AddressDTO assemblyAddressDTO(Address address) {
    AddressDTO addressDTO = new AddressDTO().setProvince(address.getProvince())
        .setStreetName(address.getStreetName()).setStreetNumber(address.getStreetNumber())
        .setTown(address.getTown()).setZipCode(address.getZipCode());
    return addressDTO;
  }
}
