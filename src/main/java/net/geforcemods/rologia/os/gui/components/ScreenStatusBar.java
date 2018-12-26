package net.geforcemods.rologia.os.gui.components;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.utils.Colors;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;
import net.geforcemods.rologia.os.notifications.Notification;
import net.geforcemods.rologia.os.time.TimeConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class ScreenStatusBar extends ScreenComponent {

	private Color color;

	public ScreenStatusBar(RologiaOS os, Color rgbColor) {
		super(os);
		color = rgbColor;
	}
	
	public ScreenStatusBar(RologiaOS os, Position pos, Color rgbColor) {
		super(os, pos);
		color = rgbColor;
	}

	@Override
	public void drawComponent() {
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
		
		GlStateManager.glBegin(GL11.GL_QUADS);
		GlStateManager.color(color.getRed(), color.getGreen(), color.getBlue(), 0);
		GlStateManager.glVertex3f((float)getPosition().getX(), (float)getPosition().getY() + getHeight(), 0.0F);
        GlStateManager.glVertex3f((float)getPosition().getX() + getWidth(), (float)getPosition().getY() + getHeight(), 0.0F);
        GlStateManager.glVertex3f((float)getPosition().getX() + getWidth(), (float)getPosition().getY(), 0.0F);
        GlStateManager.glVertex3f((float)getPosition().getX(), (float)getPosition().getY(), 0.0F);
        GlStateManager.glEnd();
        
		GlStateManager.disableBlend();
		GlStateManager.enableTexture2D();
        
        float scaleOfText = 0.85F;
		GlStateManager.scale(scaleOfText, scaleOfText, 1F);
        drawString(getFontRenderer(), getScreen().getOS().getTime(TimeConstants.HM), (int) ((getPosition().getX() + 50) / scaleOfText), (int) ((getPosition().getY() + 1) / scaleOfText), Colors.AQUAMARINE.hexValue);
		GlStateManager.popMatrix();
        
        //Draw notifications
        drawNotifications();
    }

	private void drawNotifications() {
		if(isExpanded()) {
			GlStateManager.pushMatrix();
			GlStateManager.color(1, 1, 1, 1);
			GuiUtils.drawFilledRect(getScreen().getPosition(), getWidth(), getHeight() + 1, Colors.BLACK.color, true);
			GlStateManager.popMatrix();
		}

		for(int i = 0; i < getOS().getNotifications().size(); i++) {
			if(i > 3 && !isExpanded()) {
				GlStateManager.pushMatrix();
				Minecraft.getMinecraft().getTextureManager().bindTexture(Notification.NOTIFICATION_ICONS_LIGHT);

				GlStateManager.color(1, 1, 1, 1);
				GlStateManager.translate(getPosition().shiftX(40).getX(), getPosition().getY(), 0);
				GlStateManager.scale(0.2F, 0.2F, 0);
				GlStateManager.translate(-getPosition().shiftX(40).getX(), -getPosition().getY(), 0);
				drawTexturedModalRect(getPosition().shiftX(40).getX(), getPosition().getY(), 38, 0, 37, 33);
				GlStateManager.popMatrix();

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
		if(mousePos.getY() >= getScreen().getPosition().getY() && mousePos.getY() <= getScreen().getPosition().getY() + 7 && getOS().hasNotifications())
			return true;

		for(int i = 0; i < getOS().getNotifications().size(); i++) {
			if(getOS().getNotifications().get(i).isMouseHoveringOverXButton(getScreen())) {
				getOS().removeNotification(getOS().getNotifications().get(i));
				break;
			}

		}

		return false;
	}
	
	public void setColor(Color rgbColor) {
		color = rgbColor;
	}
	
	@Override
	public int getWidth() {
		return Screen.WATCH_SCREEN_X_SIZE;
	}
	
	@Override
	public int getHeight() {
		return isExpanded() ? 7 + (getOS().getNotifications().size() * Notification.NOTIFICATION_HEIGHT) : 7;
	}

}