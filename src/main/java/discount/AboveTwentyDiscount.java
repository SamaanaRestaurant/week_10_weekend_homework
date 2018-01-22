package discount;

import basket.Item;

import java.util.ArrayList;

public class AboveTwentyDiscount implements IDiscount {

    @Override
    public double applyDiscount(ArrayList<Item> items, double total) {
        if (total > 20) {
           total = total * 0.9;
        }
        return total;
    }
}
