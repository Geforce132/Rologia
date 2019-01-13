package net.geforcemods.rologia.os.apps.im;

import java.util.ArrayList;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.apps.App;
import net.geforcemods.rologia.os.apps.AppInfo;
import net.geforcemods.rologia.os.apps.events.AppEvent;
import net.geforcemods.rologia.os.apps.events.AppEventReceiveMessage;
import net.geforcemods.rologia.os.apps.events.AppEventType;
import net.geforcemods.rologia.os.gui.components.ScreenButton;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.text.ScreenTextField;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.imc.IMCManager;
import net.geforcemods.rologia.os.misc.Position;
import net.geforcemods.rologia.os.sounds.Sounds;

@AppInfo(id=AppIM.ID, name = AppIM.NAME, version = AppIM.VERSION)
public class AppIM extends App {
	
	public static final String ID = "im";
	public static final String NAME = "IM Chat";
	public static final String VERSION = "1.0.0";

	private ScreenTextField textField = new ScreenTextField(getOS(), 40, 10);
	private ScreenButton sendButton = new ScreenButton(getOS(), "Send");

	private ArrayList<IMTab> tabs = new ArrayList<IMTab>();

	public AppIM(RologiaOS rologia) {
		super(rologia);
	}
	
	@Override
	public void initializeApp() {
		textField.centerPosition(-15, 55);
		sendButton.centerPosition(25, 55);
		addComponent(textField);
		addComponent(sendButton);

		if(tabs.size() == 0) {
			//tabs.add(new IMTab(new Position(10, 10), "+"));
			tabs.add(new IMTab(getOS().getCurrentScreen().getPosition(), "Geforce"));
		}
	}

	@Override
	public void updateApp() {
		boolean hasMessage = !textField.getText().isEmpty();

		if(hasMessage && !sendButton.isEnabled())
			sendButton.setEnabled(true);
		else if(!hasMessage && sendButton.isEnabled())
			sendButton.setEnabled(false);
	}

	@Override
	public void drawApp(Screen currentScreen) {
		GuiUtils.drawFilledRect(currentScreen.getPosition(), Screen.WATCH_SCREEN_X_SIZE, Screen.WATCH_SCREEN_Y_SIZE, getOS().getTheme().IM_BACKGROUND);

		for(IMTab tab : tabs)
			tab.drawTab(currentScreen);
	}

	@Override
	public void onComponentClicked(ScreenComponent component, Position mousePos, int mouseButtonClicked) {
		if(component == sendButton) {
			getOS().getIMCManager().sendRologiaMessage(IMCManager.IM, textField.getText());
			textField.clearText();
		}
	}

	@Override
	public void onEventPosted(AppEvent type) {
		String sender = ((AppEventReceiveMessage) type).sender;
		String message = ((AppEventReceiveMessage) type).message;

		addMessageToTab(sender, message);
		getOS().getUser().playSound(Sounds.BEEP_2.event, 1.0F, 1.0F);
	}

	private void addMessageToTab(String sender, String message) {
		for(IMTab tab : tabs) {
			if(tab.destinationPlayerName.matches(sender))
				tab.addMessage("<" + sender + "> " + message);
		}
	}

	@Override
	public Object replaceKeywords(String keyword) {
		return null;
	}

	@Override
	public AppEventType[] subscribeToEvents() {
		return new AppEventType[] {AppEventType.RECEIVE_MESSAGES};
	}

}
