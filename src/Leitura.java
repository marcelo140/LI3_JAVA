import java.io.*;

public class Leitura {

    public static CatalogSet<String> carregarCatalogo(String fich) throws IOException {
		CatalogSet<String> linhas = new CatalogSet<String>(26);
		BufferedReader inStream = null;
		String linha = null;

		inStream = new BufferedReader(new FileReader(fich));
		while( (linha = inStream.readLine()) != null )
			linhas.add(linha.charAt(0) - 'A', linha);

		return linhas;
	}

}
