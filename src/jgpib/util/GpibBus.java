package jgpib.util;

import jgpib.jvisa.JVisaInstrument;
import jgpib.jvisa.JVisaReturnString;

public class GpibBus {
	
	StringBuilder sb ;
	JVisaInstrument visa ;
	int counter = 0 ;
	
	public GpibBus() {
		sb = new StringBuilder() ;
		visa = new JVisaInstrument() ;
		visa.openDefaultResourceManager() ;
	}

	public String printBus() {
		sb.append("Scanning all GPIB buses..." + "\n") ;
		for(int bus=0; bus<10; bus++)
			for(int address=0; address<100; address++) {
				String fullAddress = "GPIB"+bus+"::"+address+"::INSTR" ;
				try {
					visa.openInstrument(fullAddress) ;
					JVisaReturnString response = new JVisaReturnString() ;
					visa.sendAndReceive("*IDN?", response) ;
					String[] st = response.returnString.split(",") ;
					String identifier = st[0] + " , " + st[1] ;
					sb.append(fullAddress + "-->" + identifier + "\n") ;
					counter++ ;
				} catch (Exception e) {
				}
			}
		if(counter != 0)
			return sb.toString() ;
		else {
			sb.append("No GPIB device found" + "\n") ;
			return sb.toString() ;
		}
	}

}
