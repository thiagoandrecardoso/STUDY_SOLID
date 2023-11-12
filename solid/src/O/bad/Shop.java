package O.bad;

public class Shop {
    private double value;
    private String city;

    public Shop(double value, String city) {
        this.value = value;
        this.city = city;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
