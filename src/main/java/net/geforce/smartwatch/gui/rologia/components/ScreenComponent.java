package net.geforce.smartwatch.gui.rologia.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureManager;

public abstract class ScreenComponent extends Gui {
	
	protected int xPos;
	protected int yPos;
	protected int defaultXPos;
	protected int defaultYPos;
	
	protected ScreenComponent(int x, int y) {
		setPosition(x, y);
		defaultXPos = x;
		defaultYPos = y;
	}
	
	public abstract void drawComponent();
	
	public abstract void mouseClick(int mouseX, int mouseY, int mouseButtonClicked);
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public abstract int getWidth();
	
	public abstract int getHeight();
	
	public void setPosition(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	public int getDefaultXPos() {
		return defaultXPos;
	}
	
	public int getDefaultYPos() {
		return defaultYPos;
	}
	
	public TextureManager getTextureManager() {
		return Minecraft.getMinecraft().getTextureManager();
	}
	
	public FontRenderer getFontRenderer() {
		return Minecraft.getMinecraft().fontRendererObj;
	}
	
	/*@Override
	public boolean equals(Object comp) {
		if(comp instanceof ScreenComponent)
			return ((ScreenComponent) comp).getName().matches(getName());
		
		return false;
	}*/

}
