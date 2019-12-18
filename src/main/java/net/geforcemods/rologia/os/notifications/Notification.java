package net.geforcemods.rologia.os.notifications;

import com.mojang.blaze3d.platform.GlStateManager;

import net.geforcemods.rologia.os.apps.App;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;
import net.geforcemods.rologia.os.resources.ResourceLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class Notification {
	
	public static final int NOTIFICATION_WIDTH = 85;
	public static final int NOTIFICATION_HEIGHT = 26;
	
	public static ResourceLocation NOTIFICATION_BOX = new ResourceLocation(ResourceLoader.TEXTURE_FOLDER_PATH + "notifications.png");
	public static ResourceLocation NOTIFICATION_ICONS_LIGHT = new ResourceLocation(ResourceLoader.TEXTURE_FOLDER_PATH + "icons_light.png");
	public static ResourceLocation NOTIFICATION_ICONS_DARK = new ResourceLocation(ResourceLoader.TEXTURE_FOLDER_PATH + "icons_dark.png");

	private App sourceApp;
	private String notificationTitle;
	private String notificationBody;
	private int slotNumber = 0;

	public Notification(Screen screen, App app, String title, String body) {
		sourceApp = app;
		notificationTitle = title;
		notificationBody = body;
	}
	
	public void drawNotification(Screen screen, Position position, boolean expanded) {
		if(expanded) {
			// If the status bar has been clicked
			if(slotNumber <= 2) {
				// Render texture
				GlStateManager.func_227626_N_();
				GlStateManager.func_227702_d_(1, 1, 1, 1);
				Minecraft.getInstance().getTextureManager().bindTexture(NOTIFICATION_BOX);

				///if(isMouseHoveringOverXButton(screen))
					//GuiUtils.drawTexturedModalRect(position.getX(), position.shiftY(7).getY(), 86, 0, 85, 25);
				//else
					//GuiUtils.drawTexturedModalRect(position.getX(), position.shiftY(7).getY(), 0, 0, 85, 25);

				// Draw title string
				GlStateManager.func_227670_b_(position.getX(), 10 + position.getY(), 0);
				GlStateManager.func_227672_b_(0.9F, 0.9F, 0);
				GlStateManager.func_227670_b_(-position.getX(), -(10 + position.getY()), 0);
				screen.drawString(Minecraft.getInstance().fontRenderer, notificationTitle, position.shiftX(22).getX(), 10 + position.getY(), GuiUtils.toHex(screen.getOS().getTheme().NOTIFICATION_TITLE));

				// Draw body string
				GlStateManager.func_227670_b_(position.getX(), 22 + position.getY(), 0);
				GlStateManager.func_227672_b_(0.75F, 0.75F, 0);
				GlStateManager.func_227670_b_(-position.getX(), -(22 + position.getY()), 0);
				screen.drawString(Minecraft.getInstance().fontRenderer, notificationBody, position.shiftX(31).getX(), 22 + position.getY(), GuiUtils.toHex(screen.getOS().getTheme().NOTIFICATION_BODY));
				GlStateManager.func_227627_O_();

				// Render icon
				GlStateManager.func_227626_N_();
				Minecraft.getInstance().getTextureManager().bindTexture(NOTIFICATION_ICONS_DARK);
				GlStateManager.func_227670_b_(position.getX(), position.getY(), 0);
				GlStateManager.func_227672_b_(0.415F, 0.415F, 0);
				GlStateManager.func_227670_b_(-position.getX(), -position.getY(), 0);
				//screen.drawTexturedModalRect((position.getX() + 4), position.getY() + (7) + 22, -1, 0, 40, 33);
				GlStateManager.func_227627_O_();

			}
		}
		else {
			// If the status bar hasn't been clicked
			GlStateManager.func_227626_N_();
			Minecraft.getInstance().getTextureManager().bindTexture(NOTIFICATION_ICONS_LIGHT);
		
			GlStateManager.func_227702_d_(1, 1, 1, 1);
			GlStateManager.func_227670_b_(position.getX(), position.getY(), 0);
			GlStateManager.func_227672_b_(0.2F, 0.2F, 0);
			GlStateManager.func_227670_b_(-position.getX(), -position.getY(), 0);
			//screen.drawTexturedModalRect(position.getX(), position.getY(), -1, 0, 38, 33);
			GlStateManager.func_227627_O_();
		}
	}

	public boolean isMouseHoveringOverXButton(Screen screen) {
		int notificationEndWidth = screen.getPosition().getX() + NOTIFICATION_WIDTH;
		int notificationBeginWidth = notificationEndWidth - 10;
		int notificationEndHeight = screen.getPosition().getY() + (((slotNumber + 1) * NOTIFICATION_HEIGHT) - 8);
		int notificationBeginHeight = notificationEndHeight - 10;
		Position mousePos = screen.getMousePosition();

		return mousePos.getX() >= notificationBeginWidth && mousePos.getX() <= notificationEndWidth && mousePos.getY() >= notificationBeginHeight && mousePos.getY() <= notificationEndHeight;
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

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int number) {
		slotNumber = number;
	}

}