class GeraEndereco {
    static gerarEnderecoAleatorio() {
        const CARACTERES = '0123456789abcdef';
        const TAMANHO_ENDERECO = 40;
        let endereco = 'BAT';

        for (let i = 0; i < TAMANHO_ENDERECO; i++) {
            const index = Math.floor(Math.random() * CARACTERES.length);
            endereco += CARACTERES[index];
        }

        return endereco;
    }
}

module.exports = GeraEndereco;