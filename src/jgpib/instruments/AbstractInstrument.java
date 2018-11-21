package jgpib.instruments;

import java.util.Map;

import jgpib.jvisa.JVisaInstrument;

public abstract class AbstractInstrument {
	
	/*
	 * setting up the general GPIB commands here
	 */

	JVisaInstrument visa ;
	
	String fullAddress ;
	int address ;
	int busNumber ;
	
	public abstract Map<String, String> getAllParameters() ;
	
	public AbstractInstrument(
			int busNumber,
			int address
			) {
		this.busNumber = busNumber ;
		this.address = address ;
		this.fullAddress = "GPIB"+busNumber+"::"+address+"::INSTR" ;
	}
	
	public int getAddress() {
		return address ;
	}
	
	public String getfullAddress() {
		return fullAddress ;
	}
	
	// common GPIB commands
	
	public String identify() {
		visa.openDefaultResourceManager() ;
		visa.openInstrument(fullAddress) ;
		return null ;
	}
	
	
}
