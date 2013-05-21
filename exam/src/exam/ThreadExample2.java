package exam;

import java.io.File;
import java.util.Scanner;

public class ThreadExample2 implements Runnable {
	public void run() { // implements Runnable run()
		System.out.println("Here is the starting point of Thread.");
		for (int i = 0; i < 1; i++) { // infinite loop to print message
			System.out.println("User Created Thread");
		}
	}

	public static void main(String[] argv) {
		Thread a = new Thread(new ThreadExample2()); // 產生Thread物件
		a.start(); // 開始執行Runnable.run();
		for (int i = 0; i < 1; i++) {
			System.out.println("Main Thread");
		}
		Runnable r = new Runnable() {
			public void run() {
				System.out.println("Cat");
			}
		};
		Thread t = new Thread(r) {
			public void run() {
				System.out.println("Dog");
			}
		};
		File file=new File("a", "b");
		t.start();
		a.run();
		 a.run();
		 
		   Scanner scanner = new Scanner("One,5,true,3,true,6,7,false");  
		   scanner.useDelimiter(",");  
		     while(scanner.hasNext ()){  
		      if(scanner.hasNextBoolean () ) {  
		       System.out.println (scanner.nextBoolean () +" " ); }  
		      else scanner.next ();  
		   }

	}
}