<h1 align="center"> 📜 Documentação do Projeto de Blockchain em Java</h1>

## Introdução

<p align="center"><i> Este projeto implementa uma versão básica de uma blockchain em Java. Ele permite a criação de transações, inclusão de blocos na cadeia e validação da autenticidade dos dados, garantindo segurança e integridade da cadeia. Desenvolvido para fins educativos e como parte do Programa de Bolsas da Compass UOL - Blockchain.</i></p>

## 🛠️ Pré-requisitos

- A versão utilizada do windows foi a 10, versões anteriores podem ficar ultrapassadas para a instalação de algumas IDE's;

- >Os tutoriais de instalação são direcionados a *windows*, caso utilize *outro sistema operacional* é necessário procurar outros tutoriais.

- **Java Development Kit (JDK)**: Este projeto foi desenvolvido utilizando a versão Java Zulu 17.52.17-ca-jdk17.0.12-win_x64. Para compilar e executar o código, certifique-se de que esta ou uma versão compatível do JDK esteja instalada.

- **IDE**: Você pode usar qualquer IDE de sua preferência para rodar o projeto, como o [IntelliJ IDEA](https://www.youtube.com/watch?v=2D5Ww-m6auw "tutorial de instação do IntelliJ IDEA"), [Eclipse](https://www.youtube.com/watch?v=KWGIaWh71q4 "tutorial de instalação do Eclipse"), [Visual Studio Code](https://www.youtube.com/watch?v=QT-YWT1-YI4 "tutorial de instalação VS code") ou o próprio terminal com `javac` e `java` para compilar e executar o código.

- Em caso de dúvida, acesse essa seção: [Execução de código em outras IDE's](#executando-o-código-em-outras-ide).

## 🛠️ baixando o arquivo do github

Siga os passos abaixo para baixar um arquivo do GitHub:

1. **Acesse o repositório**  
   Vá até o repositório do GitHub onde o arquivo que deseja baixar está localizado. Você pode fazer isso clicando no link do repositório ou buscando o nome no [GitHub](https://github.com).

2. **Navegue até o arquivo**  
   Dentro do repositório, navegue até a pasta onde o arquivo está localizado. Isso pode ser feito clicando nas pastas e arquivos dentro do repositório.

3. **Baixe o arquivo**  
   Para baixar o arquivo diretamente, siga os passos:
   - Clique no nome do arquivo.
   - No canto superior direito da página do arquivo, clique no botão **Raw**.
   - Após a visualização do arquivo em formato bruto, clique com o botão direito do mouse e selecione **Salvar como** para baixá-lo.

### Alternativa: Clonando o repositório inteiro

Se preferir baixar o repositório inteiro, siga os passos abaixo:

1. No repositório, clique no botão **Code**.
2. Copie o link fornecido (se preferir baixar via Git) ou clique em **Download ZIP** para baixar um arquivo compactado com todo o repositório.
3. Navegue até uma pasta, selecione o caminho do arquivo, escreva cmd e aperte enter.
4. Quando o cmd abrir, digite:

```bash
   git clone https://github.com/arlissondiogo/SimpleJavaBlockchain.git 
```

![Caminho do arquivo](caminho.png)

![Escreva cmd](digite.png)

![git clone](clone.png)

## 🧩 Alguns conceitos importantes

- Blockchain: Estrutura de dados onde blocos conectados mantêm um histórico imutável de transações.
- Bloco: Contém uma lista de transações e o hash que o vincula ao bloco anterior.
- Hash: Uma função criptográfica que gera uma sequência única com base nos dados do bloco.
- Transação: Representa uma operação entre um remetente e um destinatário.

## Estrutura do Código

O projeto contém as seguintes classes:

### 1. Transacao 💸

A classe Transacao modela uma transação com os atributos de remetente, destinatário e quantia.

Construtor:

```java
public Transacao(String remetente, String destinatario, long quantia): Inicializa uma nova transação com um remetente, destinatário e a quantia transferida.
```

Métodos:

```java
public String getDestinatario(): Retorna o destinatário da transação.
public String getRemetente(): Retorna o remetente da transação.
public long getQuantia(): Retorna a quantia envolvida na transação.
@Override public String toString(): Retorna uma representação textual da transação, incluindo remetente, destinatário e quantia.
```

### 2. Bloco 🧱

A classe Bloco armazena transações e calcula o hash do bloco com base nas transações e no hash anterior, garantindo a imutabilidade da cadeia.

Construtor:

```java
public Bloco(String hashAnterior, List<Transacao> dados): Inicializa um novo bloco, definindo o hash anterior e as transações contidas.
```

Métodos:

```java
public String calcularHash(): Calcula e retorna o hash do bloco, baseado no hash anterior, timestamp e nas transações. Utiliza a função SHA-256 para a criptografia.
public String getHashAnterior(): Retorna o hash do bloco anterior.
public String getHash(): Retorna o hash do bloco atual.
public long getTimestamp(): Retorna o timestamp em que o bloco foi criado.
public List<Transacao> getDados(): Retorna a lista de transações armazenadas no bloco.
```

### 3. Blockchain 🔗

A classe Blockchain é responsável por gerenciar a cadeia de blocos e verificar sua integridade.

Construtor:

```java
- public Blockchain(): Inicializa uma nova blockchain e adiciona o bloco gênese.
```

Métodos:

```java
- private Bloco criarBlocoGênese(): Cria e retorna o bloco gênese com o hash anterior definido como "0".
- public void adicionarBloco(List<Transacao> transacoes): Adiciona um novo bloco à cadeia com as transações fornecidas. O hash do bloco anterior é obtido do último bloco da cadeia.
- public boolean cadeiaValida(): Verifica a integridade da cadeia de blocos, recalculando os hashes e comparando-os com os originais. Retorna true se a cadeia for válida e false caso contrário.
- public void imprimirCadeia(): Imprime no console os detalhes de cada bloco na cadeia, incluindo hash, hash anterior, timestamp e transações.
```

### 4. Main 🖥️

A classe Main contém o ponto de entrada do programa e demonstra o uso da blockchain.
Inicializa uma nova instância da blockchain, adiciona transações e imprime a cadeia de blocos, além de verificar se a cadeia é válida.

## 🚀 Como Executar

1. **Instale o Java Zulu 17**:

   - Baixe e instale o JDK da versão Zulu 17.52.17-ca-jdk17.0.12 ou mais recente a partir do site oficial da Azul: [Azul Zulu Downloads](https://www.azul.com/downloads/#zulu "Área de download");
   - Certifique-se de que as variáveis `JAVA_HOME` e `PATH` estão apontando para o diretório correto do JDK;
   - Para evitar problemas um tutorial é sempre bem vindo, então clique nesse [link](https://www.youtube.com/watch?v=QekeJBShCy4&pp=ygUSY29tbyBiYWl4YXIgamRrIDE3 "tutorial para baixar e instalar o jdk") para saber mais.

2. **Compilação**:

Abra o terminal e navegue até o diretório do projeto onde está a pasta `src`. *Exemplo:*

```bash
cd C:\Users\User\Desktop\projetoBitcoin\projetoBitcoin
```

Compile todos os arquivos .java na pasta src/projetoBitcoin com o seguinte comando:

```bash
for %f in (src/projetoBitcoin/*.java) do javac -d bin %f
```

>Nota: Se aparecer uma mensagem de erro indicando que um arquivo não foi encontrado, verifique se você está no diretório correto e se os arquivos `.java` existem na pasta src/projetoBitcoin.

3. **Execute o projeto**:

Após a compilação bem-sucedida, execute o programa principal com o seguinte comando:

   ```bash
    java -cp bin projetoBitcoin.Main
   ```

> Dica: Verifique se o diretório `bin` foi criado corretamente após a compilação, contendo os arquivos `.class`.

## Executando o código em outras IDE

### **Visual Studio Code** 💻

1. **Instalação**:
   - Baixe e instale o [Visual Studio Code](https://code.visualstudio.com/).
   - Instale a extensão **"Java Extension Pack"** para suporte ao Java.

2. **Configurando o Projeto**:
   - Crie uma nova pasta para o projeto e copie os arquivos `.java` para essa pasta.
   - Abra o Visual Studio Code e selecione **"File" > "Open Folder..."** para abrir a pasta do projeto.

3. **Compilação e Execução**:
   - Abra o terminal integrado (`Ctrl + `).
   - Navegue até o diretório do projeto se necessário.
   - Compile todos os arquivos com:
  
    ```bash
    javac -d bin src/projetoBitcoin/*.java
    ```

   - Execute o programa principal:

    ```bash
    java -cp bin projetoBitcoin.Main
    ```

### **IntelliJ IDEA** 🧩

1. **Instalação**:
   - Baixe e instale o [IntelliJ IDEA](https://www.jetbrains.com/idea/download/).
   - Durante a instalação, escolha a opção para instalar o JDK se ainda não o tiver.

2. **Criando um Novo Projeto**:
   - Abra o IntelliJ e selecione **"New Project"**.
   - Selecione **"Java"**, configure o JDK e clique em **"Next"**.
   - Escolha **"Create project from existing sources"** e localize a pasta do projeto.

3. **Compilação e Execução**:
   - Clique com o botão direito na classe `Main` e selecione **"Run 'Main.main()'"**.
   - A saída será exibida na janela de console na parte inferior.

### **Eclipse** 🌒

1. **Instalação**:
   - Baixe e instale o [Eclipse IDE](https://www.eclipse.org/downloads/).
   - Durante a instalação, certifique-se de que o JDK está configurado corretamente.

2. **Criando um Novo Projeto**:
   - Abra o Eclipse e selecione **"File" > "New" > "Java Project"**.
   - Dê um nome ao projeto e clique em **"Finish"**.
   - Copie os arquivos `.java` para a pasta `src` do projeto.

3. **Compilação e Execução**:
   - Clique com o botão direito na classe `Main` e selecione **"Run As" > "Java Application"**.
   - A saída será exibida na janela de console na parte inferior

## 🔍 Saída Esperada

O programa exibirá o processo de criação de transações, mineração de blocos e a verificação da validade da blockchain. A saída deve ser semelhante a:

```bash
Iniciando Blockchain...
Criando bloco com as transações pendentes...
Blockchain válida? true

Bloco: e5c926d5e4e58d39644ac473ae5c2451dd4819a6728ab82211ff7051ca9deab7
Hash Anterior: a36aaa010b5d17ae0533da7550c9bf667946f4b38976647f059fd109d973827e
Timestamp: 1728563760569
Transações:
      Remetente = Grayson
      Destinatário = Wayne
      Quantidade = 10


Bloco: 0d340968443c86db9042aaa940bcbdc62f9657dd44be7eb4d38c35dae95be697
Hash Anterior: e5c926d5e4e58d39644ac473ae5c2451dd4819a6728ab82211ff7051ca9deab7
Timestamp: 1728563760696
Transações:
      Remetente = Drake
      Destinatário = Todd
      Quantidade = 4
```

## Detalhes Técnicos

- **Validação**: O método `cadeiaValida` verifica se a cadeia de blocos foi alterada, recalculando os hashes e comparando-os com os originais.
- **Algoritmo de Hashing:** Utiliza a função SHA-256 para garantir a integridade dos dados e dificultar fraudes.

Estrutura de Dados:

- Cada bloco contém uma lista de transações (List<*Transacao*>).
- Os blocos são encadeados pelo campo hashAnterior, assegurando a imutabilidade.
- Tratamento de Erros: Implementa tratamento básico para garantir operações robustas ao adicionar blocos e validar a cadeia.

- Exibição Detalhada: O método imprimirCadeia exibe informações sobre cada bloco, incluindo hash, hash anterior, timestamp e transações.

- Exemplo de Uso: O método main demonstra a criação da blockchain, adição de transações e validação da cadeia.

## Conclusão 📚

Este projeto demonstra uma implementação básica de blockchain com criação de transações, mineração e validação de blocos. É um exemplo simples, mas útil para entender os conceitos fundamentais de como uma blockchain funciona.
