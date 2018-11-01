package jgpib.tutorial;

import jgpib.jvisa.JVisaInstrument;

public class Tutorial01 {
	public static void main(String[] args) {
		// step 1: create jvisa instrument
		JVisaInstrument instrument = new JVisaInstrument() ;
		// step 2: 
		instrument.openInstrument("GPIB0::1::INSTR") ;
//		instrument.openDefaultResourceManager() ;
		
	}
}
