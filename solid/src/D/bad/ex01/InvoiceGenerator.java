package D.bad.ex01;

public class InvoiceGenerator {
    private final EmailSender emailSender;
    private final InvoiceDao invoiceDto;

    public InvoiceGenerator(EmailSender emailSender, InvoiceDao invoiceDto) {
        this.emailSender = emailSender;
        this.invoiceDto = invoiceDto;
    }

    public Invoice generator(Bill bill){
        double value = bill.getValueMonthly();
        Invoice invoice = new Invoice(
                value,
                simpleTaxOn(value)
        );

        emailSender.sender(invoice);
        invoiceDto.persiste(invoice);
        return invoice;
    }

    private double simpleTaxOn(double value) {
        return value * 0.06;
    }
}
