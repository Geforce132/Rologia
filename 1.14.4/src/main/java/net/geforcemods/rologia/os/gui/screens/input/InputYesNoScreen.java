package net.geforcemods.rologia.os.gui.screens.input;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.components.Button;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.images.Image;
import net.geforcemods.rologia.os.gui.components.text.Text;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;

public class InputYesNoScreen extends Screen {

	public static final String NAME = "input_decision";

	private Text prompt = new Text(getOS(), null, GuiUtils.toHex(getOS().getTheme().INPUT_YES_NO_PROMPT));
	private Button yesButton = new Button(getOS(), "Yes");
	private Button noButton = new Button(getOS(), "No");

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
			getOS().setScreen(screenToReturnTo.getScreenName());
		}
		else if(component == noButton) {
			getOS().getInputManager().addChoice(false);
			getOS().setScreen(screenToReturnTo.getScreenName());
		}
	}

	@Override
	public String getScreenName() {
		return NAME + "_" + screenToReturnTo.getScreenName();
	}

	@Override
	public Image getBackgroundImage() {
		return new Image(getOS(), "rologia:textures/gui/watch/boot_screen_light.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);	
	}

}
