package projeto;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        
        System.out.println("Iniciando Blockchain...");
        
        // Primeira lista de transações
        List<Transacao> transacoes1 = new ArrayList<>();
        transacoes1.add(new Transacao("Grayson", "Wayne", 10));
        blockchain.adicionarBloco(transacoes1); // Adiciona o primeiro bloco
        
        // Segunda lista de transações
        List<Transacao> transacoes2 = new ArrayList<>(); // Declarando transacoes2
        transacoes2.add(new Transacao("Drake", "Todd", 4));
        blockchain.adicionarBloco(transacoes2); // Adiciona o segundo bloco

        System.out.println("Criando bloco com as transações pendentes...");
        
        System.out.println("Blockchain válida? " + blockchain.cadeiaValida());
        
        blockchain.imprimirCadeia();
    }
}
