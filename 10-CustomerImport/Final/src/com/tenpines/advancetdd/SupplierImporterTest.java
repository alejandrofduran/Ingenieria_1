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
  }

  public StringReader validDateReader() { /*Listo*/
    StringWriter writer = new StringWriter();
    writer.write("S,Supplier1,D,123\n");
    writer.write("NC,Jose,Lopez,D,5456774\n");
    writer.write("NC,John,Doe,D,22333444\n");
    writer.write("A,San Martin,3322,Olivos,1636,BsAs\n");
    writer.write("A,Maipu,888,Florida,1122,Buenos Aire\n");
    writer.write("S,Supplier2,D,22-22222222-7\n");
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
