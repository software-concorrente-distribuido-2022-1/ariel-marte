package exercicio02;

import java.time.LocalTime;

public class MainFruta {
	public static void main(String[] args) {
		
		Frutas banana = new Frutas("banana");
		Thread tBanana = new Thread(banana);
		tBanana.start();
		
		Frutas maca = new Frutas("maca");
		Thread tMaca = new Thread(maca);
		tMaca.start();
		
		Frutas pera = new Frutas("pera");
		Thread tPera = new Thread(pera);
		tPera.start();
		
		Frutas abacate = new Frutas("abacate");
		Thread tAbacate = new Thread(abacate);
		tAbacate.start();
		
		Frutas uva = new Frutas("uva");
		Thread tUva = new Thread(uva);
		tUva.start();
		

		
	}
}
