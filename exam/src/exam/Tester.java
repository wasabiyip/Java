package exam;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Tester implements Runnable {
	static PingPong2 pp2 = new PingPong2();

	public static void main(String[] args) {
//		System.out.println(Thread.currentThread().getId());
//
//		new Thread(new Tester()).start();
//		new Thread(new Tester()).start();
//		
//		System.setProperty("user.timezone","Asia/Shanghai");
 		
		Locale loc = Locale.getDefault();

 	
 		String a = loc.getCountry();
 		Date d= new Date();
        DateFormat df = 
            new SimpleDateFormat("EE-MM-dd-yyyy");
       df= DateFormat.getInstance();
       

		System.out.println(" "+loc.getDisplayCountry()+ " " + df.format(d));

	}

	public void run() {
		System.out.println(Thread.currentThread().getId());
		pp2.hit(Thread.currentThread().getId());


	}
}
