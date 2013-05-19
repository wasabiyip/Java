package exam;

class A {
	public String sayHello(String name) throws TestException {
		if (name == null)
			throw new TestException();
		return "Hello " + name;
	}

	void foo() { // throw new Exception();
	}

	public void father() {
		System.out.println("father");
	}
	public class Aa{}
	// public A(String name){}
}