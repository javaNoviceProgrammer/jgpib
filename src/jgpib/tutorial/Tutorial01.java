package jgpib.tutorial;

import jgpib.jvisa.JVisaException;
import jgpib.jvisa.JVisaInstrument;
import jgpib.jvisa.JVisaReturnString;

public class Tutorial01 {
	public static void main(String[] args) {
		/*
		 *  step 1: create jvisa instrument
		 */
		JVisaInstrument instrument = new JVisaInstrument() ;
		/*
		 *  step 2: open resource manager
		 */
		instrument.openDefaultResourceManager() ;
		/*
		 *  step 3: open instrument
		 */
		instrument.openInstrument("GPIB0::22::INSTR") ;
		/*
		 * Step 4: send command and read response
		 */
		JVisaReturnString read = new JVisaReturnString() ;
		try {
			instrument.sendAndReceive("MEAS:VOLT:DC? 10, 0.0001", read) ;
		} catch (JVisaException e) {
			e.printStackTrace();
		}
		System.out.println(read.returnString);
	}
}
