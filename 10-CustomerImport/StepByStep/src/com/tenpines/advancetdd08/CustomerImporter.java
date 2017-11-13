package com.tenpines.advancetdd08;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;

import org.hibernate.Session;

public class CustomerImporter {

	private String line;
	private LineNumberReader lineReader;

	public void value(Session session, Reader fileReader) throws IOException{
		
		//3: convert to field
		lineReader = new LineNumberReader(fileReader);
		
		Customer newCustomer = null;
		//4: Extract method
		//5: Inline
		while (hasLineToProcess()) {
			//6: Extract method
			String[] record = line.split(",");
			if (isCustomerRecord()){
				//8: extract to local
				//9: Inline
				newCustomer = new Customer();
				newCustomer.setFirstName(record[1]);
				newCustomer.setLastName(record[2]);
				newCustomer.setIdentificationType(record[3]);
				newCustomer.setIdentificationNumber(record[4]);
				session.persist(newCustomer);
			}
			//7: Extract method
			else if (isAddressRecord()) {
				//10: Inline
				Address newAddress = new Address();
	
				newCustomer.addAddress(newAddress);
				newAddress.setStreetName(record[1]);
				newAddress.setStreetNumber(Integer.parseInt(record[2]));
				newAddress.setTown(record[3]);
				newAddress.setZipCode(Integer.parseInt(record[4]));
				newAddress.setProvince(record[5]);
			}
			
			line = lineReader.readLine();
		}
			
		fileReader.close();
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
