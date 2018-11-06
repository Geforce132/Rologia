package net.geforcemods.smartwatch.rologia.gui.screens;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

import net.geforcemods.smartwatch.rologia.gui.components.ScreenComponent;
import net.geforcemods.smartwatch.rologia.gui.components.ScreenStatusBar;
import net.geforcemods.smartwatch.rologia.gui.components.images.ScreenImage;
import net.geforcemods.smartwatch.rologia.gui.components.text.ScreenText;
import net.geforcemods.smartwatch.rologia.gui.rendering.Colors;
import net.geforcemods.smartwatch.rologia.os.Rologia;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureManager;

public abstract class Screen extends Gui {
	
	public static final int WATCH_BACKGROUND_X_SIZE = 95;
	public static final int WATCH_BACKGROUND_Y_SIZE = 125;
	public static final int WATCH_SCREEN_X_SIZE = WATCH_BACKGROUND_X_SIZE - 8;
	public static final int WATCH_SCREEN_Y_SIZE = WATCH_BACKGROUND_Y_SIZE - 7;
	
	private Rologia OS;
	
	private int screenXPos;
	private int screenYPos;
	
	private int mousePosX = 0;
	private int mousePosY = 0;
	
	protected ArrayList<ScreenImage> images = new ArrayList<ScreenImage>();
	protected ArrayList<ScreenComponent> components = new ArrayList<ScreenComponent>();
	
	private ScreenStatusBar statusBar;
	private ScreenImage backgroundImage;
	
	private ScreenComponent leftArrow;
	private ScreenComponent rightArrow;

	public Screen(Rologia os, int x, int y) {
		OS = os;
		
		screenXPos = x;
		screenYPos = y;
		backgroundImage = getBackgroundImage();
		backgroundImage.setPosition(x, y);
	}

	public abstract void initializeScreen();
	
	public abstract void onComponentClicked(ScreenComponent component, int mouseX, int mouseY);
	
	public void addStartupComponents() {
		statusBar = new ScreenStatusBar(OS, getXPos(), getYPos(), Colors.RED);
		
		ScreenComponent leftArrow = getComponentAsImage("arrow_left_light");
		leftArrow.centerPosition(-54, 0);
		ScreenComponent rightArrow = getComponentAsImage("arrow_right_light");
		rightArrow.centerPosition(54, 0);

		addComponent(leftArrow);
		addComponent(rightArrow);
	}
	
	public void updateScreen() {}

	public void editImages() {}

