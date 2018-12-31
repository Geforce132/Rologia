package net.geforcemods.rologia.os.gui.screens;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.apps.App;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.images.ScreenImage;
import net.geforcemods.rologia.os.misc.Position;

/**
 * A blank Screen that Apps can overlay on. Not used yet, however.
 */
public class AppScreen extends Screen {
	
	private App app;

	public AppScreen(RologiaOS OS, Position pos, App app) {
		super(OS, pos);
	}

	@Override
	public void initializeScreen() {
		getOS().setApp(app);
	}

	@Override
	public void onComponentClicked(ScreenComponent component, Position mousePos, int mouseButtonClicked) {}

	@Override
	public ScreenImage getBackgroundImage() {
		return new ScreenImage(getOS(), "rologia:textures/gui/watch/boot_screen_dark.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);
	}

}
