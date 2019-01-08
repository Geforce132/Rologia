package net.geforcemods.rologia.os.apps.im;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.apps.App;
import net.geforcemods.rologia.os.apps.AppInfo;
import net.geforcemods.rologia.os.apps.events.AppEventType;
import net.geforcemods.rologia.os.gui.components.text.ScreenText;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;

@AppInfo(id=AppIM.ID, name = AppIM.NAME, version = AppIM.VERSION)
public class AppIM extends App {
	
	public static final String ID = "im";
	public static final String NAME = "IM Chat";
	public static final String VERSION = "1.0.0";

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
	public Object replaceKeywords(String keyword) {
		return null;
	}

	@Override
	public AppEventType[] subscribeToEvents() {
		return new AppEventType[] {AppEventType.RECEIVE_MESSAGES};
	}

}
