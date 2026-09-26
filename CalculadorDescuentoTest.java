import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class CalculadorDescuentoTest {

    private CalculadorDescuento calculador;

    @BeforeEach
    void setUp() {
        // Inicializamos la clase antes de cada prueba
        calculador = new CalculadorDescuento();
    }

    @Test
    @DisplayName("Debe aplicar 10% de descuento para cliente FRECUENTE")
    void testCalcularDescuentoFrecuente() {
        // Dado un valor de compra de 1000, el 10% es 100
        double resultado = calculador.calcular("FRECUENTE", 1000.0);
        assertEquals(100.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Debe aplicar 15% de descuento para TEMPORADA_BAJA")
    void testCalcularDescuentoTemporadaBaja() {
        // Dado un valor de compra de 1000, el 15% es 150
        double resultado = calculador.calcular("TEMPORADA_BAJA", 1000.0);
        assertEquals(150.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Debe aplicar 20% de descuento para CONVENIO")
    void testCalcularDescuentoConvenio() {
        // Dado un valor de compra de 1000, el 20% es 200
        double resultado = calculador.calcular("CONVENIO", 1000.0);
        assertEquals(200.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Debe retornar 0 para un tipo de descuento no registrado")
    void testCalcularSinDescuento() {
        // Cualquier string diferente a los evaluados en los if-else debe retornar 0
        double resultado = calculador.calcular("CUALQUIER_OTRO", 1000.0);
        assertEquals(0.0, resultado, 0.001);
    }
}