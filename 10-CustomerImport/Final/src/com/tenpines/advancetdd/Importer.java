package com.tenpines.advancetdd;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;

public abstract class Importer {

  public static final String ADDRESS_WITHOUT_CUSTOMER_ERROR_DESCRIPTION = "Direccion sin cliente";
  public static final String INVALID_RECORD_TYPE = "Registro invalido";
  public static final String INVALID_ADDRESS_RECORD = "Registro de direccion invalido";
  public static final String INVALID_CUSTOMER_RECORD = "Registro de cliente invalido";
  protected String line;
  protected LineNumberReader lineReader;
  protected String[] record;
  protected AddressParser addressParser = new AddressParser();
  protected CustomerParser customerParser = new CustomerParser();
  protected SupplierParser supplierParser = new SupplierParser();
  protected CustomerDTO newCustomer;
  protected Enviroment enviroment;
  protected SupplierDTO newSupplier;

  public Importer(Enviroment customerService) {
    this.enviroment = customerService;
  }

  public void process(Reader fileReader) throws IOException {
    lineReader = new LineNumberReader(fileReader);
    while (hasLineToProcess()) {
      createRecordFromLine();
      processCurrent();
    }
  }

  protected abstract void processCurrent();

  public void createRecordFromLine() {
    record = line.split(",");
  }


  public boolean hasLineToProcess() throws IOException {
    line = lineReader.readLine();
    boolean hasLine = line != null;
    return hasLine;
  }

  public boolean hasNotImportedCustomer() {
    return newCustomer == null;
  }

  public boolean hasNotImportedSupplier() {
    return newSupplier == null;
  }

}
