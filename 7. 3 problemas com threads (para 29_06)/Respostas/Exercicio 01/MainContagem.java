package exercicio01;

public class MainContagem {

	
	public static void main(String[] args) {

		Contagem c1 = new Contagem();
		Thread t1 = new Thread(c1);
		t1.start();
		
		System.out.println("Programa finalizado");
	}
	

}
