package tests;
import myMath.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ComplexFunctionTest {

	

	@Test
	void ComplexFunction() {
		String s1 = "mul(1,2)"; 
		String s2 = "mul(mul(1,2),3)";
		String s3 = "mul(1,mul(2,3))";
		
		ComplexFunction complex1 = new ComplexFunction(s1);
		ComplexFunction complex2 = new ComplexFunction(s2);
		ComplexFunction complex3 = new ComplexFunction(s3);
		

	}
	

}
