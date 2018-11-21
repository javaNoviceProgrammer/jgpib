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
	
	int address ;
	int busNumber ;
	String fullAddress ;
	
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
	
	public abstract String getName() ;
	public abstract String getModel() ;
	
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
		String[] st = response.returnString.split(",") ;
		String identifier = st[0] + " , " + st[1] ;
		return identifier ;
	}
	
	public void reset() {
		visa.openInstrument(fullAddress) ;
		try {
			visa.sendAndReceive("*RST", response) ;
		} catch (JVisaException e) {
			e.printStackTrace();
		}
	}
	
	
}
