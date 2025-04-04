package models;

import java.time.LocalDate;

public class ShippableWithExpirationDate extends Product {
    boolean isExpire;
    private final double wight;

    public ShippableWithExpirationDate(String name, double price, int quantity,double wight, boolean isExpire) {
        super(name, price, quantity);
        this.isExpire=isExpire;
        this.wight=wight;
    }


    public boolean isExpire() {
        return isExpire;
    }

    public double getWight() {
        return wight;
    }
}

