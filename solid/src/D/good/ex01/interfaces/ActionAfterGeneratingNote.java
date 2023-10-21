package D.good.ex01.interfaces;

import D.good.ex01.Invoice;

public interface ActionAfterGeneratingNote {
    void executeAction(Invoice invoice);
}
