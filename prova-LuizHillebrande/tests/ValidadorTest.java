import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ValidadorTest {

    @Test
    void deveValidarCPFValido() {
        // válido com máscara
        assertTrue(Validador.validarCPF("529.982.247-25"));
        // válido sem máscara
        assertTrue(Validador.validarCPF("52998224725"));
    }
}
