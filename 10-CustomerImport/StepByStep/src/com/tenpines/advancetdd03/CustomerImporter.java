package com.tenpines.advancetdd03;

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
		//1: convert to field
		line = lineReader.readLine(); 
		//2: extract to local
		boolean hasLine = line!=null;
		while (hasLine) {
			if (line.startsWith("C")){
				String[] customerData = line.split(",");
				newCustomer = new Customer();
				newCustomer.setFirstName(customerData[1]);
				newCustomer.setLastName(customerData[2]);
				newCustomer.setIdentificationType(customerData[3]);
				newCustomer.setIdentificationNumber(customerData[4]);
				session.persist(newCustomer);
			}
			else if (line.startsWith("A")) {
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

}
