package ex05;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Principal {
	public static void main(String[] args) {
		try {
			CalculadoraClassificacaoImpl obj = new CalculadoraClassificacaoImpl();
			Registry registry = LocateRegistry.createRegistry(8080);
			registry.rebind("ClassificacaoServer", obj);
			System.out.println("Servidor carregado no registry");
		} catch (Exception e) {
			System.out.println("ReajusteServer erro: " + e.getMessage());
		}

	}
}
