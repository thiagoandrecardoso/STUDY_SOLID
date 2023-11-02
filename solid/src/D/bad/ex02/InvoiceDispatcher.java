package D.bad.ex02;

public class InvoiceDispatcher {
    private InvoiceDao invoiceDao;
    private TaxCalculator taxCalculator;
    private DeliveryLaw deliveryLaw;
    private PostOffice postOffice;

    public InvoiceDispatcher(InvoiceDao invoiceDao, TaxCalculator taxCalculator, DeliveryLaw deliveryLaw, PostOffice postOffice) {
        this.invoiceDao = invoiceDao;
        this.taxCalculator = taxCalculator;
        this.deliveryLaw = deliveryLaw;
        this.postOffice = postOffice;
    }

    public void process(Invoice invoice){
        double tax = taxCalculator.to(invoice);
        invoice.setTaxOn(tax);

        if (deliveryLaw.urgenteDeliveryRequired(invoice)){
            postOffice.sendBySedex10(invoice);
        } else {
            postOffice.sendBySedexRegular(invoice);
        }
        invoiceDao.persiste(invoice);
    }
}
