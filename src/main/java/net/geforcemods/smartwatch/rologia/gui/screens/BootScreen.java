package net.geforcemods.smartwatch.rologia.gui.screens;

import net.geforcemods.smartwatch.rologia.gui.components.ScreenComponent;
import net.geforcemods.smartwatch.rologia.gui.components.images.ScreenImage;
import net.geforcemods.smartwatch.rologia.os.Rologia;

public class BootScreen extends Screen {

	public BootScreen(Rologia OS, int screenX, int screenY) {
		super(OS, screenX, screenY);
	}

	@Override
	public void initializeScreen() {}

	@Override
	public void updateScreen() {

	}

	@Override
	public void editComponent(ScreenComponent comp) {}

	@Override
	public void onComponentClicked(ScreenComponent component, int mouseX, int mouseY) {
		//if(component == arrow)
			//ResourceLoader.loadComponents(getOS());
	}

	@Override
	public ScreenImage getBackgroundImage() {
		return new ScreenImage(getOS(), "minewatch:textures/gui/watch/default.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);	
	}

}
