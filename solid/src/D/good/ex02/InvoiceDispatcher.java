package D.good.ex02;

public class InvoiceDispatcher {
    private InvoiceDao invoiceDao;
    private TaxCalculator taxCalculator;
    private InvoiceDelivery delivery;

    public InvoiceDispatcher(InvoiceDao invoiceDao, TaxCalculator taxCalculator, InvoiceDelivery delivery) {
        this.invoiceDao = invoiceDao;
        this.taxCalculator = taxCalculator;
        this.delivery = delivery;
    }

    public void process(Invoice invoice){
        double tax = taxCalculator.to(invoice);
        invoice.setTaxOn(tax);
        delivery.delivery(invoice);
        invoiceDao.persiste(invoice);
    }
}
