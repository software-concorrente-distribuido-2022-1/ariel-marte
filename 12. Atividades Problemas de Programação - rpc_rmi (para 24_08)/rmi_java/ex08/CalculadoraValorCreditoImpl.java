package ex08;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraValorCreditoImpl extends UnicastRemoteObject implements CalculadoraValorCredito {

	private static final long serialVersionUID = -5153005751996036873L;

	protected CalculadoraValorCreditoImpl() throws RemoteException {
		super();
	}

	@Override
	public BigDecimal calcularValorCrediro(BigDecimal saldoMedio) throws RemoteException {
		BigDecimal valorCredito = null;
		Integer percentualCredito = null;
		if (saldoMedio.compareTo(BigDecimal.valueOf(200)) <= 0)
			return BigDecimal.ZERO;
		if (saldoMedio.compareTo(BigDecimal.valueOf(200)) > 0 && saldoMedio.compareTo(BigDecimal.valueOf(400)) <= 0) {
			percentualCredito = 20;
		}
		if (saldoMedio.compareTo(BigDecimal.valueOf(400)) > 0 && saldoMedio.compareTo(BigDecimal.valueOf(600)) <= 0) {
			percentualCredito = 30;
		}
		if (saldoMedio.compareTo(BigDecimal.valueOf(600)) > 0) {
			percentualCredito = 40;
		}

		valorCredito = saldoMedio == null ? BigDecimal.ZERO
				: (saldoMedio.multiply(BigDecimal.valueOf(percentualCredito))).divide(new BigDecimal("100.00"));

		return valorCredito;
	}

}
