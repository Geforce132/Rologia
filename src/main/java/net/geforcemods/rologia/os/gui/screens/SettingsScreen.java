package net.geforcemods.rologia.os.gui.screens;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.apps.events.AppEventType;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.ToggleButton;
import net.geforcemods.rologia.os.gui.components.images.Image;
import net.geforcemods.rologia.os.gui.components.text.Text;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;
import net.geforcemods.rologia.os.notifications.Notification;

public class SettingsScreen extends Screen {

	public static final String NAME = "Settings";

	private Text themeText = new Text(getOS(), "Theme:", GuiUtils.toHex(getOS().getTheme().SETTINGS_TEXT));

	private ToggleButton themeButton = new ToggleButton(getOS(), "Light", "Dark");

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
			getOS().addNotification(new Notification(this, "testing", "testing"));
		}
	}

	@Override
	public String getScreenName() {
		return NAME;
	}

	@Override
	public Image getBackgroundImage() {
		return new Image(getOS(), "rologia:textures/gui/watch/boot_screen_dark.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);
	}

	@Override
	public AppEventType[] subscribeToEvents() {
		return null;
	}

}
