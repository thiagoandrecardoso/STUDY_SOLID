package S.good.impl;

import S.good.Employee;
import S.good.interfaces.CalcRule;

public class TenOrTwentyPercent implements CalcRule {
    @Override
    public double calc(Employee e) {
        if (e.getSalary() > 3000.0) {
            return e.getSalary() * 0.1;
        } else {
            return e.getSalary() * 0.2;
        }
    }
}
