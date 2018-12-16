package net.geforcemods.rologia.os.gui.components;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import net.geforcemods.rologia.os.Rologia;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.misc.Constants;
import net.geforcemods.rologia.os.misc.Position;

public class ScreenStatusBar extends ScreenComponent {

	private Color color;

	public ScreenStatusBar(Rologia os, Color rgbColor) {
		super(os);
		color = rgbColor;
	}
	
	public ScreenStatusBar(Rologia os, Position pos, Color rgbColor) {
		super(os, pos);
		color = rgbColor;
	}

	@Override
	public void drawComponent() {
		GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        GL11.glVertex3d((double)getPosition().getX(), (double)getPosition().getY() + getHeight(), 0.0D);
        GL11.glVertex3d((double)getPosition().getX() + getWidth(), (double)getPosition().getY() + getHeight(), 0.0D);
        GL11.glVertex3d((double)getPosition().getX() + getWidth(), (double)getPosition().getY(), 0.0D);
        GL11.glVertex3d((double)getPosition().getX(), (double)getPosition().getY(), 0.0D);
        GL11.glEnd();
        
		GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        
        float scaleOfText = 0.85F;
		GL11.glScalef(scaleOfText, scaleOfText, 1F);
        this.drawString(getFontRenderer(), getScreen().getOS().getTime(Constants.HM), (int) ((getPosition().getX() + 50) / scaleOfText), (int) ((getPosition().getY() + 1) / scaleOfText), 44444);
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