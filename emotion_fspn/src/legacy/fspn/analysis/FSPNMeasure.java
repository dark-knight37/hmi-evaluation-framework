package legacy.fspn.analysis;

import legacy.formalism.analysis.SerializableMeasure;
import legacy.formalism.core.Container;
import legacy.formalism.features.Measurable;
import legacy.formalism.features.Printable;
import legacy.fspn.model.Place;

public class FSPNMeasure extends SerializableMeasure implements Printable {

	public FSPNMeasure(String name, Measurable m, String fileName){
		super(name,m,fileName);
	}

	@Override
	public String toSpecificFormat(Container container) {
		Place p = (Place) this.m;
		return "measure " + this.name + " mean " + p.getName();
	}
}