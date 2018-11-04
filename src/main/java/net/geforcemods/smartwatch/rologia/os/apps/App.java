package net.geforcemods.smartwatch.rologia.os.apps;

import com.google.gson.JsonObject;

import net.geforcemods.smartwatch.rologia.gui.components.ScreenComponent;
import net.geforcemods.smartwatch.rologia.gui.components.images.ScreenImage;
import net.geforcemods.smartwatch.rologia.gui.screens.Screen;
import net.geforcemods.smartwatch.rologia.os.Rologia;
import net.geforcemods.smartwatch.rologia.os.apps.events.AppEvent;
import net.geforcemods.smartwatch.rologia.os.apps.events.AppEventType;
import net.minecraft.util.ResourceLocation;

public abstract class App {

	private String app_id;
	private String app_name;
	private String app_version;
	private String app_icon = "";
	private String app_background_image = "";
	private ScreenComponent[] components;

	private transient ScreenImage appIcon = null;	
	private transient ScreenImage appBackgroundImage = null;

	protected transient Rologia os;
	
	public App(Rologia rologia) {
		os = rologia;
	}
	
	public abstract void updateApp();
	
	public abstract void drawApp(Screen currentScreen);
	
	public abstract void onEventPosted(AppEvent event);
	
	public abstract void loadInfoFromJson(JsonObject json);
	
	public abstract Object replaceKeywords(String keyword);
	
	public abstract AppEventType[] subscribeToEvents();
	
	public String getAppID() {
		return app_id;
	}
	
	public void setAppID(String app_id) {
		this.app_id = app_id;
	}

	public String getAppName() {
		return app_name;
	}

	public void setAppName(String app_name) {
		this.app_name = app_name;
	}

	public String getAppVersion() {
		return app_version;
	}
	
	public void setAppVersion(String app_version) {
		this.app_version = app_version;
	}

	public ScreenImage getAppIcon() {
		if(app_icon != null && !app_icon.isEmpty())
		{
			if(appIcon == null)
			{
				appIcon = new ScreenImage(os, new ResourceLocation(app_icon), 64, 64);
				return appIcon;
			}
		}

		return appIcon;
	}

	public void setAppIcon(String app_icon) {
		this.app_icon = app_icon;
	}

	public ScreenImage getAppBackgroundImage() {
		if(app_background_image != null && !app_background_image.isEmpty())
		{
			if(appBackgroundImage == null)
			{
				appBackgroundImage = new ScreenImage(os, new ResourceLocation(app_background_image), Screen.WATCH_SCREEN_X_SIZE, Screen.WATCH_SCREEN_Y_SIZE);
				return appBackgroundImage;
			}
		}

		return appBackgroundImage;
	}

	public void setAppBackgroundImage(String app_background_image) {
		this.app_background_image = app_background_image;
	}
	
	public ScreenComponent[] getComponents() {
		return components;
	}

	public void setComponents(ScreenComponent[] components) {
		this.components = components;
	}
	
	public void setOS(Rologia os) {
		this.os = os;
	}
	
}
