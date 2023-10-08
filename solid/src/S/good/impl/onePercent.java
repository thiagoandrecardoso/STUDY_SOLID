package S.good.impl;

import S.good.Employee;
import S.good.interfaces.CalcRule;

public class onePercent implements CalcRule {
    @Override
    public double calc(Employee e) {
        return e.getSalary() * 0.01;
    }
}
