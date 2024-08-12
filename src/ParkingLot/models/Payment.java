package ParkingLot.models;

import java.util.Date;

public class Payment extends BaseModel{
    private double amount;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
    /*
    // alreadt present in base class.
      private Date time;
     */
    private String referenceNo;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }



    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    /*
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

     */
}
