package jgpib.instruments;

public interface TunableLaser {

	void setPowermW(double powermW) ;
	void setPowerdBm(double powerdBm) ;
	
	double getPowermW() ;
	double getPowerdBm() ;
	
	void setWavelength(double lambdaNm) ;
	double getWavelength() ;
}
