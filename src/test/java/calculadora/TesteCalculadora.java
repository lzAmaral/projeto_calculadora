package calculadora;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Verifica operações, limites de inteiros e mensagens de erro da calculadora.
 * Cada caso informa entradas, resultado esperado e resultado efetivamente obtido.
 *
 * @author Luiz Amaral
 * @version 1.0
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TesteCalculadora {

    /** Instância sem estado usada pelos casos de teste. */
    private final Calculadora calculadora = new Calculadora();

    /** Tolerância para comparar resultados em ponto flutuante. */
    private static final double TOLERANCIA = 1e-9;

    /** Cria a suíte de testes. */
    public TesteCalculadora() {
    }

    /**
     * Confere as quatro operações, valores negativos, zero e limites de int.
     *
     * @param primeiroNumero primeiro operando
     * @param segundoNumero segundo operando
     * @param operador operação solicitada
     * @param esperado valor de referência independente da implementação
     */
    @Order(1)
    @ParameterizedTest(name = "{0} {2} {1} = {3}")
    @CsvSource({
        "10, 5, +, 15", "-10, 5, +, -5", "0, 0, +, 0", "2147483647, 1, +, 2147483648",
        "10, 5, -, 5", "5, 10, -, -5", "-2147483648, 1, -, -2147483649", "0, 0, -, 0",
        "4, 5, *, 20", "-3, 4, *, -12", "0, 8, *, 0", "50000, 50000, *, 2500000000",
        "10, 2, /, 5", "7, 2, /, 3.5", "-7, 2, /, -3.5", "0, 9, /, 0",
        "-2147483648, -1, /, 2147483648", "1, 3, /, 0.3333333333333333"
    })
    public void deveCalcularOperacoes(int primeiroNumero, int segundoNumero,
                                     String operador, double esperado) {
        double obtido = calculadora.calc(primeiroNumero, segundoNumero, operador);
        assertEquals(esperado, obtido, TOLERANCIA);
        System.out.printf("[OK] Entrada: %d %s %d | esperado: %s | obtido: %s%n",
                primeiroNumero, operador, segundoNumero, esperado, obtido);
    }

    /**
     * Confere que qualquer dividendo com divisor zero é rejeitado.
     *
     * @param dividendo número a dividir por zero
     */
    @Order(2)
    @ParameterizedTest(name = "{0} / 0 deve falhar de forma controlada")
    @ValueSource(ints = {10, 0, -10})
    public void deveImpedirDivisaoPorZero(int dividendo) {
        ArithmeticException obtido = assertThrows(ArithmeticException.class,
                () -> calculadora.calc(dividendo, 0, "/"));
        String esperado = "Não é possível dividir por zero.";
        assertEquals(esperado, obtido.getMessage());
        System.out.printf("[OK] Entrada: %d / 0 | esperado: ArithmeticException (%s)"
                        + " | obtido: %s (%s)%n", dividendo, esperado,
                obtido.getClass().getSimpleName(), obtido.getMessage());
    }

    /**
     * Confere operadores desconhecidos, vazios e nulos.
     *
     * @param operador valor inválido fornecido pelo chamador
     */
    @Order(3)
    @ParameterizedTest(name = "operador inválido: [{0}]")
    @NullAndEmptySource
    @ValueSource(strings = {"%", "potencia", " ", " + "})
    public void deveRejeitarOperacoesInvalidas(String operador) {
        IllegalArgumentException obtido = assertThrows(IllegalArgumentException.class,
                () -> calculadora.calc(10, 5, operador));
        String esperado = "Operação inválida: " + operador + ". Use +, -, * ou /.";
        assertEquals(esperado, obtido.getMessage());
        System.out.printf("[OK] Entrada: calc(10, 5, [%s]) | esperado: IllegalArgumentException (%s)"
                        + " | obtido: %s (%s)%n", operador, esperado,
                obtido.getClass().getSimpleName(), obtido.getMessage());
    }
}
