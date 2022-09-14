package ex08;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Principal {
	public static void main(String[] args) {
		try {
			CalculadoraValorCreditoImpl obj = new CalculadoraValorCreditoImpl();
			Registry registry = LocateRegistry.createRegistry(8080);
			registry.rebind("CreditoServer", obj);
			System.out.println("Servidor carregado no registry");
		} catch (Exception e) {
			System.out.println("ReajusteServer erro: " + e.getMessage());
		}

	}
}
