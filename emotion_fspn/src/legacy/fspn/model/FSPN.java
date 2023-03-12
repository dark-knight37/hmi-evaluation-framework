package legacy.fspn.model;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import legacy.commons.calculator.Calculator;
import legacy.formalism.analysis.Measure;
import legacy.formalism.core.Container;
import legacy.formalism.core.Node;
import legacy.formalism.features.Labellable;
import legacy.fspn.analysis.AnalysisItem;
import legacy.fspn.analysis.FSPNMeasure;
import legacy.fspn.utils.Querier;

public class FSPN extends Container {

	protected Vector<Place> places;
	
	protected Vector<Transition> transitions;
	
	protected Vector<Arc> arcs;
	
	public String debugNote;

	public FSPN(String name) {
		super(name);
		this.places = new Vector<Place>();
		this.transitions = new Vector<Transition>();
		this.arcs = new Vector<Arc>();
	}
	
	public FSPN(FSPN s) {
		this.name = s.getName(); 
		this.places = s.getPlaces();
		this.transitions = s.getTransitions();
		this.arcs = s.getArcs();
	}

	public FSPN() {
		super("");
		this.places = new Vector<Place>();
		this.transitions = new Vector<Transition>();
		this.arcs = new Vector<Arc>();
	}

	public void add(Place p) {
		this.places.add(p);
	} 
	
	public void add(Transition t) {
		this.transitions.add(t);
	} 

	public void add(Arc a) {
		this.arcs.add(a);
	} 

	public Place getPlaceAt(int i) {
		return this.places.elementAt(i);
	}
	
	public Transition getTransitionAt(int i) {
		return this.transitions.elementAt(i);
	}
	
	public Arc getArcAt(int i) {
		return this.arcs.elementAt(i);
	}

	public int getPlaceNumber() {
		return this.places.size();
	}
	
	public int getTransitionNumber() {
		return this.transitions.size();
	}

	public int getArcNumber() {
		return this.arcs.size();
	}

	public String debug() {
		String retval = "fspn " + this.getName() + " = {//" + this.debugNote + "\n";
		for (int i=0; i<getPlaceNumber(); i++) {
			retval += this.getPlaceAt(i).toSpecificFormat(null);
			retval += this.labelPrint(getPlaceAt(i));
			retval += this.getSimpleTrailer(i, getPlaceNumber());
		}
		for (int i=0; i<getTransitionNumber(); i++) {
			retval += this.getTransitionAt(i).toSpecificFormat(null);
			retval += this.labelPrint(getTransitionAt(i));
			retval += this.getSimpleTrailer(i, getTransitionNumber());
		}
		for (int i=0; i<getArcNumber(); i++) {
			retval += this.getArcAt(i).toSpecificFormat(null);
			retval += this.getSimpleTrailer(i, getArcNumber());
		}
		return retval;
	}

	private String labelPrint(Labellable l) {
		String retval = "";
		Vector<String> las = l.getLabels();
		if (las != null) {
			if (las.size() != 0) {
				retval += "/*";
				for (String s: las) {
					retval += s + " ";
				}
				retval += "*/";
			}
		}
		return retval;
	}
	
	private String getSimpleTrailer(int index, int bound) {
		return (index == bound-1) ? "\n;\n" : ",\n";
	}

	private String getFinalTrailer(int index, int bound) {
		return (index == bound-1) ? "\n}\n" : ",\n";
	}
	
	public Vector<Place> getPlaces() {
		return this.places;
	}
	
	public Vector<Transition> getTransitions() {
		return this.transitions;
	}
	
	public Vector<Arc> getArcs() {
		return this.arcs;
	}

	public void substituteLabel(String former, String newer) {
		for (int i = 0; i < this.getPlaceNumber(); i++) {
			this.getPlaceAt(i).substitute(former, newer);
		}
		for (int i = 0; i < this.getTransitionNumber(); i++) {
			this.getTransitionAt(i).substitute(former, newer);
		}
	}

