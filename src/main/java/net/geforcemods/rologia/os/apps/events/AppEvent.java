package net.geforcemods.rologia.os.apps.events;

import net.geforcemods.rologia.os.RologiaOS;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;

public abstract class AppEvent {
	
	public RologiaOS getOS() {
		return RologiaOS.getInstance();
	}
	
	public PlayerEntity getPlayer() {
		return Minecraft.getInstance().player;
	}
	
	public abstract AppEventType getEventType();

}
