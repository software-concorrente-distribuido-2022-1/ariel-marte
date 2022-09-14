package ex08;
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
		
		BigDecimal saldoMedio;
		try {
			saldoMedio = new BigDecimal(entrada);
		} catch (NumberFormatException e) {
			return "Saldo inválido";
		}
		BigDecimal valorCredito = null;
		Integer percentualCredito = null;
		if(saldoMedio.compareTo(BigDecimal.valueOf(200)) <= 0) return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN).toPlainString();
		if (saldoMedio.compareTo(BigDecimal.valueOf(200)) > 0 && saldoMedio.compareTo(BigDecimal.valueOf(400)) <= 0) {
			percentualCredito = 20;
		}
		if (saldoMedio.compareTo(BigDecimal.valueOf(400)) > 0 && saldoMedio.compareTo(BigDecimal.valueOf(600)) <= 0) {
			percentualCredito = 30;
		}
		if (saldoMedio.compareTo(BigDecimal.valueOf(600)) > 0) {
			percentualCredito = 40;
		}
		
		valorCredito = (saldoMedio.multiply(BigDecimal.valueOf(percentualCredito))).divide(new BigDecimal("100.00"));
		
		return valorCredito.setScale(2, RoundingMode.HALF_EVEN).toPlainString();
	}


}