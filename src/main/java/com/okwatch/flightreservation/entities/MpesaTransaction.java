package com.okwatch.flightreservation.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MpesaTransaction {

    @Id
    private String checkoutRequestId;
    private Integer resultCode;
    private String resultDesc;

    public MpesaTransaction() {
    }

    public MpesaTransaction(String checkoutRequestId, Integer resultCode, String resultDesc) {
        this.checkoutRequestId = checkoutRequestId;
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
    }

    public String getCheckoutRequestId() {
        return checkoutRequestId;
    }

    public void setCheckoutRequestId(String checkoutRequestId) {
        this.checkoutRequestId = checkoutRequestId;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    @Override
    public String toString() {
        return "MpesaTransaction{" +
                "checkoutRequestId='" + checkoutRequestId + '\'' +
                ", resultCode=" + resultCode +
                ", resultDesc='" + resultDesc + '\'' +
                '}';
    }
}
