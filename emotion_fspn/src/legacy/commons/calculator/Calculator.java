package legacy.commons.calculator;

import java.io.PushbackReader;
import java.io.StringReader;

import legacy.commons.calculator.lexer.Lexer;
import legacy.commons.calculator.node.Start;
import legacy.commons.calculator.parser.Parser;

public class Calculator {
	
	public String computeExpression(String expression) throws Exception {
		StringReader sr = new StringReader(expression);
		PushbackReader pr = new PushbackReader(sr,1024);
		Lexer lexer = new Lexer(pr);
		Parser parser = new Parser(lexer);
		Start root = parser.parse();
		ExpressionAnalyser ea = new ExpressionAnalyser();
		root.apply(ea);
		return ea.toString();
	}
}