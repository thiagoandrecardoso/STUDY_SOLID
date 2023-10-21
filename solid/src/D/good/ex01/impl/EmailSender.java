package D.good.ex01.impl;

import D.good.ex01.Invoice;
import D.good.ex01.interfaces.ActionAfterGeneratingNote;

public class EmailSender implements ActionAfterGeneratingNote {
    @Override
    public void executeAction(Invoice invoice) {
        // implementation
    }
}
