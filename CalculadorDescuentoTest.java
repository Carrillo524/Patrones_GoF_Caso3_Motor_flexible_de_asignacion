import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class CalculadorDescuentoTest {

    private CalculadorDescuento calculador;

    @BeforeEach
    void setUp() {
        calculador = new CalculadorDescuento();
    }



    @Test
    @DisplayName("Debe aplicar 10% de descuento usando DescuentoFrecuente")
    void testCalcularDescuentoFrecuente() {
        calculador.setEstrategia(new DescuentoFrecuente());
        assertEquals(100.0, calculador.calcular(1000.0), 0.001);
    }

    @Test
    @DisplayName("Debe aplicar 15% de descuento usando DescuentoTemporadaBaja")
    void testCalcularDescuentoTemporadaBaja() {
        calculador.setEstrategia(new DescuentoTemporadaBaja());
        assertEquals(150.0, calculador.calcular(1000.0), 0.001);
    }

    @Test
    @DisplayName("Debe aplicar 20% de descuento usando DescuentoConvenio")
    void testCalcularDescuentoConvenio() {
        calculador.setEstrategia(new DescuentoConvenio());
        assertEquals(200.0, calculador.calcular(1000.0), 0.001);
    }



    @Test
    @DisplayName("Debe aplicar 25% de descuento por Campaña de Aniversario")
    void testCalcularDescuentoAniversario() {
        calculador.setEstrategia(new DescuentoAniversario());
        assertEquals(250.0, calculador.calcular(1000.0), 0.001);
    }

    @Test
    @DisplayName("Debe aplicar 12% de descuento por Promoción Regional")
    void testCalcularDescuentoRegional() {
        calculador.setEstrategia(new DescuentoRegional());
        assertEquals(120.0, calculador.calcular(1000.0), 0.001);
    }

    @Test
    @DisplayName("Debe aplicar 5% de descuento por Municipio")
    void testCalcularDescuentoMunicipio() {
        calculador.setEstrategia(new DescuentoMunicipio());
        assertEquals(50.0, calculador.calcular(1000.0), 0.001);
    }

    @Test
    @DisplayName("Debe aplicar 30% de descuento por Promoción Temporal")
    void testCalcularDescuentoTemporal() {
        calculador.setEstrategia(new DescuentoTemporal());
        assertEquals(300.0, calculador.calcular(1000.0), 0.001);
    }

    @Test
    @DisplayName("Debe aplicar 18% de descuento por Caja de Compensación")
    void testCalcularDescuentoCajaCompensacion() {
        calculador.setEstrategia(new DescuentoCajaCompensacion());
        assertEquals(180.0, calculador.calcular(1000.0), 0.001);
    }


    @Test
    @DisplayName("Debe permitir reemplazar la estrategia dinámicamente en tiempo de ejecución")
    void testReemplazarEstrategiaDinamica() {
        // Inicia con Aniversario
        calculador.setEstrategia(new DescuentoAniversario());
        assertEquals(250.0, calculador.calcular(1000.0), 0.001);

        // Cambia a Regional sobre la misma instancia del calculador
        calculador.setEstrategia(new DescuentoRegional());
        assertEquals(120.0, calculador.calcular(1000.0), 0.001);
    }
}