package net.geforcemods.rologia.os.stats;

import java.sql.Time;

public class UserStats {
	
	private Time timePlaying;
	private int stepCount;
	private int foodConsumptionCount;

	public void setTimePlaying(Time time) {
		timePlaying = time;
	}
	
	public Time getTimePlaying() {
		return timePlaying;
	}
	
	public void setStepCount(int steps) {
		stepCount = steps;
	}
	
	public int increaseStepCount(int numOfSteps) {
		return stepCount += numOfSteps;
	}
	
	public int getStepCount() {
		return stepCount;
	}
	
	public void setFoodConsumptionCount(int foodConsumption) {
		foodConsumptionCount = foodConsumption;
	}
	
	public int getFoodConsumptionCount() {
		return foodConsumptionCount;
	}

}
