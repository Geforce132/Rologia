package net.geforcemods.rologia.os.gui.screens;

import java.util.ArrayList;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.apps.events.AppEventType;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.images.Image;
import net.geforcemods.rologia.os.gui.components.text.Text;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;

public class SelectionScreen extends Screen {

	public static final String NAME = "App selection";

	private ArrayList<String> appsDisplayed = new ArrayList<String>();

	public SelectionScreen(RologiaOS os, Position pos) {
		super(os, pos);
	}

	@Override
	public void initializeScreen() {
		for(int i = 0; i < getOS().getApps().size(); i++) {
			String appName = getOS().getApps().get(i).getScreenName();

			if(appsDisplayed.contains(appName)) continue;

			ScreenComponent text = new Text(getOS(), appName, GuiUtils.toHex(getOS().getTheme().SELECTION_TEXT));
			text.centerPosition(0, -35 + (20 * i));
			text.setHoverColor(GuiUtils.toHex(getOS().getTheme().SELECTION_TEXT_HOVERING));
			addComponent(text);

			appsDisplayed.add(appName);
		}
	}

	@Override
	public void onComponentClicked(ScreenComponent component, Position mousePos, int mouseButtonClicked) {
		if(component instanceof Text) {
			String text = ((Text) component).getText();
			
			if(appsDisplayed.contains(text))
				getOS().setScreen(text);
		}
			
	}

	@Override
	public String getScreenName() {
		return NAME;
	}

	@Override
	public Image getBackgroundImage() {
		return new Image(getOS(), "rologia:textures/gui/watch/boot_screen_dark.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);
	}

	@Override
	public AppEventType[] subscribeToEvents() {
		return null;
	}

}
