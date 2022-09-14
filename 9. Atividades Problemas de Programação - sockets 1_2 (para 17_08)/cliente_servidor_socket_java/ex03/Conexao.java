package ex03;

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
		if (mensagem.length != 3) {
			return "Erro no conteúdo da mensagem";
		}

		BigDecimal n1;
		BigDecimal n2;
		BigDecimal n3;
		try {
			n1 = new BigDecimal(mensagem[0]);
			n2 = new BigDecimal(mensagem[1]);
			n3 = new BigDecimal(mensagem[2]);
		} catch (NumberFormatException e) {
			return "Nota inválida";
		}

		BigDecimal media = (n1.add(n2)).divide(new BigDecimal("2.00"));
		if (media.compareTo(new BigDecimal("7.00")) >= 0) {
			return "Aprovado";
		}

		if (media.compareTo(new BigDecimal("3.00")) <= 0) {
			return "Reprovado";
		}

		media = (media.add(n3)).divide(new BigDecimal("2.00"));
		if (media.compareTo(new BigDecimal("5.00")) >= 0) {
			return "Aprovado";
		}

		return "Reprovado";
	}

}