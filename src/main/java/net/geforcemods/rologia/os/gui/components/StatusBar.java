package net.geforcemods.rologia.os.gui.components;

import com.mojang.blaze3d.platform.GlStateManager;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;
import net.geforcemods.rologia.os.notifications.Notification;
import net.geforcemods.rologia.os.time.TimeConstants;
import net.minecraft.client.Minecraft;

public class StatusBar extends ScreenComponent {
	
	public static final int DEFAULT_WIDTH = 7;

	public StatusBar(RologiaOS os) {
		super(os);
	}
	
	public StatusBar(RologiaOS os, Position pos) {
		super(os, pos);
	}

	@Override
	public void drawComponent() {
		GlStateManager.func_227626_N_();

		GlStateManager.func_227702_d_(1, 1, 1, 1);
		GuiUtils.drawFilledRect(getPosition(), getWidth(), getHeight(), getTheme().STATUS_BAR, 0.5F);

        float scaleOfText = 0.85F;
		GlStateManager.func_227672_b_(scaleOfText, scaleOfText, 1F);
        drawString(getFontRenderer(), getScreen().getOS().getTime(TimeConstants.HM), (int) ((getPosition().getX() + 50) / scaleOfText), (int) ((getPosition().getY() + 1) / scaleOfText), GuiUtils.toHex(getTheme().STATUS_BAR_CLOCK));
        GlStateManager.func_227627_O_();
        
        //Draw notifications
        drawNotifications();
    }

	private void drawNotifications() {
		for(int i = 0; i < getOS().getNotifications().size(); i++) {
			if(i > 3 && !isExpanded()) {
				GlStateManager.func_227626_N_();
				Minecraft.getInstance().getTextureManager().bindTexture(Notification.NOTIFICATION_ICONS_LIGHT);

				GlStateManager.func_227702_d_(1, 1, 1, 1);
				GlStateManager.func_227670_b_(getPosition().shiftX(40).getX(), getPosition().getY(), 0);
				GlStateManager.func_227672_b_(0.2F, 0.2F, 0);
				GlStateManager.func_227670_b_(-getPosition().shiftX(40).getX(), -getPosition().getY(), 0);
				GuiUtils.drawTexturedModalRect(getPosition().shiftX(40).getX(), getPosition().getY(), 38, 0, 37, 33, this.getBlitOffset());
				GlStateManager.func_227627_O_();

				break;
			}

			if(isExpanded())
				getOS().getNotifications().get(i).drawNotification(getScreen(), getPosition().shiftX(1).shiftY(i * Notification.NOTIFICATION_HEIGHT), true);
			else
				getOS().getNotifications().get(i).drawNotification(getScreen(), getPosition().shiftX(1 + (i * 10)), false);
		}
	}
	
	public boolean isExpanded() {
		return getScreen().getFocusedComponent() == this && getOS().hasNotifications();
	}

	@Override
	public boolean mouseClick(Position mousePos, int mouseButtonClicked) {
		if(mousePos.getY() >= getScreen().getPosition().getY() && mousePos.getY() <= getScreen().getPosition().getY() + DEFAULT_WIDTH && getOS().hasNotifications())
			return true;

		for(int i = 0; i < getOS().getNotifications().size(); i++) {
			if(getOS().getNotifications().get(i).isMouseHoveringOverXButton(getScreen())) {
				getOS().removeNotification(getOS().getNotifications().get(i));
				break;
			}

		}

		return false;
	}

	@Override
	public boolean isSystemComponent() {
		return true;
	}

	@Override
	public int getWidth() {
		return Screen.WATCH_SCREEN_X_SIZE;
	}

	@Override
	public int getHeight() {
		return isExpanded() ? DEFAULT_WIDTH + (getOS().getNotifications().size() * Notification.NOTIFICATION_HEIGHT) : DEFAULT_WIDTH;
	}

}