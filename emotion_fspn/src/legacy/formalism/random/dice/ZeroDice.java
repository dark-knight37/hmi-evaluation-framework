package legacy.formalism.random.dice;

import legacy.formalism.random.RandomGenerator;

/**
 * @author Stefano Marrone
 */
public class ZeroDice extends RandomGenerator implements NumberGenerator {
	
	protected int faces;
	
	public ZeroDice() {
		this.faces = 6;
	}
	
	public ZeroDice(int f) {
		this.faces = f;
	}

	public int roll() {		
		int result = super.engine.nextInt(this.faces);
		return result;
	}
}