package ex07;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraAposentadoriaImpl extends UnicastRemoteObject implements CalculadoraAposentadoria{

	private static final long serialVersionUID = -5153005751996036873L;

	protected CalculadoraAposentadoriaImpl() throws RemoteException {
		super();
	}

	@Override
	public Boolean podeAposentar(Integer idade, Integer tempoServico) throws RemoteException {
		return (idade >= 65) || (tempoServico >= 30) || (idade >= 60 && tempoServico >= 25);
	}
	

}
