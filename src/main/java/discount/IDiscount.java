package discount;

import basket.Item;

import java.util.ArrayList;

public interface IDiscount {

    double applyDiscount(ArrayList<Item> items, double total);
}
