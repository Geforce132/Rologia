package net.geforce.smartwatch.gui.rologia.screens;

import java.util.ArrayList;

import net.geforce.smartwatch.gui.rologia.components.ScreenComponent;
import net.geforce.smartwatch.gui.rologia.components.ScreenImage;
import net.geforce.smartwatch.gui.rologia.components.ScreenText;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureManager;

public abstract class Screen extends Gui {
	
	public static final int SCREEN_WIDTH = 240;
	public static final int SCREEN_HEIGHT = 160;
	
	private int mousePosX = 0;
	private int mousePosY = 0;
	
	protected ArrayList<ScreenImage> images = new ArrayList<ScreenImage>();
	protected ArrayList<ScreenComponent> components = new ArrayList<ScreenComponent>();

	public abstract void initializeScreen();
	
	public abstract void onComponentClicked(ScreenComponent component, int mouseX, int mouseY);
	
	public void drawImages() {
		getBackgroundImage().drawComponent();
		
		for(ScreenImage image : images)
		{
			image.drawComponent();
		}
	}
	
	public void drawComponents() {
		for(ScreenComponent component : components)
		{
			component.drawComponent();
		}
	}
	
	public void editImages() {}
	
	public void editComponents() {}
	
	public void onScreenOpened() {}
	
	public void onScreenClosed() {}
	
	public void onScreenSwitched() {}
		
	public void addComponent(ScreenComponent component) {
		if(!components.contains(component))
			components.add(component);
	}
	
	public void addTextComponent(String text, int xPos, int yPos, int color) {
		addComponent(new ScreenText(text, xPos, yPos, color));
	}
	
	public void handleMouseClick(int mouseX, int mouseY, int mouseButtonClicked) {
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
	
	public int getMouseX() {
		return mousePosX;
	}
	
	public int getMouseY() {
		return mousePosY;
	}
	
	public ScreenImage getBackgroundImage() {
		return ScreenImage.DEFAULT;
	}
	
	public TextureManager getTextureManager() {
		return Minecraft.getMinecraft().getTextureManager();
	}
	
	public FontRenderer getFontRenderer() {
		return Minecraft.getMinecraft().fontRendererObj;
	}
	
}
