package atv02ex01;

public class Contador implements Runnable{

	public void run() {
		for (int i = 0; i <= 10; i++) {
			
			System.out.println("Contador: " + i);
		}
		
	}
}
