package common.fspn.utils;

import legacy.formalism.core.Node;
import legacy.fspn.model.FluidPlace;
import legacy.fspn.model.ImmTransition;
import legacy.fspn.model.OrdinaryPlace;
import legacy.fspn.model.StoTransition;

public class Querier {
	public static boolean areImmTrans(Node n, Node m) {
		return (n instanceof ImmTransition) && (m instanceof ImmTransition);
	}

	public static boolean areStoTrans(Node n, Node m) {
		return (n instanceof StoTransition) && (m instanceof StoTransition);
	}

	public static boolean areFluPla(Node n, Node m) {
		return (n instanceof FluidPlace) && (m instanceof FluidPlace);
	}

	public static boolean areOrdPla(Node n, Node m) {
		return (n instanceof OrdinaryPlace) && (m instanceof OrdinaryPlace);
	}

	public static boolean areOfSameType(Node n, Node m) {
		return areFluPla(n,m) || areImmTrans(n,m) || areOrdPla(n,m) || areStoTrans(n,m);
	}
}