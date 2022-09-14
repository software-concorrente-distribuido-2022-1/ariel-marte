package exe3;

public class ThreadSimples extends Thread {
	
	@Override
	public void run() {
		System.out.println(this.getName());
		String info[] = {
				"Java",
				"é uma boa linguagem.",
				"Com threads",
				"é melhor ainda."
		};
		for (int i = 0; i < info.length; i++) {

			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {}
			System.out.println(info[i]);
		}	
	}
	public static void main(String args[]) throws InterruptedException {
		ThreadSimples simples = new ThreadSimples();
		Thread thread = new Thread(simples);
		thread.start();
	}
}
