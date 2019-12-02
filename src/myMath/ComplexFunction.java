package myMath;

import java.util.function.Function;

public class ComplexFunction implements complex_function {

	private Operation root;
	private function left;
	private function right;

	public ComplexFunction() {

	}
	public ComplexFunction(String string) {
		
		ComplexFunction complexFunction = (ComplexFunction) initFromString(string);       //needs casting becayse init retirnds functions which dosent know left and right
		this.root = complexFunction.getOp();
		this.left = complexFunction.left();
		this.right = complexFunction.right();
		
	}
	public ComplexFunction(function f) {
		
		this.root = Operation.None;
		this.left = f;
		this.right = null;
	}
	public ComplexFunction(String string, function f1, function f2) {

		root = checkWhichOperation(string);
		left = f1;
		right = f2;
	}
	private Operation checkWhichOperation(String string) {

		switch (string) {
		case ("plus"):
			return Operation.Plus;
		case ("mul"):
			return Operation.Times;
		case ("Comp"):
			return Operation.Comp;
		case ("div"):
			return Operation.Divid;
		case ("max"):
			return Operation.Max;
		case ("min"):
			return Operation.Min;
		case (""):
			return Operation.None;
		default :                     //maybe a runtime exception that not a valid operation 
			return Operation.Error;
		}
	}
	private String checkWhichString(Operation operation) {     //checks which string represents the operation

		switch (operation) {
		case Plus:
			return "plus";
		case Times:
			return "times";
		case Comp:
			return "comp";
		case Divid:
			return "divide";
		case Max:
			return "max";
		case Min:
			return "min";
		case None:
			return "no operation";
		default :                     //maybe a runtime exception that not a valid operation 
			return "error";
		}
	}
	/**
	 * builds a complex function from a valid string recursively
	 * @param string
	 * @return
	 */
	public function buildComplexFromString(String string) {

		try {
			Polynom polynom = new Polynom(string);
			return new ComplexFunction(polynom);
		}
		catch (Exception exception) {
		}

		int start = string.indexOf('(');
		int mainComma = findMainComma(string.substring(start+1));
		if (mainComma == -1) {
			//throw exception
		}
		String operation = string.substring(0, start);
		function left = buildComplexFromString(string.substring(start+1,mainComma + start+1));           
		function right = buildComplexFromString(string.substring(mainComma+2+start,string.length()-1));

		ComplexFunction comlexFunction = new ComplexFunction(operation,left,right);

		return comlexFunction;
	}
	/**
	 * finds the comma that belongs to its Operation
	 * @param string
	 * @return
	 */
	public int findMainComma(String string) {

		int leftBracket = 0;
		int rightBracket = 0;

		for(int i = 0; i<string.length(); i++) {
			if(string.charAt(i) == ',' && leftBracket == rightBracket) {
				return i;
			}
			if(string.charAt(i) == '(') {
				leftBracket++;
			}else if(string.charAt(i) == ')') {
				rightBracket++;
			}
		}
		return -1;
	}

	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public function initFromString(String s) {

		function complexFunction = new ComplexFunction();
		String trimmedString = s.replaceAll("\\s","");
		if (!trimmedString.isEmpty()) {
			complexFunction = buildComplexFromString(trimmedString);
		}
		return complexFunction;
	}	


	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void plus(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mul(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void div(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void max(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void min(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void comp(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public function left() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function right() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operation getOp() {
		// TODO Auto-generated method stub
		return null;
	}

}
