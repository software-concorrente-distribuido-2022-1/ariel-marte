package livraria.util;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.stream.Collectors;

import livraria.model.Livraria;
import livraria.model.Livro;
import livraria.model.Vendedor;

public class QueryGenerator<T> {

	public String gerarInsert(T object) {
		String sql = null;
		if (object instanceof Livro) {
			Livro l = (Livro) object;
			sql = "INSERT INTO livro (nome, quantidade, id_livraria) VALUES ('" + l.getNome() + "'," + l.getQuantidade()
					+ ", " + l.getId_livraria() + ")";
		}

		if (object instanceof Vendedor) {
			Vendedor v = (Vendedor) object;
			sql = "INSERT INTO vendedor (nome, usuario, senha, id_livraria) VALUES ('" + v.getNome() + "','"
					+ v.getUsuario() + "', '" + v.getSenha() + "', " + v.getId_livraria() + ")";
		}

		if (object instanceof Livraria) {
			Livraria l = (Livraria) object;
			sql = "INSERT INTO livraria (nome) VALUES ('" + l.getNome() + "')";
		}

		return sql;
	}

	public String gerarUpdate(T object) {
		String sql = null;
		if (object instanceof Livro) {
			Livro l = (Livro) object;
			sql = "UPDATE livro SET quantidade = " + l.getQuantidade() + " WHERE id_livro = " + l.getId_livro();
		}

		return sql;
	}

	public String gerarDelete(T object) {
		String sql = null;
		if(object instanceof Livro) {
			Livro l = (Livro) object;
			sql = "DELETE FROM livro WHERE id_livro = " + l.getId_livro();
		}
		
		return sql;
	}

	public String gerarSelect(T object) {
		String sql = null;
		if(object instanceof Livro) {
			Livro l = (Livro) object;
			
			sql = l.getNome() != null && !l.getNome().isEmpty() ?
					"SELECT * FROM livro WHERE nome LIKE '%" + l.getNome() + "%'"
					: "SELECT * FROM livro WHERE quantidade > 0 and id_livraria = " + l.getId_livraria();
		}
		
		if(object instanceof Vendedor) {
			Vendedor v = (Vendedor) object;
			sql = "SELECT * FROM vendedor WHERE usuario = '" + v.getUsuario() + "'";
		}
		
		if(object instanceof Livraria) {
			Livraria v = (Livraria) object;
			sql = "SELECT * FROM livraria WHERE id_livraria = '" + v.getId_livraria() + "'";
		}
		
		return sql;
	}
	
}
