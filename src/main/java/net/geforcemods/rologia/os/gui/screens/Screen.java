package net.geforcemods.rologia.os.gui.screens;

import java.util.ArrayList;
import java.util.logging.Level;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.apps.App;
import net.geforcemods.rologia.os.gui.components.ScreenAppBar;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.ScreenScrollBar;
import net.geforcemods.rologia.os.gui.components.ScreenStatusBar;
import net.geforcemods.rologia.os.gui.components.images.ScreenImage;
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

	private ArrayList<ScreenComponent> components = new ArrayList<ScreenComponent>();

	private ScreenComponent focusedComponent;

	private ScreenStatusBar statusBar;
	private ScreenScrollBar scrollBar;
	private ScreenAppBar appBar;
	private ScreenImage backgroundImage;

	private int screenHeight = 0;

	public Screen(RologiaOS os, Position pos) {
		OS = os;

		screenPos = pos;
		backgroundImage = getBackgroundImage();

		if(backgroundImage == null)
			RologiaOS.LOGGER.log(Level.WARNING, getClass().getSimpleName() + " has no background image!");

		backgroundImage.setPosition(pos);
	}

	public Screen(RologiaOS os, Position pos, App app) {
		OS = os;

		screenPos = pos;
		backgroundImage = app.getAppBackgroundImage();

		if(backgroundImage == null)
			RologiaOS.LOGGER.log(Level.WARNING, getClass().getSimpleName() + " has no background image!");

		backgroundImage.setPosition(pos);
	}

	public abstract void initializeScreen();

	public abstract void onComponentClicked(ScreenComponent component, Position mousePos, int mouseButtonClicked);

	public abstract String getScreenName();

	public void addStartupComponents() {
		// Status bar
		if(statusBar == null) {
			statusBar = new ScreenStatusBar(OS, getPosition());
			addComponent(statusBar);
		}

		// Scroll bar
		if(scrollBar == null) {
			scrollBar = new ScreenScrollBar(OS, getPosition().shiftX(90));
			addComponent(scrollBar);
		}

		// App selection bar
		if(appBar == null) {
			appBar = new ScreenAppBar(OS, getPosition());
			addComponent(appBar);
		}
	}

	public void updateScreen() {}

	public void drawBackgroundImage() {
		GlStateManager.pushMatrix();

		backgroundImage.performPrerenderGLFixes();
		backgroundImage.drawComponent();

		GlStateManager.pushMatrix();
		GuiUtils.drawFilledRect(getPosition(), WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE, getOS().getTheme().BACKGROUND_OVERLAY, 0.6F);
		GlStateManager.popMatrix();

		GlStateManager.popMatrix();
	}

	public void editComponents() {
		int l = components.size();
		for(int i = 0; i < l; i++)
		{
			editComponent(components.get(i));
		}
	}

	public void editComponent(ScreenComponent comp) {}

	public void drawComponents() {
		for(ScreenComponent component : components){
			if(!component.isVisible()) continue;

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

	public void drawScreenInfo(int startingXPos, int startingYPos) {
		int color = GuiUtils.toHex(getOS().getTheme().DEBUG_TEXT);

		drawString(getFontRenderer(), "Screen info:", startingXPos, 38, color);
		drawString(getFontRenderer(), "Name: " + getClass().getSimpleName(), startingXPos, 50, color);
		drawString(getFontRenderer(), "Position - X: " + getPosition().getX() + " Y: " + getPosition().getY(), startingXPos, 62, color);
		drawString(getFontRenderer(), "Mouse position - X: " + mousePos.getX() + " Y: " + mousePos.getY(), startingXPos, 74, color);
		drawString(getFontRenderer(), "# of notifications: " + getOS().getNotifications().size(), startingXPos, 98, color);

		if(getOS().isAppOpen()) {
			drawString(getFontRenderer(), "App info: ", startingXPos, 110, color);
			drawString(getFontRenderer(), "ID: " + getOS().getCurrentApp().getAppID(), startingXPos, 122, color);
			drawString(getFontRenderer(), "Name: " + getOS().getCurrentApp().getAppName(), startingXPos, 134, color);
		}
	}

	public void drawComponentInfo(int startingXPos, int startingYPos) {
		int color = GuiUtils.toHex(getOS().getTheme().DEBUG_TEXT);

		drawString(getFontRenderer(), "Components:", startingXPos, 38, color);

		for (int i = 0; i < components.size(); i++) {
			boolean isBeingHoveredOver = components.get(i).isMouseHoveringOver(mousePos);
			
			drawString(getFontRenderer(), (focusedComponent == components.get(i) ? "* " : "") + i + ": " + components.get(i).getClass().getSimpleName() + (!components.get(i).isVisible() ? " (H)" : ""), startingXPos, 50 + (i * 12), isBeingHoveredOver ? GuiUtils.toHex(getOS().getTheme().DEBUG_TEXT_HOVERING) : color);

			if(isBeingHoveredOver) {
				drawString(getFontRenderer(), "Component info:", startingXPos, 50 + ((components.size() + 1) * 12), color);
				drawString(getFontRenderer(), " - Position - X: " + components.get(i).getPosition().getX() + " Y: " + components.get(i).getPosition().getY(), startingXPos, 50 + ((components.size() + 2) * 12), color);
			}
		}
	}

	public void onScreenOpened() {}

	/**
	 * Called when the watch's GUI is closed.
	 */
	public void screenClosed() {
		setFocusedComponent(null);
	}

	public void onScreenSwitched() {}

	public void addComponent(ScreenComponent component) {
		if(!components.contains(component)){
			components.add(component);
		}

		if(!component.isSystemComponent() && (component.getPosition().getY() - WATCH_SCREEN_Y_SIZE) > screenHeight)
			screenHeight = component.getPosition().getY() - WATCH_SCREEN_Y_SIZE;
	}

	public void addComponents(App app) {
		for(ScreenComponent comp : app.getComponents()) {
			addComponent(comp);
		}
	}

	public void removeComponents(App app) {
		for(int i = 0; i < app.getComponents().size(); i++) {
			components.remove(app.getComponents().get(i));
		}
	}

	/*
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
	*/

	public ArrayList<ScreenComponent> getComponents() {
		return components;
	}

	public ScreenComponent getFocusedComponent() {
		return focusedComponent;
	}

	public void setFocusedComponent(ScreenComponent comp) {
		focusedComponent = comp;
	}

	public boolean hasFocusedComponent() {
		return focusedComponent != null;
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

		//TODO getImage from all apps
		//if(OS.isAppOpen())
			//OS.getCurrentApp().getAppBackgroundImage().setPositionAndOrigin(pos);

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

	public ScreenScrollBar getScrollBar() {
		return scrollBar;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public RologiaOS getOS() {
		return OS;
	}

	public TextureManager getTextureManager() {
		return Minecraft.getMinecraft().getTextureManager();
	}

	public FontRenderer getFontRenderer() {
		return Minecraft.getMinecraft().fontRenderer;
	}

}
