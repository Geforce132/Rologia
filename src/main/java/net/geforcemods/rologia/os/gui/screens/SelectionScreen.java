package net.geforcemods.rologia.os.gui.screens;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.images.ScreenImage;
import net.geforcemods.rologia.os.gui.components.text.ScreenText;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;

public class SelectionScreen extends Screen {

	public SelectionScreen(RologiaOS os, Position pos) {
		super(os, pos);
	}

	@Override
	public void initializeScreen() {
		for(int i = 0; i < getOS().getApps().size(); i++) {
			ScreenComponent text = new ScreenText(getOS(), getOS().getApps().get(i).getAppName(), GuiUtils.toHex(getOS().getTheme().SELECTION_TEXT));
			text.centerPosition(0, -35 + (20 * i));
			addComponent(text);
		}
	}

	@Override
	public void onComponentClicked(ScreenComponent component, Position mousePos, int mouseButtonClicked) {}

	@Override
	public ScreenImage getBackgroundImage() {
		return new ScreenImage(getOS(), "rologia:textures/gui/watch/boot_screen_dark.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);
	}

}
