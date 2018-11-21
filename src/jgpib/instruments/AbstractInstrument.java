package jgpib.instruments;

import java.util.Map;

import jgpib.jvisa.JVisaException;
import jgpib.jvisa.JVisaInstrument;
import jgpib.jvisa.JVisaReturnString;

public abstract class AbstractInstrument {
	
	/*
	 * setting up the general GPIB commands here
	 */

	JVisaInstrument visa ;
	JVisaReturnString response ;
	
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
		visa = new JVisaInstrument() ;
		response = new JVisaReturnString() ;
		visa.openDefaultResourceManager() ;
	}
	
	public int getAddress() {
		return address ;
	}
	
	public String getfullAddress() {
		return fullAddress ;
	}
	
	// common GPIB commands
	
	public String identify() {
		visa.openInstrument(fullAddress) ;
		try {
			visa.sendAndReceive("*IDN?", response) ;
		} catch (JVisaException e) {
			e.printStackTrace();
		}
		return response.returnString ;
	}
	
	
}
