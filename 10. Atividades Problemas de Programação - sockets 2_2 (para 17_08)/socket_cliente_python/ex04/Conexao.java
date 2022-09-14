package ex04;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.Socket;

class Conexao extends Thread {
	final String msgBadRqt = "400 Bad Request";
	Socket socketCliente;

	Conexao(Socket aSocketCliente) throws IOException {
		this.socketCliente = aSocketCliente;
	}

	public void run() {

		PrintWriter saida = null;
		BufferedReader entrada = null;

		InetAddress endCliente = this.socketCliente.getInetAddress();

		String mensagem = null;

		try {
			saida = new PrintWriter(this.socketCliente.getOutputStream(), true);
			entrada = new BufferedReader(new InputStreamReader(this.socketCliente.getInputStream()));

			mensagem = entrada.readLine();
			String retorno = realizarOperacao(mensagem);
			
			saida.println(retorno);
			System.out.println("Cliente " + endCliente.getHostAddress() + " atendido com a " + "mensagem " + retorno);

			entrada.close();

		} catch (IOException e) {
			System.out.println("Erro E/S " + e);
		} finally {
			try {
				socketCliente.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			saida.close();
		}
	}
	
	private String realizarOperacao(String entrada) {
		String[] mensagem = entrada.split("\\|");
		if(mensagem.length != 2) {
			return "Erro no conteúdo da mensagem";
		}
		
		String sexo = mensagem[0];
		Integer altura = null;
		try {
			altura = Integer.parseInt(mensagem[1]);
		} catch (NumberFormatException e) {
			return "Altura inválida";
		}
		
		BigDecimal pesoIdeal = null; 
		if(sexo.equals("F")) {
			pesoIdeal = ((BigDecimal.valueOf(altura).divide(new BigDecimal("100.00"))).multiply(new BigDecimal("62.1")).subtract(new BigDecimal("44.7")));
		} else if (sexo.equals("M")) {
			pesoIdeal = ((BigDecimal.valueOf(altura).divide(new BigDecimal("100.00"))).multiply(new BigDecimal("72.7")).subtract(new BigDecimal("58")));
		} else {
			return "Indeterminado";
		}
		
		return pesoIdeal.toPlainString().replace("\\.", ",") + "kg";
	}


}