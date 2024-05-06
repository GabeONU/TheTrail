package game;

public class Wagon {
	
	private int wagonWeight = 0;
	
	private int numOx = 1; 
	
	public int speed = 12;

	public int food = 0;
	
	
	void setWagonWeight(int lbs) {
		wagonWeight = wagonWeight + lbs;
	}
	
	int getWagonWeight() {
		return wagonWeight;
	}
	
	void setOxNumber(int toAdd) {
		numOx += toAdd;
	}
	
	int getNumOx() {
		return numOx;
	}

	int changePace(int change) {
		speed =+ change;


		return 0;
	}

}
