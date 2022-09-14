

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

}