const Validador = require('./validador');

class Transacao {
    constructor(remetente, destinatario, quantia, taxas = 0.1) {
        if (!Validador.validarEndereco(remetente) || !Validador.validarEndereco(destinatario)) {
            throw new Error('Endereço de remetente ou destinatário inválido.');
        }
        if (quantia <= 0) {
            throw new Error('A quantia deve ser maior que zero.');
        }

        this.remetente = remetente;
        this.destinatario = destinatario;
        this.quantia = quantia;
        this.taxas = Math.max(taxas, 0.1); 
        this.timestamp = Date.now();
    }
}

module.exports = Transacao;