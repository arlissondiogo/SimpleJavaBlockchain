package projeto;

public class Transacao {
    private final long quantia;
    private final String remetente;
    private final String destinatario;

    public Transacao(String remetente, String destinatario, long quantia) {
        if (!Validador.validarEndereco(remetente) || !Validador.validarEndereco(destinatario)) {
            throw new IllegalArgumentException("Endereço de remetente ou destinatário inválido.");
        }
        
        if (quantia <= 0) {
            throw new IllegalArgumentException("A quantia deve ser maior que zero.");
        }
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.quantia = quantia;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getRemetente() {
        return remetente;
    }

    public long getQuantia() {
        return quantia;
    }

    @Override
    public String toString() {
        return "Remetente = " + remetente + "\n" +
               "Destinatário = " + destinatario + "\n" +
               "Quantia = " + quantia;
    }
}
