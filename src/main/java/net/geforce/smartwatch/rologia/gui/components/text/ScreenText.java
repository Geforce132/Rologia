package net.geforce.smartwatch.rologia.gui.components.text;

import net.geforce.smartwatch.rologia.gui.components.ScreenComponent;
import net.geforce.smartwatch.rologia.gui.screens.Screen;

public class ScreenText extends ScreenComponent {
	
	private String text;
	
	public ScreenText(Screen screen, String string, int color) {
		super(screen);
		text = string;
		setColor(color);
	}

	public ScreenText(Screen screen, String string, int x, int y, int color) {
		super(screen, x, y);
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
