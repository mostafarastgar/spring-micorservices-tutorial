package com.mostafa.microservices.paymentproject.balancecheck;

public class PaymentOrder {
    private String txId;
    private String originatorBic;
    private String beneficiaryBic;
    private Double amount;
    private PaymentStatus status;
    private ValidationResult validationResult;

    public PaymentOrder() {
    }

    public PaymentOrder(String txId, String originatorBic, String beneficiaryBic, Double amount, PaymentStatus status, ValidationResult validationResult) {
        this.txId = txId;
        this.originatorBic = originatorBic;
        this.beneficiaryBic = beneficiaryBic;
        this.amount = amount;
        this.status = status;
        this.validationResult = validationResult;
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

    public ValidationResult getValidationResult() {
        return validationResult;
    }

    public void setValidationResult(ValidationResult validationResult) {
        this.validationResult = validationResult;
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
