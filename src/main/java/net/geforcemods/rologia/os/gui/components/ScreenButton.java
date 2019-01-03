package net.geforcemods.rologia.os.gui.components;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;

public class ScreenButton extends ScreenComponent {
	
	private String text;
	private boolean enabled = true;
	
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
		GuiUtils.drawHollowRect(position, getWidth(), getHeight(), getTheme().BUTTON_OUTLINE);

		if(this.isMouseHoveringOver(getScreen().getMousePosition()) || !enabled) {
			GuiUtils.drawFilledRect(getPosition(), getWidth(), getHeight(), getTheme().BUTTON_INTERIOR_HOVERING);
		}
		else {
			GuiUtils.drawFilledRect(getPosition(), getWidth(), getHeight(), getTheme().BUTTON_INTERIOR, 0.5F);
		}

		drawString(getFontRenderer(), text, getPosition().getX() + 2, getPosition().getY() + 2, GuiUtils.toHex(getTheme().BUTTON_TEXT));
	}

	public String getText() {
		return text;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean isEnabled) {
		enabled = isEnabled;
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
