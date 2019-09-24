package net.geforcemods.rologia.os.gui.screens;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.apps.App;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.images.Image;
import net.geforcemods.rologia.os.misc.Position;

/**
 * A blank Screen that Apps can overlay on. Not used yet, however.
 */
public class AppScreen extends Screen {

	private App app;

	public AppScreen(RologiaOS OS, Position pos, App app) {
		super(OS, pos, app);
		this.app = app;
	}

	@Override
	public void initializeScreen() {
		
	}

	@Override
	public void onComponentClicked(ScreenComponent component, Position mousePos, int mouseButtonClicked) {
		app.onComponentClicked(component, mousePos, mouseButtonClicked);
	}

	public App getApp() {
		return app;
	}

	@Override
	public String getScreenName() {
		return app.getAppName();
	}

	@Override
	public Image getBackgroundImage() {
		return new Image(getOS(), app.app_background_image, WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);
	}

}
