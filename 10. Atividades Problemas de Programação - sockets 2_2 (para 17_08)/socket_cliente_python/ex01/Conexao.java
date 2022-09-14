package ex01;
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
		
		BigDecimal salario = null;
		try {
			salario = new BigDecimal(mensagem[2].replace(",", "."));
		} catch (NumberFormatException e) {
			return "O salário deve ser informado no formato 9999,99";
		}
		
		BigDecimal novoSalario = null;
		if(mensagem[1].equals("operador")) {
			novoSalario = salario.add(salario.multiply((BigDecimal.valueOf(20)).divide(BigDecimal.valueOf(100))));
		} else if(mensagem[1].equals("programador")) {
			novoSalario = salario.add(salario.multiply((BigDecimal.valueOf(18)).divide(BigDecimal.valueOf(100))));
		} else {
			return "Cargo inválido";
		}
		
		String novoSalarioStr = novoSalario.setScale(2, RoundingMode.HALF_EVEN).toPlainString().replace("\\.", ",");
			
		return mensagem[0] + ". Novo salario: " + novoSalarioStr;
	}


}