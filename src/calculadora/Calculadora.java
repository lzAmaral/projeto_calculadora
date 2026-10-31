package calculadora;

/**
 * Calculadora de operações básicas com números inteiros.
 * @author Luiz Amaral
 * @version 2.0
 */
public class Calculadora {
    /** Cria a calculadora. */
    public Calculadora() {
    }

    /**
     * Calcula uma operação com dois inteiros. A divisão descarta a parte decimal.
     * @param a primeiro número
     * @param b segundo número
     * @param op operação: +, -, * ou /
     * @return resultado inteiro
     * @throws IllegalArgumentException se a operação for inválida ou o divisor for zero
     */
    public int calc(int a, int b, String op) {
        if (op == null) {
            throw new IllegalArgumentException("Operação inválida. Use +, -, * ou /.");
        }
        // Cada caso retorna o resultado da operação escolhida.
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return dividir(a, b);
            default:
                throw new IllegalArgumentException("Operação inválida. Use +, -, * ou /.");
        }
    }

    /**
     * Verifica o divisor e realiza a divisão inteira.
     * @param a número que será dividido
     * @param b divisor
     * @return quociente inteiro
     * @throws IllegalArgumentException se o divisor for zero
     */
    private int dividir(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Não é possível dividir por zero.");
        }
        return a / b;
    }
}
