package net.geforcemods.smartwatch.rologia.gui.components.images;

import java.awt.image.BufferedImage;

import org.lwjgl.opengl.GL11;

import net.geforcemods.smartwatch.rologia.gui.screens.Screen;
import net.minecraft.util.ResourceLocation;

public class ScreenRotatingImage extends ScreenImage {
	
	private float rotation = 0F;

	public ScreenRotatingImage(Screen screen, BufferedImage image, float rotationValue) {
		super(screen, image);
		setRotation(rotationValue);
	}
	
	public ScreenRotatingImage(Screen screen, ResourceLocation imageLocation, int width, int height, float rotationValue) {
		super(screen, imageLocation, width, height);
		setRotation(rotationValue);
	}
	
	public ScreenRotatingImage(Screen screen, String imagePath, int width, int height, float rotationValue) {
		super(screen, imagePath, width, height);
		setRotation(rotationValue);
	}

	public ScreenRotatingImage(Screen screen, BufferedImage image, int x, int y, float rotationValue) {
		super(screen, image, x, y);
		setRotation(rotationValue);
	}
	
	public ScreenRotatingImage(Screen screen, ResourceLocation imageLocation, int x, int y, int width, int height, float rotationValue) {
		super(screen, imageLocation, x, y, width, height);
		setRotation(rotationValue);
	}
	
	public ScreenRotatingImage(Screen screen, String imagePath, int x, int y, int width, int height, float rotationValue) {
		super(screen, imagePath, x, y, width, height);
		setRotation(rotationValue);
	}
	
	@Override
	public void performPrerenderGLFixes() {
		GL11.glTranslatef(getWidth() / 2 + getScreen().getCenteredXForComponent(this), getHeight() / 2 + getScreen().getCenteredYForComponent(this), 0);
		GL11.glRotatef(rotation += getRotation(), 0, 0, 1);
		GL11.glTranslatef(-getWidth() / 2 - getScreen().getCenteredXForComponent(this), -getHeight() / 2 - getScreen().getCenteredYForComponent(this), 0);
		
		GL11.glScalef(getScale(), getScale(), 1F);
		if(getScale() != 1.0F)
		{
			int translatedX, translatedY;
			translatedX = (int) (getScreen().getCenteredXForComponent(this) / getScale());
			translatedY = (int) (getScreen().getCenteredYForComponent(this) / getScale());
			if(getXPos() != translatedX && getYPos() != translatedY)
				setPosition(translatedX, translatedY);
		}
	}

}
