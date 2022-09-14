package livraria.util;

import java.util.ArrayList;

public class GerarAleatoriedade {

	public static final String[] ArrayNomesPessoas = { "Miguel", "Arthur", "Theo", "Heitor", "Davi", "Helena", "Alice", "Laura", "Manuela",
			"Sophia" };
	public static final String[] ArrayNomesLivros = { "Torto Arado", "Do mil ao milhao", "1984", "Os sete maridos", "O poder do habito",
			"A Revolucao dos Bichos", "Mentirosos", "Pai Rico, Pai Pobre", "Mindset", "O milagre da manha" };

	public static int GerarNumAleatorio() {
		return (int) (Math.random() * 10);
	}

	public static String GerarNomePessoa() {
		return ArrayNomesPessoas[GerarNumAleatorio()];
	}

	public static String GerarNomeLivro() {
		return (ArrayNomesLivros[GerarNumAleatorio()] + " Edicao: " + GerarNumAleatorio());
	}

}
