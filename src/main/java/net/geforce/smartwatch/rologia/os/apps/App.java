package net.geforce.smartwatch.rologia.os.apps;

import net.geforce.smartwatch.rologia.gui.screens.Screen;
import net.geforce.smartwatch.rologia.os.Rologia;
import net.geforce.smartwatch.rologia.os.apps.events.AppEvent;
import net.geforce.smartwatch.rologia.os.apps.events.AppEventType;

public abstract class App {
	
	public abstract void updateApp(Rologia os);
	
	public abstract void drawApp(Rologia os, Screen currentScreen);
	
	public abstract void onEventPosted(AppEvent event);
	
	public abstract AppEventType[] subscribeToEvents();
	
	public abstract AppType getAppType();
	
	public abstract AppImage getAppImage();
	
	public abstract String getAppName();

}
