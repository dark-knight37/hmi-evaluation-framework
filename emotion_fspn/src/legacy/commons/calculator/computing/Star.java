package legacy.commons.calculator.computing;

public class Star implements Operation {

	@Override
	public Double op(Double a, Double b) {
		return a * b;
	}
}