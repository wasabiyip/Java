package exam;

public class ThreadExample2 implements Runnable {
    public void run() { // implements Runnable run()
        System.out.println("Here is the starting point of Thread.");
        for (int i=0;i<5;i++) { // infinite loop to print message
            System.out.println("User Created Thread");
        }
    }
    public static void main(String[] argv) {
        Thread a = new Thread(new ThreadExample2()); // 產生Thread物件
        a.start(); // 開始執行Runnable.run();
        for (int i=0;i<5;i++) {
            System.out.println("Main Thread");
        }
 Runnable r = new Runnable() {
 public void run() {
        	 System.out.print("Cat");
 }
 };
 Thread t = new Thread(r) {
 public void run() {
 System.out.print("Dog");
 }
 };
 t.start();
        	
        
    }
}