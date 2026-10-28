package calculadora;

/**
 * Calculadora de operações básicas com números inteiros.
 * @author Luiz Amaral
 * @version 1.0
 */
public class Calculadora {
    /** Guarda o resultado do último cálculo válido. */
    private int r;

    /** Cria a calculadora com resultado inicial igual a zero. */
    public Calculadora() {
        r = 0;
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
        if ("+".equals(op)) {
            r = a + b;
        } else if ("-".equals(op)) {
            r = a - b;
        } else if ("*".equals(op)) {
            r = a * b;
        } else if ("/".equals(op)) {
            // Verifica o divisor antes de fazer a divisão.
            if (b == 0) {
                throw new IllegalArgumentException("Não é possível dividir por zero.");
            }
            r = a / b;
        } else {
            throw new IllegalArgumentException("Operação inválida. Use +, -, * ou /.");
        }
        return r;
    }
}
