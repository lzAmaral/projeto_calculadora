package calculadora;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Interface de console que apresenta resultados e trata erros de uso.
 *
 * @author Luiz Amaral
 * @version 1.0
 */
public final class Aplicacao {

    /** Impede instâncias de uma classe usada apenas como ponto de entrada. */
    private Aplicacao() {
    }

    /**
     * Inicia a interface de console.
     *
     * @param args argumentos de linha de comando, não utilizados
     */
    public static void main(String[] args) {
        try (Scanner entrada = new Scanner(System.in, StandardCharsets.UTF_8)) {
            executar(entrada, System.out);
        }
    }

    /**
     * Executa cálculos até o usuário digitar sair ou encerrar a entrada.
     * Erros esperados são apresentados ao usuário; a próxima operação continua disponível.
     *
     * @param entrada fonte das linhas digitadas, não nula
     * @param saida destino das mensagens, não nulo
     */
    public static void executar(Scanner entrada, PrintStream saida) {
        Calculadora calculadora = new Calculadora();
        saida.println("FinançApp | Calculadora");
        saida.println("Operações: +, -, *, /. Digite sair em qualquer etapa para encerrar.");

        while (true) {
            try {
                String primeiroTexto = lerLinha(entrada, saida, "Primeiro número: ");
                if (primeiroTexto == null) {
                    break;
                }
                int primeiroNumero = Integer.parseInt(primeiroTexto);
                String segundoTexto = lerLinha(entrada, saida, "Segundo número: ");
                if (segundoTexto == null) {
                    break;
                }
                int segundoNumero = Integer.parseInt(segundoTexto);
                String operador = lerLinha(entrada, saida, "Operação (+, -, *, /): ");
                if (operador == null) {
                    break;
                }

                double resultado = calculadora.calc(primeiroNumero, segundoNumero, operador);
                saida.println("Resultado: " + resultado);
            } catch (NumberFormatException erro) {
                saida.println("Erro: digite um número inteiro entre -2147483648 e 2147483647.");
            } catch (ArithmeticException | IllegalArgumentException erro) {
                // O domínio sinaliza a falha; a interface decide como informar e continuar.
                saida.println("Erro: " + erro.getMessage());
            }
        }
        saida.println("Calculadora encerrada.");
    }

    /**
     * Solicita uma linha, removendo espaços nas extremidades.
     *
     * @param entrada fonte de dados
     * @param saida destino da pergunta
     * @param pergunta mensagem apresentada antes da leitura
     * @return texto lido, ou null para sair ou fim de entrada
     */
    private static String lerLinha(Scanner entrada, PrintStream saida, String pergunta) {
        saida.print(pergunta);
        if (!entrada.hasNextLine()) {
            return null;
        }
        String linha = entrada.nextLine().trim();
        return "sair".equalsIgnoreCase(linha) ? null : linha;
    }
}
