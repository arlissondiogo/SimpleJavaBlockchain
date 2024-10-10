package projeto;

// Classe Bloco
import java.security.MessageDigest;
import java.util.List;

public class Bloco {
    private final String hashAnterior;
    private final List<Transacao> dados;
    private final long timestamp;
    private final String hash;

    public Bloco(String hashAnterior, List<Transacao> dados) {
        this.hashAnterior = hashAnterior;
        this.dados = dados;
        this.timestamp = System.currentTimeMillis();
        // Calcula o hash do bloco
        this.hash = calcularHash();
    }

    public String calcularHash() {
        try {
            StringBuilder registro = new StringBuilder(hashAnterior + Long.toString(timestamp));
            for (Transacao transacao : dados) {
                registro.append(transacao.toString()); // Converte a transação em String
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

    public long getTimestamp() {
        return timestamp;
    }

    public List<Transacao> getDados() {
        return dados;
    }

}