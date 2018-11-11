package net.geforcemods.smartwatch.rologia.gui.components.text;

import net.geforcemods.smartwatch.rologia.gui.components.ScreenComponent;
import net.geforcemods.smartwatch.rologia.os.Rologia;

public class ScreenText extends ScreenComponent {
	
	private String text;
	
	public ScreenText(Rologia os, String string, int color) {
		super(os);
		text = string;
		setColor(color);
	}

	public ScreenText(Rologia os, String string, int x, int y, int color) {
		super(os, x, y);
		text = string;
		setColor(color);
	}
	
	@Override
	public void drawComponent() {
		drawString(getFontRenderer(), text, xPos, yPos, colorValue);
	}

	@Override
	public void mouseClick(int mouseX, int mouseY, int mouseButtonClicked) {
		
	}
	
	public String getText() {
		return text;
	}
	
	@Override
	public int getWidth() {
		return (int) ((getFontRenderer().getStringWidth(text)) * scale);
	}
	
	@Override
	public int getHeight() {
		return (int) (20 * scale);
	}

}
