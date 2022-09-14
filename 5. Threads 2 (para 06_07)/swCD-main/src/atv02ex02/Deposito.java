package atv02ex02;

public class Deposito {
	private int items = 0;
	private final int capacidade = 10;
	private boolean monitor = false;

	public int retirar() {
		if (items > 0) {
			items--;
			System.out.println("Caixa retirada: Sobram " + items + " caixas");
			return 1;
		}
		return 0;
	}

	public int colocar() {
		if (items < capacidade) {
			items++;
			System.out.println("Caixa armazenada: Passaram a ser " + items + " caixas");
			return 1;
		}
		return 0;
	}

	public synchronized void request() {
		while (monitor) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		monitor = true;
	}

	public synchronized void release() {
		monitor = false;
		notifyAll();
	}

	public static void main(String[] args) {
		Deposito dep = new Deposito();
		Produtor p1 = new Produtor(dep, 1);
		Produtor p2 = new Produtor(dep, 1);
		Consumidor c1 = new Consumidor(dep, 2);
		Consumidor c2 = new Consumidor(dep, 2);

		Thread tp1 = new Thread(p1);
		Thread tp2 = new Thread(p1);
		Thread tp3 = new Thread(p2);
		Thread tp4 = new Thread(p2);

		Thread tc1 = new Thread(c1);
		Thread tc2 = new Thread(c1);
		Thread tc3 = new Thread(c2);
		Thread tc4 = new Thread(c2);

		tp1.start();
		tp2.start();
		tp3.start();
		tp4.start();

		tc1.start();
		tc2.start();
		tc3.start();
		tc4.start();

		System.out.println("Execução do main da classe Deposito terminada!");
	}
}