package exam;

public class Base {
	public static final String FOO = "foo";
	public String _base="base";
	public void show1(){System.out.println("base show1");}
	public void show2(){System.out.println("base show2");}

	public static void main(String[] args) {
		Base b = new Sub();
		b.show1();
		b.show2();
		System.out.println((b)._base);

		System.out.println(((Sub)b)._base);
		System.out.println(((Base)new Sub())._base);
//		Sub.innerclass a = new Sub().new innerclass();
//		b.show1();
		
//		Sub s = new Sub();
//		System.out.print(Base.FOO);
//		System.out.print(Sub.FOO);
//		System.out.print(b.FOO);
//		System.out.print(s.FOO);
//		System.out.print(((Base) s).FOO);
	}
}