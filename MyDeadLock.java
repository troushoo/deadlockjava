package mydeadlock;

public class MyDeadLock {

	String str1 = "str1";
	String str2 = "str2";

	Thread th1 = new Thread("Thread1") {
		public void run() {
			synchronized(str1) {
				System.out.println("Thread1 is holding str1.");
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Thread1 is waiting for str2.");
				synchronized(str2) {
					System.out.println("Thread1 is holding str2");
				}
			}
		}
	};
	
	Thread th2 = new Thread("Thread2") {
		public void run() {
			synchronized(str2) {
				System.out.println("\tThread2 is holding str2");
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("\tThread2 is waiting for str1.");
				synchronized(str1) {
				System.out.println("\tThread2 is holding str1");
				}
			}
		}
	};
	
	public static void main(String[] args) {
		MyDeadLock dl = new MyDeadLock();

		dl.th1.start();
		dl.th2.start();
		
		System.out.println("finished");
	}

}
