package net.geforce.smartwatch.rologia.os.apps;

import java.awt.image.BufferedImage;

import net.geforce.smartwatch.rologia.gui.screens.Screen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

public class AppImage {
	
	private ResourceLocation location;
	private int imageWidth;
	private int imageHeight;
	private boolean isDynamicImage;
	
	public AppImage(Screen screen, ResourceLocation imageLocation) {
		location = imageLocation;
		imageWidth = 64;
		imageHeight = 64;
	}
	
	public AppImage(Screen screen, BufferedImage image) {
		location = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation(image.toString(), new DynamicTexture(image));
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
	}
	
	public ResourceLocation getImage() {
		return location;
	}

}
