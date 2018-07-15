package net.geforce.smartwatch.rologia.os.apps;

import net.geforce.smartwatch.rologia.gui.screens.Screen;
import net.geforce.smartwatch.rologia.os.Rologia;
import net.geforce.smartwatch.rologia.os.apps.events.AppEvent;
import net.geforce.smartwatch.rologia.os.apps.events.AppEventType;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class AppStepCounter extends App {
	
	public void drawApp(Rologia os, Screen currentScreen) {
		currentScreen.getFontRenderer().drawString(os.getUserStats().getStepCount() + "", 10, 10, 55555);
	}
	
	public void updateApp(Rologia os) {
		//if(os.getUserStats().g)
	}
	
	public void onEventPosted(AppEvent event) {
		Rologia os = event.getOS();

		System.out.println(os.getUserStats().getStepCount() + " | " + FMLCommonHandler.instance().getSide());
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
	public String getAppName() {
		return "Step Counter";
	}

}
