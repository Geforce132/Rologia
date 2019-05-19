package net.geforcemods.rologia.os.gui.components;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;
import net.geforcemods.rologia.os.sounds.Sounds;
import net.geforcemods.rologia.utils.Utils;

public class ToggleButton extends ScreenComponent {
	
	private String text1, text2;
	private boolean toggled = false;
	private boolean enabled = true;
	
	public ToggleButton(RologiaOS OS, String string1, String string2) {
		super(OS);
		text1 = string1;
		text2 = string2;
	}

	public ToggleButton(RologiaOS OS, Position pos, String string1, String string2) {
		super(OS, pos);
		text1 = string1;
		text2 = string2;
	}

	@Override
	public void drawComponent() {
		drawButtonInterior(this.isMouseHoveringOver(getScreen().getMousePosition()) || !enabled);

		GuiUtils.drawHollowRect(getPosition(), getWidth(), getHeight(), getTheme().BUTTON_OUTLINE);

		drawString(getFontRenderer(), text1, getPosition().getX() + 2, getPosition().getY() + 2, GuiUtils.toHex(getTheme().BUTTON_TEXT));
		drawString(getFontRenderer(), text2, getPosition().getX() + getFontRenderer().getStringWidth(text1) + 6, getPosition().getY() + 2, GuiUtils.toHex(getTheme().BUTTON_TEXT));
	}
	
	private void drawButtonInterior(boolean mouseHoveringOver) {
		if(!toggled)
			GuiUtils.drawFilledRect(getPosition(), getFontRenderer().getStringWidth(text1) + 4, getHeight(), getTheme().BUTTON_INTERIOR_HOVERING);
		else
			GuiUtils.drawFilledRect(getPosition().shiftX(getFontRenderer().getStringWidth(text1) + 4), getFontRenderer().getStringWidth(text2) + 3, getHeight(), getTheme().BUTTON_INTERIOR_HOVERING);
	}

	public String getText() {
		return toggled ? text2 : text1;
	}
	
	public void toggle() {
		toggled = !toggled;
		Utils.playSound(Sounds.BEEP_2.event);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean isEnabled) {
		enabled = isEnabled;
	}

	@Override
	public int getWidth() {
		return getFontRenderer().getStringWidth(text1) + getFontRenderer().getStringWidth(text2) + 7;
	}

	@Override
	public int getHeight() {
		return 12;
	}

}
