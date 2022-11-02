package com.prisma.servicepaymentbackend.models;

public class PaymentRequest {
    String amount;
    String transaction_date;
    String payment_method;
    String merchant_name;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) { this.amount = amount; }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) { this.payment_method = payment_method; }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String transaction_date) {
        this.merchant_name = merchant_name;
    }

}
