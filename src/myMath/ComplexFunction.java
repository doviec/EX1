package myMath;

import java.util.function.Function;

public class ComplexFunction implements complex_function {

	private Operation root = Operation.None;
	private function left;
	private function right;

	public ComplexFunction() {

	}
	public ComplexFunction(String string) {

		ComplexFunction complexFunction = (ComplexFunction) initFromString(string);   //needs casting because init returns function (polynom or complex function)
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
	private function buildComplexFromString(String string) {

		try {                                      //if the string is Polynom or Monom it returns it as a Polynom
			Polynom polynom = new Polynom(string);
			return new ComplexFunction(polynom);
		}
		catch (Exception exception) {
		}
		
		int firstComma = string.indexOf(',');
		int lastComma = string.lastIndexOf(',');
		int firstBracket = string.indexOf('(');
		
		if (lastComma == firstComma) {               //if the string is a simple Complex as mul(x,x+2)etc then it returns it 
			String lastOperation = string.substring(0,firstBracket);
			Polynom f1 = new Polynom(string.substring(firstBracket+1, lastComma));
			Polynom f2 = new Polynom(string.substring(lastComma+1, string.length()-1));
			return new ComplexFunction(lastOperation,f1,f2);                                          
		}

		int mainComma = findMainComma(string.substring(firstBracket+1));  //mainComma is the comma of the enum function (plus mul etc) that separates f1 and f2.
		if (mainComma == -1) {
			//throw exception
			//
			//
		}
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public function initFromString(String s) {

		function complexFunction = null;
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

		if (this.right != null) {
			this.left = this;
		}		
		root = Operation.Plus;
		this.right = f1;
	}


	@Override
	public void mul(function f1) {

		if (this.right != null) {
			this.left = this;
		}		
		root = Operation.Times;
		this.right = f1;
	}

	@Override
	public void div(function f1) {

		if (this.right != null) {
			this.left = this;
		}		
		root = Operation.Divid;
		this.right = f1;
	}

	@Override
	public void max(function f1) {

		if (this.right != null) {
			this.left = this;
		}		
		root = Operation.Max;
		this.right = f1;
	}

	@Override
	public void min(function f1) {

		if (this.right != null) {
			this.left = this;
		}		
		root = Operation.Min;
		this.right = f1;
	}

	@Override
	public void comp(function f1) {

		if (this.right != null) {
			this.left = this;
		}		
		root = Operation.Comp;
		this.right = f1;
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
		
		String ComplexFunctionString = "";
		StringBuilder sb = new StringBuilder();
		

		return ComplexFunctionString;
	}

}
