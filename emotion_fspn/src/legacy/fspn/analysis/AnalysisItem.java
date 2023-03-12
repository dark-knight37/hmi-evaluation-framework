package legacy.fspn.analysis;

import legacy.formalism.core.Container;
import legacy.formalism.features.Analyzable;
import legacy.formalism.features.Printable;
import legacy.fspn.model.FSPN;

public class AnalysisItem extends Container implements Analyzable, Printable {
	
	protected FSPN f;
	
	protected Property p;

	public AnalysisItem(FSPN f, Property p) {
		this.f = f;
		this.p = p;
	}

	public FSPN getNet() {
		return f;
	}

	public void setNet(FSPN f) {
		this.f = f;
	}

	public Property getProperty() {
		return p;
	}

	public void setProperty(Property p) {
		this.p = p;
	}

	@Override
	public String toSpecificFormat(Container c) {
		return this.f.toSpecificFormat(this);
	}
}