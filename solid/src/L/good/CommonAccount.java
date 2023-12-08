package L.good;

public class CommonAccount {
    private final AccountManager accountManager;

    public CommonAccount() {
        accountManager = new AccountManager();
    }

    public void deposit(double value){
        accountManager.deposit(value);
    }

    public void income(double value){
        // Faz uso do método icome()
        accountManager.income(value);
    }
}
