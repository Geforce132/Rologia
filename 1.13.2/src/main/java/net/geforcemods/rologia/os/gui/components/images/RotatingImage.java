package net.geforcemods.rologia.os.gui.components.images;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.misc.Position;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.util.ResourceLocation;

public class RotatingImage extends Image {
	
	private float rotation = 0F;

	public RotatingImage(RologiaOS os, NativeImage image, float rotationValue) {
		super(os, image);
		setRotation(rotationValue);
	}
	
	public RotatingImage(RologiaOS os, ResourceLocation imageLocation, int width, int height, float rotationValue) {
		super(os, imageLocation, width, height);
		setRotation(rotationValue);
	}
	
	public RotatingImage(RologiaOS os, String imagePath, int width, int height, float rotationValue) {
		super(os, imagePath, width, height);
		setRotation(rotationValue);
	}

	public RotatingImage(RologiaOS os, NativeImage image, Position pos, float rotationValue) {
		super(os, image, pos);
		setRotation(rotationValue);
	}
	
	public RotatingImage(RologiaOS os, ResourceLocation imageLocation, Position pos, int width, int height, float rotationValue) {
		super(os, imageLocation, pos, width, height);
		setRotation(rotationValue);
	}
	
	public RotatingImage(RologiaOS os, String imagePath, Position pos, int width, int height, float rotationValue) {
		super(os, imagePath, pos, width, height);
		setRotation(rotationValue);
	}
	
	@Override
	public void performPrerenderGLFixes() {
		GlStateManager.translated(getWidth() / 2 + getScreen().getCenteredPositionForComponent(this).getX(), getHeight() / 2 + getScreen().getCenteredPositionForComponent(this).getY(), 0);
		GlStateManager.rotatef(rotation += getRotation(), 0, 0, 1);
		GlStateManager.translated(-getWidth() / 2 - getScreen().getCenteredPositionForComponent(this).getX(), -getHeight() / 2 - getScreen().getCenteredPositionForComponent(this).getY(), 0);
		
		GlStateManager.scalef(getScale(), getScale(), 1F);
		if(getScale() != 1.0F)
		{
			Position pos = getScreen().getCenteredPositionForComponent(this).scalePos(getScale());

			if(!getPosition().matches(pos))
				setPosition(pos);
		}
	}

}
