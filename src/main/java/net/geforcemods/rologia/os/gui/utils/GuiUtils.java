package net.geforcemods.rologia.os.gui.utils;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.misc.Position;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;

public class GuiUtils {
	
	public static final char BACKSPACE = '';
	
	public static void drawHollowRect(Position startPos, int width, int height, Color color) {
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
        
		GlStateManager.glBegin(GL11.GL_LINES);
		GlStateManager.color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		GlStateManager.glVertex3f(startPos.getX(), startPos.getY(), 0);
		GlStateManager.glVertex3f(startPos.getX() + width, startPos.getY(), 0);
		
		GlStateManager.glVertex3f(startPos.getX() + width, startPos.getY(), 0);
		GlStateManager.glVertex3f(startPos.getX() + width, startPos.getY() + height, 0);
		
		GlStateManager.glVertex3f(startPos.getX() + width, startPos.getY() + height, 0);
		GlStateManager.glVertex3f(startPos.getX(), startPos.getY() + height, 0);
		
		GlStateManager.glVertex3f(startPos.getX(), startPos.getY(), 0);
		GlStateManager.glVertex3f(startPos.getX(), startPos.getY() + height, 0);

		GlStateManager.glEnd();

		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
	}
	
	public static void drawFilledRect(Position startPos, int width, int height, Color color) {
		drawFilledRect(startPos, width, height, color, color.getAlpha());
	}
	
	public static void drawFilledRect(Position startPos, int width, int height, Color color, float transparency) {
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();

		GlStateManager.glPolygonMode(GL11.GL_FRONT, GL11.GL_FILL);
		GlStateManager.glBegin(GL11.GL_QUADS);

		GlStateManager.color(color.getRed(), color.getGreen(), color.getBlue(), transparency);
		GlStateManager.glVertex3f(startPos.getX(), startPos.getY(), 0);
		GlStateManager.glVertex3f(startPos.getX(), startPos.getY() + height, 0);
		GlStateManager.glVertex3f(startPos.getX() + width, startPos.getY() + height, 0);
		GlStateManager.glVertex3f(startPos.getX() + width, startPos.getY(), 0);

		GlStateManager.glEnd();

		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
	}

	public static String formatString(RologiaOS OS, String text) {
		String[] keywords = StringUtils.substringsBetween(text, "$$", "$$");
		
		if(keywords != null) {
			for(int i = 0; i < keywords.length; i++) {						
				text = text.replace("$$" + keywords[i] + "$$", OS.getCurrentApp().replaceKeywords(keywords[i]).toString());
			}
		}
		
		return text;
	}
	
	public static int getWordWrappedWidth(FontRenderer renderer, String text, int width) {
		int longestWidth = 0;

		for(String line : renderer.listFormattedStringToWidth(text, width)) {
			int lineWidth = renderer.getStringWidth(line);
			if(renderer.getStringWidth(line) > longestWidth)
				longestWidth = lineWidth;
		}
		
		return longestWidth;
	}


}
