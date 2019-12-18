package net.geforcemods.rologia;

import net.geforcemods.rologia.network.packets.CSendRologiaMessage;
import net.geforcemods.rologia.network.packets.SSendRologiaMessage;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class RegistrationHandler {
	
	public static void registerPackets() {
		int index = 0;
		
		Rologia.channel.registerMessage(index++, CSendRologiaMessage.class, CSendRologiaMessage::encode, CSendRologiaMessage::decode, CSendRologiaMessage::onMessage);
		Rologia.channel.registerMessage(index++, SSendRologiaMessage.class, SSendRologiaMessage::encode, SSendRologiaMessage::decode, SSendRologiaMessage::onMessage);
	}

}
