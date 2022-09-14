package ex08;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculadoraValorCredito extends Remote{
	
	BigDecimal calcularValorCrediro(BigDecimal saldoMedio) throws RemoteException;
	
}
