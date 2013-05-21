package exam;

import java.util.HashMap;

public class Test130 extends Bar{
	public void addfive(){a+=5; System.out.print("Test130 ");}
public  static void main(String[] args){
	Bar a= (Bar)new Test130();
	a.addfive();
	HashMap<String,Integer> map= new HashMap<String,Integer>();
	map.keySet();
}

}
