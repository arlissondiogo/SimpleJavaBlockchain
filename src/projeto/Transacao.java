package projeto;

public class Transacao {
    private final long quantia;
    private final String remetente;
    private final String destinatario;


    public String getDestinatario() {
        return destinatario;
    }

    public String getRemetente() {
        return remetente;
    }
    
    public long getQuantia() {
        return quantia;
    }

    public Transacao(String remetente, String destinatario, long quantia) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.quantia = quantia;
    }

    @Override
    public String toString() {
        return "Remetente = " + remetente + "\n" +
               "Destinatário = " + destinatario + "\n" +
               "Quantia = " + quantia;
}

}