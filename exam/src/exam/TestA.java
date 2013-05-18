package exam;

public class TestA {
	public static void main(String[] args) {
	 try{
 new A().sayHello("Aiko");
	 }catch(Exception e){}
	 
	 
	 double d = 12.345;
//	 System.out.printf("|%7d| \n", d);
	  System.out.printf("|%7f| \n", d);
	//  System.out.printf("|%3.7d| \n", d);
	  System.out.printf("|%3.7f| \n", d);
	//  System.out.printf("|%7.3d| \n", d);
	System.out.printf("|%7.3f| \n", d);
	String test = "Test A. Test B. Test C.";
	String regex = "\\.\\s*";
	String[] result = test.split(regex);
	for (String a:result)
	System.out.println(a);
 }
}