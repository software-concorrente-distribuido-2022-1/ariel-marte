package ex05;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraClassificacaoImpl extends UnicastRemoteObject implements CalculadoraClassificacao{

	private static final long serialVersionUID = -5153005751996036873L;

	protected CalculadoraClassificacaoImpl() throws RemoteException {
		super();
	}
	
	@Override
	public String getClassificacao(Integer idade) throws RemoteException {
		
		if (idade >= 5 && idade <= 7) {
			return "Infantil A";
		}
		if (idade >= 9 && idade <= 10) {
			return "Infantil B";
		}
		if (idade >= 11 && idade <= 13) {
			return "Juvenil A";
		}
		if (idade >= 14 && idade <= 17) {
			return "Juvenil B";
		}
		if (idade >= 18) {
			return "Adulto";
		}

		return "Idade inválida";
	}

}
