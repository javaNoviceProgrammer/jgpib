package jgpib.gui;

import java.util.ArrayList;

import ch.epfl.general_libraries.clazzes.ParamName;
import ch.epfl.general_libraries.experiment_aut.Experiment;
import ch.epfl.general_libraries.experiment_aut.WrongExperimentException;
import ch.epfl.general_libraries.results.AbstractResultsDisplayer;
import ch.epfl.general_libraries.results.AbstractResultsManager;
import ch.epfl.general_libraries.results.DataPoint;
import ch.epfl.javancox.experiments.builder.ExperimentConfigurationCockpit;
import jgpib.instruments.AbstractInstrument;
import jgpib.instruments.intf.Laser;
import jgpib.instruments.intf.MultiMeter;
import jgpib.instruments.intf.VoltageSource;

public class Laboratory3 implements Experiment {

	AbstractInstrument[] instruments ;
	ArrayList<Laser> lasers ;
	ArrayList<VoltageSource> voltageSources ;
	ArrayList<MultiMeter> multiMeters ;
	
	public Laboratory3(
			@ParamName(name="Select Lab Equipment") AbstractInstrument[] instruments
			) {
		this.instruments = instruments ;
		// first setup optical/electrical sources
		int n = instruments.length ;
		for(int i=0; i<n; i++)
			if(instruments[i].getIdentifier().contains("Laser")) {
				((Laser) instruments[i]).setup() ;
				lasers.add((Laser) instruments[i]) ;
			}
				
		// then setup the measuring equipment (multimeter, powermeter, ...)
	}
	
	@Override
	public void run(AbstractResultsManager man, AbstractResultsDisplayer dis) throws WrongExperimentException {
		DataPoint dp = new DataPoint() ;
		man.addDataPoint(dp);
	}
	
	public static void main(String[] args) {
		ExperimentConfigurationCockpit.execute(new String[] {"-p", "jgpib", "-c", Laboratory3.class.getName() }, true); 
	}

}
