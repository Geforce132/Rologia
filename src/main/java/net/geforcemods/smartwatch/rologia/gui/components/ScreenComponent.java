package net.geforcemods.smartwatch.rologia.gui.components;

import org.lwjgl.opengl.GL11;

import net.geforcemods.smartwatch.rologia.gui.screens.Screen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureManager;

public abstract class ScreenComponent extends Gui {
	
	protected int xPos = Screen.WATCH_BACKGROUND_X_SIZE;
	protected int yPos = Screen.WATCH_BACKGROUND_Y_SIZE;
	protected int defaultXPos;
	protected int defaultYPos;
	
	protected float rotation;
	protected float scale;
	
	protected int colorValue;
	
	private Screen screen;

	protected ScreenComponent(Screen screenToInsertIn) {
		screen = screenToInsertIn;

		rotation = 0F;
		scale = 1F;
	}
	
	protected ScreenComponent(Screen screenToInsertIn, int x, int y) {
		screen = screenToInsertIn;
		setPosition(x, y);
		defaultXPos = x;
		defaultYPos = y;

		rotation = 0F;
		scale = 1F;
	}
	
	public abstract void drawComponent();

	public abstract void mouseClick(int mouseX, int mouseY, int mouseButtonClicked);
	
	public boolean isMouseHoveringOver(int mouseX, int mouseY) {
		int compX, compY;
		int compWidth, compHeight;
		compX = getXPos();
		compY = getYPos();
		compWidth = getWidth();
		compHeight = getHeight();
		//System.out.println(getXPos() + " | " + getYPos() + " | " + Math.abs(getWidth()) + " | " + Math.abs(getHeight()) + " | " + ((mouseX >= compX && mouseX <= (compX + compWidth) && mouseY >= compY && mouseY <= (compY + compHeight))));

		if(mouseX >= compX && mouseX <= (compX + compWidth) && mouseY >= compY && mouseY <= (compY + compHeight))
			return true;
		
		return false;
	}

	public void performPrerenderGLFixes() {		
		GL11.glTranslatef(getXPos(), getYPos(), 0);
		GL11.glRotatef(getRotation(), 0, 0, 1);
		GL11.glTranslatef(-getXPos(), -getYPos(), 0);

		GL11.glScalef(getScale(), getScale(), 1F);

		if(getScale() != 1.0F)
		{
			int translatedX, translatedY;
			translatedX = (int) (getDefaultXPos() / getScale());
			translatedY = (int) (getDefaultYPos() / getScale());
			if(getXPos() != translatedX && getYPos() != translatedY)
				setPosition(translatedX, translatedY);
		}
	}

	public void setPosition(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	public void resetPosition() {
		xPos = defaultXPos;
		yPos = defaultYPos;
	}
	
	public void setDefaultPosition(int x, int y) {
		defaultXPos = x;
		defaultYPos = y;
	}
	
	public void setPositionAndOrigin(int x, int y) {
		xPos = x;
		yPos = y;
		defaultXPos = x;
		defaultYPos = y;
	}

	public void centerPosition() {
		centerPosition(0, 0);
	}

	public void centerPosition(int xOffset, int yOffset) {
		int centeredX = screen.getCenteredXForComponent(this) + xOffset;
		int centeredY = screen.getCenteredYForComponent(this) + yOffset - screen.getStatusBar().getHeight();

		setPositionAndOrigin(centeredX, centeredY);
	}

	public void setRotation(float newRotation) {
		rotation = newRotation;
	}
	
	public void setScale(float newScale) {
		scale = newScale;
	}
	
	public void setColor(int color) {
		colorValue = color;
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
	
	public int getColor() {
		return colorValue;
	}

	public Screen getScreen() {
		return screen;
	}

	public TextureManager getTextureManager() {
		return Minecraft.getMinecraft().getTextureManager();
	}
	
	public FontRenderer getFontRenderer() {
		return Minecraft.getMinecraft().fontRenderer;
	}

}
