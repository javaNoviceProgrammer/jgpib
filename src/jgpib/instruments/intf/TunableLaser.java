package jgpib.instruments.intf;

public interface TunableLaser {
	
	void trigger() ;
	double getWavelength() ;
	double getPowermW() ;
	double getPowerdBm() ;
	
	public enum PowerUnit {
		dBm,
		mW
	}

}
