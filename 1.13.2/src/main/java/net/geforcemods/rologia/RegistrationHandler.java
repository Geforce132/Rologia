package net.geforcemods.rologia;

import net.geforcemods.rologia.network.packets.CSendRologiaMessage;
import net.geforcemods.rologia.network.packets.SSendRologiaMessage;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class RegistrationHandler {
	
	public static void registerPackets() {
		int index = 0;
		
		Rologia.channel.registerMessage(index++, CSendRologiaMessage.class, CSendRologiaMessage::encode, CSendRologiaMessage::decode, CSendRologiaMessage::onMessage);
		Rologia.channel.registerMessage(index++, SSendRologiaMessage.class, SSendRologiaMessage::encode, SSendRologiaMessage::decode, SSendRologiaMessage::onMessage);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void registerResourceLocations(ModelRegistryEvent event)
	{
		//TODO
		//ModelLoader.setCustomModelResourceLocation(Rologia.smartwatch, 0, new ModelResourceLocation("rologia:smart_watch", "inventory"));
	}

}
