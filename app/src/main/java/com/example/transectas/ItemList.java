package com.example.transectas;

import java.util.Date;

public class ItemList {

    private String number;
    private final Date dateCreation;

    public ItemList(String number, Date dateCreation) {
        this.number = number;
        this.dateCreation = dateCreation;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDateCreation() {
        return dateCreation;
    }
}
