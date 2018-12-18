package net.geforcemods.rologia.os.gui.components;

import org.lwjgl.opengl.GL11;

import net.geforcemods.rologia.os.Rologia;
import net.geforcemods.rologia.os.misc.Position;
import net.minecraft.client.renderer.GlStateManager;

public class ScreenTextField extends ScreenComponent {
	
	private int width;
	private int height;
	
	public ScreenTextField(Rologia OS, int w, int h) {
		super(OS);
		width = w;
		height = h;
	}

	public ScreenTextField(Rologia OS, Position pos, int w, int h) {
		super(OS, pos);
		width = w;
		height = h;
	}

	@Override
	public void drawComponent() {
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
        
		GlStateManager.glBegin(GL11.GL_LINES);
		GlStateManager.color(255, 0, 0);
		GlStateManager.glVertex3f(getPosition().getX(), getPosition().getY(), 0);
		GlStateManager.glVertex3f(getPosition().getX() + width, getPosition().getY(), 0);
		GlStateManager.glEnd();
		
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
	}

	@Override
	public void mouseClick(int mouseX, int mouseY, int mouseButtonClicked) {

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
