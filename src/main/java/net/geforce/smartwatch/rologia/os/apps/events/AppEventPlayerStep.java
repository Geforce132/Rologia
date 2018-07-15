package net.geforce.smartwatch.rologia.os.apps.events;

import net.minecraft.entity.player.EntityPlayer;

public class AppEventPlayerStep extends AppEvent {
	
	public final int previousStepCount;
	public final int newStepCount;
	
	public AppEventPlayerStep(EntityPlayer entityPlayer, int oldCount, int newCount) {
		super(entityPlayer);
		previousStepCount = oldCount;
		newStepCount = newCount;
	}
	
	public AppEventType getEventType() {
		return AppEventType.PLAYER_STEP;
	}

}
