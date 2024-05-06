package game;

import java.util.ArrayList;
import java.util.List;

public class Trade {
	//dependacy injection
	public void trade(Person tradee, Person trader, Item itemAway, Item itemIn) {

		if (tradee.items.contains(itemAway) && trader.items.contains(itemIn)) {
            tradee.items.add(itemIn);
            trader.items.add(itemAway);
            tradee.items.remove(itemAway);
            trader.items.remove(itemIn);
            System.out.println("Trade successful!");
        } else {
            System.out.println("Trade failed. One or both items not found.");
        }
		System.out.println("Tradee: " + tradee.items.get(0).getName() + " Trader: " + trader.items.get(0).name);
	}
}
