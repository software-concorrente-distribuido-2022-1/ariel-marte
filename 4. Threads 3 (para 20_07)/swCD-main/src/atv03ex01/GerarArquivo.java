package atv03ex01;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GerarArquivo {

	  public static void GerarTxt(String nomeDoArquivo, String conteudo) throws IOException {
			    BufferedWriter writer = new BufferedWriter(new FileWriter(nomeDoArquivo));
			    writer.write(conteudo);
			    writer.close();
		        System.out.println("Arquivo Exportado");

}

}
