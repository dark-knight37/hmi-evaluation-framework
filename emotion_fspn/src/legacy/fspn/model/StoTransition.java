package legacy.fspn.model;

import legacy.formalism.core.Container;
import legacy.formalism.features.Printable;


public class StoTransition extends Transition implements Printable {

	private String rate;
	
	public StoTransition(String name, double rate) {
		super(name);
		Float temp = new Float(rate);
		this.rate = temp.toString(); 
	}

	public StoTransition(String name, String rate) {
		super(name);
		this.rate = rate; 
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Object clone() {
		StoTransition st = new StoTransition(this.name,this.rate);
		this.copyLabels(st);
		return st;
	}

	@Override
	public String toSpecificFormat(Container c) {
		return "transition " + this.name + " timed " + this.rate; 
	}
}
