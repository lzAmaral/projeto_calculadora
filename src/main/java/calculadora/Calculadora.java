package calculadora;

/**
 * Realiza operações básicas com duas entradas inteiras.
 *
 * @author Luiz Amaral
 * @version 1.0
 */
public class Calculadora {

    /** Cria uma calculadora sem estado compartilhado. */
    public Calculadora() {
    }

    /**
     * Calcula a operação solicitada.
     *
     * @param a primeiro número
     * @param b segundo número
     * @param op operador: +, -, * ou /
     * @return resultado em ponto flutuante, preservando a parte fracionária da divisão
     * @throws ArithmeticException se houver divisão por zero
     * @throws IllegalArgumentException se o operador for inválido ou nulo
     */
    public double calc(int a, int b, String op) {
        // A conversão antes da operação evita o transbordamento aritmético de int.
        if ("+".equals(op)) {
            return (double) a + b;
        } else if ("-".equals(op)) {
            return (double) a - b;
        } else if ("*".equals(op)) {
            return (double) a * b;
        } else if ("/".equals(op)) {
            if (b == 0) {
                throw new ArithmeticException("Não é possível dividir por zero.");
            }
            return (double) a / b;
        } else {
            throw new IllegalArgumentException("Operação inválida: " + op + ". Use +, -, * ou /.");
        }
    }
}
