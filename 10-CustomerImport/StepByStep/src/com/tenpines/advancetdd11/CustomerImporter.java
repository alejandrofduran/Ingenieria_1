package com.tenpines.advancetdd11;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;

import org.hibernate.Session;

public class CustomerImporter {

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
		
		//3: convert to field
		lineReader = new LineNumberReader(fileReader);
		//14: Convert to field
		newCustomer = null;
		//4: Extract method
		//5: Inline
		while (hasLineToProcess()) {
			//15: Convert to field
			//16: extract method
			createRecordFromLine();
			if (isCustomerRecord())
				//17: extract method
				parseCustomer();
			//7: Extract method
			else if (isAddressRecord()) {
				//10: Inline
				Address newAddress = new Address();
	
				newCustomer.addAddress(newAddress);
				newAddress.setStreetName(records[1]);
				newAddress.setStreetNumber(Integer.parseInt(records[2]));
				newAddress.setTown(records[3]);
				newAddress.setZipCode(Integer.parseInt(records[4]));
				newAddress.setProvince(records[5]);
			}
			
			line = lineReader.readLine();
		}
			
		fileReader.close();
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
