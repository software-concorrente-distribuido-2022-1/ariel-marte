package livraria.model;

public class Livro {

	private Integer id_livro;


	private String nome;

	private Integer quantidade;

	private Integer id_livraria;

	public Livro(String nome, Integer quantidade, Integer id_livraria) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.id_livraria = id_livraria;
	}

	public Livro() {
	}

	public Integer getId_livro() {
		return id_livro;
	}

	public void setId_livro(Integer id_livro) {
		this.id_livro = id_livro;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getId_livraria() {
		return id_livraria;
	}

	public void setId_livraria(Integer id_livraria) {
		this.id_livraria = id_livraria;
	}

}
