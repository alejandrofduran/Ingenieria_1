package com.tenpines.advancetdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Consumer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CustomerImporterTest {

  private CustomerService customerService;

  @Test
  public void importsValidDataCorrectly() throws IOException {
    new CustomerImporter(customerService).process(validDateReader());
    assertEquals(2, numberOfCustomers());
    assertPepeSanchezWasImportedCorrectly();
    assertJuanPerezWasImportedCorrectly();
  }

  public int numberOfCustomers() {
    List<CustomerDTO> customers = customerService.list();
    int size = customers.size();
    return size;
  }

  @Test
  public void canNotImportAddressWithoutCustomer() throws IOException {
    CustomerImporter importer = new CustomerImporter(customerService);
    try {
      importer.process(addressWithoutCustomerData());
      fail();
    } catch (RuntimeException e) {
      assertEquals(
          CustomerImporter.ADDRESS_WITHOUT_CUSTOMER_ERROR_DESCRIPTION, e.getMessage());
      assertEquals(0, numberOfCustomers());
    }
  }

  @Test
  public void TestDoesNotImportRecordsStartingWithCButMoreCharacters() throws IOException {
    CustomerImporter importer = new CustomerImporter(customerService);
    try {
      importer.process(invalidCustomerRecordStartData());
      fail();
    } catch (RuntimeException e) {
      assertEquals(
          CustomerImporter.INVALID_RECORD_TYPE, e.getMessage());
      assertEquals(0, numberOfCustomers());
    }
  }

  @Test
  public void TestDoesNotImportRecordsStartingWithAButMoreCharacters() throws IOException {
    CustomerImporter importer = new CustomerImporter(customerService);
    try {
      importer.process(invalidAddressRecordStartData());
      fail();
    } catch (RuntimeException e) {
      assertEquals(
          CustomerImporter.INVALID_RECORD_TYPE, e.getMessage());
      CustomerDTO customer = customerIdentifiedAs("D", "22333444");
      assertTrue(customer.addressesIsEmpty());
    }
  }

  @Test
  public void TestCanNotImportAddressRecordWithLessThanSixFields() throws IOException {
    CustomerImporter importer = new CustomerImporter(customerService);
    try {
      importer.process(addressRecordWithLessThanSixFields());
      fail();
    } catch (RuntimeException e) {
      assertEquals(
          CustomerImporter.INVALID_ADDRESS_RECORD, e.getMessage());
      CustomerDTO customer = customerIdentifiedAs("D", "22333444");
      assertTrue(customer.addressesIsEmpty());
    }

  }

  @Test
  public void TestCanNotImportAddressRecordWithMoreThanSixFields() throws IOException {
    shouldFaildImporting(addressRecordWithMoreThanSixFields(),
        e -> {
          assertEquals(CustomerImporter.INVALID_ADDRESS_RECORD, e.getMessage());
          CustomerDTO customer = customerIdentifiedAs("D", "22333444");
          assertTrue(customer.addressesIsEmpty());
        });

  }

  public <T extends Throwable> void shouldFaildImporting(Reader data,
      Consumer<T> assertClosure) throws IOException {

    CustomerImporter importer = new CustomerImporter(customerService);
    try {
      importer.process(data);
      fail();
    } catch (Throwable e) {
      assertClosure.accept((T) e);
    }
  }

  @Test
  public void TestCanNotImportCustomerRecordWithLessThanFiveFields() throws IOException {
    CustomerImporter importer = new CustomerImporter(customerService);
    try {
      importer.process(customerRecordWithLessThanFiveFields());
      fail();
    } catch (RuntimeException e) {
      assertEquals(
          CustomerImporter.INVALID_CUSTOMER_RECORD, e.getMessage());
      assertEquals(0, numberOfCustomers());
    }

  }

  @Test
  public void TestCanNotImportCustomerRecordWithMoreThanFiveFields() throws IOException {
    CustomerImporter importer = new CustomerImporter(customerService);
    try {
      importer.process(customerRecordWithMoreThanFiveFields());
      fail();
    } catch (RuntimeException e) {
      assertEquals(
          CustomerImporter.INVALID_CUSTOMER_RECORD, e.getMessage());
      assertEquals(0, numberOfCustomers());
    }

  }

  public StringReader customerRecordWithMoreThanFiveFields() {
    StringWriter writer = new StringWriter();
    writer.write("C,Pepe,Sanchez,D,22333444,x\n");

    StringReader fileReader = new StringReader(writer.getBuffer().toString());
    return fileReader;
  }

  public StringReader customerRecordWithLessThanFiveFields() {
    StringWriter writer = new StringWriter();
    writer.write("C,Pepe,Sanchez,D\n");

    StringReader fileReader = new StringReader(writer.getBuffer().toString());
    return fileReader;
  }

  public StringReader addressRecordWithMoreThanSixFields() {
    StringWriter writer = new StringWriter();
    writer.write("C,Pepe,Sanchez,D,22333444\n");
    writer.write("A,San Martin,3322,Olivos,1636,BsAs,xx\n");

    StringReader fileReader = new StringReader(writer.getBuffer().toString());
    return fileReader;
  }

  public StringReader addressRecordWithLessThanSixFields() {
    StringWriter writer = new StringWriter();
    writer.write("C,Pepe,Sanchez,D,22333444\n");
    writer.write("A,San Martin,3322,Olivos,1636\n");

    StringReader fileReader = new StringReader(writer.getBuffer().toString());
    return fileReader;
  }

  public StringReader invalidAddressRecordStartData() {
    StringWriter writer = new StringWriter();
    writer.write("C,Pepe,Sanchez,D,22333444\n");
    writer.write("AA,San Martin,3322,Olivos,1636,BsAs\n");

    StringReader fileReader = new StringReader(writer.getBuffer().toString());
    return fileReader;
  }

  public StringReader invalidCustomerRecordStartData() {
    StringWriter writer = new StringWriter();
    writer.write("CC,Pepe,Sanchez,D,22333444\n");

    StringReader fileReader = new StringReader(writer.getBuffer().toString());
    return fileReader;
  }

  public StringReader addressWithoutCustomerData() {
    StringWriter writer = new StringWriter();
    writer.write("A,San Martin,3322,Olivos,1636,BsAs\n");

    StringReader fileReader = new StringReader(writer.getBuffer().toString());
    return fileReader;
  }

  public StringReader validDateReader() {
    StringWriter writer = new StringWriter();
    writer.write("C,Pepe,Sanchez,D,22333444\n");
    writer.write("A,San Martin,3322,Olivos,1636,BsAs\n");
    writer.write("A,Maipu,888,Florida,1122,Buenos Aires\n");
    writer.write("C,Juan,Perez,C,23-25666777-9\n");
    writer.write("A,Alem,1122,CABA,1001,CABA\n");

    StringReader fileReader = new StringReader(writer.getBuffer().toString());
    return fileReader;
  }

  public void assertJuanPerezWasImportedCorrectly() {
    CustomerDTO customer;
    AddressDTO address;
    customer = customerIdentifiedAs("C", "23-25666777-9");
    assertEquals("Juan", customer.getFirstName());
    assertEquals("Perez", customer.getLastName());
    assertEquals("C", customer.getIdentificationType());
    assertEquals("23-25666777-9", customer.getIdentificationNumber());

    assertEquals(1, customer.numberOfAddresses());
    address = customer.addressAt("Alem");
    assertEquals(1122, address.getStreetNumber());
    assertEquals("CABA", address.getTown());
    assertEquals(1001, address.getZipCode());
    assertEquals("CABA", address.getProvince());
  }

  public CustomerDTO customerIdentifiedAs(String idType, String idNumber) {
    List<CustomerDTO> customers;
    CustomerDTO customer;
    customers = customerService.customerIdentifiedAs(idType, idNumber);
    assertEquals(1, customers.size());
    customer = customers.get(0);
    return customer;
  }

  public void assertPepeSanchezWasImportedCorrectly() {

    CustomerDTO customer = customerIdentifiedAs("D", "22333444");
    assertEquals("Pepe", customer.getFirstName());
    assertEquals("Sanchez", customer.getLastName());
    assertEquals("D", customer.getIdentificationType());
    assertEquals("22333444", customer.getIdentificationNumber());

    assertEquals(2, customer.numberOfAddresses());
    AddressDTO address = customer.addressAt("San Martin");
    assertEquals(3322, address.getStreetNumber());
    assertEquals("Olivos", address.getTown());
    assertEquals(1636, address.getZipCode());
    assertEquals("BsAs", address.getProvince());

    address = customer.addressAt("Maipu");
    assertEquals(888, address.getStreetNumber());
    assertEquals("Florida", address.getTown());
    assertEquals(1122, address.getZipCode());
    assertEquals("Buenos Aires", address.getProvince());
  }

  @After
  public void closeSession() {
    customerService.finalize();
  }

  @Before
  public void openSession() {
    customerService = new CustomerServiceImpl();
  }

}
