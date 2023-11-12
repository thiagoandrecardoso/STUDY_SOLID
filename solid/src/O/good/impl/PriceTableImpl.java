package O.good.impl;

import O.good.IPriceTable;

public class PriceTableImpl implements IPriceTable {
    public double discountTo(double value){
        if (value > 5000) return 0.03;
        if (value > 1000) return 0.05;
        return 0;
    }
}
