package network.thread;

public class MultithreadEx01 {

	public static void main(String[] args) {
		Thread digitThread1 = new DigitThread();
		Thread digitThread2 = new DigitThread();
		Thread alphabetThread = new AlphabetThread();
		Thread aplphbetThread2 = new Thread(new AlphabetRunnableImpl());

		digitThread1.start();
		alphabetThread.start();
		digitThread2.start();
		aplphbetThread2.start();
	}
}