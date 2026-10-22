# Projeto Calculadora — FinançApp

Atividade prática de Java: implementação, testes, tratamento de erros, refatoração, JavaDoc e versionamento.

## 1. Identificação do Projeto

| Campo | Informação |
| --- | --- |
| Projeto | `projeto_calculadora` |
| Contexto | FinançApp — startup fictícia de finanças pessoais |
| Aluno | Luiz Amaral |
| RA | 251676 |
| Curso | Análise e Desenvolvimento de Sistemas (ADS) |
| Disciplina | AS406TSN4 - UX/UI e Testes de Software |
| Professor | Ohata |
| Semestre | 2026.2 |
| Repositório | [lzAmaral/projeto_calculadora](https://github.com/lzAmaral/projeto_calculadora) |

A organização das seções iniciais segue o [modelo de README indicado na aula](https://github.com/dr-ohata/calculadora_refatoracao/blob/main/README.md). As seções seguintes registram como executar o projeto e verificar cada etapa da atividade.

## 2. Visão Geral

O projeto oferece uma classe Java reutilizável para cálculos básicos e uma interface de console para experimentar as operações. O contexto da FinançApp envolve orçamento mensal, divisão de despesas e projeções de economia.

A lógica matemática fica em `Calculadora`; a leitura de dados e a apresentação das mensagens ficam em `Aplicacao`. Essa separação permite testar os cálculos diretamente e verificar, de forma independente, o comportamento da interface diante de erros.

## 3. Problema

Um cálculo incorreto ou uma entrada inválida pode comprometer uma funcionalidade que depende desse módulo. Por isso, além de produzir resultados corretos, o projeto precisa rejeitar divisão por zero, informar operadores inválidos e permitir que o usuário continue após um erro.

## 4. Objetivo

### 4.1 Objetivo Geral

Construir, validar e documentar uma calculadora em Java, demonstrando práticas de qualidade e manutenção de software.

### 4.2 Objetivos Técnicos

- Implementar soma, subtração, multiplicação e divisão.
- Validar entradas e apresentar mensagens de erro compreensíveis.
- Automatizar testes unitários e funcionais.
- Refatorar em uma branch própria e executar novamente os mesmos testes.
- Documentar o código com JavaDoc e gerar a versão HTML.
- Preservar as etapas em commits e publicar as duas branches no GitHub.

## 5. Escopo Funcional

O método público é `double calc(int primeiroNumero, int segundoNumero, String operador)`, correspondente à assinatura `calc(int a, int b, String op)` solicitada na atividade.

| ID | Operação | Exemplo | Resultado esperado | Situação |
| --- | --- | --- | --- | --- |
| RF01 | Soma (`+`) | `calc(10, 5, "+")` | `15.0` | Implementado e testado |
| RF02 | Subtração (`-`) | `calc(10, 5, "-")` | `5.0` | Implementado e testado |
| RF03 | Multiplicação (`*`) | `calc(4, 5, "*")` | `20.0` | Implementado e testado |
| RF04 | Divisão (`/`) | `calc(7, 2, "/")` | `3.5` | Implementado e testado |
| RF05 | Divisor zero | `calc(10, 0, "/")` | `ArithmeticException`: “Não é possível dividir por zero.” | Implementado e testado |
| RF06 | Operador inválido | `calc(10, 5, "%")` | `IllegalArgumentException`: “Operação inválida: %. Use +, -, * ou /.” | Implementado e testado |

**Contrato de erro:** a classe `Calculadora` lança exceções para chamadas inválidas. A interface `Aplicacao` captura essas exceções, mostra a mensagem e oferece um novo cálculo. Assim, um erro não é confundido com um resultado numérico válido.

As entradas são inteiros de 32 bits. O retorno é `double`, para permitir divisão fracionária. A conversão acontece antes da operação, evitando overflow intermediário de `int`. Valores em ponto flutuante podem sofrer arredondamento; este exercício não implementa uma política de precisão monetária.

Na API, os operadores devem ser exatamente `+`, `-`, `*` ou `/`. Operador nulo, vazio ou com espaços é inválido. A interface de console remove espaços externos antes de chamar a API.

## 6. Requisitos Não Funcionais

| ID | Categoria | Como foi atendido |
| --- | --- | --- |
| RNF01 | Legibilidade | Indentação consistente, nomes descritivos e métodos com responsabilidade definida |
| RNF02 | Testabilidade | 27 casos unitários e 11 testes funcionais automatizados com JUnit |
| RNF03 | Manutenção | Refatoração registrada na branch `Refatoracao`; mesmos testes executados antes e depois |
| RNF04 | Documentação | JavaDoc de classes, construtores, métodos, parâmetros, retornos e exceções; atributos dos testes documentados |
| RNF05 | Versionamento | Histórico organizado e branches `main` e `Refatoracao` |
| RNF06 | Confiabilidade | Tratamento de números inválidos, divisor zero, operador inválido e fim de entrada |

A calculadora não possui atributos de estado; a documentação dos atributos existentes está nas classes de teste, junto ao código-fonte.

## 7. Tecnologias Utilizadas

| Tecnologia | Versão utilizada | Finalidade |
| --- | --- | --- |
| Java / JDK | 17.0.16, compatibilidade Java 17 | Implementação, compilação e execução |
| Maven | 3.9.11 | Gerenciamento de dependências, testes e empacotamento |
| JUnit Jupiter | 5.10.2 | Testes parametrizados e asserções |
| Maven Surefire | 3.2.5 | Execução dos testes e relatórios XML |
| Git | 2.51.0 | Commits e branches |
| GitHub | Serviço web | Hospedagem pública do repositório |
| JavaDoc | JDK 17 | Documentação HTML |
| Ambiente | Editor e terminal | Não depende de uma IDE específica |

As evidências HTML foram produzidas a partir dos relatórios XML do Surefire. Os prints são capturas desses relatórios e da documentação aberta em navegador.

## 8. Estrutura do Projeto

```text
projeto_calculadora/
├── src/
│   ├── main/java/calculadora/
│   │   ├── Calculadora.java
│   │   ├── Aplicacao.java
│   │   └── package-info.java
│   └── test/java/calculadora/
│       ├── TesteCalculadora.java
│       └── TesteAplicacao.java
├── docs/
│   ├── index.html
│   └── calculadora/Calculadora.html
├── evidencias/
│   ├── testes-antes.html
│   ├── testes-depois.html
│   ├── testes-antes.txt
│   ├── testes-depois.txt
│   ├── testes-antes.json
│   ├── testes-depois.json
│   └── comparacao-refatoracao.txt
├── images/
│   ├── testes/antes.png
│   ├── testes/depois.png
│   └── javadoc/
│       ├── index.png
│       └── calculadora.png
├── scripts/
│   ├── gerar-javadoc.sh
│   └── gerar-javadoc.bat
├── pom.xml
├── .gitignore
└── README.md
```

Foi adotada a organização padrão do Maven, com fontes em `src/main/java` e testes em `src/test/java`. A pasta `target/` é criada durante o build e não faz parte do versionamento.

## 9. Como Executar

### Pré-requisitos

- JDK 17 ou superior, com `java`, `javac` e `javadoc` no `PATH`.
- Maven 3.9 ou superior.
- Git para clonar e consultar o histórico.
- Internet no primeiro build para baixar as dependências do Maven e do JUnit.

```sh
git clone https://github.com/lzAmaral/projeto_calculadora.git
cd projeto_calculadora
mvn clean verify
java -jar target/projeto_calculadora-1.0.0.jar
```

Digite o primeiro inteiro, o segundo inteiro e o operador, cada um em sua própria linha. Digite `sair` em qualquer etapa para encerrar.

```text
Primeiro número: 7
Segundo número: 2
Operação (+, -, *, /): /
Resultado: 3.5
Primeiro número: 10
Segundo número: 0
Operação (+, -, *, /): /
Erro: Não é possível dividir por zero.
Primeiro número: 2
Segundo número: 3
Operação (+, -, *, /): +
Resultado: 5.0
Primeiro número: sair
Calculadora encerrada.
```

Também é possível compilar e executar somente a aplicação sem Maven, usando o JDK:

```sh
javac -encoding UTF-8 -d target/classes src/main/java/calculadora/*.java
java -cp target/classes calculadora.Aplicacao
```

## 10. Testes e Evidências

```sh
mvn test
```

`TesteCalculadora.java` executa 27 casos: 18 operações com resultados numéricos, três divisões por zero e seis operadores inválidos. Inclui números negativos, zero, divisão fracionária e resultados que ultrapassam os limites de `int`.

`TesteAplicacao.java` executa 11 testes funcionais: cinco sessões com erro seguido de cálculo válido e seis cenários de saída voluntária ou fim de entrada. Cada caso imprime as entradas, o resultado esperado e a saída efetivamente obtida.

O `pom.xml` configura explicitamente o padrão `Teste*.java` para que o Surefire encontre os nomes exigidos no enunciado. Nenhum teste depende da opção `-ea` da JVM.

| Execução | Testes | Falhas | Erros | Ignorados |
| --- | ---: | ---: | ---: | ---: |
| Antes da refatoração | 38 | 0 | 0 | 0 |
| Depois da refatoração | 38 | 0 | 0 | 0 |

### Antes da refatoração

![Execução anterior à refatoração: 38 testes aprovados](images/testes/antes.png)

[Relatório HTML](evidencias/testes-antes.html) · [Saída do Maven](evidencias/testes-antes.txt)

### Depois da refatoração

![Execução posterior à refatoração: 38 testes aprovados](images/testes/depois.png)

[Relatório HTML](evidencias/testes-depois.html) · [Saída do Maven](evidencias/testes-depois.txt) · [Comparação das execuções](evidencias/comparacao-refatoracao.txt)

Os relatórios HTML podem ser abertos localmente no navegador. Seus detalhes expansíveis incluem a saída dos 38 testes. Os arquivos JSON preservam os resultados individuais, sem propriedades do ambiente da máquina. Nos logs, somente o caminho absoluto local do projeto foi substituído por `[projeto_calculadora]`.

## 11. Processo de Refatoração

1. A implementação inicial foi criada na branch `main`.
2. Os testes foram adicionados e executados com sucesso.
3. A branch `Refatoracao` foi criada a partir dessa versão testada.
4. Na nova branch, as operações foram extraídas para `somar`, `subtrair`, `multiplicar` e `dividir`.
5. Os parâmetros receberam nomes descritivos; a cadeia de `if/else` foi substituída por um `switch`.
6. A mensagem de operador inválido foi centralizada, preservando tipo e texto das exceções.
7. Os mesmos 38 testes foram executados novamente, sem alterações nos arquivos de teste.
8. A documentação e as evidências foram adicionadas; a branch foi integrada à `main` com um commit de merge, preservando ambas as branches.

A comparação dos relatórios confirmou as mesmas classes, casos e saídas observadas antes e depois. Essa evidência demonstra preservação do comportamento nos cenários cobertos pela suíte.

```sh
# Visualizar o histórico completo
git log --graph --oneline --all

# Consultar a branch de refatoração
git switch Refatoracao

# Comparar a versão inicial testada com a refatoração
git diff 647229e 13f7ecb -- src/main/java/calculadora/Calculadora.java

# Confirmar que os testes não foram alterados pela refatoração
git diff 647229e 13f7ecb -- src/test/java

git switch main
```

## 12. Documentação JavaDoc

A documentação gerada está em [`docs/index.html`](docs/index.html). Depois de clonar ou extrair o projeto, abra esse arquivo no navegador. Os links para classes e métodos funcionam localmente; no GitHub, arquivos HTML são exibidos como código.

Para gerar novamente no Linux/macOS:

```sh
sh scripts/gerar-javadoc.sh
```

No Windows, execute no Prompt de Comando:

```bat
scripts\gerar-javadoc.bat
```

A geração usa `-private`, `-author`, `-version`, `-Xdoclint:all` e `-Werror`. Isso inclui métodos privados, verifica a documentação e faz o comando falhar se houver avisos. O HTML contém a API de produção; as classes de teste também possuem comentários JavaDoc no código-fonte.

São usados `@author`, `@version`, `@param`, `@return`, `@throws` e referências entre classes. Comentários técnicos explicam a conversão numérica e o tratamento de falhas na interface.

![Índice da documentação JavaDoc](images/javadoc/index.png)

![Documentação da classe Calculadora e seus métodos](images/javadoc/calculadora.png)

## 13. Repositório e Entrega

- [Repositório público](https://github.com/lzAmaral/projeto_calculadora)
- [Branch principal](https://github.com/lzAmaral/projeto_calculadora/tree/main)
- [Branch Refatoracao](https://github.com/lzAmaral/projeto_calculadora/tree/Refatoracao)
- [Histórico de commits](https://github.com/lzAmaral/projeto_calculadora/commits/main)
- [Código da calculadora](src/main/java/calculadora/Calculadora.java)
- [Testes unitários](src/test/java/calculadora/TesteCalculadora.java)
- [Testes funcionais](src/test/java/calculadora/TesteAplicacao.java)
- [Documentação gerada](docs/index.html)

O repositório contém o projeto completo, as duas branches, o histórico da refatoração, os testes, a documentação HTML e os prints de execução.
