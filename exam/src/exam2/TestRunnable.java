package exam2;

public class TestRunnable {
	public static void main(String[] args) throws InterruptedException{
		Runnable r= new Runnable(){
			public void run (){
				try{
					System.out.println("begin");
					Thread.sleep(1000);
					Thread.sleep(3000);
				}catch(InterruptedException e){
					System.out.println("Interrupted");
				}
				System.out.println("ran");
			}
		};
		Thread t = new Thread(r);
		t.start();
		System.out.println("started");
		t.sleep(2000);
		System.out.println("sleep");

		t.sleep(2000);
		System.out.println("interrupting");
		t.interrupt();
		System.out.println("end");
	}

}
