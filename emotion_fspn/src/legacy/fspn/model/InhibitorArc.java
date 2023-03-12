package legacy.fspn.model;

import legacy.formalism.core.Container;

public class InhibitorArc extends DiscreteArc {
	
	public InhibitorArc(String name, Place p, Transition t, int w) {
		super(name,p,t,1);
	}
	
	public String toSpecificFormat(Container c) {
		return "arc " + this.name + " inhibitor " + this.from.getName() + ' ' + this.to.getName() + " " + this.weigth;
	}
}