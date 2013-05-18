package exam;

class Sub extends Base {
	public static final String FOO = "bar";

	public String _base="sub";
	public void show1() {
		System.out.println("Sub show1");
	}
	public void show2() {
		System.out.println("Sub show2");
	}
	public class innerclass {
		void innerclass() {
		}
	}
}