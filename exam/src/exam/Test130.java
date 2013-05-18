package exam;

public class Test130 extends Bar{
	public void addfive(){a+=5; System.out.print("Test130 ");}
public  static void main(String[] args){
	Bar a= (Bar)new Test130();
	a.addfive();
	
}

}
