package net.geforcemods.rologia.os.gui.screens.input;

import net.geforcemods.rologia.os.Rologia;
import net.geforcemods.rologia.os.gui.components.ScreenButton;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.images.ScreenImage;
import net.geforcemods.rologia.os.gui.components.text.ScreenText;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.misc.Position;

public class InputYesNoScreen extends Screen {
	
	private ScreenText prompt = new ScreenText(getOS(), "Do you want to delete all of your diamonds?", 55555);
	private ScreenButton yesButton = new ScreenButton(getOS(), "Yes");
	private ScreenButton noButton = new ScreenButton(getOS(), "No");

	public InputYesNoScreen(Rologia os, Position pos) {
		super(os, pos);
	}

	@Override
	public void initializeScreen() {
		yesButton.centerPosition(-20, 30);
		addComponent(yesButton);
		
		noButton.centerPosition(20, 30);
		addComponent(noButton);
		
		prompt.centerPosition(0, -30);
		addComponent(prompt);
	}
	
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
