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
  public List<CustomerDTO> list() {
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
    if (supplierDTOS.contains(supplierDTO)) {
      return supplierDTO.getId();
    }
    supplierDTO.setId(first++);
    supplierDTOS.add(supplierDTO);
    return supplierDTO.getId();
  }

}
