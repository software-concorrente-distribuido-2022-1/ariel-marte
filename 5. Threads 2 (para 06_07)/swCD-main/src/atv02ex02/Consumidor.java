package atv02ex02;

public class Consumidor implements Runnable {
	private Deposito dep;
	private int i;

	public Consumidor(Deposito dep, int i) {
		this.dep = dep;
		this.i = i;
	}

	@Override
	public void run() {
		for (int i = 0; i <= 10; i++) {
			dep.request();

			while (dep.retirar() == 0) {
				System.out.println("Vazio");
				dep.release();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				dep.request();
			}
			dep.release();
			System.out.println("Uma caixa retirada");

			try {
				Thread.sleep(this.i * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
