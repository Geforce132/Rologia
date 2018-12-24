package net.geforcemods.rologia.os.gui.components;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.utils.Colors;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;

public class ScreenButton extends ScreenComponent {
	
	private String text;
	
	public ScreenButton(RologiaOS OS, String string) {
		super(OS);
		text = string;
	}

	public ScreenButton(RologiaOS OS, Position pos, String string) {
		super(OS, pos);
		text = string;
	}

	@Override
	public void drawComponent() {
		GuiUtils.drawHollowRect(position, getWidth(), getHeight(), Colors.BLUE);

		if(this.isMouseHoveringOver(getScreen().getMousePosition())) {
			GuiUtils.drawFilledRect(getPosition(), getWidth(), getHeight(), Colors.DARK_BLUE, true);
		}
		else {
			GuiUtils.drawFilledRect(getPosition(), getWidth(), getHeight(), Colors.BLUE, true);
		}

		drawString(getFontRenderer(), text, getPosition().getX() + 2, getPosition().getY() + 2, 55555);
	}

	@Override
	public void mouseClick(Position mousePos, int mouseButtonClicked) {}

	@Override
	public int getWidth() {
		return getFontRenderer().getStringWidth(text) + 4;
	}

	@Override
	public int getHeight() {
		return 12;
	}

}
