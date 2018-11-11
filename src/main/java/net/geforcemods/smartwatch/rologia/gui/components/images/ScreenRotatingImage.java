package net.geforcemods.smartwatch.rologia.gui.components.images;

import java.awt.image.BufferedImage;

import org.lwjgl.opengl.GL11;

import net.geforcemods.smartwatch.rologia.os.Rologia;
import net.minecraft.util.ResourceLocation;

public class ScreenRotatingImage extends ScreenImage {
	
	private float rotation = 0F;

	public ScreenRotatingImage(Rologia os, BufferedImage image, float rotationValue) {
		super(os, image);
		setRotation(rotationValue);
	}
	
	public ScreenRotatingImage(Rologia os, ResourceLocation imageLocation, int width, int height, float rotationValue) {
		super(os, imageLocation, width, height);
		setRotation(rotationValue);
	}
	
	public ScreenRotatingImage(Rologia os, String imagePath, int width, int height, float rotationValue) {
		super(os, imagePath, width, height);
		setRotation(rotationValue);
	}

	public ScreenRotatingImage(Rologia os, BufferedImage image, int x, int y, float rotationValue) {
		super(os, image, x, y);
		setRotation(rotationValue);
	}
	
	public ScreenRotatingImage(Rologia os, ResourceLocation imageLocation, int x, int y, int width, int height, float rotationValue) {
		super(os, imageLocation, x, y, width, height);
		setRotation(rotationValue);
	}
	
	public ScreenRotatingImage(Rologia os, String imagePath, int x, int y, int width, int height, float rotationValue) {
		super(os, imagePath, x, y, width, height);
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
