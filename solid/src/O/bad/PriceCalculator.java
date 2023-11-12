package O.bad;

public class PriceCalculator {

    public double calc(Shop shop){
        PriceTable priceTable = new PriceTable();
        Fedex fedex = new Fedex();

        double discount = priceTable.discountTo(shop.getValue());
        double delivery = fedex.to(shop.getCity());

        return shop.getValue() * (1 - discount) + delivery;
    }

    public double calcWithRules(Shop shop, int rule){
        Fedex fedex = new Fedex();
        double discount = 0;
        if (rule == 1){
            PriceTable priceTable = new PriceTable();
            discount = priceTable.discountTo(shop.getValue());
        }
        if (rule == 2){
            PriceTable priceTable = new PriceTable();
            discount = priceTable.discountToSpecial(shop.getValue());
        }
        double delivery = fedex.to(shop.getCity());
        return shop.getValue() * (1 - discount) + delivery;
    }
}
