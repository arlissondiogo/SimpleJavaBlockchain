package projeto;

import java.security.SecureRandom;

public class GeraEndereco {

    private static final String CARACTERES = "0123456789abcdef"; 
    private static final int TAMANHO_ENDERECO = 40;

    public static String gerarEnderecoAleatorio() {
        SecureRandom random = new SecureRandom(); 
        StringBuilder endereco = new StringBuilder("BR");

        for (int i = 0; i < TAMANHO_ENDERECO; i++) {
            int index = random.nextInt(CARACTERES.length()); 
            endereco.append(CARACTERES.charAt(index)); 
        }

        return endereco.toString();
    }
}
