package legacy.commons.calculator;

import java.util.Stack;

import legacy.commons.calculator.analysis.DepthFirstAdapter;
import legacy.commons.calculator.computing.Operation;
import legacy.commons.calculator.computing.OperationFactory;
import legacy.commons.calculator.node.AExpression;
import legacy.commons.calculator.node.AMain;
import legacy.commons.calculator.node.AMinusOp;
import legacy.commons.calculator.node.APlusOp;
import legacy.commons.calculator.node.AReal;
import legacy.commons.calculator.node.ARealPart;
import legacy.commons.calculator.node.ASlashOp;
import legacy.commons.calculator.node.AStarOp;

public class ExpressionAnalyser extends DepthFirstAdapter {
	
	protected Stack<String> stack;
	
	protected double result;
	
	public ExpressionAnalyser() {
		this.stack = new Stack<String>();
	}
	
	public double getValue() {
		return this.result;
	}
	
    public void inAMain(AMain node) {
        this.stack.clear();
        this.result = 0;
    }

    public void outAMain(AMain node) {
        Double d = new Double(this.stack.peek());
        this.result = d.doubleValue();
    }

    public void outAExpression(AExpression node) {
    	Double rr = new Double(this.stack.pop());
    	String oo = this.stack.pop();
    	Double ll = new Double(this.stack.pop());
    	Operation opp = OperationFactory.factory(oo);
    	Double res = opp.op(ll,rr);
    	this.stack.push(res.toString());
    }

    public void outAPlusOp(APlusOp node) {
        this.stack.push("+");
    }

    public void outAMinusOp(AMinusOp node) {
        this.stack.push("-");
    }

    public void outAStarOp(AStarOp node) {
        this.stack.push("*");
    }

    public void outASlashOp(ASlashOp node) {
        this.stack.push("/");
    }

    public void inAReal(AReal node) {
    	ARealPart d = (ARealPart) node.getRealPart();
    	String dpart = (d == null) ? "0" : "0." + d.getNum().getText();
    	String ipart = node.getNum().getText();
        Double dd = new Double(ipart).doubleValue() + new Double(dpart).doubleValue();
        this.stack.push(dd.toString());
    }
}