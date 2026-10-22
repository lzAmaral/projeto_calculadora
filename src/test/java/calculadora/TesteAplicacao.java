package calculadora;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes funcionais da interface: recuperação de erros e encerramento normal.
 *
 * @author Luiz Amaral
 * @version 1.0
 */
public class TesteAplicacao {

    /** Cria a suíte de testes funcionais. */
    public TesteAplicacao() {
    }

    /**
     * Fornece sessões que provocam um erro e depois realizam uma soma válida.
     *
     * @return descrição, entrada de console e mensagem esperada
     */
    public static Stream<Arguments> sessoesComErro() {
        return Stream.of(
                Arguments.of("divisão por zero", "10\n0\n/\n2\n3\n+\nsair\n",
                        "Erro: Não é possível dividir por zero."),
                Arguments.of("operador inválido", "10\n5\n%\n2\n3\n+\nsair\n",
                        "Erro: Operação inválida: %. Use +, -, * ou /."),
                Arguments.of("primeiro número inválido", "abc\n2\n3\n+\nsair\n",
                        "Erro: digite um número inteiro"),
                Arguments.of("segundo número inválido", "10\nabc\n2\n3\n+\nsair\n",
                        "Erro: digite um número inteiro"),
                Arguments.of("inteiro fora do limite", "2147483648\n2\n3\n+\nsair\n",
                        "Erro: digite um número inteiro")
        );
    }

    /**
     * Verifica que um erro não impede uma operação posterior.
     *
     * @param cenario descrição do erro provocado
     * @param entrada linhas simuladas do usuário
     * @param mensagemEsperada trecho esperado na saída
     */
    @ParameterizedTest(name = "recupera após {0}")
    @MethodSource("sessoesComErro")
    public void deveContinuarDepoisDeErro(String cenario, String entrada, String mensagemEsperada) {
        String obtido = executarSessao(entrada);
        assertTrue(obtido.contains(mensagemEsperada), obtido);
        assertTrue(obtido.contains("Resultado: 5.0"), obtido);
        assertTrue(obtido.indexOf(mensagemEsperada) < obtido.indexOf("Resultado: 5.0"), obtido);
        assertTrue(obtido.contains("Calculadora encerrada."), obtido);
        System.out.printf("[OK] Cenário: %s | entrada: %s | esperado: erro tratado, soma 5.0 e saída normal"
                        + " | obtido: %s%n", cenario, entrada.replace("\n", " → "),
                obtido.replace(System.lineSeparator(), " | "));
    }

    /**
     * Confere saída solicitada ou fim de arquivo nas três etapas de leitura.
     *
     * @param entrada linhas simuladas até o encerramento
     */
    @ParameterizedTest(name = "encerra sem erro: [{0}]")
    @ValueSource(strings = {"sair\n", "2\nsair\n", "2\n3\nsair\n", "", "2\n", "2\n3\n"})
    public void deveEncerrarSemFalhas(String entrada) {
        String obtido = executarSessao(entrada);
        assertTrue(obtido.contains("Calculadora encerrada."), obtido);
        assertFalse(obtido.contains("Erro:"), obtido);
        assertFalse(obtido.contains("Resultado:"), obtido);
        System.out.printf("[OK] Entrada: [%s] + EOF | esperado: encerramento normal, sem cálculo"
                        + " | obtido: %s%n", entrada.replace("\n", " → "),
                obtido.replace(System.lineSeparator(), " | "));
    }

    /**
     * Executa a aplicação com entrada em memória e captura sua saída real.
     *
     * @param texto linhas enviadas à aplicação
     * @return saída produzida pela sessão
     */
    private String executarSessao(String texto) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (Scanner entrada = new Scanner(texto);
             PrintStream saida = new PrintStream(buffer, true, StandardCharsets.UTF_8)) {
            assertDoesNotThrow(() -> Aplicacao.executar(entrada, saida));
        }
        return buffer.toString(StandardCharsets.UTF_8);
    }
}
