package net.geforcemods.smartwatch.rologia.gui.components;

import org.lwjgl.opengl.GL11;

import net.geforcemods.smartwatch.rologia.gui.screens.Screen;
import net.geforcemods.smartwatch.rologia.os.Rologia;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureManager;

public abstract class ScreenComponent extends Gui {
	
	protected int xPos = Screen.WATCH_SCREEN_X_SIZE;
	protected int yPos = Screen.WATCH_SCREEN_Y_SIZE;
	protected int defaultXPos;
	protected int defaultYPos;
	
	protected float rotation;
	protected float scale;
	
	protected int colorValue;
	
	private String type;
	
	private transient Rologia os;

	protected ScreenComponent(Rologia OS) {
		os = OS;

		rotation = 0F;
		scale = 1F;
	}

	protected ScreenComponent(Rologia OS, int x, int y) {
		os = OS;

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

	public ScreenComponent setPosition(int x, int y) {
		xPos = x;
		yPos = y;
		return this;
	}
	
	public void resetPosition() {
		xPos = defaultXPos;
		yPos = defaultYPos;
	}
	
	public ScreenComponent setDefaultPosition(int x, int y) {
		defaultXPos = x;
		defaultYPos = y;
		return this;
	}
	
	public ScreenComponent setPositionAndOrigin(int x, int y) {
		xPos = x;
		yPos = y;
		defaultXPos = x;
		defaultYPos = y;
		return this;
	}

	public ScreenComponent centerPosition() {
		centerPosition(0, 0);
		return this;
	}

	public ScreenComponent centerPosition(int xOffset, int yOffset) {
		int centeredX = getScreen().getCenteredXForComponent(this) + xOffset;
		int centeredY = getScreen().getCenteredYForComponent(this) + yOffset - getScreen().getStatusBar().getHeight();

		setPositionAndOrigin(centeredX, centeredY);
		return this;
	}

	public void setOS(Rologia OS) {
		os = OS;
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

	public Rologia getOS() {
		return os;
	}

	public Screen getScreen() {
		return os.getCurrentScreen();
	}

	public TextureManager getTextureManager() {
		return Minecraft.getMinecraft().getTextureManager();
	}
	
	public FontRenderer getFontRenderer() {
		return Minecraft.getMinecraft().fontRenderer;
	}

}
