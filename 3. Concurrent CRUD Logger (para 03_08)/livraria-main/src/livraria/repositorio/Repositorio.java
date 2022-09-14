package livraria.repositorio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import livraria.io.DbConnection;
import livraria.model.Livraria;
import livraria.model.Livro;
import livraria.model.Vendedor;
import livraria.util.QueryGenerator;

public class Repositorio {
	DbConnection conn;

	public Repositorio() {
		this.conn = new DbConnection();
	}
	
	public Boolean inserirLivro(Livro l) throws SQLException {
		QueryGenerator<Livro> qg = new QueryGenerator<Livro>();
		String query = qg.gerarInsert(l);
		return this.conn.getSt().execute(query);
	}
	
	public Boolean inserirVendedor(Vendedor v) throws SQLException {
		QueryGenerator<Vendedor> qg = new QueryGenerator<Vendedor>();
		String query = qg.gerarInsert(v);
		return this.conn.getSt().execute(query);
	}
	
	public Boolean inserirLivraria(Livraria l) throws SQLException {
		QueryGenerator<Livraria> qg = new QueryGenerator<Livraria>();
		String query = qg.gerarInsert(l);
		return this.conn.getSt().execute(query);
	}
	
	public Boolean deletarLivro(Livro l) throws SQLException {
		QueryGenerator<Livro> qg = new QueryGenerator<Livro>();
		String query = qg.gerarDelete(l);
		return this.conn.getSt().execute(query);
	}
	
	public Vendedor consultarVendedorPorUsuario(String usuario) throws SQLException {
		QueryGenerator<Vendedor> qg = new QueryGenerator<Vendedor>();
		Vendedor v = new Vendedor();
		v.setUsuario(usuario);
		String query = qg.gerarSelect(v);
		
		Vendedor vConsultado = null;
		ResultSet rs = this.conn.getSt().executeQuery(query);
		
		if(rs.next()) {
			vConsultado = new Vendedor();
			vConsultado.setUsuario(rs.getString("usuario"));
			vConsultado.setSenha(rs.getString("senha"));
			vConsultado.setId_livraria(rs.getInt("id_livraria"));
		}
		
		return vConsultado;
	}
	
	public Livro consultarLivroPorId(String nome) throws SQLException {
		QueryGenerator<Livro> qg = new QueryGenerator<Livro>();
		Livro l = new Livro();
		l.setNome(nome);
		String query = qg.gerarSelect(l);
		
		Livro lConsultado = null;
		ResultSet rs = this.conn.getSt().executeQuery(query);
		
		if(rs.next()) {
			lConsultado = new Livro();
			lConsultado.setId_livraria(rs.getInt("id_livraria"));
			lConsultado.setId_livro(rs.getInt("id_livro"));
			lConsultado.setNome(rs.getString("nome"));
			lConsultado.setQuantidade(rs.getInt("quantidade"));
		}
		
		return lConsultado;
		
	}
	
	public List<Livro> consultarLivrosComEstoque(Integer id_livraria) throws SQLException {
		QueryGenerator<Livro> qg = new QueryGenerator<Livro>();
		Livro l = new Livro();
		l.setId_livraria(id_livraria);
		String query = qg.gerarSelect(l);
		
		List<Livro> listaLivros = new ArrayList<>();
		ResultSet rs = this.conn.getSt().executeQuery(query);
		
		while(rs.next()) {
			Livro lConsultado = new Livro();
			lConsultado.setId_livraria(rs.getInt("id_livraria"));
			lConsultado.setId_livro(rs.getInt("id_livro"));
			lConsultado.setNome(rs.getString("nome"));
			lConsultado.setQuantidade(rs.getInt("quantidade"));
			listaLivros.add(lConsultado);
		}
		
		return listaLivros;
		
	}
	
	public Livraria consultarLivrariaPorId(Integer id) throws SQLException {
		QueryGenerator<Livraria> qg = new QueryGenerator<Livraria>();
		Livraria l = new Livraria();
		l.setId_livraria(id);
		String query = qg.gerarSelect(l);
		
		Livraria lConsultado = null;
		ResultSet rs = this.conn.getSt().executeQuery(query);
		
		if(rs.next()) {
			lConsultado = new Livraria();
			lConsultado.setId_livraria(rs.getInt("id_livraria"));
			lConsultado.setNome(rs.getString("nome"));
		}
		
		return lConsultado;
		
	}
	
	public Boolean atualizarQuantidade(Integer idLivro, Integer novaQuantidade) throws SQLException {
		QueryGenerator<Livro> qg = new QueryGenerator<Livro>();
		Livro l = new Livro();
		l.setId_livro(idLivro);
		l.setQuantidade(novaQuantidade);
		
		String query = qg.gerarUpdate(l);
		
		return this.conn.getSt().executeUpdate(query) > 0;
		
	}
	
	
}
