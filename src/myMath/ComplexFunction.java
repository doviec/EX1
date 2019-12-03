package myMath;

import java.util.function.Function;

public class ComplexFunction implements complex_function {

	private Operation root = Operation.None;
	private function left;
	private function right;

	public ComplexFunction() {

	}
	public ComplexFunction(String string) {

		function function = initFromString(string);
		if (function instanceof Polynom){
			this.left = function;
			this.root = Operation.None;
			this.right = null;
		} else {
			ComplexFunction complexFunction = (ComplexFunction) function;   //needs casting because init returns function (polynom or complex function)
			this.root = complexFunction.getOp();
			this.left = complexFunction.left();
			this.right = complexFunction.right();
		}
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
			return "mul";
		case Comp:
			return "comp";
		case Divid:
			return "div";
		case Max:
			return "max";
		case Min:
			return "min";
		case None:
			return "";
		default :                     //maybe a runtime exception that not a valid operation 
			return "error";
		}
	}
	/**
	 * builds a complex function from a valid string recursively
	 * @param string
	 * @return
	 */
	private function buildComplexFromString(String string) {

		try {                                      //if the string is Polynom or Monom it returns it as a Polynom
			Polynom polynom = new Polynom(string);
			return polynom;
		}
		catch (Exception exception) {
		}
		int firstComma = string.indexOf(',');
		int lastComma = string.lastIndexOf(',');
		int firstBracket = string.indexOf('(');

		if (lastComma == firstComma) {                                    //if the string is a simple Complex as mul(x,x+2)etc then it returns it 
			String lastOperation = string.substring(0,firstBracket);
			Polynom f1 = new Polynom(string.substring(firstBracket+1, lastComma));
			Polynom f2 = new Polynom(string.substring(lastComma+1, string.length()-1));
			return new ComplexFunction(lastOperation,f1,f2);                                          
		}

		int mainComma = findMainComma(string.substring(firstBracket+1));  //mainComma is the comma of the enum function (plus mul etc) that separates f1 and f2.
		String operation = string.substring(0, firstBracket);
		function left = buildComplexFromString(string.substring(firstBracket+1,mainComma + firstBracket+1));           //Recursive for f1 (left function)
		function right = buildComplexFromString(string.substring(mainComma+2+firstBracket,string.length()-1));         //Recursive for f2 (right function)

		ComplexFunction comlexFunction = new ComplexFunction(operation,left,right);

		return comlexFunction;
	}

	/**
	 * finds the comma that belongs to its Operation in the beginning of the string
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

		return 0;
	}

	@Override
	public function initFromString(String s) {

		String trimmedString = s.replaceAll("\\s","");
		if (trimmedString.isEmpty()) {
			//exception
		}
		function f;
		f = buildComplexFromString(trimmedString);
		ComplexFunction complexFunction = new ComplexFunction(f);

		return complexFunction;
	}	

	@Override
	public function copy() {

		ComplexFunction copy = new ComplexFunction();
		copy.root = this.root;
		copy.left = this.left;
		copy.right = this.right;
		return copy;
	}

	public void mathOperation(function f1, Operation op) {
		if (this.right != null) {
			this.left = copy();
		}
		root = op;
		ComplexFunction complexFunction = (ComplexFunction)f1;
		if (complexFunction.getOp() == Operation.None){
			this.right = complexFunction.left();
		} else {
			this.right = f1;
		}
	}
	@Override
	public void plus(function f1) {

		mathOperation(f1, Operation.Plus);
	}

	@Override
	public void mul(function f1) {

		mathOperation(f1, Operation.Times);
	}

	@Override
	public void div(function f1) {

		mathOperation(f1, Operation.Divid);
	}

	@Override
	public void max(function f1) {

		mathOperation(f1, Operation.Max);
	}

	@Override
	public void min(function f1) {

		mathOperation(f1, Operation.Min);
	}

	@Override
	public void comp(function f1) {

		mathOperation(f1, Operation.Comp);
	}

	@Override
	public function left() {
		return this.left;
	}

	@Override
	public function right() {
		return this.right;
	}

	@Override
	public Operation getOp() {
		return this.root;
	}
	public String toString() {

		StringBuilder sb = new StringBuilder();
		if(this.root == Operation.None) {
			return this.left.toString();
		}
			sb.append(this.root+ "(");
			sb.append(this.left.toString() + ',');
			sb.append(this.right.toString() + ')');
		
		return sb.toString();
	}
}
