package game;

import java.util.Arrays;
import java.util.List;

public class Wagon {
	
	private int wagonWeight = 0;
	
	private int numOx = 1; 
	
	public int speed = 12;

<<<<<<< HEAD
	public int food = 0;
=======
	
	public int speed_Slow = 15;
	public int speed_Fast = 17;
	public int speed_grueling = 20;

	int currentIndex = 0;
	
	List<Integer> speeds = Arrays.asList(speed_Slow, speed_Fast, speed_grueling);

>>>>>>> a6012e55d831b11b92ed9889d869f8994fb239f7
	
	
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

	int IncreasePace(int increase) {
		speed =+ increase;
		return 0;
	}

	int decreasePace(int Decrease) {
		speed =- Decrease;
		return 0;
	}

}
