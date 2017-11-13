package com.tenpines.advancetdd20;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;

import org.hibernate.Session;

public class CustomerImporter {

	public static final String ADDRESS_WITHOUT_CUSTOMER_ERROR_DESCRIPTION = "Direccion sin cliente";
	public static final String INVALID_RECORD_TYPE = "Registro invalido";
	public static final String INVALID_ADDRESS_RECORD = "Registro de direccion invalido";
	public static final String INVALID_CUSTOMER_RECORD = "Registro de cliente invalido";
	private String line;
	private LineNumberReader lineReader;
	private Session session;
	private Customer newCustomer;
	private String[] record;

	//10: Agregar constructor
	//11: change method signature
	//12: Inicializar field
	public CustomerImporter(Session session){
		this.session = session;
		
	}
	
	//13: Change method signature
	public void value(Reader fileReader) throws IOException{
		
		lineReader = new LineNumberReader(fileReader);
		while (hasLineToProcess()) {
			createRecordFromLine();
			parseRecord();
		}
	}

	public void parseRecord() {
		if (isCustomerRecord())
			parseCustomer();
		else if (isAddressRecord()) 
			parseAddress();
		else
			throw new RuntimeException(INVALID_RECORD_TYPE);
	}

	public void parseAddress() {
		if (hasNotImportedCustomer()) throw new RuntimeException(ADDRESS_WITHOUT_CUSTOMER_ERROR_DESCRIPTION);
		if (invalidAddressRecordSize()) throw new RuntimeException(INVALID_ADDRESS_RECORD);
		
		Address newAddress = new Address();

		newCustomer.addAddress(newAddress);
		newAddress.setStreetName(record[1]);
		newAddress.setStreetNumber(Integer.parseInt(record[2]));
		newAddress.setTown(record[3]);
		newAddress.setZipCode(Integer.parseInt(record[4]));
		newAddress.setProvince(record[5]);
	}

	public boolean invalidAddressRecordSize() {
		return record.length!=6;
	}

	public boolean hasNotImportedCustomer() {
		return newCustomer==null;
	}

	public void parseCustomer() {
		if (invalidCustomerRecordSize()) throw new RuntimeException(INVALID_CUSTOMER_RECORD);
		
		newCustomer = new Customer();
		newCustomer.setFirstName(record[1]);
		newCustomer.setLastName(record[2]);
		newCustomer.setIdentificationType(record[3]);
		newCustomer.setIdentificationNumber(record[4]);
		session.persist(newCustomer);
	}

	public boolean invalidCustomerRecordSize() {
		return record.length!=5;
	}

	public void createRecordFromLine() {
		record = line.split(",");
	}

	public boolean isAddressRecord() {
		return record[0].equals("A");
	}

	public boolean isCustomerRecord() {
		return record[0].equals("C");
	}

	public boolean hasLineToProcess() throws IOException {
		line = lineReader.readLine(); 
		boolean hasLine = line!=null;
		return hasLine;
	}

}
