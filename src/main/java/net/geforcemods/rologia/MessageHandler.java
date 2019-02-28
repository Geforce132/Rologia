package net.geforcemods.rologia;

import net.geforcemods.rologia.os.imc.IRologiaMessageHandler;

public class MessageHandler implements IRologiaMessageHandler {

	@Override
	public void handleMessage(String key, String body) {
		System.out.printf("Rologia message: %s\n", body);
	}

}
