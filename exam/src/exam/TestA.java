package exam;

import java.util.Scanner;

public class TestA {
	public static void doStuff(int[] doArgs) { }
	public static void main(String[]... a) throws Exception{ 
	//public static void main(String[] args) throws Exception {
		
//		
//		public static void main(String...a) { 
//			public static void main(String.* a) { 
//				public static void main(String... a) { 
//					public static void main(String[]... a) { 
//				
//			
//		
//			public static void main(String...[] a) {
//		int [] doArgs = null;
//		TestA.doStuff(doArgs);
//		TestA.doStuff(1);
//		TestA.doStuff(1,2);
	 try{
 new A().sayHello("Aiko");
	 }catch(Exception e){}
	// new A().new Aa(); // new inner class
	 new SubB2().father();
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
	for (String interator_a:result)
	System.out.println(interator_a);
	
	
	
//	A a = new SubB2();
//	
//	a.foo();
	SubB2 c= new SubB2();
	String testvariable= c.bb;
	System.out.println(testvariable);

	
	final int finala;
	finala=13;
	System.out.println(finala);
	
 String csv = "Sue,5,true,3";
 Scanner scanner = new Scanner( csv ); 
 scanner.useDelimiter(",");
 String age1 = scanner.next();
 //int age = scanner.nextInt();
 System.out.print(age1);
//	try {
// args = null;
// args[0] = "test";
// System.out.println(args[0]);
// } catch (Exception ex) {
// System.out.println("Exception");
// } catch (NullPointerException npe) {
// System.out.println("NullPointerException");  }
 }
}