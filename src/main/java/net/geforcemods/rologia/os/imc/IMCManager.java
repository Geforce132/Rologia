package net.geforcemods.rologia.os.imc;

import org.apache.commons.lang3.StringUtils;

import net.geforcemods.rologia.Rologia;
import net.geforcemods.rologia.events.RologiaEventHandler;
import net.geforcemods.rologia.network.packets.PacketSSendRologiaMessage;
import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.apps.events.AppEventReceiveMessage;

public class IMCManager {
	
	public static final String IMC = "imc";
	public static final String IM = "im";
	
	private static final String SEPARATOR = "$";

	private RologiaOS os;
	
	public IMCManager(RologiaOS OS) {
		os = OS;
	}

	public void handleRologiaMessage(String sender, String text) {
		String type = StringUtils.substringBefore(text, SEPARATOR);
		String message = StringUtils.substringAfter(text, SEPARATOR);

		System.out.printf("received %s message from %s: %s\n", type, sender, message);

		RologiaEventHandler.postRologiaEvent(new AppEventReceiveMessage(sender, type, message));
	}
	
	public void sendRologiaMessage(String type, String message) {
		Rologia.network.sendToServer(new PacketSSendRologiaMessage(os.getUser().getName(), type + SEPARATOR + message));
	}
	
}