	public void drawImages() {

		GL11.glPushMatrix();
		
		//TODO Uncomment two lines below if the screen needs to be drawn in the future.
		if(OS.getCurrentApp().getAppBackgroundImage() == null)
		{
			//backgroundImage.performPrerenderGLFixes();
			//backgroundImage.drawComponent();
		}
		else
		{
			OS.getCurrentApp().getAppBackgroundImage().performPrerenderGLFixes();
			OS.getCurrentApp().getAppBackgroundImage().drawComponent();
		}
		
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
	
	public void editComponent(ScreenComponent comp) {}

	public void drawComponents() {
		for(ScreenComponent component : components)
		{
			GL11.glPushMatrix();
			component.performPrerenderGLFixes();
			component.drawComponent();
			GL11.glPopMatrix();
		}
	}

	public void drawStatusBar() {
		GL11.glPushMatrix();
		statusBar.performPrerenderGLFixes();
		statusBar.drawComponent();
		GL11.glPopMatrix();
	}

	public void editStatusBar() {}
	
	public void drawDebuggingTools(int mouseX, int mouseY) {
		GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        
		GL11.glBegin(GL11.GL_LINES);
		GL11.glColor3f(255, 0, 0);
		GL11.glVertex2f(mouseX, 0);
		GL11.glVertex2f(mouseX, Minecraft.getMinecraft().displayHeight);
		GL11.glEnd();
		
		GL11.glBegin(GL11.GL_LINES);
		GL11.glColor3f(255, 0, 0);
		GL11.glVertex2f(0, mouseY);
		GL11.glVertex2f(Minecraft.getMinecraft().displayWidth, mouseY);
		GL11.glEnd();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        
		this.drawString(getFontRenderer(), "Mouse pos: (" + mouseX + ", " + mouseY + ")", mouseX + 10, mouseY + 5, 555555);
		
		for(ScreenComponent comp : components) {
			GL11.glEnable(GL11.GL_BLEND);
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
	        
			GL11.glBegin(GL11.GL_LINES);
			GL11.glColor3f(0, 0, 255);
			GL11.glVertex2f(comp.getXPos(), comp.getYPos());
			GL11.glVertex2f(comp.getXPos() + comp.getWidth(), comp.getYPos());
			
			GL11.glVertex2f(comp.getXPos() + comp.getWidth(), comp.getYPos());
			GL11.glVertex2f(comp.getXPos() + comp.getWidth(), comp.getYPos() + comp.getHeight());
			
			GL11.glVertex2f(comp.getXPos() + comp.getWidth(), comp.getYPos() + comp.getHeight());
			GL11.glVertex2f(comp.getXPos(), comp.getYPos() + comp.getHeight());
			
			GL11.glVertex2f(comp.getXPos(), comp.getYPos());
			GL11.glVertex2f(comp.getXPos(), comp.getYPos() + comp.getHeight());

			GL11.glEnd();

	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	        GL11.glDisable(GL11.GL_BLEND);
			
	        if(statusBar.isMouseHoveringOver(mouseX, mouseY)) {
	        	this.drawString(getFontRenderer(), "--- Status bar: ---", mouseX + 10, mouseY + 25, 555555);
				this.drawString(getFontRenderer(), "X, Y pos: (" + comp.getXPos() + ", " + comp.getYPos() + ")" + " -> " + " w, h: (" + (comp.getXPos() + comp.getWidth()) + ", " + (comp.getYPos() + comp.getHeight()) + ")", mouseX + 10, mouseY + 35, 555555);
				this.drawString(getFontRenderer(), "Default pos: (" + comp.getDefaultXPos() + ", " + comp.getDefaultYPos() + ")", mouseX + 10, mouseY + 45, 555555);	        }

			if(comp.isMouseHoveringOver(mouseX, mouseY)) {
				this.drawString(getFontRenderer(), "--- Component: ---", mouseX + 10, mouseY + 25, 555555);
				this.drawString(getFontRenderer(), "X, Y pos: (" + comp.getXPos() + ", " + comp.getYPos() + ")" + " -> " + " w, h: (" + (comp.getXPos() + comp.getWidth()) + ", " + (comp.getYPos() + comp.getHeight()) + ")", mouseX + 10, mouseY + 35, 555555);
				this.drawString(getFontRenderer(), "Default pos: (" + comp.getDefaultXPos() + ", " + comp.getDefaultYPos() + ")", mouseX + 10, mouseY + 45, 555555);
			}
		}
	}
	
	public void onScreenOpened() {}
	
	public void onScreenClosed() {}
	
	public void onScreenSwitched() {}
		
	public void addComponent(ScreenComponent component) {
		if(!components.contains(component))
		{
			components.add(component);
		}		
	}
	
	public void addTextComponent(String text, int xPos, int yPos, int color) {
		addComponent(new ScreenText(getOS(), text, xPos, yPos, color));
	}

	public void drawString(String text, int x, int y, int color) {
		String[] keywords = StringUtils.substringsBetween(text, "$$", "$$");

		if(keywords != null) {
			for(int i = 0; i < keywords.length; i++) {						
				text = text.replace("$$" + keywords[i] + "$$", OS.getCurrentApp().replaceKeywords(keywords[i]).toString());
			}
		}

		this.drawString(getFontRenderer(), text, x, y, color);	
	}

	public ScreenComponent getComponent(String compName) {
		ScreenComponent component = getOS().components.get(compName);
		component.setOS(getOS());
		return component;
	}

	public ScreenImage getComponentAsImage(String compName) {
		ScreenImage image = (ScreenImage) getOS().components.get(compName);
		image.setOS(getOS());
		return image;
	}
	
	public void handleMouseClick(int mouseX, int mouseY, int mouseButtonClicked) {
		for(ScreenComponent component : components)
		{
			if(component.isMouseHoveringOver(mouseX, mouseY))
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

		backgroundImage.setPositionAndOrigin(x + 3, y + 3);

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

	public Rologia getOS() {
		return OS;
	}

	public TextureManager getTextureManager() {
		return Minecraft.getMinecraft().getTextureManager();
	}
	
	protected FontRenderer getFontRenderer() {
		return Minecraft.getMinecraft().fontRenderer;
	}
	
}
