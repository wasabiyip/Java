package exam2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Go extends Game {
	// Go() { super(s2); }
	Go() {
		super("hi");
		super.love();
		s += "constructor ";
	}

	{
		s += "no static ";
	}

	public static void main(String[] args) {
//		HashMap mapa = new HashMap();
//		mapa.put(null, "a");
//		mapa.put(null, "b");
//		mapa.put("c", null);
//		System.out.println(mapa.get("c"));
		//Arrays.sor
		
		// System.out.println(aa);
		 new Go();
		 System.out.println(s);
		// test();
		// Arrays.binarySearch(null,null); array and key_to_search
		// for(int a=0,System.out.println("hi");;); this is wrong
	}

	static int aa = 0;
	static {
		s += "sb ";
	}

	static void test() {
		Go a= new Go();
		a.dostuff();
		System.out.println("hi");
	}

	void test_dostuff(){		
		Go a= new Go();
		a.dostuff();
	}

	void dostuff() {
		System.out.println("do stuff");
	}
}