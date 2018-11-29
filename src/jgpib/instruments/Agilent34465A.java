package jgpib.instruments;

import java.util.HashMap;
import java.util.Map;

import jgpib.instruments.intf.MultiMeter;
import jgpib.jvisa.JVisaException;

public class Agilent34465A extends AbstractInstrument implements MultiMeter {

	double voltageRes = 1e-4 ;
	double voltageMax = 10.0 ;
	
	public Agilent34465A(
			int busNumber, 
			int address,
			double voltageMax ,
			double voltageRes) {
		super(busNumber, address);
		this.voltageMax = voltageMax ;
		this.voltageRes = voltageRes ;
	}

	@Override
	public Map<String, String> getAllParameters() {
		Map<String, String> map = new HashMap<>() ;
		map.put("name", getName()) ;
		map.put("address", fullAddress) ;
		map.put("Voltage (V) on " + getName(), getVoltage()+"") ;
		return map ;
	}
	
	public double getVoltage() {
		try {
			visa.sendAndReceive("MEAS:VOLT:DC? " + voltageMax + "," + voltageRes, response) ;
		} catch (JVisaException e) {
			e.printStackTrace();
		}
		return Double.parseDouble(response.returnString) ;
	}

	@Override
	public String getName() {
		return "Agilent34465A" + "," + fullAddress ;
	}

	@Override
	public String getModel() {
		return "Agilent34465A";
	}

	@Override
	public String getIdentifier() {
		return "Measure";
	}

}
