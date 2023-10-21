package D.good.ex01;

public class Invoice {
    private double value;
    private double taxOn;
    public Invoice(double value, double taxOn) {
        this.taxOn = taxOn;
        this.value = value;
    }
}
