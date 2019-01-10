package net.geforcemods.rologia.os.gui.screens;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.ScreenToggleButton;
import net.geforcemods.rologia.os.gui.components.images.ScreenImage;
import net.geforcemods.rologia.os.gui.components.text.ScreenText;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.imc.IMCManager;
import net.geforcemods.rologia.os.misc.Position;

public class SettingsScreen extends Screen {

	public static final String NAME = "Settings";


	private ScreenText themeText = new ScreenText(getOS(), "Theme:", GuiUtils.toHex(getOS().getTheme().SETTINGS_TEXT));

	private ScreenToggleButton themeButton = new ScreenToggleButton(getOS(), "Light", "Dark");

	public SettingsScreen(RologiaOS os, Position pos) {
		super(os, pos);
	}

	@Override
	public void initializeScreen() {
		themeText.centerPosition(0, -35);

		themeButton.centerPosition(0, -20);

		addComponent(themeText);
		addComponent(themeButton);
	}

	@Override
	public void onComponentClicked(ScreenComponent component, Position mousePos, int mouseButtonClicked) {
		if(component == themeButton && themeButton.isEnabled()) {
			themeButton.toggle();
			getOS().setTheme(themeButton.getText().toLowerCase());
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
