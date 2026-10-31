# Projeto Calculadora — FinançApp

## 1. Identificação do Projeto

- Aluno: Luiz Amaral
- RA: 251676
- Curso: Análise e Desenvolvimento de Sistemas
- Disciplina: AS406TSN4 - UX/UI e Testes de Software
- Professor: Ohata
- Semestre: 2026.2

## 2. Visão Geral

Calculadora em Java com soma, subtração, multiplicação e divisão.

## 3. Problema

A FinançApp precisa fazer cálculos básicos e tratar erros sem interromper o programa.

## 4. Objetivo

Praticar programação em Java, testes, tratamento de erros, refatoração, JavaDoc e Git.

## 5. Escopo Funcional

O método `calc(int a, int b, String op)` recebe dois inteiros e uma operação.

| Operação | Exemplo | Resultado |
| --- | --- | --- |
| Soma | `2 + 3` | 5 |
| Subtração | `10 - 4` | 6 |
| Multiplicação | `3 * 5` | 15 |
| Divisão | `8 / 2` | 4 |
| Divisão por zero | `8 / 0` | Mensagem de erro |
| Operação inválida | `5 x 5` | Mensagem de erro |

A divisão é inteira: `7 / 2` retorna `3`. Os erros são tratados com `try/catch` nos testes.

## 6. Requisitos Não Funcionais

Código organizado, testes automáticos, documentação JavaDoc e controle de versões com Git.

## 7. Tecnologias Utilizadas

Java 17, JavaDoc, Git e GitHub. Não precisa instalar bibliotecas.

## 8. Estrutura do Projeto

```text
projeto_calculadora/
├── src/calculadora/
│   ├── Calculadora.java
│   └── TesteCalculadora.java
├── docs/
├── images/
├── .gitignore
└── README.md
```

## 9. Como Executar

Com o JDK 17 instalado, execute na pasta do projeto:

```bash
javac -encoding UTF-8 -d bin src/calculadora/*.java
java -cp bin calculadora.TesteCalculadora
```

## 10. Testes

São oito testes: as quatro operações, divisão inteira, divisão por zero, operador inválido e uma soma após os erros. Cada teste mostra a entrada, o resultado esperado e o obtido. Uma diferença gera `AssertionError`.

Os oito passaram antes e depois da simplificação. Os prints mostram a saída real do console, exibida em uma página para facilitar a leitura.

Antes:

![Testes antes](images/testes/antes.png)

Depois:

![Testes depois](images/testes/depois.png)

## 11. Refatoração

Na branch `Refatoracao`, o `if/else` foi trocado por `switch` e a divisão ficou em um método separado. Depois, os cálculos passaram a retornar o resultado diretamente. Os testes continuaram passando e a versão foi integrada à `main`.

## 12. JavaDoc

Para gerar a documentação:

```bash
javadoc -private -encoding UTF-8 -charset UTF-8 -docencoding UTF-8 -author -version -d docs src/calculadora/*.java
```

Abra `docs/index.html` no navegador.

![Página inicial do JavaDoc](images/javadoc/index.png)

![Classe Calculadora](images/javadoc/calculadora.png)

## 13. Repositório

[Projeto no GitHub](https://github.com/lzAmaral/projeto_calculadora) · [Branch Refatoracao](https://github.com/lzAmaral/projeto_calculadora/tree/Refatoracao)

[Modelo do README da aula](https://github.com/dr-ohata/calculadora_refatoracao)
