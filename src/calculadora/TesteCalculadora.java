package calculadora;

/**
 * Executa testes simples no console, comparando resultado esperado e obtido.
 * @author Luiz Amaral
 * @version 1.0
 */
public class TesteCalculadora {
    /** Cria a classe de testes. */
    public TesteCalculadora() {
    }

    /**
     * Testa as operações e confirma que o programa continua após erros.
     * @param args argumentos da execução, não utilizados
     */
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        testar(calculadora, 2, 3, "+", 5);
        testar(calculadora, 10, 4, "-", 6);
        testar(calculadora, 3, 5, "*", 15);
        testar(calculadora, 8, 2, "/", 4);
        testar(calculadora, 7, 2, "/", 3);
        testarErro(calculadora, 8, 0, "/", "Não é possível dividir por zero.");
        testarErro(calculadora, 5, 5, "x", "Operação inválida. Use +, -, * ou /.");
        // Este teste mostra que os erros anteriores não encerraram o programa.
        testar(calculadora, 4, 2, "+", 6);
        System.out.println("Todos os 8 testes passaram.");
    }

    /**
     * Compara um cálculo com o valor esperado.
     * @param calculadora objeto que será testado
     * @param a primeiro número
     * @param b segundo número
     * @param op símbolo da operação
     * @param esperado resultado correto
     * @throws AssertionError se o resultado for diferente do esperado
     */
    private static void testar(Calculadora calculadora, int a, int b, String op, int esperado) {
        int obtido = calculadora.calc(a, b, op);
        System.out.println(a + " " + op + " " + b
                + " | Esperado: " + esperado + " | Obtido: " + obtido);
        if (esperado != obtido) {
            throw new AssertionError("Resultado diferente do esperado.");
        }
    }

    /**
     * Confirma a mensagem de uma operação que deve gerar erro.
     * @param calculadora objeto que será testado
     * @param a primeiro número
     * @param b segundo número
     * @param op símbolo da operação
     * @param mensagem mensagem de erro esperada
     * @throws AssertionError se não houver erro ou a mensagem for diferente
     */
    private static void testarErro(Calculadora calculadora, int a, int b, String op, String mensagem) {
        try {
            calculadora.calc(a, b, op);
            throw new AssertionError("Era esperado um erro.");
        } catch (IllegalArgumentException e) {
            System.out.println(a + " " + op + " " + b
                    + " | Esperado: " + mensagem + " | Obtido: " + e.getMessage());
            if (!mensagem.equals(e.getMessage())) {
                throw new AssertionError("Mensagem diferente da esperada.");
            }
        }
    }
}
