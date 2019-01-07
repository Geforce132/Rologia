package net.geforcemods.rologia.os.gui.screens;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.components.ScreenButton;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.images.ScreenImage;
import net.geforcemods.rologia.os.gui.components.text.ScreenText;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;

public class SettingsScreen extends Screen {

	public static final String NAME = "Settings";

	private ScreenButton lightButton = new ScreenButton(getOS(), "Light");
	private ScreenButton darkButton = new ScreenButton(getOS(), "Dark");
	private ScreenText themeText = new ScreenText(getOS(), "Theme:", GuiUtils.toHex(getOS().getTheme().SETTINGS_TEXT));

	public SettingsScreen(RologiaOS os, Position pos) {
		super(os, pos);
	}

	@Override
	public void initializeScreen() {
		lightButton.centerPosition(-20, -20);
		darkButton.centerPosition(20, -20);
		themeText.centerPosition(0, -35);

		if(getOS().getTheme().getName().equalsIgnoreCase(lightButton.getText()))
			lightButton.setEnabled(false);

		if(getOS().getTheme().getName().equalsIgnoreCase(darkButton.getText()))
			darkButton.setEnabled(false);

		addComponent(lightButton);
		addComponent(darkButton);
		addComponent(themeText);
	}

	@Override
	public void onComponentClicked(ScreenComponent component, Position mousePos, int mouseButtonClicked) {
		if(component == lightButton && lightButton.isEnabled()) {
			//getOS().getInputManager().requestDecision(this, "ohaidere");
			getOS().setTheme("light");
			lightButton.setEnabled(false);
			darkButton.setEnabled(true);
		}
		else if(component == darkButton && darkButton.isEnabled()) {
			//getOS().getInputManager().requestDecision(this, "ohaidere");
			getOS().setTheme("dark");
			darkButton.setEnabled(false);
			lightButton.setEnabled(true);
		}
	}

	@Override
	public String getScreenName() {
		return NAME;
	}

	@Override
	public ScreenImage getBackgroundImage() {
		return new ScreenImage(getOS(), "rologia:textures/gui/watch/boot_screen_dark.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);
	}

}
