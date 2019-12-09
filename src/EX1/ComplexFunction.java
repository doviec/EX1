package EX1;

import java.util.function.Function;

import javax.management.RuntimeErrorException;

public class ComplexFunction implements complex_function {

	private Operation root = Operation.None;
	private function left;
	private function right;

	public ComplexFunction() {

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
		case ("comp"):
			return Operation.Comp;
		case ("div"):
			return Operation.Divid;
		case ("max"):
			return Operation.Max;
		case ("min"):
			return Operation.Min;
		case (""):
			return Operation.None;
		default :                      
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
		default :                     
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

		switch (this.root) {

		case Plus :
			return left.f(x) + right.f(x);
		case Times :
			return left.f(x) * right.f(x);
		case Max :
			return Math.max(left.f(x),right.f(x));
		case Min :
			return Math.min(left.f(x),right.f(x));
		case Comp :
			return left.f(right.f(x));
		case Divid :
			if (right.f(x) == 0) {
				throw new ArithmeticException("Can't divide by zero");
			}
			return left.f(x) / right.f(x);
		case None :
			if (right == null) {
				return left.f(x);
			}else {
				throw new RuntimeException("There is no Operation to calculate between the functions");
			}
		case Error :
			throw new RuntimeException("Due to an Error no calculation may be done");
		default :
			return this.f(x);
		}
	}
	@Override
	public function initFromString(String s) {

		String trimmedString = s.replaceAll("\\s","");
		if (trimmedString.isEmpty()) {
			//exception
		}
		function function = buildComplexFromString(trimmedString);
		if (function instanceof Polynom){
			ComplexFunction complexFunction = new ComplexFunction(function);
			return complexFunction;
		} 
		return function;	
	}	
	@Override
	public function copy() {

		ComplexFunction copy = new ComplexFunction(checkWhichString(getOp()),left(),right());
		return copy;
	}
	public void mathOperation(function f1, Operation op) {
		if (this.right != null) {
			this.left = copy();
		}
		root = op;
		if (f1 instanceof Monom || f1 instanceof Polynom) {
			this.right = f1;
		}else {
			ComplexFunction complexFunction =  (ComplexFunction) f1.copy();
			if (complexFunction.getOp() == Operation.None){
				this.right = complexFunction.left();
			} else {
				this.right = f1.copy();
			}
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
	public boolean equals(Object obj) { 

		if (!((obj instanceof ComplexFunction)||(obj instanceof Polynom)||(obj instanceof Monom))) {
			return false;
		}else {
			ComplexFunction complexFunction = new ComplexFunction((function) obj);
			for(double i = -10; i<=10; i+=0.1){                           //checks if the functions are equal in range between [-10,10] with jumps of 0.1
				if(complexFunction.f(i) != this.f(i)) {
					return false;
				}
			}
		}		
		return true;
	}
}
