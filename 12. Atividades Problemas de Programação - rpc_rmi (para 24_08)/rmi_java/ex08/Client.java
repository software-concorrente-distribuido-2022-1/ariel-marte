package ex08;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		
		CalculadoraValorCredito obj = null;
		BigDecimal retorno = null;
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 8080);
			obj = (CalculadoraValorCredito) registry.lookup("CreditoServer");
			
			Scanner scn = new Scanner(System.in);
			System.out.println("Insira o saldo medio no formato 9.99: ");
			System.out.print("Saldo medio: ");
			BigDecimal saldoMedio = scn.nextBigDecimal();
			
			retorno = obj.calcularValorCrediro(saldoMedio);
			System.out.println(retorno.setScale(2, RoundingMode.HALF_EVEN).toPlainString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Client exception: " + e.getMessage());
		}
	}
}