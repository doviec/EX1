package tests;

import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.util.Iterator;
import javax.xml.crypto.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.function;
import Ex1.functions;
/**
 * Note: minor changes (thanks to Amichai!!)
 * The use of "get" was replaced by iterator!
 * 
 * Partial JUnit + main test for the GUI_Functions class, expected output from the main:
 * 0) java.awt.Color[r=0,g=0,b=255]  f(x)= plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)
1) java.awt.Color[r=0,g=255,b=255]  f(x)= plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)
2) java.awt.Color[r=255,g=0,b=255]  f(x)= div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)
3) java.awt.Color[r=255,g=200,b=0]  f(x)= -1.0x^4 +2.4x^2 +3.1
4) java.awt.Color[r=255,g=0,b=0]  f(x)= +0.1x^5 -1.2999999999999998x +5.0
5) java.awt.Color[r=0,g=255,b=0]  f(x)= max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)
6) java.awt.Color[r=255,g=175,b=175]  f(x)= min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)

 * @author boaz_benmoshe
 *
 */
class Functions_GUITest {
	@Test
	void testBoazGui() {
		{
			functions ans = new Functions_GUI();
			String s1 = "3.1 +2.4x^2 -x^4";
			String s2 = "5 +2x -3.3x +0.1x^5";
			String[] s3 = {"x +3","x -2", "x -4"};
			Polynom p1 = new Polynom(s1);
			Polynom p2 = new Polynom(s2);
			Polynom p3 = new Polynom(s3[0]);
			ComplexFunction cf3 = new ComplexFunction(p3);
			for(int i=1;i<s3.length;i++) {
				cf3.mul(new Polynom(s3[i]));
			}
			ComplexFunction cf = new ComplexFunction(Operation.Plus, p1,p2);
			ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x +1"),cf3);
			cf4.plus(new Monom("2"));
			ans.add(cf.copy());
			ans.add(cf4.copy());
			cf.div(p1);
			ans.add(cf.copy());
			String s = cf.toString();
			function cf5 = cf4.initFromString(s1);
			System.out.println(cf4.toString());
			function cf6 = cf4.initFromString(s2);
			ans.add(cf5.copy());
			System.out.println(cf5.copy().toString());
			ans.add(cf6.copy());
			Iterator<function> iter = ans.iterator();
			function f = iter.next();
			ComplexFunction max = new ComplexFunction(f);
			ComplexFunction min = new ComplexFunction(f);
			while(iter.hasNext()) {
				f = iter.next();
				max.max(f);
				min.min(f);
			}
			ans.add(max);
			ans.add(min);		

			functions data = ans;
			//		int w=1000, h=600, res=200;       default
			//		Range rx = new Range(-10,10);
			//		Range ry = new Range(-5,15);
			//		data.drawFunctions(w,h,rx,ry,res);
			String file = "function_file.txt";
			String file2 = "function_file2.txt";
			try {
				data.saveToFile(file);
				Functions_GUI data2 = new Functions_GUI();
				data2.initFromFile(file);
				data.saveToFile(file2);
			}
			catch(Exception e) {e.printStackTrace();}

			String JSON_param_file = "GUI_params.json";
			data.drawFunctions(JSON_param_file);
		}
	}
	@Test
	void testMyGui() {
		String s1 = "div(1,x)";
		String s2 = "mul(x,x)";
		String s3 = "mul(1,2)"; 
		String s4 = "plus(4,mul(5x+2,x^5))";
		String s5 = "plus(mul(x^5,5x+2),4)";
		String s6 = "mul(x^2,x)";
		String s7 = "plus(12,mul(3x,mul(x^4,3-x)))";
		String s8 = "plus(-x,1)";

		ComplexFunction complex1 = (ComplexFunction)new ComplexFunction().initFromString(s1);
		ComplexFunction complex2 = (ComplexFunction)new ComplexFunction().initFromString(s2);
		ComplexFunction complex3 = (ComplexFunction)new ComplexFunction().initFromString(s3);
		ComplexFunction complex4 = (ComplexFunction)new ComplexFunction().initFromString(s4);
		ComplexFunction complex5 = (ComplexFunction)new ComplexFunction().initFromString(s5);
		ComplexFunction complex6 = (ComplexFunction)new ComplexFunction().initFromString(s6);
		ComplexFunction complex7 = (ComplexFunction)new ComplexFunction().initFromString(s7);
		ComplexFunction complex8 = (ComplexFunction)new ComplexFunction().initFromString(s8);

		Functions_GUI listGui1 = new Functions_GUI();
		Functions_GUI listGui2 = new Functions_GUI();
	
		
		listGui1.add(complex1);
		listGui1.add(complex2);
		listGui1.add(complex3);
		listGui1.add(complex4);
		listGui1.add(complex5);
		listGui1.add(complex6);
		listGui1.add(complex7);
		listGui1.add(complex8);

		String myTestFile = "myTestFile222.txt";
		String testGuiCopy = "testGuiCopy.txt";
		try {
			listGui1.saveToFile(myTestFile);
			
			listGui2.initFromFile(myTestFile);

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		String JSON_param_file = "GUI_params.json";

		System.out.println(listGui1.linkedList.toString());
		System.out.println(listGui2.linkedList.toString());

		listGui1.drawFunctions(JSON_param_file);
		listGui2.drawFunctions(JSON_param_file);

		assertTrue(listGui1.containsAll(listGui1));
	}
}