	public Object clone() {
		FSPN c = new FSPN(this.name);
		
		Hashtable<Place,Place> placemaps = new Hashtable<Place,Place>();
		for (int i = 0; i < this.getPlaceNumber(); i++) {
			Place oldp = this.getPlaceAt(i);
			Place newp = (Place) oldp.clone();
			c.add(newp);
			placemaps.put(oldp,newp);
		}

		Hashtable<Transition,Transition> transmaps = new Hashtable<Transition,Transition>();
		for (int i = 0; i < this.getTransitionNumber(); i++) {
			Transition oldt = this.getTransitionAt(i);
			Transition newt = (Transition) oldt.clone();
			c.add(newt);
			transmaps.put(oldt,newt);
		}
		
		for (int i = 0; i < this.getMeasureNumber(); i++) {
			FSPNMeasure oldm = (FSPNMeasure) this.getMeasure(i);
			FSPNMeasure newm = new FSPNMeasure(oldm.getName(), placemaps.get((Place)oldm.getMeasurable()), oldm.getFileName());
			c.add(newm);
		}
 		
		for (int i = 0; i < this.getArcNumber(); i++) {
			Arc olda = this.getArcAt(i);
			Arc newa = null;
			Place p;
			Transition t;
			if (olda instanceof InhibitorArc) {
				InhibitorArc actualOld = (InhibitorArc) olda;
				p = placemaps.get((Place) olda.getFrom());
				t = transmaps.get((Transition) olda.getTo());
				newa = new InhibitorArc(actualOld.getName(),p,t,actualOld.getWeigth());
			} else if (olda instanceof DiscreteArc) {
				DiscreteArc actualOld = (DiscreteArc) olda;
				if (olda.isPre()) {
					p = placemaps.get((Place) olda.getFrom());
					t = transmaps.get((Transition) olda.getTo());
					newa = new DiscreteArc(actualOld.getName(),p,t,actualOld.getWeigth());
				} else {
					p = placemaps.get((Place) olda.getTo());
					t = transmaps.get((Transition) olda.getFrom());					
					newa = new DiscreteArc(actualOld.getName(),t,p,actualOld.getWeigth());
				}
			} else {
				FluidArc actualOld = (FluidArc) olda;
				if (olda.isPre()) {
					p = placemaps.get((Place) olda.getFrom());
					t = transmaps.get((Transition) olda.getTo());
					newa = new FluidArc(actualOld.getName(),p,t,actualOld.getRate());
				} else {
					p = placemaps.get((Place) olda.getTo());
					t = transmaps.get((Transition) olda.getFrom());					
					newa = new FluidArc(actualOld.getName(),t,p,actualOld.getRate());
				}
			}
			c.add(newa);
		}
		return c;
	}
	
	public Vector<Arc> getIncomings(Node n) {
		Vector<Arc> retval = new Vector<Arc>();
		for (int i=0; i<this.getArcNumber(); i++) {
			Arc a = this.getArcAt(i); 
			if (a.isIncomingTo(n)) {
				retval.add(a);
			}
		}
		return retval;
	}

	public Vector<Arc> getOutgoings(Node n) {
		Vector<Arc> retval = new Vector<Arc>();
		for (int i=0; i<this.getArcNumber(); i++) {
			Arc a = this.getArcAt(i); 
			if (a.isOutgoingFrom(n)) {
				retval.add(a);
			}
		}
		return retval;
	}

	public Hashtable<String,Node> extractTable() throws Exception {
		Hashtable<String,Node> retval = new Hashtable<String,Node>();
		// Initial construction of the pool
		Vector<Node> pool = new Vector<Node>();
		pool.addAll(this.getPlaces());
		pool.addAll(this.getTransitions());
		
		for (int i = 0; i < pool.size(); i++) {
			Node n = pool.elementAt(i);
			Vector<String> tempLabel = n.getLabels();
			for (int j = 0; j < tempLabel.size(); j++) {
				String label = tempLabel.elementAt(j);
				if (retval.containsKey(label)) {
					throw new Exception();
				} else {
					retval.put(label, n);
				}
			}
		}
		return retval;
	}

