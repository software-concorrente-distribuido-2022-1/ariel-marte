package atv03ex01;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exerc01 {
	public static void main(String[] args) throws IOException {
		int nucleos = Runtime.getRuntime().availableProcessors();
		Long numeroInicial = Long.parseLong("90000000");
		Long numeroFinal = Long.parseLong("120000000");
		Long intervalo = (numeroFinal - numeroInicial)/nucleos; 
		Long numeroInicialIntervalado;
		Long numeroFinalIntervalado;
		LocalTime startTime;
		LocalTime endTime;
		ArrayList<Long> listaPrimos = new ArrayList<Long>();
		
		
		ArrayList<GerarPrimos> tGerarPrimos = new ArrayList<GerarPrimos>();
		
		System.out.println("Numero de Thread: "+ nucleos);
		
		//Cria as Threads conforme intervalo
		for(int n = 1; n <= nucleos; n++) {
			numeroInicialIntervalado = (n != 1) ? numeroInicial+intervalo*(n-1)+1 : numeroInicial+intervalo*(n-1);
			numeroFinalIntervalado = (n != nucleos) ? numeroInicial + intervalo * n : numeroFinal;
			tGerarPrimos.add(new GerarPrimos(numeroInicialIntervalado, numeroFinalIntervalado));	
			System.out.println(n+ " Intervalo: "+numeroInicialIntervalado+" -> "+ numeroFinalIntervalado);
	
		}
		
		ExecutorService threadPool = Executors.newFixedThreadPool(nucleos);
        ExecutorCompletionService<ArrayList<Long>> completionService = new ExecutorCompletionService<>(threadPool);
        
        // Executa as tarefas
        startTime = LocalTime.now();
        endTime = LocalTime.now();
        for (GerarPrimos tGerarPrimo : tGerarPrimos) {
        	
            completionService.submit(tGerarPrimo);
        }
        System.out.println("Calculos iniciados, aguardando conclusao");

        //aguarda e imprime o retorno de cada uma
        for (int i = 0; i < tGerarPrimos.size(); i++) {
        	//while ()
            try {
            	//totalDePrimos = totalDePrimos + completionService.take().get().size();
            	listaPrimos.addAll(completionService.take().get());
                //System.out.println(completionService.take().get().size());
                //System.out.println("imprimi "+i);
                //System.out.println(completionService.take().);
            	if(i == (tGerarPrimos.size()-1)) {
            		endTime = LocalTime.now();
            	}
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        }
        threadPool.shutdown();
        System.out.println("Tempo Total: "+ Duration.between(startTime, endTime).toMillis()+" milissegundos");
        System.out.println("Total de Primos:"+listaPrimos.size());
        listaPrimos.sort((p1, p2) -> p1.compareTo(p2));
        GerarArquivo.GerarTxt("Primos", listaPrimos.toString());
        System.out.println("Execucao Finalizada");
 
        
        
	}
}
