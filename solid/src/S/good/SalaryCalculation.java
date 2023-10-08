package S.good;

public class SalaryCalculation {
    public double calculation(Employee employee) {
        return employee.getOffice().getCalcRule().calc(employee);
    }
}
