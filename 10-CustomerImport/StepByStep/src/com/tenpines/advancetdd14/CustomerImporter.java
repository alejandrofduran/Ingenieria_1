package com.tenpines.advancetdd14;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;

import org.hibernate.Session;

public class CustomerImporter {

	public static final String ADDRESS_WITHOUT_CUSTOMER_ERROR_DESCRIPTION = "Direccion sin cliente";
	private String line;
	private LineNumberReader lineReader;
	private Session session;
	private Customer newCustomer;
	private String[] records;

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
	}

	public void parseAddress() {
		if (hasNotImportedCustomer()) throw new RuntimeException(ADDRESS_WITHOUT_CUSTOMER_ERROR_DESCRIPTION);
		
		Address newAddress = new Address();

		newCustomer.addAddress(newAddress);
		newAddress.setStreetName(records[1]);
		newAddress.setStreetNumber(Integer.parseInt(records[2]));
		newAddress.setTown(records[3]);
		newAddress.setZipCode(Integer.parseInt(records[4]));
		newAddress.setProvince(records[5]);
	}

	public boolean hasNotImportedCustomer() {
		return newCustomer==null;
	}

	public void parseCustomer() {
		newCustomer = new Customer();
		newCustomer.setFirstName(records[1]);
		newCustomer.setLastName(records[2]);
		newCustomer.setIdentificationType(records[3]);
		newCustomer.setIdentificationNumber(records[4]);
		session.persist(newCustomer);
	}

	public void createRecordFromLine() {
		records = line.split(",");
	}

	public boolean isAddressRecord() {
		return line.startsWith("A");
	}

	public boolean isCustomerRecord() {
		return line.startsWith("C");
	}

	public boolean hasLineToProcess() throws IOException {
		line = lineReader.readLine(); 
		boolean hasLine = line!=null;
		return hasLine;
	}

}
