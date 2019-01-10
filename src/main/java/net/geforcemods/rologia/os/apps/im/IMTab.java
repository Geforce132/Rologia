package net.geforcemods.rologia.os.apps.im;

import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.utils.Colors;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;

public class IMTab {
	
	private Position position;
	private String destinationPlayerName;
	
	public IMTab(Position location, String name) {
		position = location;
		destinationPlayerName = name;
	}
	
	public void drawTab(Screen currentScreen) {
		GuiUtils.drawHollowRect(position, currentScreen.getFontRenderer().getStringWidth(destinationPlayerName) + 1, 10, Colors.RED.color);
		currentScreen.getFontRenderer().drawString(destinationPlayerName, position.getX() + 1, position.getY() + 1, Colors.GREEN.hexValue);
	}

}
