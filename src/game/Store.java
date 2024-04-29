package game;

import java.util.ArrayList;

public class Store {
	public ArrayList<Item> itemsStore = new ArrayList<Item>();
	
	public void purchase(Person Buyer, int item) {
		
		if(Buyer.money > itemsStore.get(item).price) {
			Buyer.setMoney(-1 * itemsStore.get(item).price);
			Buyer.items.add(itemsStore.get(item));
		}
		
	}


}
