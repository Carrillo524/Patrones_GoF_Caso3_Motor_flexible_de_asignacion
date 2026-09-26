public class DescuentoAniversario implements EstrategiaDescuento {
    @Override
    public double calcular(double valorCompra) {
        return valorCompra * 0.25;
    }
}