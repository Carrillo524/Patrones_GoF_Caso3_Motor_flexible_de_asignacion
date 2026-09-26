public class DescuentoRegional implements EstrategiaDescuento {
    @Override
    public double calcular(double valorCompra) {
        return valorCompra * 0.12;
    }
}