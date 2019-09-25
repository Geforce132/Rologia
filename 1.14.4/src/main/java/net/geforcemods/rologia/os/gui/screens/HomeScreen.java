package net.geforcemods.rologia.os.gui.screens;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.images.Image;
import net.geforcemods.rologia.os.misc.Position;

public class HomeScreen extends Screen {

	public static final String NAME = "Home";

	public HomeScreen(RologiaOS OS, Position pos) {
		super(OS, pos);
	}

	@Override
	public void initializeScreen() {}

	@Override
	public void onComponentClicked(ScreenComponent component, Position mousePos, int mouseButtonClicked) {

	}

	@Override
	public String getScreenName() {
		return NAME;
	}

	@Override
	public Image getBackgroundImage() {
		return new Image(getOS(), "rologia:textures/gui/watch/boot_screen_light.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);
	}

}
