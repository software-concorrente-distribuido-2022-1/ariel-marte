package ex07;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculadoraAposentadoria extends Remote{
	
	Boolean podeAposentar(Integer idade, Integer tempoServico) throws RemoteException;
	
}
