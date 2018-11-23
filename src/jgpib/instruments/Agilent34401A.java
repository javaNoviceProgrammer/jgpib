package jgpib.instruments;

import java.util.HashMap;
import java.util.Map;

import ch.epfl.general_libraries.clazzes.ParamName;
import jgpib.instruments.intf.MultiMeter;

public class Agilent34401A extends AbstractInstrument implements MultiMeter {

	public Agilent34401A(
			@ParamName(name="GPIB bus number") int busNumber, 
			@ParamName(name="GPIB address") int address) {
		super(busNumber, address);
	}

	@Override
	public Map<String, String> getAllParameters() {
		Map<String, String> map = new HashMap<>() ;
		map.put("name", getName()) ;
		map.put("address", fullAddress) ;
		return map ;
	}

	@Override
	public String getName() {
		return "Agilent34401A" + "," + fullAddress ;
	}

	@Override
	public String getModel() {
		return "Agilent34401A";
	}

	@Override
	public String getIdentifier() {
		return "Measurement, digital mlutimeter";
	}

}
