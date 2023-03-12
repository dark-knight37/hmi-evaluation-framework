package legacy.fspn.model;

import java.util.Vector;

import legacy.formalism.core.Node;
import legacy.formalism.features.Measurable;
import legacy.formalism.features.Printable;

/**
 * Class Node
 * @author Stefano Marrone
 */
public abstract class Place extends Node implements Printable, Measurable {
	
	public Place(String name) {
		super(name);
		labels = new Vector<String>();
	}
	
	public abstract Object clone();
}