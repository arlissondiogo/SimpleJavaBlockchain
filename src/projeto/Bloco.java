package projeto;

import java.security.MessageDigest;
import java.util.List;

public class Bloco {
    private final String hashAnterior;
    private final List<Transacao> dados;
    private final long timestamp;
    private final String hash;
    private int nonce;

    public Bloco(String hashAnterior, List<Transacao> dados, int dificuldade) {
        this.hashAnterior = hashAnterior;
        this.dados = dados;
        this.timestamp = System.currentTimeMillis();
        this.hash = minerarBloco(dificuldade);
    }

    private String minerarBloco(int dificuldade) {
        String prefixoDificuldade = "0".repeat(dificuldade);
        String hashCalculado;
        do {
            nonce++;
            hashCalculado = calcularHash();
        } while (!hashCalculado.substring(0, dificuldade).equals(prefixoDificuldade));
        return hashCalculado;
    }

    public String calcularHash() {
        try {
            StringBuilder registro = new StringBuilder(hashAnterior + timestamp + nonce);
            for (Transacao transacao : dados) {
                registro.append(transacao.toString());
            }
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] valorHash = digest.digest(registro.toString().getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : valorHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getHashAnterior() {
        return hashAnterior;
    }

    public String getHash() {
        return hash;
    }

    public List<Transacao> getDados() {
        return dados;
    }
}