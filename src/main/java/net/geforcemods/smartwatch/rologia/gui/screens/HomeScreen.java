package net.geforcemods.smartwatch.rologia.gui.screens;

import net.geforcemods.smartwatch.rologia.gui.components.ScreenComponent;
import net.geforcemods.smartwatch.rologia.gui.components.images.ScreenImage;
import net.geforcemods.smartwatch.rologia.os.Rologia;

public class HomeScreen extends Screen {

	public HomeScreen(Rologia OS, int x, int y) {
		super(OS, x, y);
	}

	@Override
	public void initializeScreen() {}

	@Override
	public void onComponentClicked(ScreenComponent component, int mouseX, int mouseY) {

	}

	@Override
	public ScreenImage getBackgroundImage() {
		return new ScreenImage(this, "minewatch:textures/gui/watch/default.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);
	}

}
