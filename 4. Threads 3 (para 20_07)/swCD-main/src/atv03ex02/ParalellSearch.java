package atv03ex02;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParalellSearch {

	public static int paralellSearch(int x, int[] a, int numThreads) {
		ArrayList<Buscador> buscadores = new ArrayList<>();
		LocalTime startTime;
		LocalTime endTime;
		
		for (int n = 0; n < numThreads; n++) {
			Buscador buscador = new Buscador();
			buscador.setNumeros(a);
			
			int posicaoInicial = (n*a.length)/numThreads;
			int posicaoFinal = (((n+1)*a.length)/numThreads)-1;
			
			buscador.setPosicaoInicial(posicaoInicial);
			buscador.setPosicaoFinal(posicaoFinal);
			buscador.setX(x);
			
			buscadores.add(buscador);
		}

		ExecutorService threadPool = Executors.newFixedThreadPool(numThreads);
		ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(threadPool);
        
        startTime = LocalTime.now();
        endTime = LocalTime.now();
        
        for(Buscador buscador : buscadores) {
        	completionService.submit(buscador);
        }
        
        Integer resultado = null;
        for(int i = 0; i <  buscadores.size(); i++) {
        	try {
				resultado = completionService.take().get();
	        	if(resultado != -1) {
	        		endTime = LocalTime.now();
	        		break;
	        	}
	        	if(i == (buscadores.size()-1)) {
            		endTime = LocalTime.now();
            	}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
        }

        threadPool.shutdown();
		return resultado.intValue();
	}
}
