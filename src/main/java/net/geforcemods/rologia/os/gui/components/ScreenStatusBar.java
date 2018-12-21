package net.geforcemods.rologia.os.gui.components;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import net.geforcemods.rologia.os.Rologia;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.misc.Position;
import net.geforcemods.rologia.os.notifications.Notification;
import net.geforcemods.rologia.os.time.TimeConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class ScreenStatusBar extends ScreenComponent {

	private Color color;

	public ScreenStatusBar(Rologia os, Color rgbColor) {
		super(os);
		color = rgbColor;
	}
	
	public ScreenStatusBar(Rologia os, Position pos, Color rgbColor) {
		super(os, pos);
		color = rgbColor;
	}

	@Override
	public void drawComponent() {
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
		
		GlStateManager.glBegin(GL11.GL_QUADS);
		GlStateManager.color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		GlStateManager.glVertex3f((float)getPosition().getX(), (float)getPosition().getY() + getHeight(), 0.0F);
        GlStateManager.glVertex3f((float)getPosition().getX() + getWidth(), (float)getPosition().getY() + getHeight(), 0.0F);
        GlStateManager.glVertex3f((float)getPosition().getX() + getWidth(), (float)getPosition().getY(), 0.0F);
        GlStateManager.glVertex3f((float)getPosition().getX(), (float)getPosition().getY(), 0.0F);
        GlStateManager.glEnd();
        
		GlStateManager.disableBlend();
		GlStateManager.enableTexture2D();
        
        float scaleOfText = 0.85F;
		GlStateManager.scale(scaleOfText, scaleOfText, 1F);
        drawString(getFontRenderer(), getScreen().getOS().getTime(TimeConstants.HM), (int) ((getPosition().getX() + 50) / scaleOfText), (int) ((getPosition().getY() + 1) / scaleOfText), 44444);
		GlStateManager.popMatrix();
        
        //Draw notifications
        for(int i = 0; i < getOS().getNotifications().size(); i++) {
        	if(i > 3) {
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
        	
        	getOS().getNotifications().get(i).drawNotification(getScreen());
        }
    }
	
	@Override
	public void mouseClick(Position mousePos, int mouseButtonClicked) {}
	
	public void setColor(Color rgbColor) {
		color = rgbColor;
	}
	
	@Override
	public int getWidth() {
		return Screen.WATCH_SCREEN_X_SIZE;
	}
	
	@Override
	public int getHeight() {
		return 7;
	}

}