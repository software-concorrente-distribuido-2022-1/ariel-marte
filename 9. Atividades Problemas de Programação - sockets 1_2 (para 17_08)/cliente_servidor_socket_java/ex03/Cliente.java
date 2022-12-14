package ex03;
/* -------------------------------------------------------------- */

/* Programa: Cliente                                              */
/* Autor: Sergio Teixeira de Carvalho                             */
/* Linguagem Utilizada: Java (JDK 1.1)                            */
/*                                                                */
/* Funcoes:                                                       */
/*      - permite estabelecimento de uma conexao TCP com          */
/*      a porta 80 (default) de um host fornecido;                */
/*      - pede como entrada uma Mensagem                          */
/*                                                                */
/* Uso: $ java ClienteSimples NomeDoHost Mensagem [porta]         */
/*                                                                */
/* -------------------------------------------------------------- */

//Package de classes para manipulacao de E/S
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
//Package de classes para manipulacao de Sockets, IP, etc
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) throws IOException {

		/* ---declaracao dos objetos utilizados--- */

		final int portaDefault = 8080; // Definicao da porta default

		String nomeHost = null; // Nome do host para conexao
		int porta = portaDefault; // Porta para conexao

		/* ---tratamento dos argumentos--- */

		if (args.length >= 1) {
			nomeHost = args[0]; // Host e' 1o. argumento
			if (args.length == 2) {
				porta = Integer.parseInt(args[1]);
			}
			// Porta fornecida como argumento sobrepoe porta default
		} else { // Fornecimento erroneo dos argumentos
			System.out.println("\n\nUso Correto: ClienteSimples NomeDoHost [porta]\n\n");
			System.exit(1);
		}

		try {
			Scanner scn = new Scanner(System.in);
			System.out.println("Insira as notas no formato 9.99. Se o aluno n??o realizou a prova inserir 0. ");
			System.out.print("Nota 1: ");
			BigDecimal n1 = scn.nextBigDecimal();
			System.out.print("Nota 2: ");
			BigDecimal n2 = scn.nextBigDecimal();
			System.out.print("Nota 3: ");
			BigDecimal n3 = scn.nextBigDecimal();
			String req = n1.setScale(2).toPlainString() + "|" + n2.setScale(2).toPlainString() + "|" + n3.setScale(2).toPlainString();
			realizarRequisicao(nomeHost, porta, req);

		} catch (UnknownHostException e) {
			System.err.println("\n\nHost nao encontrado!\n");
			System.out.println("\nUso: ClienteSimples NomeDoHost mensagem [porta]\n\n");
			System.exit(1);
		} catch (java.io.IOException e) {
			System.err.println("\n\nConexao com Host nao pode ser estabelecida.\n");
			System.out.println("\nUso: ClienteSimples NomeDoHost mensagem [porta]\n\n");
			System.exit(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

	private static void realizarRequisicao(String nomeHost, Integer porta, String mensagem)
			throws IOException, InterruptedException {
		PrintWriter saida = null; // Fluxo de saida
		BufferedReader entrada = null;// Fluxo de entrada
		Socket sock = null;
		try {

			sock = new Socket(nomeHost, porta);
			saida = new PrintWriter(sock.getOutputStream(), true);
			entrada = new BufferedReader(new InputStreamReader(sock.getInputStream()));

			saida.println(mensagem);
			String retorno = entrada.readLine();
			System.out.println(retorno);

		} catch (UnknownHostException e) {
			System.err.println("\n\nHost nao encontrado!\n");
			System.out.println("\nUso: ClienteSimples NomeDoHost mensagem [porta]\n\n");
			System.exit(1);
		} catch (java.io.IOException e) {
			System.err.println("\n\nConexao com Host nao pode ser estabelecida.\n");
			System.out.println("\nUso: ClienteSimples NomeDoHost mensagem [porta]\n\n");
			System.exit(1);
		}

	}
}
