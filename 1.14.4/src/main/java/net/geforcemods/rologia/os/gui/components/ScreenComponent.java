package net.geforcemods.rologia.os.gui.components;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.utils.Theme;
import net.geforcemods.rologia.os.misc.Position;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public abstract class ScreenComponent extends net.minecraft.client.gui.screen.Screen {
	
	protected Position position = new Position(Screen.WATCH_SCREEN_X_SIZE, Screen.WATCH_SCREEN_Y_SIZE);
	protected Position defaultPos = new Position(Screen.WATCH_SCREEN_X_SIZE, Screen.WATCH_SCREEN_Y_SIZE);
	
	private Position offset = null;
	
	private boolean visible = true;
	private boolean enabled = true;

	protected float rotation;
	protected float scale;
	
	protected int colorValue;
	protected int hoverColorValue;

	private String type;

	private transient RologiaOS os;

	protected ScreenComponent(RologiaOS OS) {
		super(null);
		os = OS;

		rotation = 0F;
		scale = 1F;
	}

	protected ScreenComponent(RologiaOS OS, Position pos) {
		super(null);
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
		// If this component has an automatically calculated position and if this component's current
		// position doesn't match it, recenter it.
		if(hasCenteredPosition() && !position.matches(new Position(position.getX() + offset.getX(), position.getY() + offset.getY()))) {
			centerPosition(offset.getX(), offset.getY());
		}

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

	/**
	 * Calls TextureManager.bindTexture(), but with pop/pushMatrix()
	 * calls to prevent unwanted colors or translations being used
	 * 
	 * @param texture The ResourceLocation to bind
	 */
	public void bindTexture(ResourceLocation texture) {
		GlStateManager.popMatrix();
		GlStateManager.color4f(1, 1, 1, 1);
		getTextureManager().bindTexture(texture);
		GlStateManager.pushMatrix();
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

	public ScreenComponent centerPosition(int xShift, int yShift) {
		Position pos = getScreen().getCenteredPositionForComponent(this).shiftX(xShift).shiftY(yShift - StatusBar.DEFAULT_WIDTH);

		if(offset == null)
			offset = new Position(0, 0);

		offset.setX(xShift);
		offset.setY(yShift);

		setPositionAndOrigin(pos);
		return this;
	}

	public void setOS(RologiaOS OS) {
		os = OS;
	}

	/**
	 * @return If this ScreenComponent is an essential component to the Screen, such as the scroll bar
	 * or clock. These components will not be reset during a Screen change and will not be used
	 * in calculations such as Screen.getScreenHeight().
	 */
	public boolean isSystemComponent() {
		return false;
	}

	/**
	 * @return If this ScreenComponent is visible, it will be rendered and will react to mouse clicks.
	 * Setting its visibility to 'false' will stop it from being drawn and prevent users from
	 * interacting with it.
	 */
	public boolean isVisible() {
		return visible;
	}

	public void setVisibility(boolean visibility) {
		visible = visibility;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean isEnabled) {
		enabled = isEnabled;
	}

	public boolean isFocused() {
		return getScreen().hasFocusedComponent() && getScreen().getFocusedComponent() == this;
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
	
	public void setHoverColor(int hoverColor) {
		hoverColorValue = hoverColor;
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
	
	public int getHoverColor() {
		return hoverColorValue;
	}

	public boolean hasCenteredPosition() {
		return offset != null;
	}

	public Theme getTheme() {
		return os.getTheme();
	}

	public RologiaOS getOS() {
		return os;
	}

	public Screen getScreen() {
		return os.getCurrentScreen();
	}

	public TextureManager getTextureManager() {
		return Minecraft.getInstance().getTextureManager();
	}
	
	public FontRenderer getFontRenderer() {
		return Minecraft.getInstance().fontRenderer;
	}

}
