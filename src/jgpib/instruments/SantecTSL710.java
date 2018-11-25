package jgpib.instruments;

import java.util.HashMap;
import java.util.Map;

import ch.epfl.general_libraries.clazzes.ParamName;
import jgpib.instruments.intf.TunableLaser;
import jgpib.jvisa.JVisaException;

public class SantecTSL710 extends AbstractInstrument implements TunableLaser {

	double lambdaNm ;
	double lambdaMinNm = 1480.0, lambdaMaxNm = 1640.0 ;
	double powermW, powerdBm, powerRaw ;
	PowerUnit unit ;
	
	public SantecTSL710(
			@ParamName(name="GPIB bus number") int busNumber, 
			@ParamName(name="GPIB address") int address,
			@ParamName(name="Wavelength (nm)") double lambdaNm,
			@ParamName(name="Optical Power") double power,
			@ParamName(name="Optical Power Unit (dBm, mW)") PowerUnit unit
			) {
		super(busNumber, address);
		// making sure input lambda is valid
		if(lambdaNm < lambdaMinNm)
			this.lambdaNm = lambdaMinNm ;
		else if (lambdaNm > lambdaMaxNm)
			this.lambdaNm = lambdaMaxNm ;
		else
			this.lambdaNm = lambdaNm ;
		// setting the power
		this.unit = unit ;
		this.powerRaw = power ;
		switch (unit) {
		case dBm:
			this.powerdBm = power ;
			this.powermW = Math.pow(10.0, power/10.0) ;
			break;
		case mW:
			this.powermW = power ;
			this.powerdBm = 10.0*Math.log10(power) ;
			break;
		default:
			break;
		}
		trigger();
	}

	@Override
	public Map<String, String> getAllParameters() {
		Map<String, String> map = new HashMap<>() ;
		map.put("name", getName()) ;
		map.put("address", fullAddress) ;
		map.put("Wavelength (nm)", lambdaNm+"") ;
		map.put("Power (mW)", powermW+"") ;
		map.put("Power (dBm)", powerdBm+"") ;
		return map ;
	}
	
	public void setWavelength(double lambdaNm) {
		this.lambdaNm = lambdaNm ;
		try {
			visa.write("WAV " + lambdaNm) ;
		} catch (JVisaException e) {
			e.printStackTrace();
		}
	}
	
	public void setPower(double power, PowerUnit unit) {
		this.unit = unit ;
		switch (unit) {
		case dBm:
			this.powerdBm = power ;
			this.powermW = Math.pow(10.0, power/10.0) ;
			try {
				visa.write(":POW:UNIT " + 0) ;
			} catch (JVisaException e) {
				e.printStackTrace();
			}
			try {
				visa.write(":POW:LEV " + power) ;
			} catch (JVisaException e) {
				e.printStackTrace();
			}
			break;
		case mW:
			this.powermW = power ;
			this.powerdBm = 10.0*Math.log10(power) ;
			try {
				visa.write(":POW:UNIT " + 1) ;
			} catch (JVisaException e) {
				e.printStackTrace();
			}
			try {
				visa.write(":POW:LEV " + power) ;
			} catch (JVisaException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
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
	public String getIdentifier() {
		return "Laser";
	}

	public String checkLaser() {
		try {
			visa.sendAndReceive("*TST?", response) ;
		} catch (JVisaException e) {
			e.printStackTrace();
		}
		String re = response.returnString ;
		if(re.equals("0"))
			return "No error" ;
		else if(re.equals("1"))
			return "LD temperature is out of range (at LD OFF)" ;
		else if(re.equals("2"))
			return "LD temperature is out of range (at LD ON)" ;
		else if(re.equals("4"))
			return "Wavelength monitor temperature is out of range" ;
		else if(re.equals("8"))
			return "LD injection current is overload" ;
		else if(re.equals("16"))
			return "Power monitor is malfunction" ;
		else
			return "OK" ;
	}

	@Override
	public void trigger() {
		setPower(this.powerRaw, this.unit);
		setWavelength(this.lambdaNm);
	}

	@Override
	public double getWavelength() {
		return this.lambdaNm ;
	}

	@Override
	public double getPowermW() {
		return this.powermW ;
	}

	@Override
	public double getPowerdBm() {
		return this.powerdBm ;
	}
	
	

}
