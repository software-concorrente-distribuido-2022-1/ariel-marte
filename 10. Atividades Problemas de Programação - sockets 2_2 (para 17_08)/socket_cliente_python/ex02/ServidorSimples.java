package ex02;
import java.net.*;
import java.io.*;

public class ServidorSimples {

	public static void main(String[] args) throws IOException {
		
		final int portaDefault = 8080;

		int porta;
		Socket socketCliente = null;
		ServerSocket socketServidor = null;

		if ((args.length == 1))
			porta = Integer.parseInt(args[0]);
		else
			porta = portaDefault;

		while (true) {
			try {
				socketServidor = new ServerSocket(porta);
				break;
			} catch (IOException e) {
				porta++;
			}
		}

		System.out.println("\nServidor Simples ativado. " + "Aguardando Cliente Simples na porta " + porta + "...\n");

		while (true) {

			socketCliente = null;
			try {
				socketCliente = socketServidor.accept();
			} catch (IOException e) {
				System.err.println("Erro de E/S " + e);
				System.exit(1);
			}

			new Conexao(socketCliente).start();
		}
	}
}
