package net.geforcemods.rologia.os.gui.components.text;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;

public class TextField extends ScreenComponent {
	
	private String text = "";
	private int width;
	private int height;
	
	private float textScale = 1.0F;

	public TextField(RologiaOS OS, int w, int h) {
		super(OS);
		width = w;
		height = h;
	}

	public TextField(RologiaOS OS, Position pos, int w, int h) {
		super(OS, pos);
		width = w;
		height = h;
	}

	@Override
	public void drawComponent() {
		GlStateManager.enableBlend();
		GlStateManager.disableTexture();
        
		GL11.glBegin(GL11.GL_LINES);
		GlStateManager.color3f(255, 0, 0);
		GL11.glVertex3f(getPosition().getX(), getPosition().getY() + height, 0);
		GL11.glVertex3f(getPosition().getX() + width, getPosition().getY() + height, 0);
		GL11.glEnd();
		
		if(isFocused()) {
			GL11.glBegin(GL11.GL_LINES);
			GlStateManager.color3f(225, 0, 0);
			GL11.glVertex3f(getPosition().shiftX((int) (getScreen().getFontRenderer().getStringWidth(text) * textScale) + 1).getX(), getPosition().getY(), 0);
			GL11.glVertex3f(getPosition().shiftX((int) (getScreen().getFontRenderer().getStringWidth(text) * textScale) + 1).getX(), (int) (getPosition().getY() + (height - 1) * textScale), 0);
			GL11.glEnd();
		}

		GlStateManager.enableTexture();
		GlStateManager.disableBlend();

		GlStateManager.translated(getPosition().getX(), getPosition().getY(), 0);
		GlStateManager.scalef(textScale, textScale, 0);
		GlStateManager.translated(-getPosition().getX(), -getPosition().getY(), 0);

		drawString(getFontRenderer(), text, getPosition().getX(), getPosition().getY(), GuiUtils.toHex(getTheme().TEXT_FIELD_TEXT));
	}

	@Override
	public boolean mouseClick(Position mousePos, int mouseButtonClicked) {
		return true;
	}
	
	@Override
	public void keyTyped(char key, int keyCode) {
		// Remove the last character in text if someone presses the backspace button.
		if(key == GuiUtils.BACKSPACE) {
			text = StringUtils.left(text, text.length() - 1);
		}
		else {
			text += key;
		}	
	}
	
	@Override
	public boolean acceptsKeyboardInput() {
		return true;
	}

	public String getText() {
		return text;
	}

	public void setText(String newText) {
		text = newText;
	}

	public void clearText() {
		text = "";
	}

	public void setTextScale(float scale) {
		textScale = scale;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

}
