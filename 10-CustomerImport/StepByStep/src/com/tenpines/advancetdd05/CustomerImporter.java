package com.tenpines.advancetdd05;

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
			if (isCustomerRecord()){
				String[] customerData = line.split(",");
				newCustomer = new Customer();
				newCustomer.setFirstName(customerData[1]);
				newCustomer.setLastName(customerData[2]);
				newCustomer.setIdentificationType(customerData[3]);
				newCustomer.setIdentificationNumber(customerData[4]);
				session.persist(newCustomer);
			}
			//7: Extract method
			else if (isAddressRecord()) {
				String[] addressData = line.split(",");
				Address newAddress = new Address();
	
				newCustomer.addAddress(newAddress);
				newAddress.setStreetName(addressData[1]);
				newAddress.setStreetNumber(Integer.parseInt(addressData[2]));
				newAddress.setTown(addressData[3]);
				newAddress.setZipCode(Integer.parseInt(addressData[4]));
				newAddress.setProvince(addressData[5]);
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
