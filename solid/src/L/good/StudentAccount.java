package L.good;

public class StudentAccount {
    private final AccountManager accountManager;

    public StudentAccount() {
        accountManager = new AccountManager();
    }

    public void deposit(double value){
        accountManager.deposit(value);
    }

    // Não tem o método income()
}
