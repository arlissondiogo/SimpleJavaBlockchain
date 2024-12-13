const GeraEndereco = require('./geraEndereco');
const Transacao = require('./transacao');
const Blockchain = require('./blockchain');

function main() {
    const blockchainPrincipal = new Blockchain();
    const no1 = new Blockchain();
    const no2 = new Blockchain();

    blockchainPrincipal.adicionarNo(no1);
    blockchainPrincipal.adicionarNo(no2);
    no1.adicionarNo(blockchainPrincipal);
    no1.adicionarNo(no2);
    no2.adicionarNo(blockchainPrincipal);
    no2.adicionarNo(no1);

    const minerador = GeraEndereco.gerarEnderecoAleatorio();
    const wayne = GeraEndereco.gerarEnderecoAleatorio();
    const richard = GeraEndereco.gerarEnderecoAleatorio();
    const todd = GeraEndereco.gerarEnderecoAleatorio();

    blockchainPrincipal.saldos[minerador] = 0;
    blockchainPrincipal.saldos[wayne] = 1000;
    blockchainPrincipal.saldos[richard] = 500;
    blockchainPrincipal.saldos[todd] = 250;

    console.log('===== ESTADO INICIAL =====');
    blockchainPrincipal.exibirSaldos();

    try {
        const transacoes1 = [
            new Transacao(wayne, richard, 100, 0.5),
            new Transacao(richard, todd, 50, 0.2)
        ];
        blockchainPrincipal.adicionarBloco(transacoes1, minerador);

        const transacoes2 = [
            new Transacao(todd, wayne, 25, 0.1)
        ];
        blockchainPrincipal.adicionarBloco(transacoes2, minerador);

        console.log('\n===== APÓS TRANSAÇÕES =====');
        blockchainPrincipal.exibirSaldos();

        console.log('\n===== TESTE DE TRANSAÇÃO INVÁLIDA =====');
        try {
            const transacaoInvalida = new Transacao(richard, wayne, 1000);
            blockchainPrincipal.adicionarBloco([transacaoInvalida], minerador);
        } catch (error) {
            console.error(`Transação inválida bloqueada: ${error.message}`);
        }

        blockchainPrincipal.imprimirCadeia();

    } catch (error) {
        console.error('Erro no processamento da blockchain:', error);
    }
}

main();