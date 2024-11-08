package projeto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Blockchain {
    private final List<Bloco> cadeia;
    private final Map<String, List<Transacao>> historicoTransacoes;

    public Blockchain() {
        this.cadeia = new ArrayList<>();
        this.cadeia.add(criarBlocoGênese());
        this.historicoTransacoes = new HashMap<>();
    }

    private Bloco criarBlocoGênese() {
        return new Bloco("0", new ArrayList<>(), 4); 
    }

    public void adicionarBloco(List<Transacao> transacoes) {
        for (Transacao transacao : transacoes) {
            if (!Validador.validarTransacao(transacao)) {
                throw new IllegalArgumentException("Transação inválida: " + transacao);
            }
            historicoTransacoes.computeIfAbsent(transacao.getRemetente(), k -> new ArrayList<>()).add(transacao);
            historicoTransacoes.computeIfAbsent(transacao.getDestinatario(), k -> new ArrayList<>()).add(transacao);
        }
        Bloco bloco = new Bloco(cadeia.get(cadeia.size() - 1).getHash(), transacoes, 4);
        cadeia.add(bloco);
    }

    public boolean cadeiaValida() {
        for (int i = 1; i < cadeia.size(); i++) {
            Bloco blocoAtual = cadeia.get(i);
            Bloco blocoAnterior = cadeia.get(i - 1);
            if (!blocoAtual.getHash().equals(blocoAtual.calcularHash()) ||
                !blocoAtual.getHashAnterior().equals(blocoAnterior.getHash())) {
                return false;
            }
        }
        return true;
    }

    public void imprimirCadeia() {
        System.out.println("===== Cadeia de Blocos =====");
        for (int i = 1; i < cadeia.size(); i++) {
            Bloco bloco = cadeia.get(i);
            System.out.println("\n--- Bloco " + i + " ---");
            System.out.println("Hash: " + bloco.getHash());
            System.out.println("Hash Anterior: " + bloco.getHashAnterior());
            System.out.println("Transações:");

            List<Transacao> transacoes = bloco.getDados();
            if (transacoes.isEmpty()) {
                System.out.println("  Nenhuma transação.");
            } else {
                for (Transacao transacao : transacoes) {
                    System.out.println("  - Remetente: " + transacao.getRemetente());
                    System.out.println("    Destinatário: " + transacao.getDestinatario());
                    System.out.println("    Quantidade: " + transacao.getQuantia());
                }
            }
            System.out.println("---------------------------");
        }
        System.out.println("===========================");
    }

    public void exibirHistoricoTransacoes(String endereco) {
        System.out.println("\nHistórico de transações para o endereço: " + endereco);
        List<Transacao> transacoes = historicoTransacoes.get(endereco);
        if (transacoes == null || transacoes.isEmpty()) {
            System.out.println("Nenhuma transação encontrada para este endereço.");
        } else {
            for (Transacao transacao : transacoes) {
                System.out.println(transacao);
            }
        }
    }
}
