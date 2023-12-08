package L.bad;

import java.util.ArrayList;
import java.util.List;

public class InvestmentProcessor {
    public static void main(String[] args) throws AccountException {
        for (CommonAccount account : bankAccount()){
            account.income();

            System.out.println("New Balance");
            System.out.println(account.getBalance());
        }
    }

    public static List<CommonAccount> bankAccount(){
        List<CommonAccount> commonAccountList = new ArrayList<>();
        // Fill in the list here...
        return commonAccountList;
    }
}
