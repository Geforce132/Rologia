package net.geforcemods.rologia.os.gui.components;

import org.lwjgl.opengl.GL11;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.misc.Position;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureManager;

public abstract class ScreenComponent extends Gui {
	
	protected Position position = new Position(Screen.WATCH_SCREEN_X_SIZE, Screen.WATCH_SCREEN_Y_SIZE);
	protected Position defaultPos = new Position(Screen.WATCH_SCREEN_X_SIZE, Screen.WATCH_SCREEN_Y_SIZE);
	
	protected float rotation;
	protected float scale;
	
	protected int colorValue;
	
	private String type;
	
	private transient RologiaOS os;

	protected ScreenComponent(RologiaOS OS) {
		os = OS;

		rotation = 0F;
		scale = 1F;
	}

	protected ScreenComponent(RologiaOS OS, Position pos) {
		os = OS;

		setPosition(pos);
		defaultPos = pos;

		rotation = 0F;
		scale = 1F;
	}
	
	public abstract void drawComponent();

	public boolean mouseClick(Position mousePos, int mouseButtonClicked) {
		return true;
	}
	
	public void keyTyped(char key, int keyCode) {}

	public boolean isMouseHoveringOver(Position mousePosition) {
		Position compPos = getPosition();
		int compWidth, compHeight;
		compWidth = getWidth();
		compHeight = getHeight();

		if(mousePosition.getX() >= compPos.getX() && mousePosition.getX() <= (compPos.getX() + compWidth) && mousePosition.getY() >= compPos.getY() && mousePosition.getY() <= (compPos.getY() + compHeight))
			return true;
		
		return false;
	}

	public void performPrerenderGLFixes() {		
		GL11.glTranslatef(position.getX(), position.getY(), 0);
		GL11.glRotatef(getRotation(), 0, 0, 1);
		GL11.glTranslatef(-position.getX(), -position.getY(), 0);

		GL11.glScalef(getScale(), getScale(), 1F);

		if(getScale() != 1.0F)
		{
            //TODO Fix scale crash
			Position pos = new Position((int) (defaultPos.getX() / getScale()), (int) (defaultPos.getY() / getScale()));

			if(!getPosition().matches(pos))
				setPosition(pos);
		}
	}

	public boolean acceptsKeyboardInput() {
		return false;
	}

	public ScreenComponent setPosition(Position pos) {
		position = pos;
		return this;
	}
	
	public void resetPosition() {
		position = defaultPos;
	}
	
	public ScreenComponent setDefaultPosition(Position pos) {
		defaultPos = pos;
		return this;
	}
	
	public ScreenComponent setPositionAndOrigin(Position pos) {
		position = pos;
		defaultPos = pos;
		return this;
	}

	public ScreenComponent centerPosition() {
		centerPosition(0, 0);
		return this;
	}

	public ScreenComponent centerPosition(int xOffset, int yOffset) {
		Position pos = getScreen().getCenteredPositionForComponent(this).shiftX(xOffset).shiftY(yOffset - getScreen().getStatusBar().getHeight());

		setPositionAndOrigin(pos);
		return this;
	}

	public void setOS(RologiaOS OS) {
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

	public Position getPosition() {
		return position;
	}
	
	public abstract int getWidth();
	
	public abstract int getHeight();
	
	public Position getDefaultPosition() {
		return defaultPos;
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

	public RologiaOS getOS() {
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
