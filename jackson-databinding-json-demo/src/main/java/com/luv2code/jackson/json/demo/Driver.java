package com.luv2code.jackson.json.demo;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {

	public static void main(String[] args) {
		try {
			// create object mapper
			ObjectMapper mapper = new ObjectMapper();
			
			// read json and convert to POJO
			Student theStudent = mapper.readValue(new File("data/sample-full.json"), Student.class);
			
			// print data
			System.out.println("First name: " + theStudent.getFirstName());
			System.out.println("Last name: " + theStudent.getLastName());
			
			Address tmpAddress = theStudent.getAddress();
			
			System.out.println("Street: " + tmpAddress.getStreet());
			System.out.println("City: " + tmpAddress.getCity());
			
			for (String tmpLang : theStudent.getLanguages()) {
				System.out.println(tmpLang);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
