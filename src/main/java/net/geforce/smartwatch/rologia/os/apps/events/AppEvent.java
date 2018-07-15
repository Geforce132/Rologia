package net.geforce.smartwatch.rologia.os.apps.events;

import net.geforce.smartwatch.rologia.os.Rologia;
import net.minecraft.entity.player.EntityPlayer;

public abstract class AppEvent {
	
	public final EntityPlayer player;
	
	public AppEvent(EntityPlayer entityPlayer) {
		player = entityPlayer;
	}
	
	public Rologia getOS() {
		return Rologia.getInstanceForPlayer(player);
	}
	
	public EntityPlayer getPlayer() {
		return player;
	}
	
	public abstract AppEventType getEventType();

}
