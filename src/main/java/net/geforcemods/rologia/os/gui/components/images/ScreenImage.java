package net.geforcemods.rologia.os.gui.components.images;

import java.awt.image.BufferedImage;

import net.geforcemods.rologia.os.Rologia;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.misc.Position;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

public class ScreenImage extends ScreenComponent {
	
	private ResourceLocation location;
	private int imageWidth;
	private int imageHeight;
	private boolean isDynamicImage;
	
	public ScreenImage(Rologia os, String imagePath, int width, int height) {
		super(os);
		location = new ResourceLocation(imagePath);
		imageWidth = width;
		imageHeight = height;
		isDynamicImage = false;
	}
	
	public ScreenImage(Rologia os, ResourceLocation imageLocation, int width, int height) {
		super(os);
		location = imageLocation;
		imageWidth = width;
		imageHeight = height;
	}

	public ScreenImage(Rologia os, BufferedImage image) {
		super(os);
		location = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation(image.toString(), new DynamicTexture(image));
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
	}
	
	public ScreenImage(Rologia os, BufferedImage image, Position pos) {
		super(os, pos);
		location = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation(image.toString(), new DynamicTexture(image));
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
		isDynamicImage = true;
	}
	
	public ScreenImage(Rologia os, ResourceLocation imageLocation, Position pos, int width, int height) {
		super(os, pos);
		location = imageLocation;
		imageWidth = width;
		imageHeight = height;
		isDynamicImage = false;
	}
	
	public ScreenImage(Rologia os, String imagePath, Position pos, int width, int height) {
		super(os, pos);
		location = new ResourceLocation(imagePath);
		imageWidth = width;
		imageHeight = height;
		isDynamicImage = false;
	}
	
	public void drawComponent() {
		if(isDynamicImage)
		{
			getTextureManager().bindTexture(location);
			drawModalRectWithCustomSizedTexture(getPosition().getX(), getPosition().getY(), 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
		}
		else
		{
			getTextureManager().bindTexture(location);
			drawModalRectWithCustomSizedTexture(getPosition().getX(), getPosition().getY(), 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
		}
	}
	
	@Override
	public int getWidth() {
		return (int) (imageWidth * scale);
	}
	
	@Override
	public int getHeight() {
		return (int) (imageHeight * scale);
	}

	public ResourceLocation getResourceLocation() {
		if(location != null)
			return location;
		
		return null;
	}

	@Override
	public void mouseClick(Position mousePos, int mouseButtonClicked) {}

}
