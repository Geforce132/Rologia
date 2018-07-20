package net.geforcemods.smartwatch.rologia.os.apps;

import com.google.gson.JsonObject;

import net.geforcemods.smartwatch.rologia.gui.screens.Screen;
import net.geforcemods.smartwatch.rologia.os.Rologia;
import net.geforcemods.smartwatch.rologia.os.apps.events.AppEvent;
import net.geforcemods.smartwatch.rologia.os.apps.events.AppEventType;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class AppStepCounter extends App {
	
	public void drawApp(Rologia os, Screen currentScreen) {
		currentScreen.getFontRenderer().drawString(os.getUserStats().getStepCount() + "", 10, 10, 55555);
	}
	
	@Override
	public void updateApp(Rologia os) {

	}
	
	@Override
	public void onEventPosted(Rologia os, AppEvent event) {
		System.out.println(os.getUserStats().getStepCount() + " | " + FMLCommonHandler.instance().getSide());
	}
	
	@Override
	public void loadInfoFromJson(JsonObject json) {
		System.out.println(json.get("app_name").getAsString());
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
	public AppImage getAppImage() {
		return null;
	}

	@Override
	public String getAppID() {
		return "step_counter";
	}

}
