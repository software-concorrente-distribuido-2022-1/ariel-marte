package livraria.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import livraria.io.DbInit;
import livraria.log.RegistrarLog;
import livraria.model.Vendedor;

public class Caixa {

	public static void main(String[] args) {
		List<Vendedor> vendedores = new ArrayList<>();
		RegistrarLog log = new RegistrarLog();
		
		try {
			DbInit.criarTabelas();
			vendedores = DbInit.criarDados();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Convertendo Vendedores para Threads
		vendedores.forEach(v -> {
			v.setLog(log);
		});
		
		for(int i = 1; i <= 20; i++) {
			vendedores.forEach(v -> {
				Vendedor temp = new Vendedor(v);
				Thread t = new Thread(temp);
				t.start();
			});
		}
		
	}

}