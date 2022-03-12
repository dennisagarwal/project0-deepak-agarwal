package com.revature.model;

import java.util.Objects;

public class ClientAccount {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private int acId;
    private String acType;
    private String acNumber;
    private int acClientId;
    private int acBalance;

    public ClientAccount(){

    }

    public ClientAccount(int id, String firstName, String lastName, int age, int acId, String acType, String acNumber, int acClientId, int acBalance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.acId = acId;
        this.acType = acType;
        this.acNumber = acNumber;
        this.acClientId = acClientId;
        this.acBalance = acBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        ClientAccount that = (ClientAccount) o;
        return id == that.id && age == that.age && acId == that.acId && acClientId == that.acClientId && acBalance == that.acBalance && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(acType, that.acType) && Objects.equals(acNumber, that.acNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, acId, acType, acNumber, acClientId, acBalance);
    }

    @Override
    public String toString() {
        return "ClientAccount{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", acId=" + acId +
                ", acType='" + acType + '\'' +
                ", acNumber='" + acNumber + '\'' +
                ", acClientId=" + acClientId +
                ", acBalance=" + acBalance +
                '}';
    }
}
