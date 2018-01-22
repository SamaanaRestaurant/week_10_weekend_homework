package discount;

import basket.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class BogofDiscount implements IDiscount {

    @Override
    public double applyDiscount(ArrayList<Item> items, double total) {
        HashMap<String, Integer> itemsMap = new HashMap<String, Integer>();
        for (Item item : items) {
            if (itemsMap.containsKey(item.getName())) {
                Integer currentOccurrence = itemsMap.get(item.getName());
                itemsMap.put(item.getName(), currentOccurrence + 1);
            } else {
                itemsMap.put(item.getName(), 1);
            }
        }
        double newTotal = 0;
        for (String name : itemsMap.keySet()) {
            Integer occurrence = itemsMap.get(name);
            double itemPrice = getItemPrice(items, name);
            if (occurrence % 2 == 0) {
                newTotal += itemPrice * (occurrence / 2);
            } else {
                newTotal += itemPrice * ((occurrence -1) /2) + itemPrice;
            }
        }
        return newTotal;
    }

    private double getItemPrice(ArrayList<Item> items, String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return item.getPrice();
            }
        }
        return 0; //in case the item with name is not in the collection of items
    }
}
