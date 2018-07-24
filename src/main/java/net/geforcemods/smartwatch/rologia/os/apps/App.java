package net.geforcemods.smartwatch.rologia.os.apps;

import com.google.gson.JsonObject;

import net.geforcemods.smartwatch.rologia.gui.components.images.ScreenImage;
import net.geforcemods.smartwatch.rologia.gui.screens.Screen;
import net.geforcemods.smartwatch.rologia.os.Rologia;
import net.geforcemods.smartwatch.rologia.os.apps.events.AppEvent;
import net.geforcemods.smartwatch.rologia.os.apps.events.AppEventType;
import net.minecraft.util.ResourceLocation;

public abstract class App {

	protected Rologia os;

	private String appName;
	private String appVersion;

	private ScreenImage appIcon = null;
	protected String appIconPath;

	private ScreenImage appBackgroundImage = null;
	protected String appBackgroundImagePath = "";

	public App(Rologia rologia) {
		os = rologia;
	}

	public abstract void updateApp();
	
	public abstract void drawApp(Screen currentScreen);
	
	public abstract void onEventPosted(AppEvent event);
	
	public abstract void loadInfoFromJson(JsonObject json);
	
	public abstract AppEventType[] subscribeToEvents();
	
	public abstract AppType getAppType();
	
	public abstract String getAppID();
	
	public void setBaseAppInformation(JsonObject json) {
		appName = json.get("app_name").getAsString();
		appVersion = json.get("version").getAsString();

		if(json.has("app_icon"))
			appIconPath = json.get("app_icon").getAsString();

		if(json.has("app_background_image"))
			appBackgroundImagePath = json.get("app_background_image").getAsString();
	}
	
	public String getAppName() {
		return appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public ScreenImage getAppBackgroundImage() {
		if(!appBackgroundImagePath.isEmpty())
		{
			if(appBackgroundImage == null)
			{
				appBackgroundImage = new ScreenImage(os.getCurrentScreen(), new ResourceLocation(appBackgroundImagePath), Screen.WATCH_SCREEN_X_SIZE, Screen.WATCH_SCREEN_Y_SIZE);
				return appBackgroundImage;
			}
		}

		return appBackgroundImage;
	}

	public ScreenImage getAppIcon() {
		if(!appIconPath.isEmpty())
		{
			if(appIcon == null)
			{
				appIcon = new ScreenImage(os.getCurrentScreen(), new ResourceLocation(appIconPath), 64, 64);
				return appIcon;
			}
		}

		return appIcon;
	}

}
