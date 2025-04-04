package models;

import java.time.LocalDate;

public class Shippable extends Product {
    private double weight;

    public Shippable(String name, double price, int quantity,double weight) {
        super(name, price, quantity);
        this.weight=weight;
    }

    public double getWeight() {
        return weight;
    }
}


