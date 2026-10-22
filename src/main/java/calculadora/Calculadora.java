package calculadora;

/**
 * Realiza operações básicas com duas entradas inteiras e resultado double.
 * A classe não guarda estado nem faz entrada ou saída de dados. O chamador
 * deve tratar as exceções de uso; a interface {@link Aplicacao} faz esse tratamento.
 * Resultados em double estão sujeitos à precisão de ponto flutuante.
 *
 * @author Luiz Amaral
 * @version 1.0
 */
public class Calculadora {

    /** Cria uma calculadora sem estado compartilhado. */
    public Calculadora() {
    }

    /**
     * Seleciona e executa uma operação matemática.
     * O operador deve ser exatamente +, -, * ou /, sem espaços adicionais.
     *
     * @param primeiroNumero primeiro número; dividendo no caso da divisão
     * @param segundoNumero segundo número; divisor no caso da divisão
     * @param operador símbolo da operação: +, -, * ou /
     * @return resultado em ponto flutuante, preservando a parte fracionária da divisão
     * @throws ArithmeticException se houver divisão por zero
     * @throws IllegalArgumentException se o operador for inválido ou nulo
     */
    public double calc(int primeiroNumero, int segundoNumero, String operador) {
        // A guarda mantém a mesma exceção de uso para operadores nulos.
        if (operador == null) {
            throw operacaoInvalida(null);
        }
        return switch (operador) {
            case "+" -> somar(primeiroNumero, segundoNumero);
            case "-" -> subtrair(primeiroNumero, segundoNumero);
            case "*" -> multiplicar(primeiroNumero, segundoNumero);
            case "/" -> dividir(primeiroNumero, segundoNumero);
            default -> throw operacaoInvalida(operador);
        };
    }

    /**
     * Soma os operandos, convertendo antes da operação para evitar overflow de int.
     *
     * @param primeiroNumero primeira parcela
     * @param segundoNumero segunda parcela
     * @return soma em double
     */
    private double somar(int primeiroNumero, int segundoNumero) {
        return (double) primeiroNumero + segundoNumero;
    }

    /**
     * Subtrai o segundo número do primeiro.
     *
     * @param primeiroNumero minuendo
     * @param segundoNumero subtraendo
     * @return diferença em double
     */
    private double subtrair(int primeiroNumero, int segundoNumero) {
        return (double) primeiroNumero - segundoNumero;
    }

    /**
     * Multiplica os operandos sem realizar a operação intermediária como int.
     *
     * @param primeiroNumero primeiro fator
     * @param segundoNumero segundo fator
     * @return produto em double, sujeito ao arredondamento de ponto flutuante
     */
    private double multiplicar(int primeiroNumero, int segundoNumero) {
        return (double) primeiroNumero * segundoNumero;
    }

    /**
     * Divide dois números, preservando a parte fracionária.
     *
     * @param dividendo número que será dividido
     * @param divisor número pelo qual se divide
     * @return quociente em double
     * @throws ArithmeticException se o divisor for zero
     */
    private double dividir(int dividendo, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Não é possível dividir por zero.");
        }
        return (double) dividendo / divisor;
    }

    /**
     * Centraliza a mensagem usada para operadores desconhecidos e nulos.
     *
     * @param operador valor recebido na chamada
     * @return exceção que o método calc lançará
     */
    private IllegalArgumentException operacaoInvalida(String operador) {
        return new IllegalArgumentException("Operação inválida: " + operador + ". Use +, -, * ou /.");
    }
}
