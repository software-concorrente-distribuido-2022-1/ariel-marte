package atv02ex02;

public class Produtor implements Runnable {
	private Deposito dep;
	private int i;

	public Produtor(Deposito dep, int i) {
		this.dep = dep;
		this.i = i;
	}

	@Override
	public void run() {
		for (int i = 0; i <= 10; i++) {
			dep.request();

			while (dep.colocar() == 0) {
				System.out.println("Cheio");
				dep.release();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				dep.request();
			}

			dep.release();
			System.out.println("Uma caixa armazenada");

			try {
				Thread.sleep(this.i * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}