const Bloco = require('./bloco');
const Validador = require('./validador');

class Blockchain {
    constructor() {
        this.cadeia = [this.criarBlocoGenesis()];
        this.nos = [];
        this.saldos = {};
        this.dificuldadeMineracao = 4;
        this.recompensaMinerador = 35;
        this.historicoTransacoes = {};
    }

    criarBlocoGenesis() {
        return new Bloco('0', [], this.dificuldadeMineracao);
    }

    adicionarNo(no) {
        if (!this.nos.includes(no)) {
            this.nos.push(no);
        }
    }

    propagarBloco(bloco) {
        this.nos.forEach(no => {
            if (no !== this) {
                no.receberBloco(bloco);
            }
        });
    }

    receberBloco(bloco) {
        if (this.validaBloco(bloco)) {
            const ultimoBloco = this.cadeia[this.cadeia.length - 1];
            
            if (bloco.hashAnterior === ultimoBloco.hash) {
                this.cadeia.push(bloco);
            } else {
                this.resolverForks(bloco);
            }
        }
    }

    resolverForks(novoBloco) {
        const dificuldadeAtual = this.calcularDificuldadeTotal(this.cadeia);
        const dificuldadeNovoBloco = this.calcularDificuldadeTotal([novoBloco]);

        if (dificuldadeNovoBloco > dificuldadeAtual) {
            console.log('Fork resolvido: Nova cadeia adotada');
            this.cadeia = [this.criarBlocoGenesis(), novoBloco];
        }
    }

    calcularDificuldadeTotal(cadeia) {
        return cadeia.reduce((total, bloco) => total + bloco.nonce, 0);
    }

    registrarHistoricoTransacoes(bloco) {
        const indexBloco = this.cadeia.length - 1;
        this.historicoTransacoes[indexBloco] = bloco.dados;
    }

    calcularSaldoTotal() {
        return Object.values(this.saldos).reduce((total, saldo) => total + saldo, 0);
    }

    auditarTransacoes() {
        const resumoAuditoria = {
            totalTransacoes: 0,
            transacoesValidas: 0,
            valorTotalTransacionado: 0,
            taxasTotais: 0
        };

        Object.values(this.historicoTransacoes).forEach(transacoes => {
            transacoes.forEach(transacao => {
                resumoAuditoria.totalTransacoes++;
                
                if (Validador.validarTransacao(transacao)) {
                    resumoAuditoria.transacoesValidas++;
                    resumoAuditoria.valorTotalTransacionado += transacao.quantia;
                    resumoAuditoria.taxasTotais += transacao.taxas;
                }
            });
        });

        return resumoAuditoria;
    }

    adicionarBloco(transacoes, minerador) {
        transacoes.forEach(transacao => {
            if (!Validador.validarTransacao(transacao)) {
                throw new Error(`Transação inválida: ${JSON.stringify(transacao)}`);
            }
        });

        const totalTaxas = transacoes.reduce((total, tx) => total + tx.taxas, 0);
        const recompensaTotal = this.recompensaMinerador + totalTaxas;

        transacoes.forEach(transacao => {
            const saldoRemetente = this.saldos[transacao.remetente] || 0;
            const custoTotal = transacao.quantia + transacao.taxas;

            if (saldoRemetente < custoTotal) {
                throw new Error(`Saldo insuficiente para ${transacao.remetente}`);
            }

            this.saldos[transacao.remetente] = (this.saldos[transacao.remetente] || 0) - custoTotal;
            this.saldos[transacao.destinatario] = (this.saldos[transacao.destinatario] || 0) + transacao.quantia;
        });

        this.saldos[minerador] = (this.saldos[minerador] || 0) + recompensaTotal;

        const novoBloco = new Bloco(
            this.cadeia[this.cadeia.length - 1].hash, 
            transacoes, 
            this.dificuldadeMineracao
        );

        this.cadeia.push(novoBloco);
        this.propagarBloco(novoBloco);
        this.registrarHistoricoTransacoes(novoBloco);  

        return novoBloco;
    }

    validaBloco(bloco) {
        return bloco.hash === bloco.calcularHash() && 
               bloco.dados.every(tx => Validador.validarTransacao(tx));
    }

    verificarIntegridadeCadeia() {
        for (let i = 1; i < this.cadeia.length; i++) {
            const blocoAtual = this.cadeia[i];
            const blocoAnterior = this.cadeia[i - 1];

            if (blocoAtual.hashAnterior !== blocoAnterior.hash) {
                return false;
            }

            if (!this.validaBloco(blocoAtual)) {
                return false;
            }
        }
        return true;
    }

    imprimirCadeia() {
        console.log('===== Cadeia de Blocos =====');
        this.cadeia.forEach((bloco, index) => {
            console.log(`\n--- Bloco ${index} ---`);
            console.log(`Hash: ${bloco.hash}`);
            console.log(`Hash Anterior: ${bloco.hashAnterior}`);
            console.log(`Timestamp: ${new Date(bloco.timestamp).toLocaleString()}`);
            console.log(`Nonce: ${bloco.nonce}`);
            console.log(`Dificuldade: ${bloco.dificuldade}`);
            console.log(`Transações: ${JSON.stringify(bloco.dados, null, 2)}`);
        });
        console.log('===========================');
    }

    exibirSaldos() {
        console.log('\n===== Saldos =====');
        for (const [endereco, saldo] of Object.entries(this.saldos)) {
            console.log(`Endereço: ${endereco}\nSaldo: ${saldo.toFixed(2)}`);
        }
        console.log('===================');

        const auditoria = this.auditarTransacoes();
        console.log('\n===== Auditoria de Transações =====');
        console.log(`Total de Transações: ${auditoria.totalTransacoes}`);
        console.log(`Transações Válidas: ${auditoria.transacoesValidas}`);
        console.log(`Valor Total Transacionado: ${auditoria.valorTotalTransacionado.toFixed(2)}`);
        console.log(`Taxas Totais: ${auditoria.taxasTotais.toFixed(2)}`);
        console.log('===================');
    }
}

module.exports = Blockchain;
