package ex07;
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
		Integer idade = null;
		Integer tempoServ = null;
		try {
			idade = Integer.parseInt(mensagem[0]);
			tempoServ = Integer.parseInt(mensagem[1]);
		} catch (NumberFormatException e) {
			return "Entrada inválida";
		}
		
		if(idade >= 65) return "Pode se aposentar";
		if(tempoServ >= 30) return "Pode se aposentar";
		if(idade >= 60 && tempoServ >= 25) return "Pode se aposentar";
		
		return "Não pode se aposentar";
	}


}