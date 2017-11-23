package com.tenpines.advancetdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SupplierImporterTest {

  private Enviroment enviroment;

  private boolean transientEnviroment = true;

  @Test
  public void importsValidDataCorrectly() throws IOException {
    new SupplierImporter(enviroment).process(validDateReader());
    assertTrue(enviroment.listCustomers().size() == 2);
    assertTrue(enviroment.listSuppliers().size() == 2);
    validateCustomer(customerIdentifiedAs("D", "5456774"), "Jose", "Lopez", "5456774", "D");
    validateCustomer(customerIdentifiedAs("D", "22333444"), "John", "Doe", "22333444", "D");
    validateSupplier(supplierIdentifiedAs("D", "123"), "Coca", "123", "D");
    validateSupplier(supplierIdentifiedAs("D", "22-22222222-7"), "Pepsi", "22-22222222-7", "D");
  }

  public void validateSupplier(SupplierDTO supplierDTO, String name, String idnumber, String type) {
    assertEquals(supplierDTO.getName(), name);
    assertEquals(supplierDTO.getIdentificationNumber(), idnumber);
    assertEquals(supplierDTO.getIdentificationType(), type);
  }

  public void validateCustomer(CustomerDTO customerDTO, String name, String lastname,
      String idnumber, String type) {
    assertEquals(customerDTO.getFirstName(), name);
    assertEquals(customerDTO.getIdentificationNumber(), idnumber);
    assertEquals(customerDTO.getLastName(), lastname);
    assertEquals(customerDTO.getIdentificationType(), type);
  }

  public StringReader validDateReader() { /*Listo*/
    StringWriter writer = new StringWriter();
    writer.write("S,Coca,D,123\n");
    writer.write("NC,Jose,Lopez,D,5456774\n");
    writer.write("NC,John,Doe,D,22333444\n");
    writer.write("A,San Martin,3322,Olivos,1636,BsAs\n");
    writer.write("A,Maipu,888,Florida,1122,Buenos Aire\n");
    writer.write("S,Pepsi,D,22-22222222-7\n");
    writer.write("A,Alem,1122,CABA,1001,CABA\n");

    StringReader fileReader = new StringReader(writer.getBuffer().toString());
    return fileReader;
  }

  public SupplierDTO supplierIdentifiedAs(String idType, String idNumber) {
    List<SupplierDTO> suppliers = enviroment.supplierIdentifiedAs(idType, idNumber);
    assertEquals(1, suppliers.size());
    SupplierDTO supplier = suppliers.get(0);
    return supplier;
  }

  public CustomerDTO customerIdentifiedAs(String idType, String idNumber) {
    List<CustomerDTO> customers;
    CustomerDTO customer;
    customers = enviroment.customerIdentifiedAs(idType, idNumber);
    assertEquals(1, customers.size());
    customer = customers.get(0);
    return customer;
  }

  @After
  public void closeSession() {
    enviroment.finalize();
  }

  @Before
  public void openSession() {
    if (transientEnviroment) {
      enviroment = new TransientEnviroment();
    } else {
      enviroment = new DBEnviroment();
    }
  }

}
