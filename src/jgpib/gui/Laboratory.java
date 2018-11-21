package jgpib.gui;

import ch.epfl.general_libraries.clazzes.ParamName;
import ch.epfl.general_libraries.experiment_aut.Experiment;
import ch.epfl.general_libraries.experiment_aut.WrongExperimentException;
import ch.epfl.general_libraries.results.AbstractResultsDisplayer;
import ch.epfl.general_libraries.results.AbstractResultsManager;
import ch.epfl.general_libraries.results.DataPoint;
import ch.epfl.javancox.experiments.builder.ExperimentConfigurationCockpit;
import jgpib.instruments.AbstractInstrument;

public class Laboratory implements Experiment {

	AbstractInstrument[] instruments ;
	
	public Laboratory(
			@ParamName(name="Select Lab Equipment") AbstractInstrument[] instruments
			) {
		this.instruments = instruments ;
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
