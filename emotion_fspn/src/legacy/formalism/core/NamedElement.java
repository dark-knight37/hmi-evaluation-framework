package legacy.formalism.core;

import legacy.formalism.utils.LabelGenerator;

abstract public class NamedElement {
	
	protected String name;

	public NamedElement(String name) {
		this.name = name;
	}

	public NamedElement() {
		this.name = LabelGenerator.get();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}


