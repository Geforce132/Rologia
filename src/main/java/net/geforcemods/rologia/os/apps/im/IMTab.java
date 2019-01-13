package net.geforcemods.rologia.os.apps.im;

import java.util.ArrayList;

import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.utils.Colors;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;
import net.minecraft.client.renderer.GlStateManager;

public class IMTab {
	
	private Position position;
	public String destinationPlayerName;
	private ArrayList<String> messages = new ArrayList<String>();
	
	public IMTab(Position location, String name) {
		position = location;
		destinationPlayerName = name;
	}
	
	public void drawTab(Screen currentScreen) {
		GuiUtils.drawHollowRect(position, currentScreen.getFontRenderer().getStringWidth(destinationPlayerName) + 1, 10, Colors.RED.color);
		currentScreen.getFontRenderer().drawString(destinationPlayerName, position.getX() + 1, position.getY() + 1, Colors.GREEN.hexValue);

		for(int i = 0; i < messages.size(); i++)
			drawMessage(currentScreen, i);
	}

	private void drawMessage(Screen screen, int index) {
		int x = position.getX();
		int y = position.shiftY(20).getY() + (index * 7);

		GlStateManager.pushMatrix();

		GlStateManager.translate(x, y, 0);
		GlStateManager.scale(.6F, .6F, 0);
		GlStateManager.translate(-x, -y, 0);

		screen.getFontRenderer().drawString(messages.get(index), x, y, Colors.GREEN.hexValue);

		GlStateManager.popMatrix();
	}

	public void addMessage(String message) {
		messages.add(message);
	}

}
