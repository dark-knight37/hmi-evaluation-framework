package legacy.formalism.random.event;

import legacy.formalism.random.RandomGenerator;

public abstract class RandomEventGenerator extends RandomGenerator implements EventGenerator {

	public abstract boolean toss();
	
}