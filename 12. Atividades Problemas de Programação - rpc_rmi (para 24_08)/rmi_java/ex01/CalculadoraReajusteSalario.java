package ex01;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraReajusteSalario extends UnicastRemoteObject implements CalculadoraReajuste{

	private static final long serialVersionUID = -5153005751996036873L;

	protected CalculadoraReajusteSalario() throws RemoteException {
		super();
	}

	@Override
	public BigDecimal calculaReajuste(String cargo, BigDecimal salario) {
		BigDecimal novoSalario = null;
		if(cargo.equals("operador")) {
			novoSalario = salario.add(salario.multiply((BigDecimal.valueOf(20)).divide(BigDecimal.valueOf(100))));
		} else if(cargo.equals("programador")) {
			novoSalario = salario.add(salario.multiply((BigDecimal.valueOf(18)).divide(BigDecimal.valueOf(100))));
		}		
		return novoSalario;
	}

}
