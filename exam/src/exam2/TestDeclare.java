package exam2;

import java.util.ArrayList;

public class TestDeclare implements DeclareStuff {
	public static void main(String[] args) {
		int x = 5;
		new TestDeclare().doStuff(++x);
		ArrayList<Object> x3 = new ArrayList<String>();
	}

	public void doStuff(int s) {
		s += EASY + ++s;
		System.out.println("s " + s);
	}
}
