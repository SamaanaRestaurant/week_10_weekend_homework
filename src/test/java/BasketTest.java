import basket.Basket;
import basket.Item;
import customers.Customer;
import discount.AboveTwentyDiscount;
import discount.BogofDiscount;
import discount.IDiscount;
import discount.LoyaltyCardDiscount;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BasketTest {

    Basket basket;
    Item item1;
    Item item2;
    Item item3;
    Item item4;
    ArrayList<IDiscount> discounts;
    Customer customer;

    @Before
    public void before() {
        customer = new Customer(true);
        discounts = new ArrayList<>();
        discounts.add(new BogofDiscount());
        discounts.add(new AboveTwentyDiscount());
        discounts.add(new LoyaltyCardDiscount());
        basket = new Basket(discounts, customer);
        item1 = new Item("Macbook Air", 900.0);
        item2 = new Item("BrewDog Punk IPA", 4.50);
        item3 = new Item("BrewDog Punk IPA", 4.50);
        item4 = new Item("BrewDog Punk IPA", 4.50);
    }

    @Test
    public void basketIsEmpty() {
        assertEquals(0, basket.getBasketSize());
    }

    @Test
    public void addItemToBasket() {
        basket.addItemToBasket(item1);
        assertEquals(1, basket.getBasketSize());
    }

    @Test
    public void removeItemFromBasket() {
        basket.addItemToBasket(item1);
        assertEquals(1, basket.getBasketSize());
        basket.removeItemFromBasket(item1);
        assertEquals(0, basket.getBasketSize());
    }

    @Test
    public void clearBasket() {
        basket.addItemToBasket(item1);
        basket.addItemToBasket(item2);
        assertEquals(2, basket.getBasketSize());
        basket.clearBasket();
        assertEquals(0, basket.getBasketSize());
    }

    @Test
    public void AboveTwentyDiscountApplies() {
        basket.addItemToBasket(item1);
        assertEquals(810, basket.getTotalValueOfBasket(), 0.01);
    }

    @Test
    public void loyaltyCardDiscountApplies() {
        basket.addItemToBasket(item1);
        assertEquals(793.8, basket.getTotalValueOfBasket(), 0.01);
    }

    @Test
    public void bogofDiscountApplies() {
        basket.addItemToBasket(item2);
        basket.addItemToBasket(item3);
        basket.addItemToBasket(item4);
        assertEquals(8.82, basket.getTotalValueOfBasket(), 0.01);
    }
}
