package net.geforcemods.smartwatch.rologia.gui.components.images;

import java.awt.image.BufferedImage;

import net.geforcemods.smartwatch.rologia.gui.components.ScreenComponent;
import net.geforcemods.smartwatch.rologia.gui.screens.Screen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

public class ScreenImage extends ScreenComponent {
	
	private ResourceLocation location;
	private int imageWidth;
	private int imageHeight;
	private boolean isDynamicImage;
	
	public ScreenImage(Screen screen, String imagePath, int width, int height) {
		super(screen);
		location = new ResourceLocation(imagePath);
		imageWidth = width;
		imageHeight = height;
		isDynamicImage = false;
	}
	
	public ScreenImage(Screen screen, ResourceLocation imageLocation, int width, int height) {
		super(screen);
		location = imageLocation;
		imageWidth = width;
		imageHeight = height;
	}
	
	public ScreenImage(Screen screen, BufferedImage image) {
		super(screen);
		location = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation(image.toString(), new DynamicTexture(image));
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
	}
	
	public ScreenImage(Screen screen, BufferedImage image, int x, int y) {
		super(screen, x, y);
		location = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation(image.toString(), new DynamicTexture(image));
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
		isDynamicImage = true;
	}
	
	public ScreenImage(Screen screen, ResourceLocation imageLocation, int x, int y, int width, int height) {
		super(screen, x, y);
		location = imageLocation;
		imageWidth = width;
		imageHeight = height;
		isDynamicImage = false;
	}
	
	public ScreenImage(Screen screen, String imagePath, int x, int y, int width, int height) {
		super(screen, x, y);
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
	public void mouseClick(int mouseX, int mouseY, int mouseButtonClicked) {
		// TODO Auto-generated method stub
		
	}

}
