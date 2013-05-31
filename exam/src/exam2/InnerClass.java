package exam2;

public class InnerClass {
	 class A {
	}
	public static void main(String []args){
		InnerClass a= new InnerClass();
		InnerClass.A b= a.new A();
		String[] s = {"duck", null, "frog"};
		if((s[1] == null) ) System.out.print("1 "+s[1]);
		boolean test=false;
		if((s[2] == null) && (test = true)) System.out.print("2 ");
		int [] []ia2 = new int[3][3];
		int [] a3= new int[9];
	}
	// ||(s[1].length() == 0)
}
