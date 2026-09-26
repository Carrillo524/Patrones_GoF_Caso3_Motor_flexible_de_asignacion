import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        double valorCompra = 1000.0;
        double resultado = calculador.calcular(valorCompra);

        System.out.printf(
                "DescuentoFrecuente | Compra: %.1f | Descuento: %.1f%n",
                valorCompra,
                resultado
        );

        assertEquals(100.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Debe aplicar 15% de descuento usando DescuentoTemporadaBaja")
    void testCalcularDescuentoTemporadaBaja() {
        calculador.setEstrategia(new DescuentoTemporadaBaja());

        double valorCompra = 1000.0;
        double resultado = calculador.calcular(valorCompra);

        System.out.printf(
                "DescuentoTemporadaBaja | Compra: %.1f | Descuento: %.1f%n",
                valorCompra,
                resultado
        );

        assertEquals(150.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Debe aplicar 20% de descuento usando DescuentoConvenio")
    void testCalcularDescuentoConvenio() {
        calculador.setEstrategia(new DescuentoConvenio());

        double valorCompra = 1000.0;
        double resultado = calculador.calcular(valorCompra);

        System.out.printf(
                "DescuentoConvenio | Compra: %.1f | Descuento: %.1f%n",
                valorCompra,
                resultado
        );

        assertEquals(200.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Debe lanzar una excepción cuando no se configura una estrategia")
    void testCalcularSinEstrategia() {
        double valorCompra = 1000.0;

        IllegalStateException excepcion = assertThrows(
                IllegalStateException.class,
                () -> calculador.calcular(valorCompra)
        );

        System.out.println(
                "Validación sin estrategia: " + excepcion.getMessage()
        );

        assertEquals(
                "Debe configurar una estrategia antes de calcular",
                excepcion.getMessage()
        );
    }

    @Test
    @DisplayName("Debe permitir reemplazar la estrategia durante la ejecución")
    void testCambiarEstrategiaDeDescuento() {
        double valorCompra = 1000.0;

        calculador.setEstrategia(new DescuentoFrecuente());
        double descuentoFrecuente = calculador.calcular(valorCompra);

        calculador.setEstrategia(new DescuentoConvenio());
        double descuentoConvenio = calculador.calcular(valorCompra);

        System.out.printf(
                "Cambio de estrategia | Compra: %.1f | Frecuente: %.1f | Convenio: %.1f%n",
                valorCompra,
                descuentoFrecuente,
                descuentoConvenio
        );

        assertEquals(100.0, descuentoFrecuente, 0.001);
        assertEquals(200.0, descuentoConvenio, 0.001);
    }
}