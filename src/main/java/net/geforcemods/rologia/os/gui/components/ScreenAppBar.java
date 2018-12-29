package net.geforcemods.rologia.os.gui.components;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.misc.Position;
import net.geforcemods.rologia.os.resources.ResourceLoader;
import net.minecraft.util.ResourceLocation;

public class ScreenAppBar extends ScreenComponent {
	
	public static ResourceLocation APP_ICONS = new ResourceLocation(ResourceLoader.TEXTURE_FOLDER_PATH + "app_icons.png");
	public static final int ICON_X_SPACING = 7;
	
	public ScreenAppBar(RologiaOS OS, Position screenPos) {
		super(OS);
		setPositionAndOrigin(screenPos.shiftX((Screen.WATCH_SCREEN_X_SIZE / 2) - (getWidth() / 2)).shiftY(Screen.WATCH_SCREEN_Y_SIZE + 7));
	}

	@Override
	public void drawComponent() {
		bindTexture(APP_ICONS);

		for(int i = 0; i < getBarSize(); i++)
			if(i == getBarHoveringOver(getScreen().getMousePosition()))
				getScreen().drawTexturedModalRect(getPosition().shiftX(i * ICON_X_SPACING).getX(), getPosition().getY(), 6, 0, 6, 6);
			else
				getScreen().drawTexturedModalRect(getPosition().shiftX(i * ICON_X_SPACING).getX(), getPosition().getY(), 0, 0, 6, 6);
	}

	@Override
	public boolean mouseClick(Position mousePos, int mouseButtonClicked) {
		System.out.println(getBarHoveringOver(mousePos));
		return false;
	}

	public int getBarHoveringOver(Position mousePosition) {
		if(!isMouseHoveringOver(mousePosition)) return -1;

		for(int i = 0; i < getBarSize(); i++) {
			if(mousePosition.getX() >= (getPosition().getX() + (i * ICON_X_SPACING)) && mousePosition.getX() <= (getPosition().getX() + ((i + 1) * ICON_X_SPACING)) && mousePosition.getY() >= getPosition().getY() && mousePosition.getY() <= getPosition().getY() + getHeight()) {
				return i;
			}
		}

		return -1;
	}
	
	public int getBarSize() {
		/* This method returns the number of icons that should be rendered.
		   Icon 1: "home" screen
		   Icon 2: "app selection" screen
		   Icons 3 - n: open apps
		   Icon n + 1: "settings" screen */
		return 1 + 1 + 1 + getOS().getApps().size();
	}

	@Override
	public boolean isSystemComponent() {
		return true;
	}

	@Override
	public int getWidth() {
		return (ICON_X_SPACING * getBarSize()) - 1;
	}

	@Override
	public int getHeight() {
		return 6;
	}

}
