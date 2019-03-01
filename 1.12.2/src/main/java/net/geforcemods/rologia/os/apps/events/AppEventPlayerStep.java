package net.geforcemods.rologia.os.apps.events;

public class AppEventPlayerStep extends AppEvent {
	
	public final int previousStepCount;
	public final int newStepCount;
	
	public AppEventPlayerStep(int oldCount, int newCount) {
		previousStepCount = oldCount;
		newStepCount = newCount;
	}
	
	public AppEventType getEventType() {
		return AppEventType.PLAYER_STEP;
	}

}
