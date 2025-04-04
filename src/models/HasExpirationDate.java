package models;

import java.time.LocalDate;

public class HasExpirationDate extends Product {
    private boolean isExpire;

    public HasExpirationDate(String name, double price, int quantity,boolean isExpire) {
        super(name, price, quantity);
        this.isExpire=isExpire;
    }

    public boolean isExpire() {
        return isExpire;
    }
}

