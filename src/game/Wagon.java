package game;

public class Wagon {
	
	private int wagonWeight = 0;
	
	private int numOx = 1; 
	
	
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

}
