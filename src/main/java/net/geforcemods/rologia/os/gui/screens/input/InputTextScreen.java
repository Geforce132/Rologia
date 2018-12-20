package net.geforcemods.rologia.os.gui.screens.input;

import net.geforcemods.rologia.os.Rologia;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.ScreenTextField;
import net.geforcemods.rologia.os.gui.components.images.ScreenImage;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.misc.Position;

public class InputTextScreen extends Screen {
	
	private ScreenTextField textField = new ScreenTextField(getOS(), 50, 12);

	public InputTextScreen(Rologia os, Position pos) {
		super(os, pos);
	}

	@Override
	public void initializeScreen() {
		textField.centerPosition();
		addComponent(textField);
	}

	@Override
	public void onComponentClicked(ScreenComponent component, Position mousePos, int mouseButtonClicked) {

	}

	@Override
	public ScreenImage getBackgroundImage() {
		return new ScreenImage(getOS(), "rologia:textures/gui/watch/boot_screen_dark.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);	
	}

}
