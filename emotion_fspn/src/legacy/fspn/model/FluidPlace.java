package legacy.fspn.model;

import legacy.formalism.core.Container;

/**
 * Classe Node
 * @author Stefano Marrone
 */
public class FluidPlace extends Place {
	
	private double marking;
	
	private double maxLevel;

	public FluidPlace(String name, double mark, double max) {
		super(name);
		this.marking = mark;
		this.maxLevel = max;
	}

	public void setMarking(double mark) {
		this.marking = mark;
	}

	public double getMarking() {
		return this.marking;
	}

	public double getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(double maxLevel) {
		this.maxLevel = maxLevel;
	}

	public Object clone() {
		FluidPlace fp = new FluidPlace(this.name, this.marking, this.maxLevel);
		this.copyLabels(fp);
		return fp;
	}

	@Override
	public String toSpecificFormat(Container c) {
		return "place " + this.name + " fluid " + this.marking + " " + this.maxLevel;
	}
}