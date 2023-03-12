package legacy.fspn.model;

import legacy.formalism.core.Container;
import legacy.formalism.features.Printable;


/**
 * Classe Node
 * @author Stefano Marrone
 */
public class OrdinaryPlace extends Place implements Printable {
	
	private int marking;

	public OrdinaryPlace(String name, int mark) {
		super(name);
		this.marking = mark;
	}

	public void setMarking(int mark) {
		this.marking = mark;
	}

	public int getMarking() {
		return this.marking;
	}

	public Object clone() {
		OrdinaryPlace op = new OrdinaryPlace(this.name, this.marking);
		this.copyLabels(op);
		return op;
	}

	@Override
	public String toSpecificFormat(Container c) {
		return "place " + this.name + " discrete " + this.marking;
	}
}