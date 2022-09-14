package ex01;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Principal {
	public static void main(String[] args) {
		try {
			CalculadoraReajusteSalario obj = new CalculadoraReajusteSalario();
			Registry registry = LocateRegistry.createRegistry(8080);
			registry.rebind("ReajusteServer", obj);
			System.out.println("Servidor carregado no registry");
		} catch (Exception e) {
			System.out.println("ReajusteServer erro: " + e.getMessage());
		}

	}
}
