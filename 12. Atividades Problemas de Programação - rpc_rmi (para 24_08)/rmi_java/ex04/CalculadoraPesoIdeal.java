package ex04;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculadoraPesoIdeal extends Remote{
	
	BigDecimal calculaPeso(String sexo, BigDecimal altura) throws RemoteException;
	
}
