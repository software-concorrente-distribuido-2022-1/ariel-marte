package ex02;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculadoraMaioridade extends Remote{
	
	Boolean ehMaiorIdade(String sexo, Integer idade) throws RemoteException;
	
}
