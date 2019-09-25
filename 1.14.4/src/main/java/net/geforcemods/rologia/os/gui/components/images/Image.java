package net.geforcemods.rologia.os.gui.components.images;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.misc.Position;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.util.ResourceLocation;

public class Image extends ScreenComponent {
	
	private ResourceLocation location;
	private int imageWidth;
	private int imageHeight;
	
	public Image(RologiaOS os, String imagePath, int width, int height) {
		super(os);
		location = new ResourceLocation(imagePath);
		imageWidth = width;
		imageHeight = height;
	}
	
	public Image(RologiaOS os, ResourceLocation imageLocation, int width, int height) {
		super(os);
		location = imageLocation;
		imageWidth = width;
		imageHeight = height;
	}

	public Image(RologiaOS os, NativeImage image) {
		super(os);
		location = Minecraft.getInstance().getTextureManager().getDynamicTextureLocation(image.toString(), new DynamicTexture(image));
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
	}
	
	public Image(RologiaOS os, NativeImage image, Position pos) {
		super(os, pos);
		location = Minecraft.getInstance().getTextureManager().getDynamicTextureLocation(image.toString(), new DynamicTexture(image));
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
	}
	
	public Image(RologiaOS os, ResourceLocation imageLocation, Position pos, int width, int height) {
		super(os, pos);
		location = imageLocation;
		imageWidth = width;
		imageHeight = height;
	}
	
	public Image(RologiaOS os, String imagePath, Position pos, int width, int height) {
		super(os, pos);
		location = new ResourceLocation(imagePath);
		imageWidth = width;
		imageHeight = height;
	}
	
	public void drawComponent() {
		getTextureManager().bindTexture(location);
		AbstractGui.blit(getPosition().getX(), getPosition().getY(), 0, 0, imageWidth, imageHeight, Screen.WATCH_SCREEN_X_SIZE, Screen.WATCH_SCREEN_Y_SIZE); // TODO make sure this doesn't break anything
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

}
