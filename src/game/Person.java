package game;

import java.util.ArrayList;

public class Person {
	
	public String name;
	public int age;
	public int money;
	public int health;
	public String relation;
	public ArrayList<Item> items = new ArrayList<Item>();
    public Person(String string, int i) {
        //TODO Auto-generated constructor stub
    }
    public void setMoney(int change) {
		money = money + change;
	}
	public int getMoney() {
		return money;
	}
	
	public int FoodComp()
	{
	
	return 0;
	}
}
