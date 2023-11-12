package O.good.impl;

import O.good.IFedex;

public class FedexSpecialImpl implements IFedex {
    public double to(String city){
        if ("San Francisco".equals(city)){
            return 30;
        } else {
            return 50;
        }
    }
}
