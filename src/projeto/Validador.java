package projeto;

public class Validador {
    public static boolean validarEndereco(String endereco) {
        String regex = "^BR[a-fA-F0-9]{40}$"; 
        return endereco.matches(regex);
    }

    public static boolean validarTransacao(Transacao transacao) {
        return validarEndereco(transacao.getRemetente()) &&
               validarEndereco(transacao.getDestinatario()) &&
               transacao.getQuantia() > 0;
    }
}
