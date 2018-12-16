package net.geforcemods.rologia.os.gui.screens;

import net.geforcemods.rologia.os.Rologia;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.images.ScreenImage;
import net.geforcemods.rologia.os.misc.Position;

public class HomeScreen extends Screen {

	public HomeScreen(Rologia OS, Position pos) {
		super(OS, pos);
	}

	@Override
	public void initializeScreen() {}

	@Override
	public void onComponentClicked(ScreenComponent component, int mouseX, int mouseY) {

	}

	@Override
	public ScreenImage getBackgroundImage() {
		return new ScreenImage(getOS(), "minewatch:textures/gui/watch/default.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);
	}

}
