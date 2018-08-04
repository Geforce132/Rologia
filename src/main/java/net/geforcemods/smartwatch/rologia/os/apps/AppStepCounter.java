package net.geforcemods.smartwatch.rologia.os.apps;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.google.gson.JsonObject;

import net.geforcemods.smartwatch.rologia.gui.screens.Screen;
import net.geforcemods.smartwatch.rologia.os.Rologia;
import net.geforcemods.smartwatch.rologia.os.apps.events.AppEvent;
import net.geforcemods.smartwatch.rologia.os.apps.events.AppEventType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class AppStepCounter extends App {
	
	public AppStepCounter(Rologia rologia) {
		super(rologia);
	}

	public void drawApp(Screen currentScreen) {
		currentScreen.getFontRenderer().drawString("Step count: " + os.getUserStats().getStepCount(), 180, 80, 55555);
		currentScreen.getFontRenderer().drawString("Distance walked: " + getDistanceInMiles(os.getUser()), 180, 100, 55555);
	}
	
	@Override
	public void updateApp() {
	}
	
	@Override
	public void onEventPosted(AppEvent event) {
		System.out.println(getDistanceInMiles(event.getPlayer()) + " | " + FMLCommonHandler.instance().getSide());
	}
	
	@Override
	public void loadInfoFromJson(JsonObject json) {}
	
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

	@Override
	public AppType getAppType() {
		return AppType.PREINSTALLED;
	}

	@Override
	public String getAppID() {
		return "step_counter";
	}

}
