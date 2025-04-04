package models;


public class CheckoutService {

    // Validate empty cart
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("Error: Cart is empty.");
            return;
        }

        double subtotal = cart.getSubtotal(); //  Sum of all items' prices
        double shippingFees = cart.getShippingFees(); //Shipping based on weight
        double totalAmount = subtotal + shippingFees;

        if (customer.getBalance() < totalAmount) {
            System.out.println("Error: Insufficient balance.");
            return;
        }

        // Print shipping details for items that require shipping
        if (!cart.getShippableItems().isEmpty()) {
            System.out.println("** Shipment notice **");
            double totalWeight = 0;
            for (Product product : cart.getShippableItems()) {
                double productWeight = 0;
                // validate if product shippable
                if (product instanceof Shippable) {
                    productWeight = ((Shippable) product).getWeight();
                } else if (product instanceof ShippableWithExpirationDate) {
                    productWeight = ((ShippableWithExpirationDate) product).getWight();
                }

                int quantity = cart.getQuantityOfProduct(product);
                System.out.println(quantity + "x " + product.getName() + " " + productWeight * quantity + "g");
                totalWeight += productWeight * quantity;
            }
            System.out.println("Total package weight: " + (totalWeight / 1000) + "kg"); // convert to kg
        }

        customer.deductBalance(totalAmount);
        System.out.println("---------------------- ");
        System.out.println("** Checkout receipt **");
        cart.printItems();
        System.out.println("---------------------- ");
        System.out.println("Subtotal: " + subtotal);
        System.out.println("Shipping: " + shippingFees);
        System.out.println("Amount: " + totalAmount);
        System.out.println("Customer balance after payment: " + customer.getBalance());

        System.out.println("END.");
    }
}
