package jgpib.gui;

import java.util.ArrayList;

import ch.epfl.general_libraries.clazzes.ParamName;
import ch.epfl.general_libraries.experiment_aut.Experiment;
import ch.epfl.general_libraries.experiment_aut.WrongExperimentException;
import ch.epfl.general_libraries.results.AbstractResultsDisplayer;
import ch.epfl.general_libraries.results.AbstractResultsManager;
import ch.epfl.general_libraries.results.DataPoint;
import ch.epfl.javancox.experiments.builder.ExperimentConfigurationCockpit;
import jgpib.instruments.intf.BroadbandLaser;
import jgpib.instruments.intf.MultiMeter;
import jgpib.instruments.intf.TunableLaser;
import jgpib.instruments.intf.VoltageSource;

public class Laboratory implements Experiment {

	ArrayList<TunableLaser> lasers ;
	ArrayList<VoltageSource> voltageSources ;
	ArrayList<MultiMeter> multiMeters ;
	
	public Laboratory(
			@ParamName(name="Tunable Lasers") TunableLaser[] tuneLasers,
			@ParamName(name="Broadband Lasers") BroadbandLaser[] bbLasers,
			@ParamName(name="Voltage Sources") VoltageSource[] vs,
			@ParamName(name="Digital Multi Meters") MultiMeter[] dmm
			) {
		// set up optical sources
		
		// set up electrical sources
				
		// then setup the measuring equipment (multimeter, powermeter, ...)
	}
	
	@Override
	public void run(AbstractResultsManager man, AbstractResultsDisplayer dis) throws WrongExperimentException {
		DataPoint dp = new DataPoint() ;
		man.addDataPoint(dp);
	}
	
	public static void main(String[] args) {
		ExperimentConfigurationCockpit.execute(new String[] {"-p", "jgpib", "-c", Laboratory.class.getName() }, true); 
	}

}
