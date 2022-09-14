package ex03;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraMediaImpl extends UnicastRemoteObject implements CalculadoraMedia{

	private static final long serialVersionUID = -5153005751996036873L;

	protected CalculadoraMediaImpl() throws RemoteException {
		super();
	}

	@Override
	public Boolean foiAprovado(BigDecimal n1, BigDecimal n2, BigDecimal n3) throws RemoteException {
		BigDecimal media = (n1.add(n2)).divide(new BigDecimal("2.00"));
		if (media.compareTo(new BigDecimal("7.00")) >= 0) {
			return true;
		}

		if (media.compareTo(new BigDecimal("3.00")) <= 0) {
			return false;
		}

		media = (media.add(n3)).divide(new BigDecimal("2.00"));
		if (media.compareTo(new BigDecimal("5.00")) >= 0) {
			return true;
		}

		return false;
	}

	

}
