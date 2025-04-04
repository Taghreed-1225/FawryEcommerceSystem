package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {

    private Map<Product, Integer> items = new HashMap<>();
    public Cart() {
        this.items = new HashMap<>();
    }

    public void add(Product product, int quantity) {
        // Check if the total quantity (current in cart + new) exceeds available stock
        int currentQuantityInCart = items.getOrDefault(product, 0);
        int totalRequestedQuantity = currentQuantityInCart + quantity;

        if (totalRequestedQuantity > product.getQuantity()) {
            System.out.println("Error: You are ordering a quantity larger than what is in stock. Total quantity in cart (" +
                    currentQuantityInCart + ") + new quantity (" + quantity + ") exceeds available stock of " + product.getQuantity());
            return;
        }

        // Check if product has expiration date and if it's expired
        if (product instanceof HasExpirationDate) {
            HasExpirationDate expirableProduct = (HasExpirationDate) product;
            if (expirableProduct.isExpire()) {
                System.out.println("Error: The product " + product.getName() + " has expired and cannot be added to the cart.");
                return;
            }
        } else if (product instanceof ShippableWithExpirationDate) {
            ShippableWithExpirationDate expirableProduct = (ShippableWithExpirationDate) product;
            if (expirableProduct.isExpire()) {
                System.out.println("Error: The product " + product.getName() + " has expired and cannot be added to the cart.");
                return;
            }
        }

        items.put(product, totalRequestedQuantity);
    }




    // Calculate subtotal
    public double getSubtotal() {
        double subtotal = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            subtotal += entry.getKey().getPrice() * entry.getValue();
        }
        return subtotal;
    }


    // Calculate shipping fees based on weight , assume 1000g=30Egp
    public double getShippingFees() {
        double shipping = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            if (product instanceof Shippable || product instanceof ShippableWithExpirationDate) {
                // If product is shippable, add weight
                double weight = (product instanceof Shippable) ? ((Shippable) product).getWeight() :
                        ((ShippableWithExpirationDate) product).getWight();
                shipping += (weight * entry.getValue()) / 1000.0;  // Convert weight to kilograms
            }
        }
        // If shipping is zero, return 0
        if (shipping <= 0) {
            return 0;
        }
        // Calculate shipping fees based on weight
        double shippingUnits = Math.round(shipping);
        return shippingUnits * 30;
    }

    // Print all items in the cart
    public void printItems() {
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            System.out.println(entry.getValue() + "x " + entry.getKey().getName() + " " + (entry.getKey().getPrice())*(entry.getValue()) + " EGP");
        }
    }

    // Get shippable items
    public List<Product> getShippableItems() {
        List<Product> shippableItems = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            if (entry.getKey() instanceof Shippable || entry.getKey() instanceof ShippableWithExpirationDate) {
                shippableItems.add(entry.getKey());
            }
        }
        return shippableItems;
    }

    // Get quantity of a specific product
    public int getQuantityOfProduct(Product product) {
        return items.getOrDefault(product, 0);
    }

    // Check if cart is empty

    public boolean isEmpty() {
        return items.isEmpty();
    }
    }






