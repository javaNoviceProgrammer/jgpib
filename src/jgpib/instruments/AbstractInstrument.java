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
		visa.openInstrument(fullAddress) ;
	}
	
	public abstract String getName() ;
	public abstract String getModel() ;
	public abstract String getIdentifier() ;
	
	public int getAddress() {
		return address ;
	}
	
	public String getfullAddress() {
		return fullAddress ;
	}
	
	// common GPIB commands
	
	public String identify() {
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
		try {
			visa.sendAndReceive("*RST", response) ;
		} catch (JVisaException e) {
			e.printStackTrace();
		}
	}
	
	public boolean selfTest() {
		try {
			visa.sendAndReceive("*TST?", response) ;
		} catch (JVisaException e) {
			e.printStackTrace();
		}
		if(response.returnString == "0")
			return true ; // success
		else
			return false ; // failure
	}
	
	
	
	
}
