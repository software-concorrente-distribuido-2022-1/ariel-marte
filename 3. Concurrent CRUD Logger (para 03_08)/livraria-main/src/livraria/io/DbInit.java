package livraria.io;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import livraria.model.Livraria;
import livraria.model.Livro;
import livraria.model.Vendedor;
import livraria.repositorio.Repositorio;
import livraria.util.GerarAleatoriedade;

public class DbInit {
	public static List<Vendedor> criarDados() throws SQLException {
		Repositorio r = new Repositorio();
		
		Livraria l1 = new Livraria();
		l1.setNome("Livraria do Ariel");
		r.inserirLivraria(l1);
		
		Livraria l2 = new Livraria();
		l2.setNome("Livraria do Marco");
		r.inserirLivraria(l2);
		
		
		List<Vendedor> vendedoresL1 = List.of(GerarAleatoriedade.ArrayNomesPessoas).subList(0, 5).stream().map(n -> {
			Vendedor v = new Vendedor();
			v.setId_livraria(1);
			v.setNome(n);
			v.setSenha(n+"123");
			v.setUsuario(n.toLowerCase());
			try {
				r.inserirVendedor(v);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return v;
		}).collect(Collectors.toList());
		
		List<Vendedor> vendedoresL2 = List.of(GerarAleatoriedade.ArrayNomesPessoas).subList(5, 10).stream().map(n -> {
			Vendedor v = new Vendedor();
			v.setId_livraria(2);
			v.setNome(n);
			v.setSenha(n+"123");
			v.setUsuario(n.toLowerCase());

			try {
				r.inserirVendedor(v);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return v;
		}).collect(Collectors.toList());
		
		List<Vendedor> vendedores = new ArrayList<>();
		vendedores.addAll(vendedoresL1);
		vendedores.addAll(vendedoresL2);
		
		List.of(GerarAleatoriedade.ArrayNomesLivros).forEach(n -> {
			Livro l = new Livro();
			l.setId_livraria(1);
			l.setNome(n);
			l.setQuantidade(100);
			try {
				r.inserirLivro(l);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		
		List.of(GerarAleatoriedade.ArrayNomesLivros).forEach(n -> {
			Livro l = new Livro();
			l.setId_livraria(2);
			l.setNome(n);
			l.setQuantidade(100);
			try {
				r.inserirLivro(l);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		
		
		return vendedores;
	}
	
	public static void criarTabelas() throws SQLException {
		DbConnection conn = new DbConnection();
		StringBuilder sql = new StringBuilder();

		sql.append("DROP TABLE IF EXISTS `livro` ");
		conn.getSt().execute(sql.toString());

		sql = new StringBuilder();
		sql.append("DROP TABLE IF EXISTS `vendedor` ");
		conn.getSt().execute(sql.toString());

		sql = new StringBuilder();
		sql.append("DROP TABLE IF EXISTS `livraria`");
		conn.getSt().execute(sql.toString());

		sql = new StringBuilder();
		sql.append("CREATE TABLE `livraria` ( ");
		sql.append("  `id_livraria` int(11) NOT NULL AUTO_INCREMENT, ");
		sql.append("  `nome` varchar(255) DEFAULT NULL, ");
		sql.append("  PRIMARY KEY (`id_livraria`) ");
		sql.append(") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ");
		conn.getSt().execute(sql.toString());
		

		sql = new StringBuilder();
		sql.append("CREATE TABLE `livro` ( ");
		sql.append("  `id_livro` int(11) NOT NULL AUTO_INCREMENT, ");
		sql.append("  `id_livraria` int(11) NOT NULL, ");
		sql.append("  `nome` varchar(1000) NOT NULL, ");
		sql.append("  `quantidade` int(11) NOT NULL, ");
		sql.append("  PRIMARY KEY (`id_livro`), ");
		sql.append("  UNIQUE KEY `nome_unico` (`nome`, `id_livraria`) USING HASH, ");
		sql.append("  KEY `fk_livraria_livro` (`id_livraria`), ");
		sql.append("  CONSTRAINT `fk_livraria_livro` FOREIGN KEY (`id_livraria`) REFERENCES `livraria` (`id_livraria`) ");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ");
		conn.getSt().execute(sql.toString());
		

		sql = new StringBuilder();
		sql.append("CREATE TABLE `vendedor` ( ");
		sql.append("  `id_vendedor` int(11) NOT NULL AUTO_INCREMENT, ");
		sql.append("  `id_livraria` int(11) NOT NULL, ");
		sql.append("  `nome` varchar(255) DEFAULT NULL, ");
		sql.append("  `usuario` varchar(30) NOT NULL, ");
		sql.append("  `senha` varchar(60) NOT NULL, ");
		sql.append("  PRIMARY KEY (`id_vendedor`), ");
		sql.append("  KEY `fk_livraria` (`id_livraria`), ");
		sql.append("  CONSTRAINT `fk_livraria` FOREIGN KEY (`id_livraria`) REFERENCES `livraria` (`id_livraria`) ");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ");
		conn.getSt().execute(sql.toString());
		
		conn.getCon().close();

	}
}