package legacy.fspn.analysis;

import legacy.formalism.core.Container;
import legacy.formalism.features.Measurable;
import legacy.fspn.model.FluidPlace;
import legacy.fspn.model.Place;

public class FSPNOffsetMeasure extends FSPNMeasure {
	
	private double offset;

	public FSPNOffsetMeasure(String name, Measurable m, String fileName, double off){
		super(name,m,fileName);
		this.offset = off;
	}

	public FSPNOffsetMeasure(String name, Measurable m, String fileName){
		this(name,m,fileName,true);
	}

	public FSPNOffsetMeasure(String name, Measurable m, String fileName, boolean toOffset) {
		super(name,m,fileName);
		this.offset = (toOffset) ? ((FluidPlace) m).getMaxLevel() / 2 : 0;
	}

	@Override
	public String toSpecificFormat(Container container) {
		Place p = (Place) this.m;
		String trailer = (this.offset > 0) ? " - " + this.offset : "";
		String retval = "measure " + this.name + " mean (" + p.getName() + trailer + ")"; 
		return retval;
	}
}