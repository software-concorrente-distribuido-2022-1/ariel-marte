package ex04;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		
		CalculadoraPesoIdeal obj = null;
		BigDecimal retorno = null;
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 8080);
			obj = (CalculadoraPesoIdeal) registry.lookup("PesoIdealServer");
			
			Scanner scn = new Scanner(System.in);
			System.out.print("Sexo: (F/M)");
			String sexo = scn.nextLine();
			System.out.print("Altura (m): (9,99)");
			String alturaStr = scn.nextLine();
			BigDecimal altura = null;
			try {
				altura = new BigDecimal(alturaStr.replace(",", "."));
			} catch (NumberFormatException e) {
				System.out.println("A altura deve ser infomada no formato 9,99");
				return;
			}
			
			retorno = obj.calculaPeso(sexo, altura);
			System.out.println(retorno.setScale(2, RoundingMode.HALF_EVEN).toPlainString() + "kg");
		} catch (Exception e) {
			System.out.println("Client exception: " + e.getMessage());
		}
	}
}