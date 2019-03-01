package net.geforcemods.rologia.os.apps.im;

import net.geforcemods.rologia.os.gui.screens.Screen;
import net.minecraft.client.Minecraft;

public class IMMessage {
	
	private String sender;
	private String message;
	
	public IMMessage(IMTab tab, String message) {
		sender = tab.sender;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getMessageWithSenderPrefix() {
		return "<" + sender + "> " + message;
	}
	
	public int getMessageHeight(boolean checkWithSenderPrefix) {
		return (int) (Minecraft.getInstance().fontRenderer.getWordWrappedHeight(checkWithSenderPrefix ? ("<" + sender + "> " + message) : message, (int) (Screen.WATCH_SCREEN_X_SIZE / AppIM.TEXT_SCALE)) * AppIM.TEXT_SCALE);
	}

}
