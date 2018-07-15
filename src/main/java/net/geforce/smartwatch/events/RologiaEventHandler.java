package net.geforce.smartwatch.events;

import net.geforce.smartwatch.MineWatch;
import net.geforce.smartwatch.rologia.os.Rologia;
import net.geforce.smartwatch.rologia.os.apps.events.AppEventPlayerStep;
import net.geforce.smartwatch.rologia.os.stats.UserStats;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RologiaEventHandler {

	@SubscribeEvent
	public void onSoundPlayed(PlaySoundAtEntityEvent event) {
		if(event.getEntity() == null) return;

		EntityPlayer player = Minecraft.getMinecraft().player;
		
		if(event.getEntity() == player && event.getSound().getSoundName().getResourcePath().startsWith("step.")) {
			Rologia os = Rologia.getInstanceForPlayer(player);
			UserStats stats = os.getUserStats();
			
			System.out.println("running");
			MineWatch.instance.postRologiaEvent(new AppEventPlayerStep(Minecraft.getMinecraft().player, stats.getStepCount(), stats.increaseStepCount(1)));
		}
	}
}
