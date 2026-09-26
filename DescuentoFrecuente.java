public class DescuentoFrecuente implements EstrategiaDescuento {

    @Override
    public double calcular(double valorCompra) {
        return valorCompra * 0.10;
    }
}