	public FSPN merge(FSPN arg) throws Exception {
		FSPN clonedArg = (FSPN) arg.clone();
		FSPN clonedThis = (FSPN) this.clone();
		Hashtable<String,Node> lookupThis = clonedThis.extractTable();
		Hashtable<String,Node> lookupArg = clonedArg.extractTable();
		clonedThis.join(clonedArg);
		Enumeration<String> labels = lookupArg.keys();
		while (labels.hasMoreElements()) {
			String l = labels.nextElement();
			if (lookupThis.containsKey(l)) {
				Node nodeThis = lookupThis.get(l);
				Node nodeArg = lookupArg.get(l);
				if (Querier.areStoTrans(nodeThis,nodeArg) || Querier.areImmTrans(nodeThis,nodeArg)) {
					clonedThis.moveTransitions((Transition)nodeThis,(Transition)nodeArg,l);
				} else if (Querier.areFluPla(nodeThis,nodeArg) || Querier.areOrdPla(nodeThis,nodeArg)) {
					clonedThis.movePlace((Place)nodeThis,(Place)nodeArg,l);
				} else {
					String message = nodeThis.getName() + " and " + nodeArg.getName() + " are not of the same type.";
					throw new Exception(message);
				}
			}
		}
		return clonedThis;
	}

	private void moveTransitions(Transition fromNode, Transition toNode, String l) {
		this.moveNode(toNode,fromNode,l);
		Calculator c = new Calculator();
		if (toNode instanceof ImmTransition) {
			int iF = ((ImmTransition) fromNode).getPriority();
			int iT = ((ImmTransition) toNode).getPriority();
			int max = Math.max(iF,iT);
			((ImmTransition) toNode).setPriority(max);
			String sF = ((ImmTransition) fromNode).getWeigth();
			String sT = ((ImmTransition) toNode).getWeigth();
			String val = this.computeAverage(sF,sT);
			((ImmTransition) toNode).setWeigth(val);
		} else {
			String sF = ((StoTransition) fromNode).getRate();
			String sT = ((StoTransition) toNode).getRate();
			String val = this.computeAverage(sF,sT);
			((StoTransition) toNode).setRate(val);
		}
		this.transitions.remove(fromNode);
	}

	private void moveNode(Node toNode, Node fromNode, String l) {
		Vector<Arc> outgoings = this.getOutgoings(fromNode);
		for (int i=0; i<outgoings.size(); i++) {
			outgoings.elementAt(i).setFrom(toNode);
		}
		Vector<Arc> ingoings = this.getIncomings(fromNode);
		for (int i=0; i<ingoings.size(); i++) {
			ingoings.elementAt(i).setTo(toNode);
		}
		Vector<String> labelsToMove = fromNode.getLabels();
		for (int i=0; i<labelsToMove.size(); i++) {
			String temp = labelsToMove.elementAt(i);
			if (!temp.equals(l)) {
				toNode.addLabel(temp);
			}
		}
	}

	private void movePlace(Place toNode, Place fromNode, String l) {
		this.moveNode(toNode,fromNode,l);
		if (toNode instanceof OrdinaryPlace) {
			int mTo = ((OrdinaryPlace) fromNode).getMarking();
			int mFrm = ((OrdinaryPlace) toNode).getMarking();
			((OrdinaryPlace) toNode).setMarking(mTo + mFrm);
		} else {
			double mFrm = ((FluidPlace) fromNode).getMarking();
			double mTo = ((FluidPlace) toNode).getMarking();
			double xFrm = ((FluidPlace) fromNode).getMaxLevel();
			double xTo = ((FluidPlace) toNode).getMaxLevel();
			((FluidPlace) toNode).setMarking(mFrm + mTo);
			((FluidPlace) toNode).setMaxLevel(xFrm + xTo);
		}
		this.compactMeasure(toNode,fromNode);
		this.places.remove(fromNode);
	}

