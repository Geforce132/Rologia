package net.geforcemods.rologia.os.gui.components;

import net.geforcemods.rologia.os.Rologia;
import net.geforcemods.rologia.os.gui.rendering.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;

public class ScreenButton extends ScreenComponent {
	
	private String text;
	//private int width;
	//private int height;
	
	public ScreenButton(Rologia OS, String string) {
		super(OS);
		text = string;
	}

	public ScreenButton(Rologia OS, Position pos, String string) {
		super(OS, pos);
		text = string;
	}

	@Override
	public void drawComponent() {
		GuiUtils.drawHollowRect(position, getWidth(), getHeight());
		drawString(getFontRenderer(), text, getPosition().getX() + 2, getPosition().getY() + 2, 55555);
	}

	@Override
	public void mouseClick(int mouseX, int mouseY, int mouseButtonClicked) {
		System.out.println("clicked");
	}

	@Override
	public int getWidth() {
		return getFontRenderer().getStringWidth(text) + 4;
	}

	@Override
	public int getHeight() {
		return 12;
	}

}
