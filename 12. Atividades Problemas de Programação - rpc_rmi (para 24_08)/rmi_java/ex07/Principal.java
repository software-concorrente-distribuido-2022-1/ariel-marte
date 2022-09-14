package ex07;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Principal {
	public static void main(String[] args) {
		try {
			CalculadoraAposentadoriaImpl obj = new CalculadoraAposentadoriaImpl();
			Registry registry = LocateRegistry.createRegistry(8080);
			registry.rebind("AposentadoriaServer", obj);
			System.out.println("Servidor carregado no registry");
		} catch (Exception e) {
			System.out.println("ReajusteServer erro: " + e.getMessage());
		}

	}
}
