package S.good;

import S.good.impl.FifteenOrTwentyPercent;
import S.good.impl.TenOrTwentyPercent;
import S.good.impl.onePercent;
import S.good.interfaces.CalcRule;

public enum Office {
    DEV(new TenOrTwentyPercent()),
    DBA(new onePercent()),
    TESTER(new FifteenOrTwentyPercent());

    private final CalcRule calcRule;

    Office(CalcRule CalcRule) {
        this.calcRule = CalcRule;
    }

    public CalcRule getCalcRule() {
        return calcRule;
    }
}
