package O.good.impl;

import O.good.IFedex;

public class FedexImpl implements IFedex {
    public double to(String city){
        if ("San Francisco".equals(city)){
            return 15;
        } else {
            return 30;
        }
    }
}
