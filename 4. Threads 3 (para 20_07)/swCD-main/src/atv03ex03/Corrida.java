package atv03ex03;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import atv03ex01.GerarPrimos;

public class Corrida {

	public static void main(String[] args) {
		int participantes = 5;
		int distanciaTotal = 20;
		ArrayList<Lebre> tLebres = new ArrayList<Lebre>();
		ArrayList<String> resultado = new ArrayList<String>();

		for (int n = 0; n < participantes; n++) {
			String nome = "Lebre"+(n+1);
			tLebres.add(new Lebre(nome, distanciaTotal));
		}
		
		ExecutorService threadPool = Executors.newFixedThreadPool(participantes);
        ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(threadPool);
		
        System.out.println("Corrida Iniciada");
        
		for(Lebre tLebre : tLebres) {
			completionService.submit(tLebre);
		}
		
        for (int i = 0; i < participantes; i++) {
            
        	try {
            	String nomeChegada =completionService.take().get();
            	resultado.add(nomeChegada);
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        
        }
        threadPool.shutdown();
		
        for(int n = 0; n <3; n++) {
        	System.out.println((n+1)+"ºLugar: "+resultado.get(n));
        	
        }
	}

}
