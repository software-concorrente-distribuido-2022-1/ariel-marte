package ex05;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculadoraClassificacao extends Remote{
	
	String getClassificacao(Integer idade) throws RemoteException;
	
}
