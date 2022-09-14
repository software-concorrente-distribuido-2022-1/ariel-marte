package ex06;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Principal {
	public static void main(String[] args) {
		try {
			CalculadoraSalarioLiquidoImpl obj = new CalculadoraSalarioLiquidoImpl();
			Registry registry = LocateRegistry.createRegistry(8080);
			registry.rebind("SalarioLiquidoServer", obj);
			System.out.println("Servidor carregado no registry");
		} catch (Exception e) {
			System.out.println("ReajusteServer erro: " + e.getMessage());
		}

	}
}
