package net.geforcemods.rologia.os.gui.components;

import org.lwjgl.opengl.GL11;

import net.geforcemods.rologia.os.Rologia;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.misc.Position;

public class ScreenScrollBar extends ScreenComponent {

	public ScreenScrollBar(Rologia OS, Position pos) {
		super(OS, pos);
	}

	@Override
	public void drawComponent() {
		GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor4f(0, 255, 0, 1);
        GL11.glVertex3d((double)getPosition().getX(), (double)getPosition().getY() + getHeight(), 0.0D);
        GL11.glVertex3d((double)getPosition().getX() + getWidth(), (double)getPosition().getY() + getHeight(), 0.0D);
        GL11.glVertex3d((double)getPosition().getX() + getWidth(), (double)getPosition().getY(), 0.0D);
        GL11.glVertex3d((double)getPosition().getX(), (double)getPosition().getY(), 0.0D);
        GL11.glEnd();
        
		GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	@Override
	public void mouseClick(int mouseX, int mouseY, int mouseButtonClicked) {

	}

	@Override
	public int getWidth() {
		return 4;
	}

	@Override
	public int getHeight() {
		return Screen.WATCH_SCREEN_Y_SIZE;
	}

}
