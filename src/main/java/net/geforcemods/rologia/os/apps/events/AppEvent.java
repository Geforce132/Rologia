package net.geforcemods.rologia.os.apps.events;

import net.geforcemods.rologia.os.RologiaOS;
import net.minecraft.entity.player.EntityPlayer;

public abstract class AppEvent {
	
	public final EntityPlayer player;
	
	public AppEvent(EntityPlayer entityPlayer) {
		player = entityPlayer;
	}
	
	public RologiaOS getOS() {
		return RologiaOS.getInstanceForPlayer(player);
	}
	
	public EntityPlayer getPlayer() {
		return player;
	}
	
	public abstract AppEventType getEventType();

}
