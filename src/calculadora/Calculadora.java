package calculadora;

/**
 * Calculadora de operações básicas com números inteiros.
 * @author Luiz Amaral
 * @version 2.0
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
        if (op == null) {
            throw new IllegalArgumentException("Operação inválida. Use +, -, * ou /.");
        }
        // O switch organiza a escolha da operação.
        switch (op) {
            case "+":
                r = a + b;
                break;
            case "-":
                r = a - b;
                break;
            case "*":
                r = a * b;
                break;
            case "/":
                r = dividir(a, b);
                break;
            default:
                throw new IllegalArgumentException("Operação inválida. Use +, -, * ou /.");
        }
        return r;
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
