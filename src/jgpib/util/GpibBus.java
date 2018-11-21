package jgpib.util;

import jgpib.jvisa.JVisaInstrument;
import jgpib.jvisa.JVisaReturnString;

public class GpibBus {
	
	StringBuilder sb ;
	JVisaInstrument visa ;
	
	public GpibBus() {
		sb = new StringBuilder() ;
		visa = new JVisaInstrument() ;
		visa.openDefaultResourceManager() ;
	}

	public String printBus() {
		for(int bus=0; bus<10; bus++)
			for(int address=0; address<100; address++) {
				String fullAddress = "GPIB"+bus+"::"+address+"::INSTR" ;
				try {
					visa.openInstrument(fullAddress) ;
					JVisaReturnString response = new JVisaReturnString() ;
					visa.sendAndReceive("*IDN?", response) ;
					sb.append(fullAddress + "-->" + response.returnString + "\n") ;
				} catch (Exception e) {
				}
			}
		return sb.toString() ;
	}

}
