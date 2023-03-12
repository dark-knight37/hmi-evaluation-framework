package legacy.formalism.random.real;


/**
 * @author Stefano Marrone
 */
public class UniformRealGenerator extends AbstractRealGenerator {
/*	
	private static UniformRealGenerator singleton = null;
	
	public static UniformRealGenerator createUniformRealGenerator(double up,double lo) {
		UniformRealGenerator retval = null;
		if (singleton == null) {
			retval = new 
		} else {
			retval = singleton;
		}
		return retval;
	}
*/
	public UniformRealGenerator(double up, double lo) {
		super(up, lo);
	}

	@Override
	public double roll() {
		double temp = this.engine.nextDouble();
		return temp*(this.upperBound - this.lowerBound) + this.lowerBound;
	}
}