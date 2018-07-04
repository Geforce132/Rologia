package net.geforce.smartwatch.gui.rologia.screens;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import net.geforce.smartwatch.gui.rologia.components.ScreenComponent;
import net.geforce.smartwatch.gui.rologia.components.ScreenStatusBar;
import net.geforce.smartwatch.gui.rologia.components.images.ScreenImage;
import net.geforce.smartwatch.gui.rologia.components.text.ScreenText;
import net.geforce.smartwatch.gui.rologia.components.text.ScreenTimeText;
import net.geforce.smartwatch.gui.rologia.rendering.Colors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureManager;

public abstract class Screen extends Gui {
	
	public static final int WATCH_BACKGROUND_X_SIZE = 95;
	public static final int WATCH_BACKGROUND_Y_SIZE = 125;
	public static final int WATCH_SCREEN_X_SIZE = WATCH_BACKGROUND_X_SIZE - 8;
	public static final int WATCH_SCREEN_Y_SIZE = WATCH_BACKGROUND_Y_SIZE - 7;

	private int screenXPos;
	private int screenYPos;
	
	private int mousePosX = 0;
	private int mousePosY = 0;
	
	protected ArrayList<ScreenImage> images = new ArrayList<ScreenImage>();
	protected ArrayList<ScreenComponent> components = new ArrayList<ScreenComponent>();
	
	private ScreenStatusBar statusBar;
	private ScreenImage backgroundImage;
	
	public Screen(int x, int y) {
		screenXPos = x;
		screenYPos = y;
		backgroundImage = getBackgroundImage();
		backgroundImage.setScreen(this);
		backgroundImage.setPosition(x, y);
		statusBar = new ScreenStatusBar(x, y, Colors.RED);
		statusBar.setScreen(this);
		ScreenTimeText timeText = new ScreenTimeText(x + 55, y + 1, 9999999);
		timeText.setScale(0.75F);
		statusBar.addSubComponent(timeText);
	}

	public abstract void initializeScreen();
	
	public abstract void onComponentClicked(ScreenComponent component, int mouseX, int mouseY);
	
	public void drawImages() {
		GL11.glPushMatrix();
		
		backgroundImage.performPrerenderGLFixes();
		backgroundImage.drawComponent();
		
		GL11.glPopMatrix();
		
		for(ScreenImage image : images)
		{
			GL11.glPushMatrix();
			image.performPrerenderGLFixes();
			image.drawComponent();
			GL11.glPopMatrix();
		}
	}
	
	public void editComponents() {
		for(ScreenComponent component : components)
		{
			editComponent(component);
		}
	}

	public void drawComponents() {
		for(ScreenComponent component : components)
		{
			GL11.glPushMatrix();
			component.performPrerenderGLFixes();
			component.drawComponent();
			GL11.glPopMatrix();

			component.drawSubComponents();
		}
	}

	public void drawStatusBar() {
		GL11.glPushMatrix();
		statusBar.drawComponent();
		GL11.glPopMatrix();

		statusBar.drawSubComponents();
	}
	
	public void editImages() {}
	
	public void editComponent(ScreenComponent comp) {}
	
	public void editStatusBar() {}
	
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
	
	public Screen setScreenPosition(int x, int y) {
		screenXPos = x;
		screenYPos = y;

		backgroundImage.setPosition(x + 3, y + 3);
		backgroundImage.setDefaultPosition(x + 3, y + 3);

		return this;
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
	
	public int getCenteredXForComponent(ScreenComponent comp) {
		return (screenXPos + (WATCH_SCREEN_X_SIZE / 2) - (comp.getWidth() / 2));
	}
	
	public int getCenteredYForComponent(ScreenComponent comp) {
		return (screenYPos + (WATCH_SCREEN_Y_SIZE / 2) - (comp.getHeight() / 2));
	}

	public Screen setBackgroundImage(ScreenImage newImage) {
		newImage.setPosition(getXPos(), getYPos());
		backgroundImage = newImage;
		return this;
	}
	
	public abstract ScreenImage getBackgroundImage();

	public ScreenStatusBar getStatusBar() {
		return statusBar;
	}

	public TextureManager getTextureManager() {
		return Minecraft.getMinecraft().getTextureManager();
	}
	
	public FontRenderer getFontRenderer() {
		return Minecraft.getMinecraft().fontRendererObj;
	}
	
}
