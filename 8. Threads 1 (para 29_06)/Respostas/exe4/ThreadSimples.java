package exe4;

public class ThreadSimples {									// Cria a classe ThreadSimples
	static void mensagem(String messagem) { 								// Função mensagem que recebe como parametro uma String
		String nomeThread = Thread.currentThread().getName(); 				// Cria uma variavel String que recebe o nome da Thread
		System.out.println(nomeThread + " " + messagem);					// Imprime o nome da Thread com a menssagem
	}
	private static class Loop implements Runnable {							// Cria a Classe Loop que implementa a interface Runnable
		public void run() {													// Implementa a função executável da interface
			String info[] = {												// Cria uma váriavel Array de String
					"Java",													// Dado na posição 0 do array
					"é uma boa linguagem.",									// Dado na posição 0 do array
					"Com threads,",											// Dado na posição 0 do array
					"é melhor ainda."										// Dado na posição 0 do array
			};
			try {															// Inicio do try-catch
				for (int i = 0; i < info.length; i++) {						// For no tamanho da Array de String (percorre o Array)
					Thread.sleep(4000);										// Thread "dorme" / "Espera" por 4000 milissegundos
					mensagem(info[i]);										// Imprime a mensagem na posição da iteração por meio da função "mensagem"
				}
			} catch (InterruptedException e) {								// Tratamento de exceção do try-catch
				mensagem("Nada feito!");									// Imprime a mensagem por meio da função "mensagem"
			}
		}
	}
	public static void main(String args[]) throws InterruptedException { 	// Cria a classe de execução
		long paciencia = 1000 * 60 * 60;									// Cria uma variavel Long e atribiu a ela o resultado de "1000 * 60 * 60"
		if (args.length > 0) {												// Condicional IF, sendo verdadeiro quando args possui tamanho maior que zero
			try {															// Inicio do try-catch
				paciencia = Long.parseLong(args[0]) * 1000;					// Seta uma novo valor para a variavel, sendo ele a posição 0 do array do args * 1000
			} catch (NumberFormatException e) {								// Tratamento de exceção do try-catch
				System.err.println("Argumento deve ser um inteiro.");		// Imprime a mensagem de erro
				System.exit(1);												// Encerra a aplicação
			}
		}
		mensagem("Iniciando a thread Loop");								// Imprime a mensagem por meio da função "mensagem"
			}
		long inicio = System.currentTimeMillis();							// Cria uma variavel long e seta o valor da hora atual em milissegundos
		Thread t = new Thread(new Loop());									// Cria uma varivael e inicializa uma Thread
		t.start();															// Executa a Thread
		mensagem("Esperando que a thread Loop termine");					// Imprime a mensagem por meio da função "mensagem"
		while (t.isAlive()) {												// Loop while enquanto a Thread estiver ativa
			mensagem("Ainda esperando...");									// Imprime a mensagem por meio da função "mensagem"
			t.join(1000);													
			if (((System.currentTimeMillis() - inicio) > paciencia) &&		// Condicional para que o tempo alcance o tempo definido
					t.isAlive()) {											// continuação da condicional - e que a Thread esteja ativa
				mensagem("Cansado de esperar!");							// Imprime a mensagem por meio da função "mensagem"
				t.interrupt();												// Interrompe a thread que está no estado de espera
				t.join();													// thread entra em estado de espera
			}
		}
		mensagem("Finalmente!");											// Imprime a mensagem por meio da função "mensagem"
	}
}
