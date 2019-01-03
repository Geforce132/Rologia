package net.geforcemods.rologia.os.gui.screens.input;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.components.ScreenButton;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.images.ScreenImage;
import net.geforcemods.rologia.os.gui.components.text.ScreenText;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;

public class InputYesNoScreen extends Screen {
	
	private ScreenText prompt = new ScreenText(getOS(), null, GuiUtils.toHex(getOS().getTheme().INPUT_YES_NO_PROMPT));
	private ScreenButton yesButton = new ScreenButton(getOS(), "Yes");
	private ScreenButton noButton = new ScreenButton(getOS(), "No");

	private Screen screenToReturnTo;

	public InputYesNoScreen(RologiaOS os, Position pos, Screen returnScreen, String promptText) {
		super(os, pos);
		screenToReturnTo = returnScreen;
		prompt.setText(promptText);
	}

	@Override
	public void initializeScreen() {
		yesButton.centerPosition(-20, 30);
		addComponent(yesButton);
		
		noButton.centerPosition(20, 30);
		addComponent(noButton);
		
		prompt.centerPosition(0, -15 - (prompt.getHeight() / 4));
		addComponent(prompt);
	}
	
	@Override
	public void updateScreen() {

	}

	@Override
	public void onComponentClicked(ScreenComponent component, Position mousePos, int mouseButtonClicked) {
		if(component == yesButton) {
			getOS().getInputManager().addChoice(true);
			getOS().setScreen(screenToReturnTo);
		}
		else if(component == noButton) {
			getOS().getInputManager().addChoice(false);
			getOS().setScreen(screenToReturnTo);
		}
	}

	@Override
	public ScreenImage getBackgroundImage() {
		return new ScreenImage(getOS(), "rologia:textures/gui/watch/boot_screen_light.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);	
	}

}
