package legacy.fspn.model;

import legacy.formalism.core.Container;
import legacy.formalism.features.Printable;

public class FluidArc extends Arc implements Printable {
	
	protected String rate;
	
	public FluidArc(String name, Place p, Transition t, String r) {
		super(name,p,t);
		this.setRate(r);
	}

	public FluidArc(String name, Place p, Transition t, double r) {
		super(name,p,t);
		this.setRate(r);
	}

	public FluidArc(String name, Transition t, Place p, String r) {
		super(name,t,p);
		this.setRate(r);
	}

	public FluidArc(String name, Transition t, Place p, double r) {
		super(name,t,p);
		this.setRate(r);
	}

	public void setRate(String r) {
		this.rate = r;
	}

	public void setRate(double d) {
		Double dd = new Double(d);
		this.rate = dd.toString();
	}

	public String getRate() {
		return this.rate;
	}

	public double getRateAsDouble() throws Exception {
		double retval = 0;
		Double d = new Double(this.rate);
		if (d.isNaN()) {
			throw new Exception();
		} else {
			retval = d.doubleValue();
		}
		return retval;
	}

	@Override
	public String toSpecificFormat(Container c) {
		return "arc " + this.name + " " + this.getDirection() + " fluid " + from.getName() + " " + to.getName() + " " + this.rate;
	}
}
