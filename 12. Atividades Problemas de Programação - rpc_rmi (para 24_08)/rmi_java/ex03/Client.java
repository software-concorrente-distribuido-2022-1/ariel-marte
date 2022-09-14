package ex03;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		
		CalculadoraMedia obj = null;
		Boolean retorno = null;
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 8080);
			obj = (CalculadoraMedia) registry.lookup("MediaServer");
			
			Scanner scn = new Scanner(System.in);
			System.out.println("Insira as notas no formato 9.99. Se o aluno não realizou a prova inserir 0. ");
			System.out.print("Nota 1: ");
			BigDecimal n1 = scn.nextBigDecimal();
			System.out.print("Nota 2: ");
			BigDecimal n2 = scn.nextBigDecimal();
			System.out.print("Nota 3: ");
			BigDecimal n3 = scn.nextBigDecimal();
			
			retorno = obj.foiAprovado(n1, n2, n3);
			System.out.println(retorno ? "Foi aprovado" : "Não foi aprovado");
		} catch (Exception e) {
			System.out.println("Client exception: " + e.getMessage());
		}
	}
}