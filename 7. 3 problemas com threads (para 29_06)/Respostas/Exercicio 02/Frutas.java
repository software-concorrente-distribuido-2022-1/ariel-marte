package exercicio02;

import java.time.LocalTime;

public class Frutas implements Runnable {

	String fruta;

	public Frutas(String nomeDaFruta) {
		this.fruta = nomeDaFruta;
	}
	
	public String getFruta() {
		return fruta;
	}

	public void setFruta(String fruta) {
		this.fruta = fruta;
	}

	@Override
	public void run() {
		
		Integer IntervalSeconds = 3;
		Integer count = 0;
		
		LocalTime currentTime = LocalTime.now();
		LocalTime endTime = currentTime.plusSeconds(IntervalSeconds);

		while(endTime.isAfter(currentTime)){
			count++;
			System.out.println(fruta + " " + count);
			currentTime = LocalTime.now();
		  }        
	            
		}
			
	}

