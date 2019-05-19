package net.geforcemods.rologia.os.gui.components.text;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.misc.Position;

public class Text extends ScreenComponent {
	
	private String text;
	
	public Text(RologiaOS os, String string, int color) {
		super(os);
		text = string;
		setColor(color);
	}

	public Text(RologiaOS os, String string, Position pos, int color) {
		super(os, pos);
		text = string;
		setColor(color);
	}

	@Override
	public void drawComponent() {		
		if(getScreen() != null) {
			getFontRenderer().drawSplitString(getText(), getPosition().getX(), getPosition().getY() + 2, Screen.WATCH_SCREEN_X_SIZE, (isMouseHoveringOver(getOS().getCurrentScreen().getMousePosition()) && getHoverColor() != 0) ? getHoverColor() : getColor());
		}
	}

	public String getText() {
		return GuiUtils.formatString(getOS(), text);
	}
	
	public void setText(String string, Object... parameters) {
		for(Object object : parameters)
			string = string.replaceFirst("%s", object.toString());

		text = string;
	}

	@Override
	public int getWidth() {
		return (int) (GuiUtils.getWordWrappedWidth(getFontRenderer(), getText(), Screen.WATCH_SCREEN_X_SIZE) * scale);
	}
	
	@Override
	public int getHeight() {
		return (int) (getFontRenderer().getWordWrappedHeight(getText(), Screen.WATCH_SCREEN_X_SIZE) * scale) + 2;
	}

}
