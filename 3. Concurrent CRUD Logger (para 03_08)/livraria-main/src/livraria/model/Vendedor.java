package livraria.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import livraria.log.RegistrarLog;
import livraria.repositorio.Repositorio;
import livraria.util.GerarAleatoriedade;

public class Vendedor implements Runnable {

	private RegistrarLog log;

	private boolean logged = false;

	private Integer id_vendedor;

	private String nome;

	private String usuario;

	private String senha;

	private Integer id_livraria;

	private List<Integer> operacoes;

	public Vendedor() {
	}
	
	public Vendedor(Vendedor v) {
		this.nome = v.getNome();
		this.senha = v.getSenha();
		this.log = v.getLog();
		this.id_vendedor = v.getId_vendedor();
		this.operacoes = v.getOperacoes();
		this.id_livraria = v.getId_livraria();
		this.usuario = v.getUsuario();
	}

	public Vendedor(String usuario, String senha, RegistrarLog log) {
		this.usuario = usuario;
		this.senha = senha;
		this.log = log;
	}

	
	public Integer getId_vendedor() {
		return id_vendedor;
	}

	public List<Integer> getOperacoes() {
		return operacoes;
	}

	public RegistrarLog getLog() {
		return this.log;
	}
	
	public void setLog(RegistrarLog log) {
		this.log = log;
	}


	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getId_livraria() {
		return id_livraria;
	}

	public void setId_livraria(Integer id_livraria) {
		this.id_livraria = id_livraria;
	}

	@Override
	public void run() {
		this.logged = this.logar();
		if(!this.logged) return;
		
		/*
		 * 1 - Consulta livraria 
		 * 2 - Consulta Livros no estoque da livraria 
		 * 3 - Vender livro (UPDATE Livro)
		 * 4 - Comprar um livro (UPDATE Livro)
		 * 5 - Inserir Livro 
		 * 6 - Excluir um livro
		 */
		List<Integer> operacoesOpcionais = new ArrayList<>(List.of(3, 4, 5, 6));
		this.operacoes =  new ArrayList<>(List.of(1, 2));
		Collections.shuffle(operacoesOpcionais);
		this.operacoes.add(operacoesOpcionais.stream().findFirst().get());
		

		Livraria livraria;
		List<Livro> livros = new ArrayList<>();
		
		for(Integer operacao : this.operacoes) {
			
			switch(operacao) {
				case 1:
					livraria = this.consultarLivraria();
					break;
				case 2:
					livros = this.listarLivrosDaLivraria();
					break;
				case 3:
					this.atualizarEstoqueLivro(this.selecionarLivroAleatoriamente(livros), -1);
					break;
				case 4:
					this.atualizarEstoqueLivro(this.selecionarLivroAleatoriamente(livros), 100);
					break;
				case 5:
					this.incluirLivro(GerarAleatoriedade.GerarNomeLivro(), (GerarAleatoriedade.GerarNumAleatorio()+1)*10);
					break;
				case 6:
					this.excluirLivroDoEstoque(this.selecionarLivroAleatoriamente(livros));
					break;
					
			}
		}

	}
	
	private void incluirLivro(String nome, Integer quantidade) {
		Repositorio r = new Repositorio();
		Livro livro = new Livro();
		livro.setId_livraria(this.id_livraria);
		livro.setNome(nome);
		livro.setQuantidade(quantidade);
		
		log.request();
		try {
			r.inserirLivro(livro);

			log.registrar(true, this.usuario, "INCLUIR LIVRO", "Nome = " + livro.getNome());		
		} catch (SQLException e) {
			log.registrar(false, this.usuario, "INCLUIR LIVRO", "Livro já Existente. Nome = " + livro.getNome());		
		}

		log.release();

	}
	
	private void excluirLivroDoEstoque(Livro livro) {
		Repositorio r = new Repositorio();

		log.request();
		try {
			
			r.deletarLivro(livro);

			log.registrar(true, this.usuario, "EXCLUIR LIVRO", "Nome = " + livro.getNome());	
		} catch (SQLException e) {
			log.registrar(false, this.usuario, "EXCLUIR LIVRO", "Nome = " + livro.getNome());	
		}

		log.release();
	}
	
	private void atualizarEstoqueLivro(Livro livro, Integer quantidade) {
		Repositorio r = new Repositorio();

		log.request();
		try {
			
			if(r.atualizarQuantidade(livro.getId_livro(), livro.getQuantidade()+quantidade)) {
				log.registrar(true, this.usuario, quantidade > 0 ? "REPOSIÇÃO DE ESTOQUE" : "VENDA LIVRO", "Nome = " + livro.getNome());	
				log.release();
				return;
			}

			log.registrar(true, this.usuario, quantidade > 0 ? "REPOSIÇÃO DE ESTOQUE" : "VENDA LIVRO", "Nome = " + livro.getNome());	
		} catch (SQLException e) {
			log.registrar(false, this.usuario, "CONSULTAR LIVRARIA", "id_livraria = " + this.id_livraria);	
		}

		log.release();
	}
	
	private Livro selecionarLivroAleatoriamente(List<Livro> livros) {
		Collections.shuffle(livros);
		
		return livros.stream().findFirst().get();
	}
	
	private List<Livro> listarLivrosDaLivraria(){
		Repositorio r = new Repositorio();

		log.request();
		try {
			List<Livro> l = r.consultarLivrosComEstoque(this.id_livraria);
			if(l == null) {
				log.registrar(false, this.usuario, "CONSULTAR LIVROS DA LIVRARIA", "id_livraria = " + this.id_livraria);	
				log.release();
				return null;
			}

			log.registrar(true, this.usuario, "CONSULTAR LIVROS DA LIVRARIA", "id_livraria = " + this.id_livraria);	
			log.release();
			return l;
		} catch (SQLException e) {
			log.registrar(false, this.usuario, "CONSULTAR LIVROS DA LIVRARIA", "id_livraria = " + this.id_livraria);	
		}

		log.release();
		return null;

	}
	
	private Livraria consultarLivraria() {
		Repositorio r = new Repositorio();

		log.request();
		try {
			Livraria l = r.consultarLivrariaPorId(this.id_livraria);
			if(l == null) {
				log.registrar(false, this.usuario, "CONSULTAR LIVRARIA", "id_livraria = " + this.id_livraria);	
				log.release();
				return null;
			}

			log.registrar(true, this.usuario, "CONSULTAR LIVRARIA", "id_livraria = " + this.id_livraria);	
			log.release();
			return l;
		} catch (SQLException e) {
			log.registrar(false, this.usuario, "CONSULTAR LIVRARIA", "id_livraria = " + this.id_livraria);	
		}

		log.release();
		return null;

	}

	private Boolean logar() {
		Repositorio r = new Repositorio();

		log.request();
		try {
			Vendedor v = r.consultarVendedorPorUsuario(this.usuario);
			if(v.getSenha().equals(this.senha)) {
				this.nome = v.getNome();
				this.id_livraria = v.getId_livraria();
				log.registrar(true, this.usuario, "LOGIN", "SENHA CORRETA");
				log.release();
				return true;
			}
			log.registrar(false, this.usuario, "LOGIN", "SENHA INCORRETA AO LOGAR");
		} catch (SQLException e) {
			log.registrar(false, this.usuario, "LOGIN", "FALHA AO CONSULTAR USUARIO");
		}

		log.release();

		return false;
	}

}