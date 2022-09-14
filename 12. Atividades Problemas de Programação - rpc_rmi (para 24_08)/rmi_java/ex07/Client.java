package ex07;

import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		
		CalculadoraAposentadoria obj = null;
		Boolean retorno = null;
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 8080);
			obj = (CalculadoraAposentadoria) registry.lookup("AposentadoriaServer");
			
			Scanner scn = new Scanner(System.in);
			System.out.print("Idade: ");
			Integer idade = scn.nextInt();
			System.out.print("Tempo de servico: ");
			Integer tempoServ = scn.nextInt();
	
			
			retorno = obj.podeAposentar(idade, tempoServ);
			System.out.println(retorno ? "Pode se aposentar" : "Nao pode se aposentar");
		} catch (Exception e) {
			System.out.println("Client exception: " + e.getMessage());
		}
	}
}