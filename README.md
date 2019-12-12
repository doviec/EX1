# Ex1 :sunglasses:
EX1 matala is an assigment that represents three functions; Monom, Polynom and ComplexFunction.
This project allows us to make a variety of math operations towards these functions. 
In addition we may read a file with a colections of functions and draw them with stddraw in class Functions_GUI.

# Class Monom
A Monom is represented by ax^b. a and b represent two objects: b-power (int) which represents the exponent of the monom and a- coefficient (double) which holds the valid coefficient of the monom.  
This class implements function and includes a variety of math operations and methods which calculate the value of the monom etc. 
# Class Polynom
Class Polynom is a class which constructs Polynom which is represented by a hashmap of monoms and the class has a variety of functions that we can use on the polynom including math opertaions, area root etc. 
This class implements Polynom_able in addition, Polynom calls the methods in Monom to help itself perform its functions. 

# Class ComplexFunction
A ComplexFunction is a binary tree represented by a three Objects; root represnted by math Operation and left & right which are represnted as functions (Monom Polynom ComplexFunction). ComplexFunction performs math operations between itself and different functions by useing the methods of Monom & Polynom.
This class implements function as well.

# Class Functions_GUI
With this class we can read given a file and take its functions and draw them in  a Y and X scales. Function_Gui is represented by a Linklised of functions.
