package jgpib.instruments;

import java.util.HashMap;
import java.util.Map;

import ch.epfl.general_libraries.clazzes.ParamName;
import jgpib.instruments.intf.Laser;

public class SantecTSL710 extends AbstractInstrument implements Laser {

	public SantecTSL710(
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
		return "SantecTSL710" + "," + fullAddress ;
	}

	@Override
	public String getModel() {
		return "TSL710";
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getIdentifier() {
		return "Laser";
	}

}
