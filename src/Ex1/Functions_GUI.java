package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.google.gson.Gson;

public class Functions_GUI implements functions{
	public LinkedList<function> linkedList;

	public Functions_GUI() {
		linkedList=new LinkedList<function>();
	}
	public LinkedList<function> getLinklist(){
		return linkedList;
	}
	@Override
	public int size() {
		return linkedList.size();
	}

	@Override
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return linkedList.contains(o);
	}

	@Override
	public Iterator<function> iterator() {
		return linkedList.iterator();
	}

	@Override
	public Object[] toArray() {
		return linkedList.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return linkedList.toArray(a);
	}

	@Override
	public boolean add(function e) {
		return linkedList.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return linkedList.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return linkedList.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		return linkedList.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return linkedList.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return linkedList.retainAll(c);
	}

	@Override
	public void clear() {
		linkedList.clear();
	}
	public static Color[] Colors = {Color.blue, Color.cyan,
			Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};
	/**
	 *  this method draws the graphic window and functions according to given measurements 
	 */
	public void drawFunctions(int Width, int Height, Range rx, Range ry, int Resolution)
	{
		StdDraw.setCanvasSize(Width,Height);
		StdDraw.setXscale(rx.get_min(), rx.get_max());       
		StdDraw.setYscale(ry.get_min(),ry.get_max());

		double maxRange = Math.max(ry.get_max(),rx.get_max());
		double minRange = Math.min(ry.get_min(),rx.get_min());
		for (int i = (int)minRange; i <= maxRange; i++) // X scale
		{			
			StdDraw.setPenRadius(0.000005);  
			StdDraw.setPenColor(Color.LIGHT_GRAY);
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
			Font font = new Font("Arial", Font.BOLD, 15);
			StdDraw.setFont(font);
			String text=Integer.toString(i);
			StdDraw.setPenColor(Color.BLUE);
			StdDraw.text(i+0.30, -0.30, text);  //numbers on the scale
		}	
		for (int i = (int)minRange; i <= maxRange; i++) // Y scale
		{
			StdDraw.setPenColor(Color.LIGHT_GRAY);  
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
			Font font = new Font("Arial", Font.BOLD, 15);
			StdDraw.setFont(font);
			String text=Integer.toString(i);
			StdDraw.setPenColor(Color.BLUE);
			StdDraw.text(-0.3,i+0.4, text);  //numbers on the scale
		}
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.0012);
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		StdDraw.setPenRadius(0.009);

		double epsRes = (Math.abs(rx.get_max())+Math.abs(rx.get_min()))/Resolution;  //epsilon according to range divide by resolution

		for (int j = 0; j < linkedList.size(); j++) {
			StdDraw.setPenColor(Colors[j%7]);
			for (double i =rx.get_min() ; i < rx.get_max(); i+=epsRes)
			{
				double x = i;
				double x0 = i + epsRes;
				double y = linkedList.get(j).f(x);
				double y0 = linkedList.get(j).f(x0);
				StdDraw.line(x,y,x0,y0);
			}
		}
	}
	/**
	 * draws a graphic window according to a given string in a file
	 */
	@Override
	public void drawFunctions(String json_file) {

		Gson gson = new Gson();
		try {
			JsonEx1 json = gson.fromJson(new FileReader(json_file), JsonEx1.class);
			String result = gson.toJson(json);
			System.out.println(result);
			drawFunctions( json.Width,json.Height,new Range(json.Range_X[0], json.Range_X[1]) ,new Range(json.Range_Y[0], json.Range_Y[1]), json.Resolution);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * this method reads the string from a given file and adds it to the linklist
	 */
	@Override
	public void initFromFile(String file) throws IOException {
		try {
			File myFile = new File(file);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(myFile));
			String string;
			while ((string = bufferedReader.readLine()) != null) {
				function function = new ComplexFunction();
				function = function.initFromString(string);   //check if ok check if can do this: function = function.initFromString(string); 
				linkedList.add(function);			
			}
		}
		catch (Exception e) {
		}
	}
	/**
	 * saves the linklist into a given file
	 */
	@Override
	public void saveToFile(String file) throws IOException {

		PrintWriter writer = new PrintWriter(file);
		for (int i = 0; i < linkedList.size(); i++) 
		{
			writer.println(linkedList.get(i));
		}
		writer.close();
	}
	public function get(int i) {
		return linkedList.get(i);
	}
}
