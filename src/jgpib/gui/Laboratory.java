package jgpib.gui;

import ch.epfl.general_libraries.clazzes.ParamName;
import ch.epfl.general_libraries.experiment_aut.Experiment;
import ch.epfl.general_libraries.experiment_aut.WrongExperimentException;
import ch.epfl.general_libraries.results.AbstractResultsDisplayer;
import ch.epfl.general_libraries.results.AbstractResultsManager;
import ch.epfl.general_libraries.results.DataPoint;
import ch.epfl.javancox.experiments.builder.ExperimentConfigurationCockpit;
import jgpib.instruments.AbstractInstrument;
import jgpib.instruments.intf.BroadbandLaser;
import jgpib.instruments.intf.MultiMeter;
import jgpib.instruments.intf.TunableLaser;
import jgpib.instruments.intf.VoltageSource;

public class Laboratory implements Experiment {

	TunableLaser tuneLasers ;
	VoltageSource voltageSources ;
	MultiMeter multiMeters ;
	
	public Laboratory(
			@ParamName(name="Tunable Lasers") TunableLaser tuneLasers,
			@ParamName(name="Broadband Lasers") BroadbandLaser bbLasers,
			@ParamName(name="Voltage Sources") VoltageSource vs,
			@ParamName(name="Digital Multi Meters") MultiMeter dmm
			) {
		this.voltageSources = vs ;
		this.multiMeters = dmm ;
		// trigger optical sources
		if(tuneLasers != null) {
			this.tuneLasers = tuneLasers ;
		}
		// set up electrical sources
				
		// then setup the measuring equipment (multimeter, powermeter, ...)
		if(dmm != null) {
			this.multiMeters = dmm ;
		}
	}
	
	@Override
	public void run(AbstractResultsManager man, AbstractResultsDisplayer dis) throws WrongExperimentException {
		DataPoint dp = new DataPoint() ;
		if(tuneLasers != null)
			dp.addProperties(((AbstractInstrument) tuneLasers).getAllParameters());
		if(this.multiMeters != null) {
			dp.addResultProperties(((AbstractInstrument) multiMeters).getAllParameters());
		}
		man.addDataPoint(dp);
	}
	
	public static void main(String[] args) {
		ExperimentConfigurationCockpit.execute(new String[] {"-p", "jgpib", "-c", Laboratory.class.getName() }, true); 
	}

}
