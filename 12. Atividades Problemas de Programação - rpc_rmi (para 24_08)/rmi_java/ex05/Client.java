package ex05;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		
		CalculadoraClassificacao obj = null;
		String retorno = null;
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 8080);
			obj = (CalculadoraClassificacao) registry.lookup("ClassificacaoServer");
			
			Scanner scn = new Scanner(System.in);
			System.out.print("Idade: ");
			Integer idade = scn.nextInt();
	
			
			retorno = obj.getClassificacao(idade);
			System.out.println(retorno);
		} catch (Exception e) {
			System.out.println("Client exception: " + e.getMessage());
		}
	}
}