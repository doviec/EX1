package Ex1;

import java.util.function.Function;

import javax.management.RuntimeErrorException;

public class ComplexFunction implements complex_function {

	private Operation root = Operation.None;
	private function left;
	private function right;

	/**
	 * Constructor 
	 */
	public ComplexFunction() {
	}
	public ComplexFunction(function f) {
		
		this.root = Operation.None;
		this.left = f.copy();
		this.right = null;
	}
	public ComplexFunction(String string, function f1, function f2) {

		root = checkWhichOperation(string);
		left = f1.copy();
		right = f2.copy();
	}
	public ComplexFunction(Operation operation, function f1, function f2) {
		root = operation;
		left = f1.copy();
		right = f2.copy();
	}
	/**
	 * Checks which operation the string represents and returns the operation
	 * @param string
	 * @return
	 */
	private Operation checkWhichOperation(String string) {

		switch (string.toLowerCase()) {
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
			throw new RuntimeException("Invalid string");
		}
	}
	/**
	 * checks which string the operation represents and returns it
	 * @param operation
	 * @return
	 */
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
			throw new RuntimeException("Invalid operation");
		}
	}
	/**
	 * builds a complex function from a string recursively (separates from operation f1 and f2)
	 * @param string which will be divided to operation left and right
	 * @return a Polynom or a new Complex function if the string didnt represent a single polynom
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
			if (f1.toString().isEmpty() || f2.toString().isEmpty()) {    //checks if f1 or f2 had values and not mul(,2) or plus(,) for example
				throw new RuntimeException("must enter two valid arguments");
			}
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
	 * if we find a comma and the number of the left brackets are equal to the right brackets then we've found our comma 
	 * that divides our string.
	 * @param string
	 * @return the index of the expected comma
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
	/**
	 * this method calculates the value of this complex function by a given number recursively
	 * @return double - the value of left and right.
	 */
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
	/**
	 * this method gets a string and converts it to a complex function unless its not a valid string and throws an exception
	 * @return function which the string represents
	 */
	@Override
	public function initFromString(String s) {
		function function;
		String trimmedString = s.replaceAll("\\s","");
		if (trimmedString.isEmpty()) {
			throw new RuntimeException("The String"+s+" is invalid");
		}
		try {
			function = buildComplexFromString(trimmedString);      //send the string to a method that examines it and returns the complex function which it represents 
			if (function instanceof Polynom){
				ComplexFunction complexFunction = new ComplexFunction(function);
				return complexFunction;
			} 
		}catch (Exception e) {
			throw new RuntimeException("The String "+s+" is invalid");
		}
		return function;	
	}	
	/**
	 * this methos copies this complex function to a new complex function
	 * @return function
	 */
	@Override
	public function copy() {

		if(right() == null) {
			return new ComplexFunction(left.copy());
		}else {
			ComplexFunction copy = new ComplexFunction(checkWhichString(getOp()),left().copy(),right().copy());
			return copy;
		}
	}
	/**
	 * The method checks which operation it needs to add as root and checks which function it received and puts it as f2 (right)
	 * @param f1 which may be Monom Polynom or Complex function 
	 * @param op - operation
	 */
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
		if (left == null) {
			return left;
		}else return this.left.copy();
	}
	@Override
	public function right() {
		if (right == null) {
			return right;
		}else return this.right.copy();
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
	/**
	 * Indicates whether some other object is "equal to" this one in a range between [-100,100] with jumps of 0.1.   
	 * @param object
	 * @return boolean 
	 */
	public boolean equals(Object obj) { 

		if (!((obj instanceof ComplexFunction)||(obj instanceof Polynom)||(obj instanceof Monom))) {
			return false;
		}else {
			ComplexFunction complexFunction = new ComplexFunction((function) obj);
			for(double i = -100; i<=100; i+=0.1){                           //checks if the functions are equal in range between [-10,10] with jumps of 0.1
				if(complexFunction.f(i) != this.f(i)) {
					return false;
				}
			}
		}		
		return true;
	 }
}
