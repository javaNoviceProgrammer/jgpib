package jgpib.instruments;

import java.util.Map;

import jgpib.jvisa.JVisaException;
import jgpib.jvisa.JVisaInstrument;
import jgpib.jvisa.JVisaReturnString;

public abstract class AbstractInstrument {

	/*
	 * define visa session as static to avoid creating multiple sessions
	 */
	
	static JVisaInstrument visa ;
	/*
	 * visa response can be local, no need to define it as static
	 */
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
		visa.openDefaultResourceManager() ;
		visa.openInstrument(fullAddress) ;
		response = new JVisaReturnString() ;
	}
	
	public abstract String getName() ;
	public abstract String getModel() ;
	public abstract String getIdentifier() ;
	
	public int getAddress() {
		return address ;
	}
	
	public String getfullAddress() {
		this.fullAddress = "GPIB" + busNumber + "::" + address + "::INSTR" ;
		return fullAddress ;
	}
	
	// common GPIB commands
	
	/**
	 * identification query
	 * @return
	 */
	
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
	
	/**
	 * devise reset
	 */
	
	public void reset() {
		try {
			visa.write("*RST") ;
		} catch (JVisaException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * self-test query
	 * @return
	 */
	
	public boolean isOK() {
		try {
			visa.sendAndReceive("*TST?", response) ;
		} catch (JVisaException e) {
			e.printStackTrace();
		}
		String st = response.returnString ;
		if(st.equals("0"))
			return true ; // success
		else
			return false ; // failure
	}
	
	/**
	 * operation complete query
	 * @return
	 * 0 (in operation) or 1 (operation completed)
	 */
	
	public boolean operationCompleted() {
		try {
			visa.sendAndReceive("*OPC?", response) ;
		} catch (JVisaException e) {
			e.printStackTrace();
		}
		String st = response.returnString ;
		if(st.equals("0"))
			return false ;
		else
			return true ;
	}
	
	/**
	 * clear status
	 */
	
	public void clearStat() {
		try {
			visa.write("*CLS") ;
		} catch (JVisaException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Standard event enable register setting
	 * @param value 
	 * 8 bit register value
	 */
	
	public void setEventEnableRegister(byte value) {
		String command = "*ESE " + value ;
		try {
			visa.write(command) ;
		} catch (JVisaException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * query standard event enable register
	 * @return
	 * a byte (8 bits) as the SEER
	 */
	
	public byte getEventEnableRegister() {
		try {
			visa.sendAndReceive("*ESE?", response) ;
		} catch (JVisaException e) {
			e.printStackTrace();
		}
		String result = response.returnString ;
		return Byte.parseByte(result) ;
	}
	
	/**
	 * places the value of the standard event status register (SESR)
	 * in the output queue. Register is cleared after being read.
	 * @return
	 * a byte (8 bits) as the SESR
	 */
	
	public byte getEventStatusRegister() {
		try {
			visa.sendAndReceive("*ESR?", response) ;
		} catch (JVisaException e) {
			e.printStackTrace();
		}
		String result = response.returnString ;
		return Byte.parseByte(result) ;
	}
	
	/**
	 * service request enable register setting
	 * @param value
	 * a byte (8 bits)
	 */
	
	public void setServiceRequestEnable(byte value) {
		String command = "*SRE " + value ;
		try {
			visa.write(command) ;
		} catch (JVisaException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * service request enable register query
	 * @return
	 * a byte (8 bit) as SRER
	 */
	
	public byte getServiceRequestEnable() {
		try {
			visa.sendAndReceive("*SRE?", response) ;
		} catch (JVisaException e) {
			e.printStackTrace();
		}
		String result = response.returnString ;
		return Byte.parseByte(result) ;
	}
	
	/**
	 * places the value of the status byte register (STBR) in the output query
	 * @return
	 * a byte (8 bits) as STBR
	 */
	
	public byte getStatusByte() {
		try {
			visa.sendAndReceive("*STB?", response) ;
		} catch (JVisaException e) {
			e.printStackTrace();
		}
		String result = response.returnString ;
		return Byte.parseByte(result) ;
	}
	
	
}
