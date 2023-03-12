package legacy.formalism.random.event;

public class UniformEventGenerator extends ThresholdEventGenerator {

	public UniformEventGenerator(double threshold) {
		super(threshold);
	}
	
	@Override
	public boolean toss() {
		double toss = this.engine.nextDouble();
		return toss < this.threshold;
	}
}