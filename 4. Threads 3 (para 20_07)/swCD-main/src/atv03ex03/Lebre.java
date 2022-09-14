package atv03ex03;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Lebre implements Callable<String>{
	

	private String nome;
	private Integer distanciaTotal;
	private Integer distanciaPercorrida;
	private Integer pulos;
	

	@Override
	public String call() throws Exception {
		this.correr();
		return this.nome;
	}

	public Lebre(String nome, Integer distanciaTotal) {
		super();
		this.nome = nome;
		this.distanciaTotal = distanciaTotal;
		this.distanciaPercorrida = 0;
		this.pulos = 0;
	}
	
	void correr() {
		while(this.distanciaPercorrida < this.distanciaTotal) {
			this.pular();
		}
	}

	void pular() {
		int distanciaPulo = (int) (Math.random()*3 +1);
		this.distanciaPercorrida = this.distanciaPercorrida + distanciaPulo;
		System.out.println(this.nome+" pulou "+distanciaPulo+" metros.");
		Thread.yield();
	}


	public String getNome() {
		return nome;
	}


	public Integer getDistanciaPercorrida() {
		return distanciaPercorrida;
	}


	public Integer getDistanciaTotal() {
		return distanciaTotal;
	}


	public Integer getPulos() {
		return pulos;
	}
	
	
	
}