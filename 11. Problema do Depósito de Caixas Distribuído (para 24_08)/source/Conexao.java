import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

class Conexao extends Thread {
	final String msgBadRqt = "400 Bad Request";
	Deposito dep = ServidorSimples.dep;
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
			
			String retorno = this.executarOperacao(mensagem);
			
			saida.println(retorno);
			System.out.println("Cliente " + endCliente.getHostAddress() + " atendido com a " + "mensagem " + mensagem);

			entrada.close();

		} catch (IOException e) {
			System.out.println("Erro E/S " + e);
		} catch (InterruptedException e) {
			saida.println("Operação falhou");
		} finally {
			try {
				socketCliente.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			saida.close();
		}
	}

	private String executarOperacao(String operacao) throws InterruptedException {

		if (operacao.equals("colocar")) {
			return this.colocar();
		}

		if(operacao.equals("retirar")) {
			return this.retirar();
		}
		
		return "Operação inválida";
	}

	private String colocar() throws InterruptedException {
		dep.request();
		if (dep.colocar() == 0) {
			dep.release();
			return "Cheio";
		}

		dep.release();
		return "Uma caixa armazenada";
	}
	
	private String retirar() throws InterruptedException {
		dep.request();

		if (dep.retirar() == 0) {
			dep.release();
			return "Vazio";
		}
		dep.release();
		return "Uma caixa retirada";
	}

}