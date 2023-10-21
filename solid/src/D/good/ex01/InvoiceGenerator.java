package D.good.ex01;
import D.good.ex01.interfaces.ActionAfterGeneratingNote;
import java.util.List;

public class InvoiceGenerator {
    private final List<ActionAfterGeneratingNote> actions;

    public InvoiceGenerator(List<ActionAfterGeneratingNote> actions) {
        this.actions = actions;
    }

    public Invoice generating(Bill bill){
        double value = bill.getValueMonthly();
        Invoice invoice = new Invoice(
                value,
                simpleTaxOn(value)
        );

        for (ActionAfterGeneratingNote action : actions) {
            action.executeAction(invoice);
        }
        return invoice;
    }

    private double simpleTaxOn(double value) {
        return value * 0.06;
    }
}
