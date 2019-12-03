package tests;
import myMath.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ComplexFunctionTest {

	

	@Test
	void testComplexFunction() {
		String s1 = "mul(1,2)"; 
		String s2 = "mul(mul(1,2),3)";
		String s3 = "mul(1,mul(2,3))";
		
		ComplexFunction complex1 = new ComplexFunction(s1);
		ComplexFunction complex2 = new ComplexFunction(s2);
		ComplexFunction complex3 = new ComplexFunction(s3);
		
		System.out.println(s1.toString());
		
	}
	@Test
	void testPlus() {
		
		String s1 = "x^2";
		String s2 = "x";
		String s3 = "mul(1,2)"; 
		String s4 = "plus(4,mul(5x+2,x^5))";
		
		ComplexFunction complex1 = new ComplexFunction(s1);
		ComplexFunction complex2 = new ComplexFunction(s2);
		ComplexFunction complex3 = new ComplexFunction(s3);
		ComplexFunction complex4 = new ComplexFunction(s4);

		complex1.plus(complex2);
		complex3.plus(complex2);
		complex2.plus(complex4);
	}
	@Test
	void testMul() {
		
		String s1 = "x^2";
		String s2 = "x";
		String s3 = "mul(1,2)"; 
		String s4 = "plus(4,mul(5x+2,x^5))";
		
		ComplexFunction complex1 = new ComplexFunction(s1);
		ComplexFunction complex2 = new ComplexFunction(s2);
		ComplexFunction complex3 = new ComplexFunction(s3);
		ComplexFunction complex4 = new ComplexFunction(s4);

		complex1.mul(complex2);
		complex3.mul(complex2);
		complex2.mul(complex4);
	}
	@Test
	void testDiv() {
		
		String s1 = "x^2";
		String s2 = "x";
		String s3 = "mul(1,2)"; 
		String s4 = "plus(4,mul(5x+2,x^5))";
		
		ComplexFunction complex1 = new ComplexFunction(s1);
		ComplexFunction complex2 = new ComplexFunction(s2);
		ComplexFunction complex3 = new ComplexFunction(s3);
		ComplexFunction complex4 = new ComplexFunction(s4);

		complex1.div(complex2);
		complex3.div(complex2);
		complex2.div(complex4);
	}
	@Test
	void testMax() {
		
		String s1 = "x^2";
		String s2 = "x";
		String s3 = "mul(1,2)"; 
		String s4 = "plus(4,mul(5x+2,x^5))";
		
		ComplexFunction complex1 = new ComplexFunction(s1);
		ComplexFunction complex2 = new ComplexFunction(s2);
		ComplexFunction complex3 = new ComplexFunction(s3);
		ComplexFunction complex4 = new ComplexFunction(s4);

		complex1.max(complex2);
		complex3.max(complex2);
		complex2.max(complex4);
	}
	@Test
	void testMin() {
		
		String s1 = "x^2";
		String s2 = "x";
		String s3 = "mul(1,2)"; 
		String s4 = "plus(4,mul(5x+2,x^5))";
		
		ComplexFunction complex1 = new ComplexFunction(s1);
		ComplexFunction complex2 = new ComplexFunction(s2);
		ComplexFunction complex3 = new ComplexFunction(s3);
		ComplexFunction complex4 = new ComplexFunction(s4);

		complex1.min(complex2);
		complex3.min(complex2);
		complex2.min(complex4);
	}
	@Test
	void testComp() {
		
		String s1 = "x^2";
		String s2 = "x";
		String s3 = "mul(1,2)"; 
		String s4 = "plus(4,mul(5x+2,x^5))";
		
		ComplexFunction complex1 = new ComplexFunction(s1);
		ComplexFunction complex2 = new ComplexFunction(s2);
		ComplexFunction complex3 = new ComplexFunction(s3);
		ComplexFunction complex4 = new ComplexFunction(s4);

		complex1.comp(complex2);
		complex3.comp(complex2);
		complex2.comp(complex4);
	}
	@Test
	void testToString() {
		String s1 = "mul(1,2)"; 
		String s2 = "mul(mul(1,2),3)";
		String s3 = "mul(1,mul(2,3))";
		
		ComplexFunction complex1 = new ComplexFunction(s1);
		ComplexFunction complex2 = new ComplexFunction(s2);
		ComplexFunction complex3 = new ComplexFunction(s3);
				
		assertEquals("mul(1,2)",s1.toString());
		assertEquals("mul(mul(1,2),3)",s2.toString());
		assertEquals("mul(1,mul(2,3))",s3.toString());	
	}

}










