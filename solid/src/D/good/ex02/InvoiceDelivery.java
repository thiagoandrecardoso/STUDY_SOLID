package D.good.ex02;

public class InvoiceDelivery {
    private DeliveryLaw deliveryLaw;
    private PostOffice postOffice;

    public InvoiceDelivery(DeliveryLaw deliveryLaw, PostOffice postOffice) {
        this.deliveryLaw = deliveryLaw;
        this.postOffice = postOffice;
    }

    public void delivery(Invoice invoice){
        if (deliveryLaw.urgenteDeliveryRequired(invoice)){
            postOffice.sendBySedex10(invoice);
        }else {
            postOffice.sendBySedexRegular(invoice);
        }
    }
}
