package game;

public class Wagon {
	
	private int wagonWeight = 0;
	
	private int numOx = 1; 
	
	public int speed = 12;
	
	
	void setWagonWeight(int lbs) {
		wagonWeight = wagonWeight + lbs;
	}
	
	int getWagonWeight() {
		return wagonWeight;
	}
	
	void setOxNumber(int oxNum) {
		numOx = numOx + oxNum;
	}
	
	int getNumOx() {
		return numOx;
	}

	int changePace(int change) {
		speed =+ change;


		return 0;
	}

}
