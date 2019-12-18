package net.geforcemods.rologia.os.apps.events;

import net.geforcemods.rologia.os.RologiaOS;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public abstract class AppEvent {
	
	public RologiaOS getOS() {
		return RologiaOS.getInstance();
	}
	
	public EntityPlayer getPlayer() {
		return Minecraft.getMinecraft().player;
	}
	
	public abstract AppEventType getEventType();

}
