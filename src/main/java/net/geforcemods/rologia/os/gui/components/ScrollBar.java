package net.geforcemods.rologia.os.gui.components;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.misc.Position;

public class ScrollBar extends ScreenComponent {

	public ScrollBar(RologiaOS OS, Position pos) {
		super(OS, pos);
	}

	@Override
	public void drawComponent() {
		RenderSystem.enableBlend();
		RenderSystem.disableTexture();
        
        GL11.glBegin(GL11.GL_QUADS);
        GlStateManager.func_227702_d_(0, 255, 0, 1);
        GL11.glVertex3f((float)getPosition().getX(), (float)getPosition().getY() + getHeight(), 0.0F);
        GL11.glVertex3f((float)getPosition().getX() + getWidth(), (float)getPosition().getY() + getHeight(), 0.0F);
        GL11.glVertex3f((float)getPosition().getX() + getWidth(), (float)getPosition().getY(), 0.0F);
        GL11.glVertex3f((float)getPosition().getX(), (float)getPosition().getY(), 0.0F);
        GL11.glEnd();
        
        RenderSystem.disableBlend();
        RenderSystem.enableTexture();
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
