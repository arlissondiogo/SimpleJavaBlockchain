package projeto;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private final List<Bloco> cadeia; //atribuido final para não ser modificado.

    public Blockchain() {
        this.cadeia = new ArrayList<>();
        // Adiciona o bloco gênese
        this.cadeia.add(criarBlocoGênese());
    }

    private Bloco criarBlocoGênese() {
        return new Bloco("0", new ArrayList<>()); // Bloco gênese com hash anterior "0"
    }

    public void adicionarBloco(List<Transacao> transacoes) {
        Bloco bloco = new Bloco(cadeia.get(cadeia.size() - 1).getHash(), transacoes);
        cadeia.add(bloco);
    }

    public boolean cadeiaValida() { //a validação ignora o bloco gênese
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
            System.out.println("Timestamp: " + bloco.getTimestamp());
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
    
    
}
