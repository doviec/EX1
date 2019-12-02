package myMath;

import java.util.function.Function;

public class ComplexFunction implements complex_function {

	private Operation root;
	private function left;
	private function right;

	public ComplexFunction() {

	}
	public ComplexFunction(String string) {
		
		initFromString(string);
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
		case ("Plus"):
			return Operation.Plus;
		case ("Times"):
			return Operation.Times;
		case ("Comp"):
			return Operation.Comp;
		case ("Divid"):
			return Operation.Divid;
		case ("Max"):
			return Operation.Max;
		case ("Min"):
			return Operation.Min;
		case ("None"):
			return Operation.None;
		default :                     //maybe a runtime exception that not a valid operation 
			return Operation.Error;
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
			return polynom;
		}
		catch (Exception exception) {
		}

		int start = string.indexOf('(');
		int mainComma = findMainComma(string.substring(start+1));
		if (mainComma == -1) {
			//throw exception
		}
		String operation = string.substring(0, start);
		function left = buildComplexFromString(string.substring(start+1,mainComma));           
		function right = buildComplexFromString(string.substring(mainComma+1,string.length()-1));

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
