public class CalculadorDescuento {

    private EstrategiaDescuento estrategia;

    public void setEstrategia(EstrategiaDescuento estrategia) {
        this.estrategia = estrategia;
    }

    public double calcular(double valorCompra) {
        if (estrategia == null) {
            throw new IllegalStateException(
                    "Debe configurar una estrategia antes de calcular"
            );
        }

        return estrategia.calcular(valorCompra);
    }
}