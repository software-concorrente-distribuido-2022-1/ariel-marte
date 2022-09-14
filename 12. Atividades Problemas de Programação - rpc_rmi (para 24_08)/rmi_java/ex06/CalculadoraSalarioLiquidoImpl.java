package ex06;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraSalarioLiquidoImpl extends UnicastRemoteObject implements CalculadoraSalarioLiquido {

	private static final long serialVersionUID = -5153005751996036873L;

	protected CalculadoraSalarioLiquidoImpl() throws RemoteException {
		super();
	}

	@Override
	public BigDecimal calculaSalarioLiquido(String nivel, BigDecimal salario, Integer numDependentes)
			throws RemoteException {
		Boolean temDependente = false;
		if (numDependentes < 0)
			throw new RemoteException("Dependentes inválidos");
		temDependente = numDependentes > 0;

		Integer desconto = null;
		if (nivel.equals("A")) {
			desconto = temDependente ? 8 : 3;
		} else if (nivel.equals("B")) {
			desconto = temDependente ? 10 : 5;
		} else if (nivel.equals("C")) {
			desconto = temDependente ? 15 : 8;
		} else if (nivel.equals("D")) {
			desconto = temDependente ? 17 : 10;
		}

		BigDecimal descontoTotal = salario.multiply((BigDecimal.valueOf(desconto)).divide(BigDecimal.valueOf(100)));
		BigDecimal salarioLiquido = salario.subtract(descontoTotal);

		return salarioLiquido;
	}

}
