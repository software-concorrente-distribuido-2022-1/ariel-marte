package ex02;
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
		if(mensagem.length != 3) {
			return "Erro no conteúdo da mensagem";
		}
		
		String nome = mensagem[0];
		String sexo = mensagem[1];
		Integer idade = null;
		try {
			idade = Integer.parseInt(mensagem[2]);
		} catch (NumberFormatException e) {
			return "Idade inválida";
		}
		
		Boolean maiorIdade = false;
		if(sexo.equals("F") && idade >= 21) {
			maiorIdade = true;
		} else if (sexo.equals("M") && idade >= 18) {
			maiorIdade = true;
		} else if (!sexo.equals("F") && !sexo.equals("M")) {
			return "Indeterminado";
		} else {
			maiorIdade = false;
		}
		
		return nome + (!maiorIdade ? " nao " : "") + " atingiu a maioridade.";
	}


}