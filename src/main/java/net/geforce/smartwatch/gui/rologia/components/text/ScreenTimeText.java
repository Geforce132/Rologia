package net.geforce.smartwatch.gui.rologia.components.text;

import org.lwjgl.opengl.GL11;

import net.geforce.smartwatch.gui.rologia.time.TimeFormat;
import net.geforce.smartwatch.gui.rologia.time.TimeManager;

public class ScreenTimeText extends ScreenText {

	public ScreenTimeText(int x, int y, int color) {
		super(TimeManager.getTime(TimeFormat.HM), x, y, color);
	}

	@Override
	public void drawComponent() {
		this.drawString(getFontRenderer(), TimeManager.getTime(TimeFormat.HM), getXPos(), getYPos(), getColor());
	}
	
	/*@Override
	public void performPrerenderGLFixes() {
		GL11.glTranslatef(getWidth() / 2 + getScreen().getCenteredXForComponent(this), getHeight() / 2 + getScreen().getCenteredYForComponent(this), 0);
		GL11.glRotatef(rotation += getRotation(), 0, 0, 1);
		GL11.glTranslatef(-getWidth() / 2 - getScreen().getCenteredXForComponent(this), -getHeight() / 2 - getScreen().getCenteredYForComponent(this), 0);
		
		GL11.glScalef(getScale(), getScale(), 1F);
		if(getScale() != 1.0F)
		{
			int translatedX, translatedY;
			translatedX = (int) (getDefaultXPos() / getScale());
			translatedY = (int) (getDefaultYPos() / getScale());
			if(getXPos() != translatedX && getYPos() != translatedY)
				setPosition(translatedX, translatedY);
		}
	}*/

}
