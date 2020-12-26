package net.geforcemods.rologia.os.gui.components;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;
import net.geforcemods.rologia.os.sounds.Sounds;
import net.minecraft.util.SoundEvent;

public class Button extends ScreenComponent {
	
	private String text;
	
	public Button(RologiaOS OS, String string) {
		super(OS);
		text = string;
	}

	public Button(RologiaOS OS, Position pos, String string) {
		super(OS, pos);
		text = string;
	}

	@Override
	public void drawComponent(MatrixStack stack) {
		if(this.isMouseHoveringOver(getScreen().getMousePosition()) || !isEnabled()) {
			GuiUtils.drawFilledRect(getPosition(), getWidth(), getHeight(), getTheme().BUTTON_INTERIOR_HOVERING);
		}
		else {
			GuiUtils.drawFilledRect(getPosition(), getWidth(), getHeight(), getTheme().BUTTON_INTERIOR, 0.5F);
		}

		GuiUtils.drawHollowRect(getPosition(), getWidth(), getHeight(), getTheme().BUTTON_OUTLINE);

		drawString(stack, getFontRenderer(), text, getPosition().getX() + 2, getPosition().getY() + 2, GuiUtils.toHex(getTheme().BUTTON_TEXT));
	}

	public String getText() {
		return text;
	}

	@Override
	public SoundEvent getClickSound() {
		return Sounds.BEEP_2.event;
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
