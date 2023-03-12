package legacy.commons.calculator.computing;

public class Plus implements Operation {

	@Override
	public Double op(Double a, Double b) {
		return a + b;
	}
}
