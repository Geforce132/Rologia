package net.geforcemods.rologia.os.apps.im;

import java.util.ArrayList;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.apps.AppInfo;
import net.geforcemods.rologia.os.apps.events.AppEvent;
import net.geforcemods.rologia.os.apps.events.AppEventReceiveMessage;
import net.geforcemods.rologia.os.apps.events.AppEventType;
import net.geforcemods.rologia.os.gui.components.Button;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.images.Image;
import net.geforcemods.rologia.os.gui.components.text.TextField;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.utils.Colors;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.imc.IMCManager;
import net.geforcemods.rologia.os.imc.IRologiaMessageHandler;
import net.geforcemods.rologia.os.imc.RologiaMessage;
import net.geforcemods.rologia.os.misc.Position;
import net.geforcemods.rologia.os.sounds.Sounds;
import net.minecraft.world.World;

@AppInfo(id=AppIM.ID, name = AppIM.NAME, version = AppIM.VERSION)
public class AppIM extends Screen implements IRologiaMessageHandler {
	
	public static final String ID = "im";
	public static final String NAME = "IM Chat";
	public static final String VERSION = "1.0.0";

	public static final float TEXT_SCALE = .6F;

	private TextField textField = new TextField(getOS(), 40, 10);
	private Button sendButton = new Button(getOS(), "Send");

	private ArrayList<IMTab> tabs = new ArrayList<IMTab>();

	public AppIM(RologiaOS rologia, Position pos) {
		super(rologia, pos);
	}
	
	@Override
	public void initializeScreen() {
		System.out.println("init");
		textField.centerPosition(-15, 55);
		textField.setTextScale(TEXT_SCALE);
		sendButton.centerPosition(25, 55);
		addComponent(textField);
		addComponent(sendButton);
	}

	@Override
	public void updateScreen() {		
		GuiUtils.drawFilledRect(getPosition(), Screen.WATCH_SCREEN_X_SIZE, Screen.WATCH_SCREEN_Y_SIZE, Colors.GRAY.color);

		for(IMTab tab : tabs)
			tab.drawTab(this);

		boolean hasMessage = !textField.getText().isEmpty();
		
		if(hasMessage && !sendButton.isEnabled())
			sendButton.setEnabled(true);
		else if(!hasMessage && sendButton.isEnabled())
			sendButton.setEnabled(false);
	}

	@Override
	public void onComponentClicked(ScreenComponent component, Position mousePos, int mouseButtonClicked) {
		if(component == sendButton) {
			IMCManager.sendMessage(getOS().getUser().getName().getFormattedText(), getOS().getUser().getName().getFormattedText(), IMCManager.IM, textField.getText());
			textField.clearText();
		}
	}

	@Override
	public void handleMessage(RologiaOS os, World world, RologiaMessage message) {		
		System.out.printf("Rologia message: %s %s %s %s %s\n", message.body, world.isRemote ? "CLIENT" : "SERVER", os, message.getSender(), message.getRecipient());
	}

	@Override
	public void onEventPosted(AppEvent type) {
		String sender = ((AppEventReceiveMessage) type).sender;
		String message = ((AppEventReceiveMessage) type).message;

		addMessageToTab(sender, message);
		getOS().getUser().playSound(Sounds.BEEP_2.event, 1.0F, 1.0F);
	}

	private void addMessageToTab(String sender, String message) {
		boolean addedMessage = false;

		for(IMTab tab : tabs) {
			if(tab.sender.matches(sender)) {
				tab.addMessage(message);
				addedMessage = true;
			}
		}

		if(!addedMessage) {
			IMTab tab = new IMTab(getOS().getCurrentScreen().getPosition().shiftY(getOS().getCurrentScreen().getStatusBar().getHeight()), sender);
			tab.addMessage(message);
			tabs.add(tab);
		}
	}

	@Override
	public AppEventType[] subscribeToEvents() {
		return new AppEventType[] {AppEventType.RECEIVE_MESSAGES};
	}

	@Override
	public String getScreenName() {
		return NAME;
	}

	@Override
	public Image getBackgroundImage() {
		return new Image(getOS(), "rologia:textures/gui/watch/boot_screen_light.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);
	}

}
