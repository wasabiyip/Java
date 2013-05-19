package exam2;

import java.io.IOException;

class B extends A {
	public void process() throws IOException {
		super.process();
		System.out.print("B,");
		throw new IOException();
	}

	public static void main(String[] args) {
		try {
			new B().process();
		} catch (IOException e) {
			System.out.println("Exception");
		}
	}
}
