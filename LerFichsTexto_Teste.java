import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * Write a description of class Cenas here.
 */

public class LerFichsTexto_Teste
{
    public static void main(String[] args) {
        ArrayList<Venda> vendas = readVendasWithBuff(args[0]);
       	HashSet<String> clientes;
 
        //System.out.println("Vendas por filial: " + comprasEmFilial(vendas, 2) + "\n");
        //System.out.println("Compras a zero: " + comprasDadas(vendas) + "\n");
        //System.out.println("Produtos por letra: " + produtosPorLetra(vendas, 'C') + "\n");
        clientes = clientesPorFilial(vendas, 2); System.out.println("Clientes por filial: " + clientes.size() + "\n");
    }

    public static int comprasEmFilial(ArrayList<Venda> vendas, int filial) {
        return (int) vendas.stream().filter(v -> v.getFilial() == filial).count();
    }

    public static int comprasDadas(ArrayList<Venda> vendas) {
        return (int) vendas.stream().filter(v -> v.getPreco() == 0.0).count();
    }
    
    public static int produtosPorLetra(ArrayList<Venda> vendas, char letra) {
        return (int) vendas.stream().filter(v -> v.getProduto().toCharArray()[0] == letra).count();
    }
    
    public static HashSet<String> clientesPorFilial(ArrayList<Venda> vendas, int filial) {
        HashSet<String> clientes = new HashSet<>();
        
        for(Venda v: vendas)
            if (v.getFilial() == filial)
                clientes.add(v.getCliente());
                
        return clientes;
    }
    
    public static ArrayList<String> readLinesWithBuff(String fich) {
        ArrayList<String> linhas = new ArrayList<>();
        BufferedReader inStream = null;
        String linha = null;
        Crono.start();
        try {
            inStream = new BufferedReader(new FileReader(fich));
            while( (linha = inStream.readLine()) != null )
                linhas.add(linha);
        }
        catch(IOException e){ 
            System.out.println(e.getMessage()); return null; 
        };
        System.out.println(Crono.print());
        return linhas;
    }

    public static ArrayList<String> readLinesArrayWithScanner(String ficheiro) {
        ArrayList<String> linhas = new ArrayList<>();
        Scanner scanFile = null;
        
        try {
            scanFile = new Scanner(new FileReader(ficheiro));
            scanFile.useDelimiter("\n\r");
            while(scanFile.hasNext())
                linhas.add(scanFile.nextLine());
        }
        catch(IOException ioExc){ 
            System.out.println(ioExc.getMessage()); return null; 
        }

        finally {
            if(scanFile != null) scanFile.close();
        }

        return linhas;
    }

    public static ArrayList<Venda> readVendasWithBuff(String ficheiro) {
        ArrayList<Venda> vendas = new ArrayList<>();
        Scanner scanFile = null;
        String linha;
        
        try {
            scanFile = new Scanner(new FileReader(ficheiro));
            scanFile.useDelimiter("\n\r");
            while(scanFile.hasNext()){
                linha = scanFile.nextLine();
                vendas.add(parseLinhaVenda(linha));
            }
        }
        catch(IOException ioExc){ 
            System.out.println(ioExc.getMessage()); return null; 
        }

        finally {
            if(scanFile != null) scanFile.close();
        }

        return vendas;
    }

    public static Venda parseLinhaVenda(String linha) {
        String[] dados;
        double preco;
        int unidades, mes, filial;
        boolean promocao;

        dados = linha.trim().split(" ");

        preco = Double.parseDouble(dados[1]);
        unidades = Integer.parseInt(dados[2]);
        promocao = !dados[3].equals("N");
        mes = Integer.parseInt(dados[5]);
        filial = Integer.parseInt(dados[6]);

        return new Venda(dados[0], preco, unidades, promocao, dados[4], mes, filial);
    }

    public static ArrayList<Venda> parseAllLinhas(ArrayList<String> linhas) {
        ArrayList<Venda> vendas = new ArrayList<Venda>();

        for(String l: linhas)
            vendas.add(parseLinhaVenda(l));

        return vendas;
    }

    public static HashSet<Venda> parseAllLinhasToSet(ArrayList<String> linhas) {
        HashSet<Venda> vendas = new HashSet<Venda>();

        for(String l: linhas)
            vendas.add(parseLinhaVenda(l));

        return vendas;
    }
}
