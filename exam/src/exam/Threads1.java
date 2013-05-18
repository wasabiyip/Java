package exam;

public class Threads1 {

	int x = 0;
	static int y = 0;

	class Runner implements Runnable {

		public void run() {
			int count=y;
			y++;
			int current = 0;
			System.out.println("Thread start" + count + "  " + x);

			if (count == 0) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				x++;
			} else {
				x = x + 2;
			}

			System.out.println("Thread end " + count + "  " + x);
			 
			// System.out.println("Thread "+x);
			// for (int i = 0; i < 4; i++) {
			// current = x;
			// System.out.print(current + ", "+x+"   ");
			// x = current + 2;
			// }
		}
	}

	public static void main(String[] args) {
		Threads1 temp = new Threads1();
		temp.go();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("main " + temp.x);
	}

	public void go() {
		Runnable r1 = new Runner();
		new Thread(r1).start();

		new Thread(r1).start();
	}
}