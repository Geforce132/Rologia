package net.geforcemods.smartwatch.rologia.os.apps;

import com.google.gson.JsonObject;

import net.geforcemods.smartwatch.rologia.gui.screens.Screen;
import net.geforcemods.smartwatch.rologia.os.Rologia;
import net.geforcemods.smartwatch.rologia.os.apps.events.AppEvent;
import net.geforcemods.smartwatch.rologia.os.apps.events.AppEventType;
import net.minecraft.util.ResourceLocation;

public abstract class App {
	
	private String appName;
	private String appVersion;
	
	private AppImage appImage;
	
	public abstract void updateApp(Rologia os);
	
	public abstract void drawApp(Rologia os, Screen currentScreen);
	
	public abstract void onEventPosted(Rologia os, AppEvent event);
	
	public abstract void loadInfoFromJson(JsonObject json);
	
	public abstract AppEventType[] subscribeToEvents();
	
	public abstract AppType getAppType();
	
	public abstract AppImage getAppImage();
	
	public abstract String getAppID();
	
	public void setAppInfo(Rologia os, JsonObject json) {
		appName = json.get("app_name").getAsString();
		appVersion = json.get("version").getAsString();
		appImage = new AppImage(os, new ResourceLocation(json.get("app_image").getAsString()));
	}
	
	public String getAppName() {
		return appName;
	}

}
