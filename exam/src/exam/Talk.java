package exam;

import java.io.*;

public class Talk {

//	static final int[] a1;
//	static {
//		a1 = new int[2];
//		a1[0] = 100;
//		a1[1] = 200;
//	}
//	static final int[] aa = new int[] { 100, 200 };
//	static final int[] a;
//
//	static void init() {
//		a = new int[3];
//		a[0] = 100;
//		a[1] = 200;
//	}

	public static void main(String[] args) {
		Console c = System.console();
		System.out.print("password: ");
		// pw = c.readLine();
		// String pw=c.readLine("%s" , "12");
		// System.out.println("got " + pw);
		// StringBuilder sb1 = new StringBuilder("123");
		// StringBuilder sb2 = sb1;

		StringBuilder sb1 = new StringBuilder("123");
		StringBuilder sb2 = new StringBuilder("123");
		;
		// sb1.append("3");
		System.out.println((sb1 == sb2) + " sb1 " + sb1 + " sb2 " + sb2);

		String a1 = "abc";
		String b1 = "abc";
		String a = new String("abc");
		String b = new String("abc");
		System.out.println(a.equals(b));
		System.out.println(a == b);
		System.out.println(a1.equals(b1));
		System.out.println(a1 == b1);

		Integer x = 400;
		Integer y = x;
		x++;
		System.out.println((x == y));

	}
}