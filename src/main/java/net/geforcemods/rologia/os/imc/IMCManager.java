package net.geforcemods.rologia.os.imc;

import org.apache.commons.lang3.StringUtils;

import net.geforcemods.rologia.Rologia;
import net.geforcemods.rologia.network.packets.PacketSSendRologiaMessage;
import net.geforcemods.rologia.os.RologiaOS;

public class IMCManager {
	
	public static final String IMC = "imc";
	public static final String IM = "im";
	
	private static final String SEPARATOR = "$";

	private RologiaOS os;
	
	public IMCManager(RologiaOS OS) {
		os = OS;
	}

	public void handleRologiaMessage(String text) {
		String type = StringUtils.substringBefore(text, SEPARATOR);
		String message = StringUtils.substringAfter(text, SEPARATOR);

		if(type.matches(IMC)) {
			System.out.println("received IMC message |" + message);
		}
		else if(type.matches(IM)) {
			System.out.println("received IM message |" + message);
		}
	}
	
	public void sendRologiaMessage(String type, String message) {
		Rologia.network.sendToServer(new PacketSSendRologiaMessage(type + SEPARATOR + message));
	}
	
}
