package atv03ex02;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Buscador implements Callable<Integer> {
	private Integer posicaoInicial;
	private Integer posicaoFinal;
	private int[] numeros;
	private Integer x;


	public void setPosicaoInicial(Integer posicaoInicial) {
		this.posicaoInicial = posicaoInicial;
	}

	public void setPosicaoFinal(Integer posicaoFinal) {
		this.posicaoFinal = posicaoFinal;
	}

	public void setNumeros(int[] numeros) {
		this.numeros = numeros;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	@Override
	public Integer call() throws Exception {
		
		for(int i = posicaoInicial; i <= posicaoFinal; i++) {
			if(numeros[i] == x) {
				return i;
			}
		}
		
		return -1;
	}

}
