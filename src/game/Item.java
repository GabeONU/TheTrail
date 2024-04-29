package game;

public class Item {
	
	public String name;
	public int price;
	private int weight;

	
	void setWeight(int lbs) {
		weight = lbs;
	}
	
	int getWeight() {
		return weight;
	}

}
