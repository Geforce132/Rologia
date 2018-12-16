package net.geforcemods.rologia.os.gui.screens.input;

import net.geforcemods.rologia.os.Rologia;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.images.ScreenImage;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.misc.Position;

public class InputYesNoScreen extends Screen {

	public InputYesNoScreen(Rologia os, Position pos) {
		super(os, pos);
	}

	@Override
	public void initializeScreen() {}
	
	@Override
	public void updateScreen() {

	}

	@Override
	public void onComponentClicked(ScreenComponent component, int mouseX, int mouseY) {

	}

	@Override
	public ScreenImage getBackgroundImage() {
		return new ScreenImage(getOS(), "rologia:textures/gui/watch/boot_screen_light.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);	
	}

}
