package com.projectmain.mazebank1.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Account {
    private final StringProperty owner;
    private final StringProperty accountNumber;
    private final DoubleProperty balance;

    public Account(String Owner, String AccountNumber, Double Balance){
        this.owner = new SimpleStringProperty(this, "Owner", Owner);
        this.accountNumber = new SimpleStringProperty(this, "Account Number", AccountNumber);
        this.balance = new SimpleDoubleProperty(this, "Balance" , Balance);
    }

    public StringProperty ownerProperty(){
        return owner;
    }
    public StringProperty accountNumberProperty() {
        return accountNumber;
    }
    public DoubleProperty balanceProperty(){
        return balance;
    }

}
