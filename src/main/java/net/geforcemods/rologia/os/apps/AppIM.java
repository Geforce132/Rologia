package net.geforcemods.rologia.os.apps;

import com.google.gson.JsonObject;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.apps.events.AppEvent;
import net.geforcemods.rologia.os.apps.events.AppEventType;
import net.geforcemods.rologia.os.gui.components.text.ScreenText;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;

@AppInfo(id="im", name = "Instant Messaging", version = "1.0.0")
public class AppIM extends App {
	
	private ScreenText text = new ScreenText(getOS(), "this is a test", 44444);

	public AppIM(RologiaOS rologia) {
		super(rologia);
	}
	
	@Override
	public void initializeApp() {
		text.centerPosition();
		addComponent(text);
	}

	@Override
	public void updateApp() {

	}

	@Override
	public void drawApp(Screen currentScreen) {
		GuiUtils.drawFilledRect(currentScreen.getPosition(), Screen.WATCH_SCREEN_X_SIZE, Screen.WATCH_SCREEN_Y_SIZE, getOS().getTheme().IM_BACKGROUND);
	}

	@Override
	public void onEventPosted(AppEvent event) {
	}

	@Override
	public void loadInfoFromJson(JsonObject json) {
	}

	@Override
	public Object replaceKeywords(String keyword) {
		return null;
	}

	@Override
	public AppEventType[] subscribeToEvents() {
		return null;
	}

}
