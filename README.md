# 🔗 Blockchain simples

## 🌟 Visão Geral

A blockchain simples desenvolvida é um projeto que simula os princípios fundamentais de uma blockchain, oferecendo uma implementação didática e clara dos conceitos básicos de cadeias de blocos usando javascript.

## 🛠 Tecnologias Utilizadas

![JavaScript](https://img.shields.io/badge/JavaScript-ES6+-yellow?logo=javascript)
![Node.js](https://img.shields.io/badge/Node.js-14+-green?logo=node.js)
![Crypto](https://img.shields.io/badge/Crypto-Nativo-blue)

## 🔗 Link do Projeto

[Repositório GitHub](https://github.com/arlissondiogo/blockchain.git)

## 🧾 Licença

Distribuído sob a Licença MIT.

## 🚀 Configuração Rápida

### Pré-requisitos

- [Node.js](https://nodejs.org/) (v14 ou superior)
- npm (v6 ou superior)

### Instalação

```bash
# Clonar o repositório
git clone https://github.com/arlissondiogo/blockchain.git

# Navegar para o diretório do projeto
cd blockchain

# Instalar dependências
npm install

# Executar o projeto
npm run dev
```

## 📂 Estrutura do projeto

```bash
blockchainjs/
│
├── src/
│   ├── blockchain.js      # Lógica principal da blockchain
│   ├── bloco.js           # Classe de criação de blocos
│   ├── geraEndereco.js    # Gerador de endereços
│   ├── main.js            # Ponto de entrada
│   ├── transacao.js       # Gerenciamento de transações
│   └── validador.js       # Validações de segurança
│
├── package.json           # Configurações do projeto
└── README.md              # Documentação
└── LICENSE                # Licença
```

## ✨ Características Principais

### 🧱 Estrutura do bloco

- Bloco é personalizado com:
  - Timestamp
  - Dados de transação
  - Nonce
  - Cálculo de hash
  - Prova de trabalho (Proof of Work)
  - Hash único
  - Hash do bloco anterior
  - Mineração.

### 💸 Sistema de transações

- **Geração de endereços aleatórios**: Utiliza uma função para gerar endereços exclusivos (começando com `BAT` seguido por uma sequência aleatória de caracteres hexadecimais) que podem ser usados como remetentes ou destinatários nas transações.

- **Validação das transações**: Antes de qualquer transação ser adicionada à blockchain, ela é validada para garantir que:
  - os endereços de remetente e destinatário são válidos, seguindo o formato `BATxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx`.
  - o remetente possui saldo suficiente para cobrir o valor da transação + taxas.
  - a quantia da transação deve ser maior que zero.
  - as taxas sejam calculadas com base em uma porcentagem e não podem ser inferiores a 0.1 para garantir a viabilidade da mineração.

- **Rastreamento do histórico das transações**: O sistema mantém um histórico de todas as transações realizadas. Cada bloco contém uma lista de transações e o blockchain armazena esse histórico para auditorias futuras.
  - **Auditoria das transações**: Permite o rastreamento das transações válidas, o valor total transacionado, e o total de taxas pagas, ajudando a garantir a integridade da rede.

- **Taxas de transação**:
  - As transações podem ter uma taxa associada, que serve para recompensar os mineradores que processam os blocos.
  - As taxas são dinâmicas e podem ser ajustadas conforme a carga da rede, incentivando os mineradores a priorizarem transações com taxas mais altas.

- **Manejo de erros e transações inválidas**:
  - Transações inválidas (como uma tentativa de enviar mais do que o saldo disponível) são bloqueadas e o erro é registrado.
  - Transações com endereços inválidos ou com valores inadequados (quantia <= 0 ou taxas abaixo do mínimo) também são rejeitadas.

- **Recompensa ao minerador**:
  - O minerador que consegue adicionar um novo bloco válido à blockchain recebe uma recompensa por seu trabalho de mineração, que é uma combinação de um valor fixo (35) e as taxas de transação (caso não seja definida, será de 0.1).

- **Processo de mineração e blocos**:
  - As transações são agrupadas em blocos, e a mineradora precisa realizar o processo de prova de trabalho (PoW) para minerar um novo bloco. O bloco só é adicionado à cadeia se o hash do bloco cumprir com a dificuldade da mineração (ou seja, o hash deve começar com uma quantidade específica de zeros).

### Componentes Principais

#### 1. Classe `Blockchain`

- Gerenciamento da cadeia de blocos
- Validação e processamento de transações
- Propagação de blocos entre nós
- Resolução de forks
- Controle de saldos
- Auditoria de transações

#### 2. Classe `Bloco`

- Mineração com prova de trabalho (Proof of Work)
- Geração de hash único
- Armazenamento de transações
- Cálculo e validação de hash
- Controle de dificuldade de mineração

#### 3. Classe `Transacao`

- Criação de transações seguras
- Validação rigorosa de endereços
- Verificação de saldo
- Atribuição de taxas de transação

#### 4. Classe `Validador`

- Validação de endereços de remetente e destinatário
- Verificação da integridade de transações
- Validação de quantias e taxas

#### 5. Classe `GeraEndereco`

- Geração de endereços aleatórios
- Formato padronizado (prefixo 'BAT')
- Geração de endereços de 40 caracteres alfanuméricos, com o prefixo "BAT"

## 📝 Relatório de Implementação

### 1. Propagação de Blocos e Transações

- **Métodos**:
  - `propagarBloco()`: Envia bloco para todos os nós
  - `receberBloco()`: Recebe e valida blocos de outros nós

### 2. Resolução de Conflitos (Forks)

- **Método**: `resolverForks()`
  - Quando dois blocos conflitantes são minerados, cada nó mantém sua versão da blockchain. O método `resolverForks()` compara o comprimento das cadeias de blocos e seleciona a cadeia mais longa como a versão válida.
  - Se um nó detecta que sua cadeia é mais curta, ele irá reverter para a cadeia mais longa e adicionar os blocos que estavam ausentes.

### 3. Controle de Saldos

- **Funcionalidades**:
  - `temSaldoSuficiente()`: Verifica saldo antes da transação
  - `atualizarSaldos()`: Atualiza saldos após transação

### 4. Taxas de Transação e Recompensas

- **Cálculo de Recompensa**:
  - Recompensa fixa de 35 unidades
  - Soma das taxas de transação

## 📄 Exemplos de Uso

Este exemplo mostra como você pode usar o projeto para criar uma blockchain, adicionar transações e minerar blocos.

### 1. **Inicializando a blockchain e criando nós**

Primeiro, criamos uma blockchain principal e dois nós adicionais para simular a rede descentralizada.

```javascript
const GeraEndereco = require('./src/geraEndereco');
const Transacao = require('./src/transacao');
const Blockchain = require('./src/blockchain');

function main() {
    // Criar a blockchain principal
    const blockchainPrincipal = new Blockchain();

    // Criar dois nós para simular a rede descentralizada
    const no1 = new Blockchain();
    const no2 = new Blockchain();

    // Adicionar nós à blockchain principal e entre si
    blockchainPrincipal.adicionarNo(no1);
    blockchainPrincipal.adicionarNo(no2);
    no1.adicionarNo(blockchainPrincipal);
    no1.adicionarNo(no2);
    no2.adicionarNo(blockchainPrincipal);
    no2.adicionarNo(no1);

    // Exibir o estado inicial da blockchain
    console.log('===== ESTADO INICIAL =====');
    blockchainPrincipal.exibirSaldos();
}
main();

```

### 2. **Criar endereços aleatórios e definir saldos**

Em seguida, geramos alguns endereços aleatórios para os participantes da rede e definimos seus saldos iniciais.

```javascript
const minerador = GeraEndereco.gerarEnderecoAleatorio();
const wayne = GeraEndereco.gerarEnderecoAleatorio();
const richard = GeraEndereco.gerarEnderecoAleatorio();
const todd = GeraEndereco.gerarEnderecoAleatorio();

// Definir saldos iniciais
blockchainPrincipal.saldos[minerador] = 0;
blockchainPrincipal.saldos[wayne] = 1000;
blockchainPrincipal.saldos[richard] = 500;
blockchainPrincipal.saldos[todd] = 250;

console.log('===== ESTADO INICIAL =====');
blockchainPrincipal.exibirSaldos();
```

### 3. **Adicionar transações e minerar blocos**

Agora, vamos adicionar transações entre os endereços e minerar blocos.

```javascript
try {
    // Criar transações
    const transacoes1 = [
        new Transacao(wayne, richard, 100, 0.5),
        new Transacao(richard, todd, 50, 0.2)
    ];

    // Adicionar o bloco com as transações
    blockchainPrincipal.adicionarBloco(transacoes1, minerador);

    // Criar mais transações
    const transacoes2 = [
        new Transacao(todd, wayne, 25, 0.1)
    ];

    // Adicionar o segundo bloco
    blockchainPrincipal.adicionarBloco(transacoes2, minerador);

    console.log('\n===== APÓS TRANSAÇÕES =====');
    blockchainPrincipal.exibirSaldos();

} catch (error) {
    console.error('Erro ao adicionar transações:', error);
}
```

### 4. **Testar transação inválida**

Você pode também testar a validação da blockchain, onde tentamos adicionar uma transação inválida (saldo insuficiente, por exemplo).

```javascript
console.log('\n===== TESTE DE TRANSAÇÃO INVÁLIDA =====');
try {
    // Criar uma transação inválida (saldo insuficiente)
    const transacaoInvalida = new Transacao(richard, wayne, 1000); // Valor maior que o saldo
    blockchainPrincipal.adicionarBloco([transacaoInvalida], minerador);
} catch (error) {
    console.error(`Transação inválida bloqueada: ${error.message}`);
}
```

### 5. **Exibir a cadeia de blocos**

Após as transações, podemos exibir a cadeia de blocos para ver o estado final da blockchain.

```javascript
// Exibir a cadeia de blocos
blockchainPrincipal.imprimirCadeia();
```
