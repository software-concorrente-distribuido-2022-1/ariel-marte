package ex01;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		
		CalculadoraReajuste obj = null;
		BigDecimal retorno = null;
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 8080);
			obj = (CalculadoraReajuste) registry.lookup("ReajusteServer");
			
			Scanner scn = new Scanner(System.in);
			System.out.print("Nome: ");
			String nome = scn.nextLine();
			System.out.print("Cargo: ");
			String cargo = scn.nextLine();
			System.out.print("Salario (999,99): ");
			String salarioStr = scn.nextLine();
			BigDecimal salario = null;
			try {
				salario = new BigDecimal(salarioStr.replace(",", "."));
			} catch (NumberFormatException e) {
				System.out.println("O salário deve ser informado no formato 9999,99");
				return;
			}
			
			retorno = obj.calculaReajuste(cargo, salario);
			System.out.println(nome  + " - Novo salário: " + retorno);
		} catch (Exception e) {
			System.out.println("Client exception: " + e.getMessage());
		}
	}
}