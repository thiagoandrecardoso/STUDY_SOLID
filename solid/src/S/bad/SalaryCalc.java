package S.bad;

public class SalaryCalc {
    public double calc(Employee employee) {
        if (Office.DEV.equals(employee.getOffice())) {
            return tenOrTwentyPercent(employee);
        }

        if (Office.DBA.equals(employee.getOffice()) || Office.TESTER.equals(employee.getOffice())) {
            return fifteenOrTwentyPercent(employee);
        }

        throw new RuntimeException("Invalid Employee");
    }

    public double tenOrTwentyPercent(Employee employee) {
        if (employee.getSalary() > 3000.0) {
            return employee.getSalary() * 0.1;
        } else {
            return employee.getSalary() * 0.2;
        }
    }

    public double fifteenOrTwentyPercent(Employee employee) {
        if (employee.getSalary() > 3000.0) {
            return employee.getSalary() * 0.15;
        } else {
            return employee.getSalary() * 0.20;
        }
    }
}
