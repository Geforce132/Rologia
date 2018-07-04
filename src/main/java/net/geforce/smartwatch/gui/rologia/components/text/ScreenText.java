package net.geforce.smartwatch.gui.rologia.components.text;

import net.geforce.smartwatch.gui.rologia.components.ScreenComponent;

public class ScreenText extends ScreenComponent {
	
	private String text;
	
	public ScreenText(String string, int x, int y, int color) {
		super(x, y);
		text = string;
		setColor(color);
	}
	
	@Override
	public void drawComponent() {
		drawString(getFontRenderer(), text, xPos, yPos, colorValue);
	}
	
	@Override
	public int getWidth() {
		return (int) ((getFontRenderer().getStringWidth(text)) * scale);
	}
	
	@Override
	public int getHeight() {
		return (int) (20 * scale);
	}

	@Override
	public void mouseClick(int mouseX, int mouseY, int mouseButtonClicked) {
		
	}

}
