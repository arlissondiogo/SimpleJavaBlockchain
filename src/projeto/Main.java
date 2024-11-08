package projeto;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        
        String enderecoRemetente = GeraEndereco.gerarEnderecoAleatorio();
        String enderecoDestinatario = GeraEndereco.gerarEnderecoAleatorio();

        List<Transacao> transacoes = new ArrayList<>();
        
        transacoes.add(new Transacao(enderecoRemetente, enderecoDestinatario, 100));

        transacoes.add(new Transacao(enderecoDestinatario, enderecoRemetente, 50));

        System.out.println("Adicionando transações válidas...");
        blockchain.adicionarBloco(transacoes);
        
        System.out.println("TRANSAÇÃO VÁLIDA:");
        blockchain.imprimirCadeia();

        blockchain.exibirHistoricoTransacoes(enderecoRemetente);
        blockchain.exibirHistoricoTransacoes(enderecoDestinatario);

        String enderecoInvalido = "EnderecoInvalido123"; 
        Transacao transacaoFalsa = null;
        
        try {
            transacaoFalsa = new Transacao(enderecoInvalido, enderecoDestinatario, 200);
            System.out.println("\nTentando adicionar transação falsa...");
            List<Transacao> transacoesFalsas = new ArrayList<>();
            transacoesFalsas.add(transacaoFalsa);
            blockchain.adicionarBloco(transacoesFalsas); 

            System.out.println("TRANSAÇÃO INVÁLIDA:");
            blockchain.imprimirCadeia();
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro ao adicionar transação falsa: " + e.getMessage());
        }

        System.out.println("A cadeia é válida? " + blockchain.cadeiaValida());
    }
}
