package game;

import java.util.ArrayList;

public class Person {
	
	public String name;
	public int age;
	public int money = 500;
	public int health;
	public String relation;
	
	public ArrayList<Item> items = new ArrayList<Item>();
	
	public void setMoney(int change) {
		money = money + change;
	}
	public int getMoney() {
		return money;
	}

}
