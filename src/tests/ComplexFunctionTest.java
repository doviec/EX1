package tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.*;

class ComplexFunctionTest {



	@Test
	void testComplexFunction() {
		String s1 = "div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)"; 
		String s2 = "plus(5,mul (mul (1,2), 3))";
		String s3 = "mul(1,mul(2,3))";
		String s4 = "div(x,x)";

		String wrongString1 = "none(x,x)";
		String wrongString2 = "plus(mul(mul(4,5)";
		String wrongString3 = "mul(2,)     ";

		ComplexFunction complex1 = (ComplexFunction)new ComplexFunction().initFromString(s1);
		ComplexFunction complex2 = (ComplexFunction)new ComplexFunction().initFromString(s2);
		ComplexFunction complex3 = (ComplexFunction)new ComplexFunction().initFromString(s3);	
		ComplexFunction complex7 = (ComplexFunction)new ComplexFunction().initFromString(s4);	

		System.out.println(complex1.toString());
		System.out.println(complex2.toString());
		System.out.println(complex3.toString());	
		System.out.println(complex7.toString());
		try {
			ComplexFunction complex4 = (ComplexFunction)new ComplexFunction().initFromString(wrongString1);
			fail("This String is invalid");
		}catch (Exception e) {
			System.out.println("Test complex4 exception is correct");
		}	
		try {
			ComplexFunction complex5 = (ComplexFunction)new ComplexFunction().initFromString(wrongString2);
			fail("This String is invalid");
		}catch (Exception e) {
			System.out.println("Test complex5 exception is correct");
		}	
		try {
			ComplexFunction complex6 = (ComplexFunction)new ComplexFunction().initFromString(wrongString3);	
			fail("This String is invalid");
		}catch (Exception e) {
			System.out.println("Test complex6 exception is correct");
		}		
	}
	@Test
	void testPlus() {

		String s1 = "x^2";
		String s2 = "x";
		String s3 = "mul(1,2)"; 
		String s4 = "plus(4,mul(5x+2,x^5))";

		ComplexFunction complex1 = (ComplexFunction)new ComplexFunction().initFromString(s1);
		ComplexFunction complex2 = (ComplexFunction)new ComplexFunction().initFromString(s2);
		ComplexFunction complex3 = (ComplexFunction)new ComplexFunction().initFromString(s3);
		ComplexFunction complex4 = (ComplexFunction)new ComplexFunction().initFromString(s4);

		complex1.plus(complex2);
		complex2.plus(complex3);
		complex3.plus(complex4);

		assertEquals(Operation.Plus, complex1.getOp());
		assertEquals(Operation.Plus, complex2.getOp());
		assertEquals(Operation.Plus, complex3.getOp());

		assertEquals(12, complex1.f(3));
		assertEquals(5.5, complex2.f(3.5));
		assertEquals(770.0617676, complex3.f(2.25),0.001);
	}
	@Test
	void testMul() {

		String s1 = "x^2";
		String s2 = "mul(plus(4,x^2),mul(4x,2))";
		String s3 = "mul(1,2)"; 
		String s4 = "plus(4,mul(5x+2,x^5))";

		ComplexFunction complex1 = (ComplexFunction)new ComplexFunction().initFromString(s1);
		ComplexFunction complex2 = (ComplexFunction)new ComplexFunction().initFromString(s2);
		ComplexFunction complex3 = (ComplexFunction)new ComplexFunction().initFromString(s3);
		ComplexFunction complex4 = (ComplexFunction)new ComplexFunction().initFromString(s4);

		complex1.mul(complex2);
		complex3.mul(complex2);
		complex2.mul(complex4);

		assertEquals(Operation.Times, complex1.getOp());
		assertEquals(Operation.Times, complex2.getOp());
		assertEquals(Operation.Times, complex3.getOp());

		assertEquals(2808, complex1.f(3));
		assertEquals(4661823.3593, complex2.f(3.5),0.0001);
		assertEquals(326.25, complex3.f(2.25));
	}
	@Test
	void testDiv() {

		String s1 = "x^2";
		String s2 = "x";
		String s3 = "mul(1,2)"; 
		String s4 = "plus(4,mul(5x+2,x^5))";

		ComplexFunction complex1 = (ComplexFunction)new ComplexFunction().initFromString(s1);
		ComplexFunction complex2 = (ComplexFunction)new ComplexFunction().initFromString(s2);
		ComplexFunction complex3 = (ComplexFunction)new ComplexFunction().initFromString(s3);
		ComplexFunction complex4 = (ComplexFunction)new ComplexFunction().initFromString(s4);

		complex1.div(complex2);
		complex3.div(complex2);
		complex2.div(complex4);

		assertEquals(Operation.Divid, complex1.getOp());
		assertEquals(Operation.Divid, complex2.getOp());
		assertEquals(Operation.Divid, complex3.getOp());

		assertEquals(3, complex1.f(3));
		assertEquals(0.0030582, complex2.f(2.23),0.0001);
		assertEquals(0.888888, complex3.f(2.25),0.0001);
	}
	@Test
	void testMax() {

		String s1 = "x^2";
		String s2 = "x";
		String s3 = "mul(1,2)"; 
		String s4 = "mul(mul(3.2x^2,x),plus(4,mul(5x+2,x^5)))";

		ComplexFunction complex1 = (ComplexFunction)new ComplexFunction().initFromString(s1);
		ComplexFunction complex2 = (ComplexFunction)new ComplexFunction().initFromString(s2);
		ComplexFunction complex3 = (ComplexFunction)new ComplexFunction().initFromString(s3);
		ComplexFunction complex4 = (ComplexFunction)new ComplexFunction().initFromString(s4);

		complex1.max(complex2);
		complex2.max(complex4);
		complex3.max(complex2);

		assertEquals(Operation.Max, complex1.getOp());
		assertEquals(Operation.Max, complex2.getOp());
		assertEquals(Operation.Max, complex3.getOp());

		assertEquals(16, complex1.f(4));
		assertEquals(633738.12799, complex2.f(3.2),0.0001);
		assertEquals(27995.851428, complex3.f(2.25),0.0001);
	}
	@Test
	void testMin() {

		String s1 = "x^2";
		String s2 = "x";
		String s3 = "mul(1,2)"; 
		String s4 = "mul(mul(3.2x^2,x),plus(4,mul(5x+2,x^5)))";

		ComplexFunction complex1 = (ComplexFunction)new ComplexFunction().initFromString(s1);
		ComplexFunction complex2 = (ComplexFunction)new ComplexFunction().initFromString(s2);
		ComplexFunction complex3 = (ComplexFunction)new ComplexFunction().initFromString(s3);
		ComplexFunction complex4 = (ComplexFunction)new ComplexFunction().initFromString(s4);

		complex1.min(complex2);
		complex3.min(complex2);
		complex2.min(complex4);

		assertEquals(Operation.Min, complex1.getOp());
		assertEquals(Operation.Min, complex2.getOp());
		assertEquals(Operation.Min, complex3.getOp());

		assertEquals(4, complex1.f(4));
		assertEquals(3.2, complex2.f(3.2),0.0001);
		assertEquals(2, complex3.f(2.25),0.0001);
	}
	@Test
	void testComp() {

		String s1 = "x^2";
		String s2 = "x";
		String s3 = "mul(1,2)"; 
		String s4 = "mul(mul(3.2x^2,x),plus(4,mul(5x+2,x^5)))";

		ComplexFunction complex1 = (ComplexFunction)new ComplexFunction().initFromString(s1);
		ComplexFunction complex2 = (ComplexFunction)new ComplexFunction().initFromString(s2);
		ComplexFunction complex3 = (ComplexFunction)new ComplexFunction().initFromString(s3);
		ComplexFunction complex4 = (ComplexFunction)new ComplexFunction().initFromString(s4);

		complex1.comp(complex2);
		complex3.comp(complex2);
		complex2.comp(complex4);

		assertEquals(Operation.Comp, complex1.getOp());
		assertEquals(Operation.Comp, complex2.getOp());
		assertEquals(Operation.Comp, complex3.getOp());

		assertEquals(25, complex1.f(5));
		assertEquals(633738.12799, complex2.f(3.2),0.0001);
		assertEquals(2, complex3.f(2.25),0.0001);
	}
	@Test
	void testToString() {
		String s1 = "mul(1,2)"; 
		String s2 = "mul(mul(1,2),3)";
		String s3 = "mul(1,mul(2,3))";

		ComplexFunction complex1 = (ComplexFunction)new ComplexFunction().initFromString(s1);
		ComplexFunction complex2 = (ComplexFunction)new ComplexFunction().initFromString(s2);
		ComplexFunction complex3 = (ComplexFunction)new ComplexFunction().initFromString(s3);

		System.out.println(complex1.toString());
		System.out.println(complex2.toString());
		System.out.println(complex3.toString());

		assertEquals("Times(1.0,2.0)",complex1.toString());
		assertEquals("Times(Times(1.0,2.0),3.0)",complex2.toString());
		assertEquals("Times(1.0,Times(2.0,3.0))",complex3.toString());	
	}
	@Test
	void testCopy() {

		String s1 = "mul(1,mul(2,3))";

		ComplexFunction complex1 = (ComplexFunction)new ComplexFunction().initFromString(s1);
		function complex2 = complex1.copy();

		assertEquals(complex1.toString(), complex2.toString());
		assertEquals("Times(1.0,Times(2.0,3.0))", complex2.toString());
	}
	@Test
	void testF() {

		String s1 = "x^3+5x-9+0"; 
		String s2 = "mul(plus(2x,2),3x^2)";
		String s3 = "div(plus(x^2,6x),mul(x,2))";    
		String s4 = "max(mul(5x+2,0.25),div(2.2x,plus(1.2x,x)))";
		String s5 = "comp(x^2,x+1)";
		String s6 = "mul(20,div(x^3+2,1-x))"; 

		ComplexFunction complex1 = (ComplexFunction)new ComplexFunction().initFromString(s1);
		ComplexFunction complex2 = (ComplexFunction)new ComplexFunction().initFromString(s2);
		ComplexFunction complex3 = (ComplexFunction)new ComplexFunction().initFromString(s3);
		ComplexFunction complex4 = (ComplexFunction)new ComplexFunction().initFromString(s4);
		ComplexFunction complex5 = (ComplexFunction)new ComplexFunction().initFromString(s5);
		ComplexFunction complex6 = (ComplexFunction)new ComplexFunction().initFromString(s6);

		double resultComplex1 = complex1.f(2);
		double resultComplex2 = complex2.f(1);
		double resultComplex3 = complex3.f(3);
		double resultComplex4 = complex4.f(5.5);    
		double resultComplex5 = complex5.f(2.2);
		int x = 1;
		try {
			double resultComplex6 = complex6.f(x);
			fail("can't divide by zero ");
		}catch (Exception e) {
			System.out.println("can't divide "+ s6.toString() + " when x = " + x);
		}
		assertEquals(9, resultComplex1);
		assertEquals(12, resultComplex2);
		assertEquals(4.5, resultComplex3);
		assertEquals(7.375, resultComplex4);
		assertEquals(10.2400000, resultComplex5,0.00001);
	}
	@Test
	void testEquals() {

		char c1 = 'g';
		String s1 = "x^2";
		String s2 = "mul(x,x)"; 
		String s3 = "mul(1,2)"; 
		String s4 = "plus(4,mul(5x+2,x^5))";
		String s5 = "plus(mul(x^5,5x+2),4)";
		ComplexFunction complex1 = (ComplexFunction)new ComplexFunction().initFromString(s1);
		ComplexFunction complex2 = (ComplexFunction)new ComplexFunction().initFromString(s2);
		ComplexFunction complex3 = (ComplexFunction)new ComplexFunction().initFromString(s3);
		ComplexFunction complex4 = (ComplexFunction)new ComplexFunction().initFromString(s4);
		ComplexFunction complex5 = (ComplexFunction)new ComplexFunction().initFromString(s5);

		boolean check1 = complex1.equals(complex2);
		boolean check2 = complex5.equals(complex4);
		boolean check3 = complex3.equals(complex3);

		assertEquals(true, check1);
		assertEquals(true, check2);
		assertEquals(true, check3);

		boolean check5 = complex1.equals(s2);
		boolean check6 = complex1.equals(c1);
		boolean check7 = complex3.equals(complex5);

		assertEquals(false, check5);
		assertEquals(false, check6);
		assertEquals(false, check7);
	}
	@Test
	void testDeepCopy() {
		Polynom polynom = new Polynom ("4 + 5x^6 + 3x^12");
		ComplexFunction complexFunction = new ComplexFunction("plus",polynom,polynom);
		polynom.multiply(new Monom ("5"));
		System.out.println(complexFunction.toString());
	}
	@Test
	void testSpeicalCases() {
		
		String s1 = " plus ( plus(0  ,0 ), 0 )";
		ComplexFunction complex1 = (ComplexFunction) new ComplexFunction().initFromString(s1);
		System.out.println(complex1.toString());
	}
}












