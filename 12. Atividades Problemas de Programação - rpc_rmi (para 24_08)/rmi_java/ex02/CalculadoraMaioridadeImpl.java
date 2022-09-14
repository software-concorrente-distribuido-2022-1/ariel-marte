package ex02;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraMaioridadeImpl extends UnicastRemoteObject implements CalculadoraMaioridade{

	private static final long serialVersionUID = -5153005751996036873L;

	protected CalculadoraMaioridadeImpl() throws RemoteException {
		super();
	}
	@Override
	public Boolean ehMaiorIdade(String sexo, Integer idade) throws RemoteException {
		Boolean maiorIdade = false;
		if(sexo.equals("F") && idade >= 21) {
			maiorIdade = true;
		} else if (sexo.equals("M") && idade >= 18) {
			maiorIdade = true;
		} else if (!sexo.equals("F") && !sexo.equals("M")) {
			throw new RemoteException("Sexo inválido");
		} else {
			maiorIdade = false;
		}
		
		return maiorIdade;
	}

}
