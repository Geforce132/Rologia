package net.geforce.smartwatch.gui.rologia.components;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import net.geforce.smartwatch.gui.rologia.screens.Screen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;

public class ScreenStatusBar extends ScreenComponent {

	private Color color;
	
	public ScreenStatusBar(int x, int y, Color rgbColor) {
		super(x, y);
		color = rgbColor;
	}

	@Override
	public void drawComponent() {
        Tessellator tessellator = Tessellator.instance;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        tessellator.startDrawingQuads();
        tessellator.addVertex((double)xPos, (double)yPos + getHeight(), 0.0D);
        tessellator.addVertex((double)xPos + getWidth(), (double)yPos + getHeight(), 0.0D);
        tessellator.addVertex((double)xPos + getWidth(), (double)yPos, 0.0D);
        tessellator.addVertex((double)xPos, (double)yPos, 0.0D);
        tessellator.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
	}
	
	@Override
	public void mouseClick(int mouseX, int mouseY, int mouseButtonClicked) {
		
	}
	
	public void setColor(Color rgbColor) {
		color = rgbColor;
	}
	
	@Override
	public int getWidth() {
		return Screen.WATCH_SCREEN_X_SIZE;
	}
	
	@Override
	public int getHeight() {
		return 7;
	}

}