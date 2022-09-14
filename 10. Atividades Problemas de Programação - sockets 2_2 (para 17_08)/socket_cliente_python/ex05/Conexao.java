package ex05;

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

		Integer idade;
		try {
			idade = Integer.parseInt(entrada);
		} catch (NumberFormatException e) {
			return "Idade inválida";
		}

		if (idade >= 5 && idade <= 7) {
			return "Infantil A";
		}
		if (idade >= 9 && idade <= 10) {
			return "Infantil B";
		}
		if (idade >= 11 && idade <= 13) {
			return "Juvenil A";
		}
		if (idade >= 14 && idade <= 17) {
			return "Juvenil B";
		}
		if (idade >= 18) {
			return "Adulto";
		}

		return "Idade inválida";
	}

}