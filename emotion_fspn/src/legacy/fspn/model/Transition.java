package legacy.fspn.model;

import legacy.formalism.core.Node;
import legacy.formalism.features.Printable;

public abstract class Transition extends Node implements Printable {
	public Transition(String name) {
		super(name);
	}
	
	public abstract Object clone();
}
