package ex03;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculadoraMedia extends Remote{
	
	Boolean foiAprovado(BigDecimal n1, BigDecimal n2, BigDecimal n3) throws RemoteException;
	
}
