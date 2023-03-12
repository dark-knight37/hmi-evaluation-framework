package legacy.fspn.model;

import legacy.formalism.core.Edge;
import legacy.formalism.core.Node;
import legacy.formalism.features.Printable;

public abstract class Arc extends Edge implements Printable {
	
	public Arc(String name, Node f, Node t) {
		super(name,f,t);
	}

	protected String getDirection() {
		return (this.from instanceof Place) ? "input": "output";
	}

	protected boolean isPre() {
		return (this.from instanceof Place);
	}

	protected boolean isPost() {
		return !(this.from instanceof Place);
	}
}
