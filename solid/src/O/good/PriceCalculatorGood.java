package O.good;

public class PriceCalculatorGood {
    private final IFedex iFedex;
    private final IPriceTable iPriceTable;

    public PriceCalculatorGood(IFedex iFedex, IPriceTable iPriceTable) {
        this.iFedex = iFedex;
        this.iPriceTable = iPriceTable;
    }

    // We no longer instantiate dependencies here, we just use them.
    public double calc(Shop shop){
        double discount = iPriceTable.discountTo(shop.getValue());
        double deliveryPrice = iFedex.to(shop.getCity());

        return shop.getValue() * (1 - discount) + deliveryPrice;
    }
}
