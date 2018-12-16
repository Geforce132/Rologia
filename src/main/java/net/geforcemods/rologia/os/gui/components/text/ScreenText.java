package net.geforcemods.rologia.os.gui.components.text;

import net.geforcemods.rologia.os.Rologia;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.rendering.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;

public class ScreenText extends ScreenComponent {
	
	private String text;
	
	public ScreenText(Rologia os, String string, int color) {
		super(os);
		text = string;
		setColor(color);
	}

	public ScreenText(Rologia os, String string, Position pos, int color) {
		super(os, pos);
		text = string;
		setColor(color);
	}
	
	@Override
	public void drawComponent() {
		if(getScreen() != null)
		drawString(getFontRenderer(), getText(), getPosition().getX(), getPosition().getY() + 2, colorValue);
	}

	@Override
	public void mouseClick(int mouseX, int mouseY, int mouseButtonClicked) {
		
	}
	
	public String getText() {
		return GuiUtils.formatString(getOS(), text);
	}
	
	@Override
	public int getWidth() {
		return (int) ((getFontRenderer().getStringWidth(getText())) * scale);
	}
	
	@Override
	public int getHeight() {
		return (int) (12 * scale);
	}

}
