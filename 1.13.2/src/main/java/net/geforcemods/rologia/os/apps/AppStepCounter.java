package net.geforcemods.rologia.os.apps;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.apps.events.AppEvent;
import net.geforcemods.rologia.os.apps.events.AppEventType;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.loading.FMLLoader;

@AppInfo(id="step_counter", name = "Step Counter", version = "1.0.0")
public class AppStepCounter extends App {
	
	public AppStepCounter(RologiaOS rologia) {
		super(rologia);
	}

	@Override
	public void initializeApp() {
	}

	@Override
	public void drawApp(Screen currentScreen) {
	}
	
	@Override
	public void updateApp() {
	}
	
	@Override
	public void onEventPosted(AppEvent event) {
		System.out.println(getDistanceInMiles(event.getPlayer()) + " | " + FMLLoader.getDist());
	}
	
	@Override
	public Object replaceKeywords(String keyword) {
		if(keyword.matches("step_count"))
			return getOS().getUserStats().getStepCount();
		
		if(keyword.matches("distance_walked"))
			return getDistanceInMiles(getOS().getUser());

		return "unknown";
	}
	
	private float getDistanceInMiles(EntityPlayer player) {
		float feet = (player.distanceWalkedModified * 3);

		DecimalFormat df = new DecimalFormat("###.##");
		df.setRoundingMode(RoundingMode.DOWN);

		return Float.parseFloat(df.format(feet / 5280F));
	}

	@Override
	public AppEventType[] subscribeToEvents() {
		return new AppEventType[] {AppEventType.PLAYER_STEP};
	}

}
