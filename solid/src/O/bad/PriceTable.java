package O.bad;

public class PriceTable {
    public double discountTo(double value){
        if (value > 5000) return 0.03;
        if (value > 1000) return 0.05;
        return 0;
    }

    public double discountToSpecial(double value){
        // Aqui adicionar novos calculos.
        return 0.0;
    }
}
