package net.geforce.smartwatch.gui.rologia.components;

import java.awt.image.BufferedImage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

public class ScreenImage extends ScreenComponent {
	
	public static ScreenImage DEFAULT = new ScreenImage("minewatch:textures/gui/watch/default.png", 0, 0, 64, 64);
	
	private ResourceLocation location;
	private int imageWidth;
	private int imageHeight;
	private boolean isDynamicImage;
	
	public ScreenImage(BufferedImage image, int x, int y) {
		super(x, y);
		location = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation(image.toString(), new DynamicTexture(image));
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
		isDynamicImage = true;
	}
	
	public ScreenImage(ResourceLocation imageLocation, int x, int y, int width, int height) {
		super(x, y);
		location = imageLocation;
		imageWidth = width;
		imageHeight = height;
		isDynamicImage = false;
	}
	
	public ScreenImage(String imagePath, int x, int y, int width, int height) {
		super(x, y);
		location = new ResourceLocation(imagePath);
		imageWidth = width;
		imageHeight = height;
		isDynamicImage = false;
	}
	
	public void drawComponent() {
		if(isDynamicImage)
		{
			getTextureManager().bindTexture(location);
			drawModalRectWithCustomSizedTexture(xPos, yPos, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
		}
		else
		{
			getTextureManager().bindTexture(location);
			drawTexturedModalRect(xPos, yPos, 0, 0, imageWidth, imageHeight);
		}
	}
	
	@Override
	public int getWidth() {
		return imageWidth;
	}
	
	@Override
	public int getHeight() {
		return imageHeight;
	}

	public ResourceLocation getResourceLocation() {
		if(location != null)
			return location;
		
		return null;
	}

	@Override
	public void mouseClick(int mouseX, int mouseY, int mouseButtonClicked) {
		// TODO Auto-generated method stub
		
	}

}
