package net.geforcemods.rologia.os.gui.rendering;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

import net.geforcemods.rologia.os.Rologia;
import net.geforcemods.rologia.os.misc.Position;
import net.minecraft.client.gui.FontRenderer;

public class GuiUtils {
	
	public static void drawHollowRect(Position startPos, int width, int height) {
		GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        
		GL11.glBegin(GL11.GL_LINES);
		GL11.glColor3f(0, 0, 255);
		GL11.glVertex2f(startPos.getX(), startPos.getY());
		GL11.glVertex2f(startPos.getX() + width, startPos.getY());
		
		GL11.glVertex2f(startPos.getX() + width, startPos.getY());
		GL11.glVertex2f(startPos.getX() + width, startPos.getY() + height);
		
		GL11.glVertex2f(startPos.getX() + width, startPos.getY() + height);
		GL11.glVertex2f(startPos.getX(), startPos.getY() + height);
		
		GL11.glVertex2f(startPos.getX(), startPos.getY());
		GL11.glVertex2f(startPos.getX(), startPos.getY() + height);

		GL11.glEnd();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
	}
	
	public static String formatString(Rologia OS, String text) {
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
