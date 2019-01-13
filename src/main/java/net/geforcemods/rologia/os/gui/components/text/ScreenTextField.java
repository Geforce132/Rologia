package net.geforcemods.rologia.os.gui.components.text;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;
import net.minecraft.client.renderer.GlStateManager;

public class ScreenTextField extends ScreenComponent {
	
	private String text = "";
	private int width;
	private int height;
	
	public ScreenTextField(RologiaOS OS, int w, int h) {
		super(OS);
		width = w;
		height = h;
	}

	public ScreenTextField(RologiaOS OS, Position pos, int w, int h) {
		super(OS, pos);
		width = w;
		height = h;
	}

	@Override
	public void drawComponent() {
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
        
		GlStateManager.glBegin(GL11.GL_LINES);
		GlStateManager.color(255, 0, 0);
		GlStateManager.glVertex3f(getPosition().getX(), getPosition().getY() + height, 0);
		GlStateManager.glVertex3f(getPosition().getX() + width, getPosition().getY() + height, 0);
		GlStateManager.glEnd();
		
		if(isFocused()) {
			GlStateManager.glBegin(GL11.GL_LINES);
			GlStateManager.color(225, 0, 0);
			GlStateManager.glVertex3f(getPosition().shiftX(getScreen().getFontRenderer().getStringWidth(text)).getX(), getPosition().getY(), 0);
			GlStateManager.glVertex3f(getPosition().shiftX(getScreen().getFontRenderer().getStringWidth(text)).getX(), getPosition().getY() + (height - 1), 0);
			GlStateManager.glEnd();
		}

		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
		this.drawString(getFontRenderer(), text, getPosition().getX(), getPosition().getY(), GuiUtils.toHex(getTheme().TEXT_FIELD_TEXT));
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

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

}
