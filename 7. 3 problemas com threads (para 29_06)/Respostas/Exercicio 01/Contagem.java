package exercicio01;

public class Contagem implements Runnable {

	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
            System.out.println(i + " de 100");
        }
		
	}

}
