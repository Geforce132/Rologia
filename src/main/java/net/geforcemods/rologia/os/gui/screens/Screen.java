package net.geforcemods.rologia.os.gui.screens;

import java.util.ArrayList;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.apps.App;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.ScreenScrollBar;
import net.geforcemods.rologia.os.gui.components.ScreenStatusBar;
import net.geforcemods.rologia.os.gui.components.images.ScreenImage;
import net.geforcemods.rologia.os.gui.utils.Colors;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
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

	private RologiaOS OS;

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

	public Screen(RologiaOS os, Position pos) {
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
		addComponent(statusBar);
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

		for(ScreenImage image : images){
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
		for(ScreenComponent component : components){
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

	public void drawScreenInfo(int startingXPos, int startingYPos) {
		drawString(getFontRenderer(), "Screen info:", startingXPos, 38, 55555);
		drawString(getFontRenderer(), "Name: " + getClass().getSimpleName(), startingXPos, 50, 55555);
		drawString(getFontRenderer(), "Position - X: " + getPosition().getX() + " Y: " + getPosition().getY(), startingXPos, 62, 55555);
		drawString(getFontRenderer(), "Mouse position - X: " + mousePos.getX() + " Y: " + mousePos.getY(), startingXPos, 74, 55555);
	}

	public void drawComponentInfo(int startingXPos, int startingYPos) {
		for (int i = 0; i < components.size(); i++) {
			boolean isBeingHoveredOver = components.get(i).isMouseHoveringOver(mousePos);
			
			drawString(getFontRenderer(), "Components:", startingXPos, 38, 55555);
			drawString(getFontRenderer(), i + ": " + components.get(i).getClass().getSimpleName(), startingXPos, 50 + (i * 12), isBeingHoveredOver ? 555555 : 55555);
		}
	}

	public void onScreenOpened() {}

	public void onScreenClosed() {}

	public void onScreenSwitched() {}

	public void addComponent(ScreenComponent component) {
		if(!components.contains(component)){
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
		for(ScreenComponent component : components){
			if(component.isMouseHoveringOver(getMousePosition())) {
				focusedComponent = component;
				onComponentClicked(component, getMousePosition(), mouseButtonClicked);
				component.mouseClick(getMousePosition(), mouseButtonClicked);
			}
		}
	}

	public void handleKeyTyped(char key, int keyCode) {
		for(ScreenComponent component : components){
			if(hasFocusedComponent() && focusedComponent.acceptsKeyboardInput())
				component.keyTyped(key, keyCode);
		}
	}

	public void setMousePos(int x, int y) {
		mousePos.setX(x);
		mousePos.setY(y);
	}

	public Screen setPosition(Position pos) {
		// Save the distance that all components have from the screen's (0, 0) position
		// before
		// resizing the screen, then shift them back that many pixels away from the
		// screen's origin
		int[][] componentOffsets = new int[components.size()][2];

		for (int i = 0; i < components.size(); i++) {
			componentOffsets[i][0] = components.get(i).getPosition().getX() - screenPos.getX();
			componentOffsets[i][1] = components.get(i).getPosition().getY() - screenPos.getY();
		}

		screenPos = pos;
		backgroundImage.setPositionAndOrigin(pos);

		for (int i = 0; i < components.size(); i++) {
			components.get(i).setPositionAndOrigin(screenPos.shiftX(componentOffsets[i][0]).shiftY(componentOffsets[i][1]));
		}

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

	public RologiaOS getOS() {
		return OS;
	}

	public TextureManager getTextureManager() {
		return Minecraft.getMinecraft().getTextureManager();
	}

	protected FontRenderer getFontRenderer() {
		return Minecraft.getMinecraft().fontRenderer;
	}

}
