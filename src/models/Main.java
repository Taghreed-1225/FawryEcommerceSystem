package models;


public class Main {


    public static void main(String[] args) {
        Product cheese = new ShippableWithExpirationDate("Cheese", 100, 10, 200, false);  // Shippable product with expiration date (expired)
        Product tv = new Shippable("TV", 200, 5, 7000);// Shippable product without expiration date
        Product scratchCard = new Product("Scratch Card", 50, 100); // // Non-shippable product without expiration date
        Product biscuits = new ShippableWithExpirationDate("Biscuits", 150, 20, 700, false);

        Product freshCheese = new ShippableWithExpirationDate("Fresh Cheese", 120, 10, 200, true);
        Product expiredBiscuits = new ShippableWithExpirationDate("Old Biscuits", 75, 15, 700, true);

        // Create customer
        Customer customer = new Customer("Taghreed", 5000);

        // Create cart
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);

        // Checkout
        CheckoutService.checkout(customer, cart);


    }
}




