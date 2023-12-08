package L.bad;

public class StudentAccount extends CommonAccount{
    public void income() throws AccountException {
        throw AccountException.accountDoesNotPayException();
    }
}
