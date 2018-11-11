package net.geforcemods.smartwatch.rologia.gui.components.shapes;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import net.geforcemods.smartwatch.rologia.gui.components.ScreenComponent;
import net.geforcemods.smartwatch.rologia.os.Rologia;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class ScreenShapeRectangle extends ScreenComponent {

	private int   width;
	private int   height;
	private Color color;
	
	public ScreenShapeRectangle(Rologia os, int w, int h, Color rgbColor) {
		super(os);
		width = w;
		height = h;
		color = rgbColor;
	}

	public ScreenShapeRectangle(Rologia os, int x, int y, int w, int h, Color rgbColor) {
		super(os, x, y);
		width = w;
		height = h;
		color = rgbColor;
	}

	@Override
	public void drawComponent() {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        buffer.pos((double)xPos, (double)yPos + height, 0.0D);
        buffer.pos((double)xPos + width, (double)yPos + height, 0.0D);
        buffer.pos((double)xPos + width, (double)yPos, 0.0D);
        buffer.pos((double)xPos, (double)yPos, 0.0D);
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
		return width;
	}
	
	@Override
	public int getHeight() {
		return height;
	}

}