package ex02;

import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		
		CalculadoraMaioridade obj = null;
		Boolean retorno = null;
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 8080);
			obj = (CalculadoraMaioridade) registry.lookup("MaiorIdadeServer");
			
			Scanner scn = new Scanner(System.in);
			System.out.print("Nome: ");
			String nome = scn.nextLine();
			System.out.print("Sexo: (F/M)");
			String sexo = scn.nextLine();
			System.out.print("Idade: ");
			Integer idade = scn.nextInt();
	
			
			retorno = obj.ehMaiorIdade(sexo, idade);
			System.out.println(nome + (!retorno ? " nao " : "") + " atingiu a maioridade.");
		} catch (Exception e) {
			System.out.println("Client exception: " + e.getMessage());
		}
	}
}