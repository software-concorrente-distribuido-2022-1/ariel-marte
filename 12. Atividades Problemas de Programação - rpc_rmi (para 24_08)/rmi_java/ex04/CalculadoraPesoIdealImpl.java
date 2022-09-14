package ex04;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraPesoIdealImpl extends UnicastRemoteObject implements CalculadoraPesoIdeal{

	private static final long serialVersionUID = -5153005751996036873L;

	protected CalculadoraPesoIdealImpl() throws RemoteException {
		super();
	}

	@Override
	public BigDecimal calculaPeso(String sexo, BigDecimal altura) throws RemoteException {
		BigDecimal pesoIdeal = null; 
		if(sexo.equals("F")) {
			pesoIdeal = (altura.multiply(new BigDecimal("62.1")).subtract(new BigDecimal("44.7")));
		} else if (sexo.equals("M")) {
			pesoIdeal = (altura.multiply(new BigDecimal("72.7")).subtract(new BigDecimal("58")));
		} else {
			throw new RemoteException("Indeterminado");
		}
		
		return pesoIdeal;
	}

}
