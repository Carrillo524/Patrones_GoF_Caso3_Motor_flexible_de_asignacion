public class DescuentoConvenio implements EstrategiaDescuento {

    @Override
    public double calcular(double valorCompra) {
        return valorCompra * 0.20;
    }
}