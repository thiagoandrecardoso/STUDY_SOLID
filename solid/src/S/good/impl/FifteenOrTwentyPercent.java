package S.good.impl;

import S.good.Employee;
import S.good.interfaces.CalcRule;

public class FifteenOrTwentyPercent implements CalcRule {
    @Override
    public double calc(Employee e) {
        if (e.getSalary() > 3000.0) {
            return e.getSalary() * 0.15;
        } else {
            return e.getSalary() * 0.20;
        }
    }
}
