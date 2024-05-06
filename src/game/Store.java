package game;

import java.util.ArrayList;
import java.util.List;

public class Store {
    public List<Item> itemsStore = new ArrayList<>(); // Change the access level to public

    public Store() {
        // Initialize the store with some items
        itemsStore.add(new Item("Item 1", 10, 1, 2.5));
        itemsStore.add(new Item("Item 2", 20, 2, 5.0));
    }

    public Item[] getItems() {
        return itemsStore.toArray(new Item[0]);
    }

    public void purchase(Person buyer, int itemIndex, int quantity) {
        if (itemIndex >= 0 && itemIndex < itemsStore.size()) {
            Item itemToBuy = itemsStore.get(itemIndex);
            int totalPrice = itemToBuy.getPrice() * quantity;
            if (buyer.getMoney() >= totalPrice) {
                buyer.setMoney(-totalPrice);
                buyer.items.add(new Item(itemToBuy.getName(), itemToBuy.getPrice(), itemToBuy.getWeight(), itemToBuy.getPricePerPound()));
                System.out.println("Purchase successful: " + itemToBuy.getName() + " x" + quantity);
            } else {
                System.out.println("Not enough money to buy: " + itemToBuy.getName() + " x" + quantity);
            }
        } else {
            System.out.println("Invalid item index: " + itemIndex);
        }
    }
}
