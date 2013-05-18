package exam;

import java.util.*;

public class Test {
	static void test() throws Error {
		if (true)
			throw new AssertionError();
		System.out.print("test ");
	}

	public static Iterator reverse(List list) {
		Collections.reverse(list);
		return list.iterator();
	}

	public static void main(String[] args) {
		List list = new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		Iterator i = list.iterator();
		System.out.println(i);
		StringBuffer a = new StringBuffer();
		String s = "123456789";
		s = s.replace("123", "").replace("56", "24").replace( "89","");
		// for (Object obj: reverse(list))
		 System.out.print(s);
	}

}
