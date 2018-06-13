package net.geforce.smartwatch.gui.rologia.components;

import net.geforce.smartwatch.gui.rologia.screens.Screen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureManager;

public abstract class ScreenComponent extends Gui {
	
	protected int xPos;
	protected int yPos;
	protected int defaultXPos;
	protected int defaultYPos;
	
	protected float rotation;
	protected float scale;
	
	private Screen screen;
	
	protected ScreenComponent(int x, int y) {
		setPosition(x, y);
		defaultXPos = x;
		defaultYPos = y;
		
		rotation = 0F;
		scale = 1F;
	}
	
	public abstract void drawComponent();
	
	public abstract void mouseClick(int mouseX, int mouseY, int mouseButtonClicked);
	
	public void setPosition(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	public void resetPosition() {
		xPos = defaultXPos;
		yPos = defaultYPos;
	}
	
	public void setRotation(float newRotation) {
		rotation = newRotation;
	}
	
	public void setScale(float newScale) {
		scale = newScale;
	}
	
	public void setScreen(Screen newScreen) {
		screen = newScreen;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public abstract int getWidth();
	
	public abstract int getHeight();
	
	public int getDefaultXPos() {
		return defaultXPos;
	}
	
	public int getDefaultYPos() {
		return defaultYPos;
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public float getScale() {
		return scale;
	}
	
	public Screen getScreen() {
		return screen;
	}
	
	public TextureManager getTextureManager() {
		return Minecraft.getMinecraft().getTextureManager();
	}
	
	public FontRenderer getFontRenderer() {
		return Minecraft.getMinecraft().fontRendererObj;
	}

}
