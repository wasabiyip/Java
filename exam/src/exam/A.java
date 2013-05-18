package exam;

class A {
 public String sayHello(String name) throws TestException {
 if(name == null) throw new TestException();
		return "Hello " + name;
	}
 }