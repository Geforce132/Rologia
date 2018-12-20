package net.geforcemods.rologia.os.notifications;

import net.geforcemods.rologia.os.apps.App;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.misc.Position;
import net.geforcemods.rologia.os.resources.ResourceLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class Notification {
	
	private App sourceApp;
	private ResourceLocation notificationIcon = new ResourceLocation(ResourceLoader.TEXTURE_FOLDER_PATH + "icons_light.png");
	private String notificationText;
	
	public Notification(App app, String text) {
		sourceApp = app;
		notificationText = text;
	}
	
	public void drawNotification(Screen screen, Position position) {		
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(notificationIcon);
		
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.translate(position.getX(), position.getY(), 0);
		GlStateManager.scale(0.2F, 0.2F, 0);
		GlStateManager.translate(-position.getX(), -position.getY(), 0);
		screen.drawTexturedModalRect(position.getX(), position.getY(), 0, 0, 37, 33);
		GlStateManager.popMatrix();
	}
	
	public App getSourceApp() {
		return sourceApp;
	}
	
	public String getText() {
		return notificationText;
	}

}
