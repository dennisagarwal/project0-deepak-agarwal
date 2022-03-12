package com.revature.model;

import java.util.Objects;

public class Account {

    private int acId;
    private String acType;
    private String acNumber;
    private int acClientId;
    private int acBalance;

    public Account(){

    }

    public Account(int acId, String acType, String acNumber, int acClientId, int acBalance) {
        this.acId = acId;
        this.acType = acType;
        this.acNumber = acNumber;
        this.acClientId = acClientId;
        this.acBalance = acBalance;
    }

    public int getAcId() {
        return acId;
    }

    public void setAcId(int acId) {
        this.acId = acId;
    }

    public String getAcType() {
        return acType;
    }

    public void setAcType(String acType) {
        this.acType = acType;
    }

    public String getAcNumber() {
        return acNumber;
    }

    public void setAcNumber(String acNumber) {
        this.acNumber = acNumber;
    }

    public int getAcClientId() {
        return acClientId;
    }

    public void setAcClientId(int acClientId) {
        this.acClientId = acClientId;
    }

    public int getAcBalance() {
        return acBalance;
    }

    public void setAcBalance(int acBalance) {
        this.acBalance = acBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return acId == account.acId && acClientId == account.acClientId && acBalance == account.acBalance && Objects.equals(acType, account.acType) && Objects.equals(acNumber, account.acNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acId, acType, acNumber, acClientId, acBalance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "acId=" + acId +
                ", acType='" + acType + '\'' +
                ", acNumber='" + acNumber + '\'' +
                ", acClientId=" + acClientId +
                ", acBalance=" + acBalance +
                '}';
    }
}
