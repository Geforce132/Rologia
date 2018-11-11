package net.geforcemods.smartwatch.rologia.os.fontrenderer;

import net.geforcemods.smartwatch.rologia.gui.screens.Screen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public class RologiaFontRenderer extends FontRenderer {

	public RologiaFontRenderer(Minecraft mc) {
		super(mc.gameSettings, new ResourceLocation("minecraft:textures/font/ascii.png"), mc.renderEngine, mc.isUnicode());
	}
	
	public void drawStringWithinScreenWidth(String text, int xPos, int yPos, int color) {
		String formattedText = trimStringToWidth(text, Screen.WATCH_SCREEN_X_SIZE - 10);
	
		drawString(formattedText, xPos, yPos, color);
	}

}
