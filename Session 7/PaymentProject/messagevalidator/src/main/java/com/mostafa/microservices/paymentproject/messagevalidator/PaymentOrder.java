package com.mostafa.microservices.paymentproject.messagevalidator;

public class PaymentOrder {
    private String txId;
    private String originatorBic;
    private String beneficiaryBic;
    private Double amount;
    private PaymentStatus status;

    public PaymentOrder() {
    }

    public PaymentOrder(String txId, String originatorBic, String beneficiaryBic, Double amount, PaymentStatus status) {
        this.txId = txId;
        this.originatorBic = originatorBic;
        this.beneficiaryBic = beneficiaryBic;
        this.amount = amount;
        this.status = status;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getOriginatorBic() {
        return originatorBic;
    }

    public void setOriginatorBic(String originatorBic) {
        this.originatorBic = originatorBic;
    }

    public String getBeneficiaryBic() {
        return beneficiaryBic;
    }

    public void setBeneficiaryBic(String beneficiaryBic) {
        this.beneficiaryBic = beneficiaryBic;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PaymentOrder{" +
                "txId='" + txId + '\'' +
                ", originatorBic='" + originatorBic + '\'' +
                ", beneficiaryBic='" + beneficiaryBic + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}
