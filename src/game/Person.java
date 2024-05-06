package game;

import java.util.ArrayList;

public class Person {
	
	public String name;
	public int age;
	public int money = 500;
	public int health;
	public String relation;
	
	public ArrayList<Item> items = new ArrayList<Item>();
<<<<<<< HEAD
    public Person(String string, int i) {
        //TODO Auto-generated constructor stub
    }
    public void setMoney(int change) {
=======
	
	public void setMoney(int change) {
>>>>>>> d84822596674684fd9155f883891ff86990aba0c
		money = money + change;
	}
	public int getMoney() {
		return money;
	}

}
