package legacy.fspn.model;

import legacy.formalism.core.Container;
import legacy.formalism.features.Printable;

public class ImmTransition extends Transition implements Printable {
	
	private String weigth = "1.0";
	
	private int priority = 1;
	
	public ImmTransition(String name, float weigth, int priority) {
		super(name);
		Float temp = new Float(weigth);
		this.weigth = temp.toString(); 
		this.priority = priority;
	}
		
	public ImmTransition(String name, String weigth, int priority) {
		super(name);
		this.weigth = weigth;
		this.priority = priority;
	}

	public String getWeigth() {
		return weigth;
	}

	public void setWeigth(String weigth) {
		this.weigth = weigth;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Object clone() {
		ImmTransition it = new ImmTransition(this.name, this.weigth, this.priority);
		this.copyLabels(it);
		return it;
	}

	@Override
	public String toSpecificFormat(Container c) {
		return "transition " + this.name + " immediate " + this.weigth + " " + this.priority; 
	}
}
