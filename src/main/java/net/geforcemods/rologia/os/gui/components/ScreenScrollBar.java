package net.geforcemods.rologia.os.gui.components;

import org.lwjgl.opengl.GL11;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.misc.Position;
import net.minecraft.client.renderer.GlStateManager;

public class ScreenScrollBar extends ScreenComponent {

	public ScreenScrollBar(RologiaOS OS, Position pos) {
		super(OS, pos);
	}

	@Override
	public void drawComponent() {
		GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        
        GlStateManager.glBegin(GL11.GL_QUADS);
        GlStateManager.color(0, 255, 0, 1);
        GlStateManager.glVertex3f((float)getPosition().getX(), (float)getPosition().getY() + getHeight(), 0.0F);
        GlStateManager.glVertex3f((float)getPosition().getX() + getWidth(), (float)getPosition().getY() + getHeight(), 0.0F);
        GlStateManager.glVertex3f((float)getPosition().getX() + getWidth(), (float)getPosition().getY(), 0.0F);
        GlStateManager.glVertex3f((float)getPosition().getX(), (float)getPosition().getY(), 0.0F);
        GlStateManager.glEnd();
        
		GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
	}

	@Override
	public boolean isSystemComponent() {
		return true;
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
