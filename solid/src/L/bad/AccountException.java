package L.bad;

public class AccountException extends Exception {
    public AccountException(String message) {
        super(message);
    }

    public static AccountException accountDoesNotPayException() throws AccountException {
        throw new AccountException("Student account has no income.");
    }

    public static AccountException invalidValueException()throws AccountException {
        throw new AccountException("Invalid value!");
    }
}
