package net.geforcemods.rologia.os.notifications;

import com.mojang.blaze3d.systems.RenderSystem;

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

	private Screen source;
	private String notificationTitle;
	private String notificationBody;
	private int slotNumber = 0;

	public Notification(Screen screen, String title, String body) {
		source = screen;
		notificationTitle = title;
		notificationBody = body;
	}
	
	public void drawNotification(Screen screen, Position position, boolean expanded) {
		if(expanded) {
			// If the status bar has been clicked
			if(slotNumber <= 2) {
				// Render texture
				RenderSystem.pushMatrix();
				RenderSystem.color4f(1, 1, 1, 1);
				Minecraft.getInstance().getTextureManager().bindTexture(NOTIFICATION_BOX);

				if(isMouseHoveringOverXButton(screen))
					GuiUtils.drawTexturedModalRect(position.getX(), position.shiftY(7).getY(), 86, 0, 85, 25, 0);
				else
					GuiUtils.drawTexturedModalRect(position.getX(), position.shiftY(7).getY(), 0, 0, 85, 25, 0);

				// Draw title string
				RenderSystem.translated(position.getX(), 10 + position.getY(), 0);
				RenderSystem.scalef(0.9F, 0.9F, 0);
				RenderSystem.translated(-position.getX(), -(10 + position.getY()), 0);
				Minecraft.getInstance().fontRenderer.drawString(notificationTitle, position.shiftX(22).getX(), 10 + position.getY(), GuiUtils.toHex(screen.getOS().getTheme().NOTIFICATION_TITLE));
				screen.drawString(Minecraft.getInstance().fontRenderer, notificationTitle, position.shiftX(22).getX(), 10 + position.getY(), GuiUtils.toHex(screen.getOS().getTheme().NOTIFICATION_TITLE), false);

				// Draw body string
				RenderSystem.translated(position.getX(), 22 + position.getY(), 0);
				RenderSystem.scalef(0.75F, 0.75F, 0);
				RenderSystem.translated(-position.getX(), -(22 + position.getY()), 0);
				screen.drawString(Minecraft.getInstance().fontRenderer, notificationBody, position.shiftX(31).getX(), 22 + position.getY(), GuiUtils.toHex(screen.getOS().getTheme().NOTIFICATION_BODY), false);
				RenderSystem.popMatrix();

				// Render icon
				RenderSystem.pushMatrix();
				Minecraft.getInstance().getTextureManager().bindTexture(NOTIFICATION_ICONS_DARK);
				RenderSystem.translated(position.getX(), position.getY(), 0);
				RenderSystem.scalef(0.415F, 0.415F, 0);
				RenderSystem.translated(-position.getX(), -position.getY(), 0);
				//screen.drawTexturedModalRect((position.getX() + 4), position.getY() + (7) + 22, -1, 0, 40, 33);
				RenderSystem.popMatrix();

			}
		}
		else {
			// If the status bar hasn't been clicked
			RenderSystem.pushMatrix();
			Minecraft.getInstance().getTextureManager().bindTexture(NOTIFICATION_ICONS_LIGHT);
		
			RenderSystem.color4f(1, 1, 1, 1);
			RenderSystem.translated(position.getX(), position.getY(), 0);
			RenderSystem.scalef(0.2F, 0.2F, 0);
			RenderSystem.translated(-position.getX(), -position.getY(), 0);
			//screen.drawTexturedModalRect(position.getX(), position.getY(), -1, 0, 38, 33);
			RenderSystem.popMatrix();
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

	public Screen getSource() {
		return source;
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
