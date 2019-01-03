package net.geforcemods.rologia.os.gui.screens;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.components.ScreenButton;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.images.ScreenImage;
import net.geforcemods.rologia.os.misc.Position;

public class SettingsScreen extends Screen {

	public SettingsScreen(RologiaOS os, Position pos) {
		super(os, pos);
	}

	@Override
	public void initializeScreen() {
		addComponent(new ScreenButton(getOS(), new Position(0, 0), "test"));
	}

	@Override
	public void onComponentClicked(ScreenComponent component, Position mousePos, int mouseButtonClicked) {
		getOS().getInputManager().requestDecision(this, "ohaidere");
	}

	@Override
	public ScreenImage getBackgroundImage() {
		return new ScreenImage(getOS(), "rologia:textures/gui/watch/boot_screen_dark.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);
	}

}
