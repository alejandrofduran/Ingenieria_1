package com.tenpines.advancetdd;

public class SupplierParser {

  public SupplierDTO parseRecord(String[] record) {
    if (record.length != 4) {
      throw new RuntimeException("Registro de supplier invalido");
    }
    return new SupplierDTO().setIdentificationNumber(record[3])
        .setIdentificationType(record[2]).setName(record[1]);
  }
}
