package net.geforcemods.rologia.os.gui.screens;

import java.util.ArrayList;

import net.geforcemods.rologia.os.Rologia;
import net.geforcemods.rologia.os.apps.App;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.ScreenScrollBar;
import net.geforcemods.rologia.os.gui.components.ScreenStatusBar;
import net.geforcemods.rologia.os.gui.components.images.ScreenImage;
import net.geforcemods.rologia.os.gui.rendering.Colors;
import net.geforcemods.rologia.os.gui.rendering.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;

public abstract class Screen extends Gui {
	
	public static final int WATCH_BACKGROUND_X_SIZE = 95;
	public static final int WATCH_BACKGROUND_Y_SIZE = 125;
	public static final int WATCH_SCREEN_X_SIZE = WATCH_BACKGROUND_X_SIZE - 8;
	public static final int WATCH_SCREEN_Y_SIZE = WATCH_BACKGROUND_Y_SIZE - 7;
	
	private Rologia OS;
	
	private Position screenPos;
	private Position mousePos = new Position();
	
	protected ArrayList<ScreenImage> images = new ArrayList<ScreenImage>();
	protected ArrayList<ScreenComponent> components = new ArrayList<ScreenComponent>();
	
	private ScreenComponent focusedComponent;
	
	private ScreenStatusBar statusBar;
	private ScreenScrollBar scrollBar;
	private ScreenImage backgroundImage;
	
	private ScreenComponent leftArrow;
	private ScreenComponent rightArrow;

	public Screen(Rologia os, Position pos) {
		OS = os;
		
		screenPos = pos;
		backgroundImage = getBackgroundImage();
		backgroundImage.setPosition(pos);
	}

	public abstract void initializeScreen();
	
	public abstract void onComponentClicked(ScreenComponent component, Position mousePos, int mouseButtonClicked);
	
	public void addStartupComponents() {
		statusBar = new ScreenStatusBar(OS, getPosition(), Colors.RED);
		scrollBar = new ScreenScrollBar(OS, getPosition().shiftX(90));
		
		ScreenComponent leftArrow = getComponentAsImage("arrow_left_light");
		leftArrow.centerPosition(-54, 0);
		ScreenComponent rightArrow = getComponentAsImage("arrow_right_light");
		rightArrow.centerPosition(59, 0);

		addComponent(leftArrow);
		addComponent(rightArrow);
		addComponent(scrollBar);
	}
	
	public void updateScreen() {}

	public void editImages() {}

	public void drawImages() {
		GlStateManager.pushMatrix();

		if(OS.isAppOpen()) {
			OS.getCurrentApp().getAppBackgroundImage().performPrerenderGLFixes();
			OS.getCurrentApp().getAppBackgroundImage().drawComponent();
		}
		else {
			backgroundImage.performPrerenderGLFixes();
			backgroundImage.drawComponent();
		}
		
		GlStateManager.popMatrix();
		
		for(ScreenImage image : images)
		{
			GlStateManager.pushMatrix();
			image.performPrerenderGLFixes();
			image.drawComponent();
			GlStateManager.popMatrix();
		}
	}
	
	public void editComponents() {
		int l = components.size();
		for(int i = 0; i < l; i++)
		//for(ScreenComponent component : components) //TODO throws an CME
		{
			editComponent(components.get(i));
		}
	}
	
	public void editComponent(ScreenComponent comp) {}

	public void drawComponents() {
		for(ScreenComponent component : components)
		{
			GlStateManager.pushMatrix();
			component.performPrerenderGLFixes();
			component.drawComponent();
			GlStateManager.popMatrix();
		}
	}

	public void drawStatusBar() {
		GlStateManager.pushMatrix();
		statusBar.performPrerenderGLFixes();
		statusBar.drawComponent();
		GlStateManager.popMatrix();
	}

	public void editStatusBar() {}
	
