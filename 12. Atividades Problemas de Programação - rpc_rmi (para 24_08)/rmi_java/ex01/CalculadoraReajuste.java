package ex01;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculadoraReajuste extends Remote{
	
	BigDecimal calculaReajuste(String cargo, BigDecimal salario) throws RemoteException;
	
}
