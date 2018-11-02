package jgpib.instruments;

import jgpib.jvisa.JVisaInstrument;

public class AbstractInstrument {
	
	/*
	 * setting up the general GPIB commands here
	 */
	
	JVisaInstrument instrument = new JVisaInstrument() ;

	String fullAddress ;
	int address ;
	String name ;
	
	public int getAddress() {
		return address ;
	}
	
	public String getfullAddress() {
		return fullAddress ;
	}
	
	public String identify() {
		return null ;
	}
	
	
}
