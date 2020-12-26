package net.geforcemods.rologia.os.gui.components;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.animations.AnimationNotificationSwipe;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;
import net.geforcemods.rologia.os.notifications.Notification;
import net.geforcemods.rologia.os.time.TimeConstants;
import net.minecraft.client.Minecraft;

public class StatusBar extends ScreenComponent {
	
	public static final int DEFAULT_HEIGHT = 7;

	public StatusBar(RologiaOS os) {
		super(os);
	}
	
	public StatusBar(RologiaOS os, Position pos) {
		super(os, pos);
	}

	@Override
	public void drawComponent(MatrixStack stack) {
		RenderSystem.pushMatrix();

		RenderSystem.color4f(1, 1, 1, 1);
		GuiUtils.drawFilledRect(getPosition(), getWidth(), getHeight(), getTheme().STATUS_BAR, 0.5F);

        float scaleOfText = 0.85F;
        RenderSystem.scalef(scaleOfText, scaleOfText, 1F);
        drawString(stack, getFontRenderer(), getScreen().getOS().getTime(TimeConstants.HM), (int) ((getPosition().getX() + 50) / scaleOfText), (int) ((getPosition().getY() + 1) / scaleOfText), GuiUtils.toHex(getTheme().STATUS_BAR_CLOCK));
        RenderSystem.popMatrix();
        
        //Draw notifications
        drawNotifications(stack);
    }

	private void drawNotifications(MatrixStack stack) {
		for(int i = 0; i < getOS().getNotifications().size(); i++) {
			if(i > 3 && !isExpanded()) {
				RenderSystem.pushMatrix();
				Minecraft.getInstance().getTextureManager().bindTexture(Notification.NOTIFICATION_ICONS_LIGHT);

				RenderSystem.color4f(1, 1, 1, 1);
				RenderSystem.translated(getPosition().shiftX(40).getX(), getPosition().getY(), 0);
				RenderSystem.scalef(0.2F, 0.2F, 0);
				RenderSystem.translated(-getPosition().shiftX(40).getX(), -getPosition().getY(), 0);
				GuiUtils.drawTexturedModalRect(getPosition().shiftX(40).getX(), getPosition().getY(), 38, 0, 37, 33, this.getBlitOffset());
				RenderSystem.popMatrix();

				break;
			}

			if(isExpanded())
				getOS().getNotifications().get(i).drawNotification(getScreen(), stack, getPosition().shiftX(1).shiftY(i * Notification.NOTIFICATION_HEIGHT), true);
			else
				getOS().getNotifications().get(i).drawNotification(getScreen(), stack, getPosition().shiftX(1 + (i * 10)), false);
		}
	}
	
	public boolean isExpanded() {
		return getScreen().getFocusedComponent() == this && getOS().hasNotifications();
	}

	@Override
	public boolean mouseClick(Position mousePos, int mouseButtonClicked) {
		if(mousePos.getY() >= getScreen().getPosition().getY() && mousePos.getY() <= getScreen().getPosition().getY() + DEFAULT_HEIGHT && getOS().hasNotifications())
			return true;

		for(int i = 0; i < getOS().getNotifications().size(); i++) {
			if(getOS().getNotifications().get(i).isMouseHoveringOverXButton(getScreen())) {
				//getOS().removeNotification(getOS().getNotifications().get(i));
				getOS().beginAnimation(new AnimationNotificationSwipe(getOS().getNotifications().get(i)));
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
		if(isExpanded()) {
			if(getOS().getNotifications().size() > 3)
				return DEFAULT_HEIGHT + (3 * Notification.NOTIFICATION_HEIGHT);

			return DEFAULT_HEIGHT + (getOS().getNotifications().size() * Notification.NOTIFICATION_HEIGHT);
		}

		return DEFAULT_HEIGHT;
	}

}