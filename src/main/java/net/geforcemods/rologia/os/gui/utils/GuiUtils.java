package net.geforcemods.rologia.os.gui.utils;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.utils.Colors.Color;
import net.geforcemods.rologia.os.misc.Position;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GuiUtils {
	
	public static final char BACKSPACE = '';
	
	public static void drawHollowRect(Position startPos, int width, int height, Color color) {
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
        
		GL11.glBegin(GL11.GL_LINES);
		GlStateManager.color4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		GL11.glVertex3f(startPos.getX(), startPos.getY(), 0);
		GL11.glVertex3f(startPos.getX() + width, startPos.getY(), 0);
		
		GL11.glVertex3f(startPos.getX() + width, startPos.getY(), 0);
		GL11.glVertex3f(startPos.getX() + width, startPos.getY() + height, 0);
		
		GL11.glVertex3f(startPos.getX() + width, startPos.getY() + height, 0);
		GL11.glVertex3f(startPos.getX(), startPos.getY() + height, 0);
		
		GL11.glVertex3f(startPos.getX(), startPos.getY(), 0);
		GL11.glVertex3f(startPos.getX(), startPos.getY() + height, 0);

		GL11.glEnd();

		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}
	
	public static void drawFilledRect(Position startPos, int width, int height, Color color) {
		drawFilledRect(startPos, width, height, color, color.getAlpha());
	}
	
	public static void drawFilledRect(Position startPos, int width, int height, Color color, float transparency) {
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();

		GlStateManager.color4f(color.getRed(), color.getGreen(), color.getBlue(), transparency);
		GlStateManager.polygonMode(GL11.GL_FRONT, GL11.GL_FILL);
		GL11.glBegin(GL11.GL_QUADS);

		GL11.glVertex3f(startPos.getX(), startPos.getY(), 0);
		GL11.glVertex3f(startPos.getX(), startPos.getY() + height, 0);
		GL11.glVertex3f(startPos.getX() + width, startPos.getY() + height, 0);
		GL11.glVertex3f(startPos.getX() + width, startPos.getY(), 0);

		GL11.glEnd();
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
	}
	
	public static void drawFilledRect(int x, int y, int width, int height, Color color, float transparency) {
		drawFilledRect(new Position(x, y), width, height, color, transparency);
	}
	
	public static void drawItem(Position startPos, Item item) {
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		RenderHelper.disableStandardItemLighting();
		RenderHelper.enableGUIStandardItemLighting();
		
		Minecraft.getInstance().getItemRenderer().renderItemIntoGUI(new ItemStack(item), startPos.getX(), startPos.getY());
		Minecraft.getInstance().getItemRenderer().renderItemOverlayIntoGUI(Minecraft.getInstance().fontRenderer, new ItemStack(item), startPos.getX(), startPos.getY(), "");

		GlStateManager.disableBlend();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.popMatrix();
	}
	
	public static void drawItem(Position startPos, Block block) {
		drawItem(startPos, Item.getItemFromBlock(block));
	}
	
	public static int toHex(Color color) {
		String hex = String.format("%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());  
		return Integer.parseInt(hex, 16);
	}

	public static String formatString(RologiaOS OS, String text) {
		if(!OS.isAppOpen()) return text;

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
