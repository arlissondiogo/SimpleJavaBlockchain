const crypto = require('crypto');

class Bloco {
    constructor(hashAnterior, dados, dificuldade) {
        this.hashAnterior = hashAnterior;
        this.dados = dados;
        this.timestamp = Date.now();
        this.dificuldade = dificuldade;
        this.nonce = 0;
        this.hash = this.minerarBloco();
    }

    calcularHash() {
        const registro = this.hashAnterior + 
                         this.timestamp + 
                         this.nonce + 
                         JSON.stringify(this.dados) + 
                         this.dificuldade;
        return crypto.createHash('sha256').update(registro).digest('hex');
    }

    minerarBloco() {
        const prefixoDificuldade = '0'.repeat(this.dificuldade);
        let hashCalculado;

        do {
            this.nonce++;
            hashCalculado = this.calcularHash();
        } while (!hashCalculado.startsWith(prefixoDificuldade));

        return hashCalculado;
    }
}

module.exports = Bloco;