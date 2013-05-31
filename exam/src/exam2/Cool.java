package exam2;

public class Cool extends SuperCool {
	public static void main(String[] args) {
		new Cool().go();
		new Cool().test();
	}
	private void test (){System.out.println("hi");}

	void go() {
		SuperCool s = new Cool();
		Cool c = (Cool) s;
		c.doStuff();

		// insert code here
	}

	void doStuff() {
		os += "cool ";
	}
}