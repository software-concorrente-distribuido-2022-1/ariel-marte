package ex06;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculadoraSalarioLiquido extends Remote{
	
	BigDecimal calculaSalarioLiquido(String nivel, BigDecimal salario, Integer dependentes) throws RemoteException;
	
}
