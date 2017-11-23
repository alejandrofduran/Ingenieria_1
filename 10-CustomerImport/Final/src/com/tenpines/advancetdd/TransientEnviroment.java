package com.tenpines.advancetdd;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TransientEnviroment implements Enviroment {

  private Set<CustomerDTO> dtos = new HashSet<>();
  private Long first = 1l;
  private Set<SupplierDTO> supplierDTOS = new HashSet<>();

  @Override
  public Long persistCustomer(CustomerDTO newCustomer) {
    if (dtos.contains(newCustomer)) {
      return newCustomer.getId();
    }
    newCustomer.setId(first++);
    dtos.add(newCustomer);
    return newCustomer.getId();
  }

  @Override
  public List<CustomerDTO> listCustomers() {
    return new ArrayList<>(dtos);
  }

  @Override
  public List<CustomerDTO> customerIdentifiedAs(String idType, String idNumber) {
    List<CustomerDTO> list = new ArrayList<>();
    for (CustomerDTO dto : dtos) {
      if (dto.getIdentificationType().equals(idType) && dto.getIdentificationNumber()
          .equals(idNumber)) {
        list.add(dto);
      }
    }
    return list;
  }

  @Override
  public void finalize() {
    dtos = null;
  }

  @Override
  public Long persistSupplier(SupplierDTO supplierDTO) {
    if (!supplierDTOS.contains(supplierDTO)) {
      supplierDTO.setId(first++);
      supplierDTOS.add(supplierDTO);
    }
    dtos.addAll(supplierDTO.getCustomers());
    return supplierDTO.getId();
  }

  @Override
  public List<SupplierDTO> listSuppliers() {
    return new ArrayList<>(supplierDTOS);
  }

  @Override
  public List<SupplierDTO> supplierIdentifiedAs(String idType, String idNumber) {
    List<SupplierDTO> list = new ArrayList<>();
    for (SupplierDTO dto : supplierDTOS) {
      if (dto.getIdentificationType().equals(idType) && dto.getIdentificationNumber()
          .equals(idNumber)) {
        list.add(dto);
      }
    }
    return list;
  }

}
