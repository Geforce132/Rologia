package net.geforce.smartwatch.gui.rologia.screens;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import net.geforce.smartwatch.gui.rologia.components.ScreenComponent;
import net.geforce.smartwatch.gui.rologia.components.ScreenImage;
import net.geforce.smartwatch.gui.rologia.components.ScreenText;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureManager;

public abstract class Screen extends Gui {
	
	public static final int WATCH_BACKGROUND_X_SIZE = 95;
	public static final int WATCH_BACKGROUND_Y_SIZE = 125;
	public static final int WATCH_SCREEN_X_SIZE = WATCH_BACKGROUND_X_SIZE - 8;
	public static final int WATCH_SCREEN_Y_SIZE = WATCH_BACKGROUND_Y_SIZE - 7;

	private int screenXPos = 0;
	private int screenYPos = 0;
	
	private int mousePosX = 0;
	private int mousePosY = 0;
	
	protected ArrayList<ScreenImage> images = new ArrayList<ScreenImage>();
	protected ArrayList<ScreenComponent> components = new ArrayList<ScreenComponent>();

	public abstract void initializeScreen();
	
	public abstract void onComponentClicked(ScreenComponent component, int mouseX, int mouseY);
	
	public void drawImages() {		
		drawComponentWithGLFixes(getBackgroundImage());
		
		for(ScreenImage image : images)
		{
			drawComponentWithGLFixes(image);
		}
	}
	
	public void drawComponents() {
		for(ScreenComponent component : components)
		{
			drawComponentWithGLFixes(component);
		}
	}
	
	private void drawComponentWithGLFixes(ScreenComponent component) {
		GL11.glPushMatrix();
		
		GL11.glTranslatef(component.getXPos(), component.getYPos(), 0);
		GL11.glRotatef(component.getRotation(), 0, 0, 1);
		GL11.glTranslatef(-component.getXPos(), -component.getYPos(), 0);
		
		GL11.glScalef(component.getScale(), component.getScale(), 1F);
		
		if(component.getScale() != 1.0F)
			component.setPosition((int) (component.getXPos() / component.getScale()), (int) (component.getYPos() / component.getScale()));
		else if(component.getScale() == 1F)
			component.resetPosition();
		
		component.drawComponent();
		
		GL11.glPopMatrix();
	}
	
	public void editImages() {}
	
	public void editComponents() {}
	
	public void onScreenOpened() {}
	
	public void onScreenClosed() {}
	
	public void onScreenSwitched() {}
		
	public void addComponent(ScreenComponent component) {
		if(!components.contains(component))
		{
			component.setScreen(this);
			components.add(component);
		}		
	}
	
	public void addTextComponent(String text, int xPos, int yPos, int color) {
		addComponent(new ScreenText(text, xPos, yPos, color));
	}
	
	public void handleMouseClick(int mouseX, int mouseY, int mouseButtonClicked) {
		System.out.println(mouseX + " | " + mouseY);
		for(ScreenComponent component : components)
		{
			int compX, compY;
			int compWidth, compHeight;
			
			compX = component.getXPos();
			compY = component.getYPos();
			compWidth = component.getWidth();
			compHeight = component.getHeight();
			
			if(mouseX >= compX && mouseX <= (compX + compWidth) && mouseY >= compY && mouseY <= (compY + compHeight))
				onComponentClicked(component, mouseX, mouseY);
		}
	}
	
	public void setMousePos(int x, int y) {
		mousePosX = x;
		mousePosY = y;
	}
	
	public void setScreenPosition(int x, int y) {
		screenXPos = x;
		screenYPos = y;
	}
	
	public int getMouseX() {
		return mousePosX;
	}
	
	public int getMouseY() {
		return mousePosY;
	}
	
	public int getXPos() {
		return screenXPos;
	}
	
	public int getYPos() {
		return screenYPos;
	}
	
	public ScreenImage getBackgroundImage() {
		return new ScreenImage("minewatch:textures/gui/watch/default.png", getXPos() + 3, getYPos() + 3, WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);
	}
	
	public TextureManager getTextureManager() {
		return Minecraft.getMinecraft().getTextureManager();
	}
	
	public FontRenderer getFontRenderer() {
		return Minecraft.getMinecraft().fontRendererObj;
	}
	
}
