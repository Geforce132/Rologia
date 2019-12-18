package net.geforcemods.rologia.os.gui.screens.input;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.components.Button;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.images.Image;
import net.geforcemods.rologia.os.gui.components.text.Text;
import net.geforcemods.rologia.os.gui.components.text.TextField;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;

public class InputTextScreen extends Screen {
	
	public static final String NAME = "input_text";

	private Text prompt = new Text(getOS(), null, GuiUtils.toHex(getOS().getTheme().INPUT_TEXT_PROMPT));
	private TextField textField = new TextField(getOS(), 50, 12);
	private Button doneButton = new Button(getOS(), "Done");

	private Screen screenToReturnTo;

	public InputTextScreen(RologiaOS os, Position pos, Screen returnScreen, String promptText) {
		super(os, pos);
		screenToReturnTo = returnScreen;
		prompt.setText(promptText);
	}

	@Override
	public void initializeScreen() {
		textField.centerPosition(0, (prompt.getHeight() / 4));
		addComponent(textField);

		prompt.centerPosition(0, -15 - (prompt.getHeight() / 4));
		addComponent(prompt);

		doneButton.centerPosition(0, 45);
		addComponent(doneButton);
	}

	@Override
	public void onComponentClicked(ScreenComponent component, Position mousePos, int mouseButtonClicked) {		
		if(component == doneButton) {
			getOS().getInputManager().addResponse(textField.getText());
			getOS().setScreen(screenToReturnTo.getScreenName());
		}
	}

	@Override
	public String getScreenName() {
		return NAME + "_" + screenToReturnTo.getScreenName();
	}

	@Override
	public Image getBackgroundImage() {
		return new Image(getOS(), "rologia:textures/gui/watch/boot_screen_dark.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);	
	}

}
