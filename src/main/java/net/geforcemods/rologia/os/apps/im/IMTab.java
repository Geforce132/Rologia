package net.geforcemods.rologia.os.apps.im;

import java.util.ArrayList;

import com.mojang.blaze3d.systems.RenderSystem;

import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.utils.Colors;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;
import net.minecraft.util.text.StringTextComponent;

public class IMTab {
	
	private Position position;
	public String sender;
	private ArrayList<IMMessage> messages = new ArrayList<IMMessage>();
	
	public IMTab(Position location, String name) {
		position = location;
		sender = name;
	}
	
	public void drawTab(Screen currentScreen) {
		GuiUtils.drawHollowRect(position, currentScreen.getFontRenderer().getStringWidth(sender) + 1, 10, Colors.RED.color);
		//currentScreen.getFontRenderer().drawString(sender, position.getX() + 1, position.getY() + 1, Colors.GREEN.hexValue);
		//TODO
		for(int i = 0; i < messages.size(); i++)
			drawMessage(currentScreen, i);
	}

	private void drawMessage(Screen screen, int index) {
		int x = position.getX();
		int y = position.shiftY(20 + getPreviousMessageHeights(index)).getY() + (index * 2);

		RenderSystem.pushMatrix();

		RenderSystem.translated(x, y, 0);
		RenderSystem.scalef(AppIM.TEXT_SCALE, AppIM.TEXT_SCALE, 0);
		RenderSystem.translated(-x, -y, 0);

		screen.getFontRenderer().func_238418_a_(new StringTextComponent(messages.get(index).getMessageWithSenderPrefix()), x, y, (int) (Screen.WATCH_SCREEN_X_SIZE / AppIM.TEXT_SCALE), Colors.DARK_GREEN.hexValue);

		RenderSystem.popMatrix();
	}

	private int getPreviousMessageHeights(int index) {
		int height = 0;

		for(int i = 0; i < index; i++)
			height += messages.get(i).getMessageHeight(true);

		return height;
	}

	public void addMessage(String message) {
		messages.add(new IMMessage(this, message));
	}

}
