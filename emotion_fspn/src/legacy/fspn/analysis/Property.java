package legacy.fspn.analysis;

import legacy.formalism.core.Container;
import legacy.formalism.features.Printable;

public class Property implements Printable {

	protected double simulationTime;

	protected double simulationStep;

	protected int simulationCycles;

	protected double sampleStart;

	protected double confidence;

	protected double sampleStep;

	protected double sampleEnd;

	public Property(double simulationTime, double simulationStep, int simulationCycles, double sampleStep,
			double sampleStart, double confidence, double sampleEnd){
		this.simulationTime=simulationTime;
		this.simulationStep=simulationStep;
		this.simulationCycles=simulationCycles;
		this.sampleStep=sampleStep;
		this.sampleStart=sampleStart;
		this.confidence=confidence;
		this.sampleEnd=sampleEnd;
	}

	@Override
	public String toSpecificFormat(Container c) {
		String s= "real SimulationTime "+this.simulationTime+",\n";
		s+="real SimulationStep "+this.simulationStep+",\n";
		s+="integer SimulationCycles "+this.simulationCycles+",\n";
		s+="real SampleStep "+this.sampleStep+",\n";
		s+="real SampleStart "+this.sampleStart+",\n";
		s+="real Confidence "+this.confidence+",\n";
		s+="real SampleEnd "+this.sampleEnd+",\n";
		return s;
	}

}
