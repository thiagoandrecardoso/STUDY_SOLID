package L.bad;

public class CommonAccount {
    protected double balance;

    public CommonAccount() {
        balance = 0;
    }

    public void deposit(double value) throws AccountException {
        if (value <= 0){ // Pré-condição.
            throw AccountException.invalidValueException();
        }
        this.balance += value;
    }

    public void income() throws AccountException {
        this.balance *= 1.1;
    }

    public double getBalance() {
        return balance;
    }
}
