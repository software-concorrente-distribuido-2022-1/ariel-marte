package ex06;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		
		CalculadoraSalarioLiquido obj = null;
		BigDecimal retorno = null;
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 8080);
			obj = (CalculadoraSalarioLiquido) registry.lookup("SalarioLiquidoServer");
			
			Scanner scn = new Scanner(System.in);
			System.out.print("Nome: ");
			String nome = scn.nextLine();
			System.out.print("Nivel: ");
			String nivel = scn.nextLine();
			System.out.print("Salario Bruto (999,99): ");
			String salarioStr = scn.nextLine();
			System.out.print("Numero de dependentes: ");
			Integer numDependentes = scn.nextInt();
			BigDecimal salario = null;
			try {
				salario = new BigDecimal(salarioStr.replace(",", "."));
			} catch (NumberFormatException e) {
				System.out.println("O salário deve ser informado no formato 9999,99");
				return;
			}
			
			retorno = obj.calculaSalarioLiquido(nivel, salario, numDependentes);
			System.out.println(nome  + " - " + nivel + " Salario Liquido: " + retorno.setScale(2, RoundingMode.HALF_EVEN).toPlainString());
		} catch (Exception e) {
			System.out.println("Client exception: " + e.getMessage());
		}
	}
}