	private void compactMeasure(Place toNode, Place fromNode) {
		for (int i = 0; i < this.getMeasureNumber(); i++) {
			FSPNMeasure temp = (FSPNMeasure) this.getMeasure(i);
			Place p = (Place) temp.getMeasurable();
			if (p.getName().equals(fromNode.getName())) {
				temp.setMeasurable(toNode);
			}
		}
	}

	public void join(FSPN f) {
		for (int i = 0; i < f.getPlaceNumber(); i++) {
			this.add(f.getPlaceAt(i));
		}
		for (int i = 0; i < f.getTransitionNumber(); i++) {
			this.add(f.getTransitionAt(i));
		}
		for (int i = 0; i < f.getArcNumber(); i++) {
			this.add(f.getArcAt(i));
		}
		for (int i = 0; i < f.getMeasureNumber(); i++) {
			super.add(f.getMeasure(i));
		}
	}

	public void rename() {
		String name = this.getName();
		String temp = null;
		for (int i=0; i<this.getPlaceNumber(); i++) {
			temp = this.getPlaceAt(i).getName();
			this.getPlaceAt(i).setName(name + "_" + temp);
		}
		for (int i=0; i<this.getTransitionNumber(); i++) {
			temp = this.getTransitionAt(i).getName();
			this.getTransitionAt(i).setName(name + "_" + temp);
		}
		for (int i=0; i<this.getArcNumber(); i++) {
			temp = this.getArcAt(i).getName();
			this.getArcAt(i).setName(name + "_" + temp);
		}
		for (int i=0; i<this.getMeasureNumber(); i++) {
			temp = this.getMeasure(i).getName();
			this.getMeasure(i).setName(name + "_" + temp);
		}
	}

	@Override
	public String toSpecificFormat(Container c) {
		AnalysisItem ai = (AnalysisItem) c; 
		String retval = "fspn " + this.getName() + " = {\n";
		for (int i=0; i<getPlaceNumber(); i++) {
			retval += this.getPlaceAt(i).toSpecificFormat(null) + this.getSimpleTrailer(i, getPlaceNumber());
		}
		for (int i=0; i<getTransitionNumber(); i++) {
			retval += this.getTransitionAt(i).toSpecificFormat(null) + this.getSimpleTrailer(i, getTransitionNumber());
		}
		for (int i=0; i<getArcNumber(); i++) {
			retval += this.getArcAt(i).toSpecificFormat(null) + this.getSimpleTrailer(i, getArcNumber());
		}
		for (int i=0; i<getMeasureNumber(); i++) {
			FSPNMeasure fm = (FSPNMeasure) this.getMeasure(i); 
			retval += fm.toSpecificFormat(null) + this.getFinalTrailer(i, getMeasureNumber());
		}
		retval += "\n";
		retval += "property {\n";
		retval += ai.getProperty().toSpecificFormat(null);
		
		for (int i = 0; i<getMeasureNumber(); i++) {
			FSPNMeasure temp = (FSPNMeasure) this.getMeasure(i);
			retval += "string " + temp.getName() + "File " + '"' + temp.getFileName() + '"' + this.getFinalTrailer(i, getMeasureNumber());
		}
		retval += '\n';
		return retval;
	}
	
	public Place getPlaceByLabel(String label) {
		Place retval = null;
		int size = this.getPlaceNumber();
		boolean found = false;
		int i = 0;
		do {
			retval = this.getPlaceAt(i);
			found = retval.hasLabel(label);
			i++;
		} while ((!found) && (i<size));
		return (found) ? retval : null;
	}
	
	private String computeAverage(String a, String b) {
		String val = null;
		try {
			Double dF = new Double(a);
			Double dT = new Double(b);
			Double res = 0.5 * dF + 0.5 * dT;
			val = res.toString();
		} catch (Exception e) {
			val = "((0.5 * " + a + ") + (0.5 * " + b + "))";
		}
		return val;
	}

	public Vector<String> getFileToGen() {
		Vector<String> retval = new Vector<String>();
		for (Measure m: this.getMeasures()) {
			FSPNMeasure fm = (FSPNMeasure) m;
			retval.add(fm.getFileName());
		}
		return retval;
	}
}
