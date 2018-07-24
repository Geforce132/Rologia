package net.geforcemods.smartwatch.rologia.os.apps;

import com.google.gson.JsonObject;

import net.geforcemods.smartwatch.rologia.gui.screens.Screen;
import net.geforcemods.smartwatch.rologia.os.Rologia;
import net.geforcemods.smartwatch.rologia.os.apps.events.AppEvent;
import net.geforcemods.smartwatch.rologia.os.apps.events.AppEventType;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class AppStepCounter extends App {
	
	public AppStepCounter(Rologia rologia) {
		super(rologia);
	}

	public void drawApp(Screen currentScreen) {
		currentScreen.getFontRenderer().drawString("Step count: " + os.getUserStats().getStepCount(), 180, 80, 55555);
	}
	
	@Override
	public void updateApp() {
	}
	
	@Override
	public void onEventPosted(AppEvent event) {
		System.out.println(os.getUserStats().getStepCount() + " | " + FMLCommonHandler.instance().getSide());
	}
	
	@Override
	public void loadInfoFromJson(JsonObject json) {}
	
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
