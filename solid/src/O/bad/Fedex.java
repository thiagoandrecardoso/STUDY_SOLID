package O.bad;

public class Fedex {
    public double to(String city){
        if ("San Francisco".equals(city)){
            return 15;
        } else {
            return 30;
        }
    }

    public double toWithRules(String city, int rule){
        if(rule == 1){
            if ("San Francisco".equals(city)){
                return 15;
            } else {
                return 30;
            }
        }
        if (rule == 2){
            if ("San Francisco".equals(city)){
                return 30;
            } else {
                return 15;
            }
        }
        else {
            return 0;
        }
    }
}
