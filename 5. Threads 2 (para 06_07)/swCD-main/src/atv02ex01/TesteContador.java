package atv02ex01;

public class TesteContador {
public static void main(String[] args) {
	Contador contador1 = new Contador();
	
	Thread t1 = new Thread(contador1);
	t1.start();
}
}
