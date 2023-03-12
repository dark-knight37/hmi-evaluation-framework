package legacy.fspn.model;

import legacy.formalism.core.Container;
import legacy.formalism.features.Printable;

public class DiscreteArc extends Arc implements Printable {
	
	protected int weigth;

	public DiscreteArc(String name, Place p, Transition t, int w) {
		super(name,p,t);
		this.weigth = w;
	}

	public DiscreteArc(String name, Transition t, Place p, int w) {
		super(name,t,p);
		this.weigth = w;
	}

	public int getWeigth() {
		return weigth;
	}

	public void setWeigth(int w) {
		this.weigth = w;
	}

	@Override
	public String toSpecificFormat(Container c) {
		return "arc " + this.name + ' '  + this.getDirection() + " discrete " + this.from.getName() + ' ' + this.to.getName() + " " + this.weigth;
	}
}