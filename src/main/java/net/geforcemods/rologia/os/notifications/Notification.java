package net.geforcemods.rologia.os.notifications;

import net.geforcemods.rologia.os.apps.App;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.utils.Colors;
import net.geforcemods.rologia.os.misc.Position;
import net.geforcemods.rologia.os.resources.ResourceLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class Notification {
	
	public static ResourceLocation NOTIFICATION_BOX = new ResourceLocation(ResourceLoader.TEXTURE_FOLDER_PATH + "notifications.png");
	public static ResourceLocation NOTIFICATION_ICONS_LIGHT = new ResourceLocation(ResourceLoader.TEXTURE_FOLDER_PATH + "icons_light.png");
	public static ResourceLocation NOTIFICATION_ICONS_DARK = new ResourceLocation(ResourceLoader.TEXTURE_FOLDER_PATH + "icons_dark.png");

	private App sourceApp;
	private String notificationTitle;
	private String notificationBody;

	public Notification(Screen screen, App app, String title, String body, int slotInStatusBar) {
		sourceApp = app;
		notificationTitle = title;
		notificationBody = body;
	}
	
	public void drawNotification(Screen screen, Position position, int slotNumber, boolean expanded) {		
		if(expanded) {
			// If the status bar has been clicked
			if(slotNumber <= 3) {
				GlStateManager.pushMatrix();

				// Render texture
				GlStateManager.color(1, 1, 1, 1);
				Minecraft.getMinecraft().getTextureManager().bindTexture(NOTIFICATION_BOX);

				screen.drawTexturedModalRect(position.getX(), position.shiftY(screen.getStatusBar().getHeight() + (slotNumber * 26)).getY(), 0, 0, 85, 25);

				// Draw title string
				GlStateManager.translate(position.shiftX(3).getX(), 10 + position.shiftY(slotNumber * 26).getY(), 0);
				GlStateManager.scale(0.9F, 0.9F, 0);
				GlStateManager.translate(-position.shiftX(3).getX(), -(10 + position.shiftY(slotNumber * 26).getY()), 0);
				screen.drawString(Minecraft.getMinecraft().fontRenderer, notificationTitle, position.shiftX(3).getX(), 10 + position.shiftY(slotNumber * 26).getY(), Colors.DARK_GREEN.hexValue);

				// Draw body string
				GlStateManager.translate(position.shiftX(3).getX(), 22 + position.shiftY(slotNumber * 26).getY(), 0);
				GlStateManager.scale(0.75F, 0.75F, 0);
				GlStateManager.translate(-position.shiftX(3).getX(), -(22 + position.shiftY(slotNumber * 26).getY()), 0);
				screen.drawString(Minecraft.getMinecraft().fontRenderer, notificationBody, position.shiftX(3).getX(), 22 + position.shiftY(slotNumber * 26).getY(), Colors.GREEN.hexValue);

				GlStateManager.popMatrix();				
			}
		}
		else {
			// If the status bar hasn't been clicked
			GlStateManager.pushMatrix();
			Minecraft.getMinecraft().getTextureManager().bindTexture(NOTIFICATION_ICONS_LIGHT);
		
			GlStateManager.color(1, 1, 1, 1);
			GlStateManager.translate(position.shiftX(slotNumber * 12).getX(), position.getY(), 0);
			GlStateManager.scale(0.2F, 0.2F, 0);
			GlStateManager.translate(-position.shiftX(slotNumber * 12).getX(), -position.getY(), 0);
			screen.drawTexturedModalRect(position.getX(), position.getY(), -1, 0, 38, 33);
			GlStateManager.popMatrix();
		}
	}

	public App getSourceApp() {
		return sourceApp;
	}
	
	public boolean fromApp() {
		return sourceApp != null;
	}
	
	public String getTitle() {
		return notificationTitle;
	}

	public String getBody() {
		return notificationBody;
	}

}