	public void drawDebuggingTools(int mouseX, int mouseY) {
		/*
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
        */
        
		this.drawString(getFontRenderer(), "Mouse pos: (" + mouseX + ", " + mouseY + ")", mouseX + 10, mouseY + 5, 555555);
		
		for(ScreenComponent comp : components) {
			GuiUtils.drawHollowRect(comp.getPosition(), comp.getWidth(), comp.getHeight(), Colors.BLUE);
			
	        if(statusBar.isMouseHoveringOver(getMousePosition())) {
	        	this.drawString(getFontRenderer(), "--- Status bar: ---", mouseX + 10, mouseY + 25, 555555);
				this.drawString(getFontRenderer(), "X, Y pos: (" + comp.getPosition().getX() + ", " + comp.getPosition().getY() + ")" + " -> " + " w, h: (" + (comp.getPosition().getX() + comp.getWidth()) + ", " + (comp.getPosition().getY() + comp.getHeight()) + ")", mouseX + 10, mouseY + 35, 555555);
				this.drawString(getFontRenderer(), "Default pos: (" + comp.getDefaultPosition().getX() + ", " + comp.getDefaultPosition().getY() + ")", mouseX + 10, mouseY + 45, 555555);	
			}

			if(comp.isMouseHoveringOver(getMousePosition())) {
				this.drawString(getFontRenderer(), "--- Component: ---", mouseX + 10, mouseY + 25, 555555);
				this.drawString(getFontRenderer(), "X, Y pos: (" + comp.getPosition().getX() + ", " + comp.getPosition().getY() + ")" + " -> " + " w, h: (" + (comp.getPosition().getX() + comp.getWidth()) + ", " + (comp.getPosition().getY() + comp.getHeight()) + ")", mouseX + 10, mouseY + 35, 555555);
				this.drawString(getFontRenderer(), "Default pos: (" + comp.getDefaultPosition().getX() + ", " + comp.getDefaultPosition().getY() + ")", mouseX + 10, mouseY + 45, 555555);
			}
		}
		
		for(int i = 0; i < components.size(); i++) {
			this.drawString(getFontRenderer(), components.get(i).toString().replace("net.geforcemods.rologia.os.gui.components.", ""), screenPos.getX() + 90, screenPos.getY() + (i * 20), 555555);
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
	
	public void addComponents(App app) {
		for(ScreenComponent comp : app.getComponents()) {
			addComponent(comp);		
		}
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
	
	public ScreenComponent getFocusedComponent() {
		return focusedComponent;
	}
	
	public boolean hasFocusedComponent() {
		return focusedComponent != null;
	}
	
	public void handleMouseClick(int mouseX, int mouseY, int mouseButtonClicked) {
		for(ScreenComponent component : components)
		{
			if(component.isMouseHoveringOver(getMousePosition())) {
				focusedComponent = component;
				onComponentClicked(component, getMousePosition(), mouseButtonClicked);
			}
		}
	}

	public void handleKeyTyped(char key, int keyCode) {
		for(ScreenComponent component : components)
		{
			if(hasFocusedComponent() && focusedComponent.acceptsKeyboardInput())
				component.keyTyped(key, keyCode);
		}
	}

	public void setMousePos(int x, int y) {
		mousePos.setX(x);
		mousePos.setY(y);
	}
	
	public Screen setPosition(Position pos) {
		screenPos = pos;

		backgroundImage.setPositionAndOrigin(pos.shiftX(3).shiftY(3));

		return this;
	}
	
	public Position getMousePosition() {
		return mousePos;
	}
	
	public Position getPosition() {
		return screenPos;
	}
	
	public Position getCenteredPositionForComponent(ScreenComponent comp) {
		int centeredX = (getPosition().getX() + (WATCH_SCREEN_X_SIZE / 2) - (comp.getWidth() / 2));
		int centeredY = (getPosition().getY() + (WATCH_SCREEN_Y_SIZE / 2) - (comp.getHeight() / 2));
		return new Position(centeredX, centeredY);
	}

	public Screen setBackgroundImage(ScreenImage newImage) {
		newImage.setPosition(screenPos);
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
