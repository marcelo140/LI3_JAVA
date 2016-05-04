import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * Write a description of class Cenas here.
 */

public class LerFichsTexto_Teste
{
    public static void main(String[] args) {
        Crono.start();
        ArrayList<String> linhas = readLinesWithBuff(args[0]);
        //ArrayList<Venda> vendas = parseAllLinhas(linhas);
        HashSet setVendas = parseAllLinhasToSet(linhas);
    
        System.out.println(Crono.print());
        System.out.println("\nLinhas lidas: " + linhas.size());
        //System.out.println("Número de vendas: " + setVendas.size());
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
