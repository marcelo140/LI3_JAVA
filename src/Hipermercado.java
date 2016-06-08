import java.io.*;

public class Hipermercado {
	CatalogSet<String> produtos;
	CatalogSet<String> clientes;

	public Hipermercado() {
		produtos = new CatalogSet<>();
		clientes = new CatalogSet<>();
	}

	public Hipermercado(CatalogSet<String> produtos, CatalogSet<String> clientes) {
		this.produtos = produtos.clone();
		this.clientes = clientes.clone();
	}

	public void guardarDados(String path) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));

		oos.writeObject(this);
		oos.flush();

		oos.close();
	}

	public Hipermercado carregarDados(String path) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));

		Hipermercado hm = (Hipermercado) ois.readObject();

		ois.close();
		return hm;
	}
}
