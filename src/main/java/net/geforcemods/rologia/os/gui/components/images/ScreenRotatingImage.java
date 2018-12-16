package net.geforcemods.smartwatch.rologia.gui.components.images;

import java.awt.image.BufferedImage;

import org.lwjgl.opengl.GL11;

import net.geforcemods.smartwatch.rologia.os.Rologia;
import net.geforcemods.smartwatch.rologia.os.misc.Position;
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

	public ScreenRotatingImage(Rologia os, BufferedImage image, Position pos, float rotationValue) {
		super(os, image, pos);
		setRotation(rotationValue);
	}
	
	public ScreenRotatingImage(Rologia os, ResourceLocation imageLocation, Position pos, int width, int height, float rotationValue) {
		super(os, imageLocation, pos, width, height);
		setRotation(rotationValue);
	}
	
	public ScreenRotatingImage(Rologia os, String imagePath, Position pos, int width, int height, float rotationValue) {
		super(os, imagePath, pos, width, height);
		setRotation(rotationValue);
	}
	
	@Override
	public void performPrerenderGLFixes() {
		GL11.glTranslatef(getWidth() / 2 + getScreen().getCenteredPositionForComponent(this).getX(), getHeight() / 2 + getScreen().getCenteredPositionForComponent(this).getY(), 0);
		GL11.glRotatef(rotation += getRotation(), 0, 0, 1);
		GL11.glTranslatef(-getWidth() / 2 - getScreen().getCenteredPositionForComponent(this).getX(), -getHeight() / 2 - getScreen().getCenteredPositionForComponent(this).getY(), 0);
		
		GL11.glScalef(getScale(), getScale(), 1F);
		if(getScale() != 1.0F)
		{
			Position pos = getScreen().getCenteredPositionForComponent(this).scalePos(getScale());

			if(!getPosition().matches(pos))
				setPosition(pos);
		}
	}

}
