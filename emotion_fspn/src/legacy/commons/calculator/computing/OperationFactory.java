package legacy.commons.calculator.computing;

public class OperationFactory {
	
	public static Operation factory(String sign) {
		Operation retval = null;
		if (sign.equals("+")) {
			retval = new Plus();
		} else if (sign.equals("-")) {
			retval = new Minus();
		} else if (sign.equals("*")) {
			retval = new Star();
		} else if (sign.equals("/")) {
			retval = new Slash();
		}
		return retval;
	}
}
