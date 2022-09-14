package atv03ex01;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class GerarPrimos implements Callable<ArrayList<Long>>{
	
	private Long numeroInicial;
	private Long numeroFinal;
	private ArrayList<Long> primos;
	
	public GerarPrimos(Long numeroInicial, Long numeroFinal) {
		this.numeroInicial = numeroInicial;
		this.numeroFinal = numeroFinal;
		this.primos = new ArrayList();
	}
	
	@Override
	public ArrayList<Long> call() throws Exception {
		for (long numero = numeroInicial; numero <= numeroFinal; numero++) {
			
			if (isPrimo(numero)) {
				primos.add(numero);
			}
//			 if(numero == numeroFinal) {
//				 System.out.println("Ultima verificação: "+numero);
//			 }
//			System.out.println("Verificado: " + numero);
		}
		
		return primos;
	}
	
	
	private boolean isPrimo(Long numero) {

		if (numero <= 1)
			return false;
		for (long j = 2; j * j <= numero; j++) {
			if (numero % j == 0)
				return false;
		}
		return true;
	}


//	private ArrayList<Long> numeros = new ArrayList<Long>();
//	private boolean monitor = false;
//	
//	public synchronized void request() {
//		while (monitor) {
//			try {
//				wait();
//			} catch (InterruptedException e) {
//			}
//		}
//		monitor = true;
//	}
//
//	public void adicionarPrimos(long numeroPrimo) {
//		numeros.add(numeroPrimo);
//	}
//	
//	public synchronized void release() {
//		monitor = false;
//		notifyAll();
//	}
//	
//	public ArrayList<Long> getNumeros() {
//		return numeros;
//	}
//	
//	public boolean isMonitor() {
//		return monitor;
//	}
//	
//	public void setMonitor(boolean monitor) {
//		this.monitor = monitor;
//	}
//	
	
	
}
