package net.geforcemods.rologia.os.notifications;

import net.geforcemods.rologia.os.apps.App;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.misc.Position;
import net.geforcemods.rologia.os.resources.ResourceLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class Notification {
	
	public static ResourceLocation NOTIFICATION_ICONS_LIGHT = new ResourceLocation(ResourceLoader.TEXTURE_FOLDER_PATH + "icons_light.png");
	public static ResourceLocation NOTIFICATION_ICONS_DARK = new ResourceLocation(ResourceLoader.TEXTURE_FOLDER_PATH + "icons_dark.png");

	private Position position;

	private App sourceApp;
	private String notificationText;
	
	public Notification(Screen screen, App app, String text, int slotInStatusBar) {
		sourceApp = app;
		notificationText = text;
		position = screen.getStatusBar().getPosition().shiftX((slotInStatusBar - 1) * 10);
	}
	
	public void drawNotification(Screen screen) {		
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(NOTIFICATION_ICONS_LIGHT);
		
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.translate(position.getX(), position.getY(), 0);
		GlStateManager.scale(0.2F, 0.2F, 0);
		GlStateManager.translate(-position.getX(), -position.getY(), 0);
		screen.drawTexturedModalRect(position.getX(), position.getY(), 0, 0, 37, 33);
		GlStateManager.popMatrix();
	}
	
	public Position getPosition() {
		return position;
	}

	public App getSourceApp() {
		return sourceApp;
	}
	
	public String getText() {
		return notificationText;
	}

}
