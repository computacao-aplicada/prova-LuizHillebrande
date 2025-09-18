import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ValidadorTest {

    @Test
    void deveValidarCPFValido() {
        // válido com máscara
        assertTrue(Validador.validarCPF("529.982.247-25"));
        // válido sem máscara
        assertTrue(Validador.validarCPF("52998224725"));
    }

    @Test
    void deveRejeitarEntradasInvalidas() {
        //
        assertFalse(Validador.validarCPF(null));
        assertFalse(Validador.validarCPF(""));
        assertFalse(Validador.validarCPF("529.982.247-2X"));
        assertFalse(Validador.validarCPF("00000000000"));
    }

}
