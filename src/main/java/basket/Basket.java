package basket;

import customers.Customer;
import discount.IDiscount;
import discount.LoyaltyCardDiscount;

import java.util.ArrayList;

/**
 * @author derkrisz
 */

public class Basket {

    private ArrayList<Item> items;
    private ArrayList<IDiscount> discounts;
    private Customer customer;

    public Basket(ArrayList<IDiscount> discounts, Customer customer) {
        this.items = new ArrayList<>();
        this.discounts = discounts;
        this.customer = customer;
    }

    public int getBasketSize() {
        return items.size();
    }

    public void addItemToBasket(Item item) {
        this.items.add(item);
    }

    public void removeItemFromBasket(Item item) {
        this.items.remove(item);
    }

    public void clearBasket() {
        this.items.clear();
    }

    public double getTotalValueOfBasket() {
        double totalValue = 0;
        for (Item item : items) {
            totalValue += item.getPrice();
        }
        for ( IDiscount discount : discounts) {
            if (discount instanceof LoyaltyCardDiscount) {
                if (customer.isLoyal()) { // if customer loyal apply discount
                    totalValue = discount.applyDiscount(items, totalValue);
                } else {
                    // do not apply discount
                }
            } else { // apply all other discounts
                totalValue = discount.applyDiscount(items, totalValue);
            }
        }
        return totalValue;
    }
}

