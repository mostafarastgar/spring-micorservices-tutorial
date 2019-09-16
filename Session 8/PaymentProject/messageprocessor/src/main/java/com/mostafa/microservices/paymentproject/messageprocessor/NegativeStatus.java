package com.mostafa.microservices.paymentproject.messageprocessor;

public class NegativeStatus {
    private String txId;
    private String originalTxId;
    private String originatorBic;
    private String beneficiaryBic;

    public NegativeStatus() {
    }

    public NegativeStatus(String txId, String originalTxId, String originatorBic, String beneficiaryBic) {
        this.txId = txId;
        this.originalTxId = originalTxId;
        this.originatorBic = originatorBic;
        this.beneficiaryBic = beneficiaryBic;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getOriginalTxId() {
        return originalTxId;
    }

    public void setOriginalTxId(String originalTxId) {
        this.originalTxId = originalTxId;
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

    @Override
    public String toString() {
        return "NegativeStatus{" +
                "txId='" + txId + '\'' +
                ", originalTxId='" + originalTxId + '\'' +
                ", originatorBic='" + originatorBic + '\'' +
                ", beneficiaryBic='" + beneficiaryBic + '\'' +
                '}';
    }
}
