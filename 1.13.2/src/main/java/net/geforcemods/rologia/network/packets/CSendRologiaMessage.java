package net.geforcemods.rologia.network.packets;

import java.util.function.Supplier;

import net.geforcemods.rologia.Rologia;
import net.geforcemods.rologia.os.imc.IRologiaMessageHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

public class CSendRologiaMessage {

	private String key;
	private String body;

	public CSendRologiaMessage() {

	}

	public CSendRologiaMessage(String key, String body) {
		this.key = key;
		this.body = body;
	}

	public void fromBytes(PacketBuffer buf) {
		key = buf.readString(Integer.MAX_VALUE / 4);
		body = buf.readString(Integer.MAX_VALUE / 4);
	}

	public void toBytes(PacketBuffer buf) {
		buf.writeString(key);
		buf.writeString(body);
	}
	
	public static void encode(CSendRologiaMessage message, PacketBuffer packet)
	{
		message.toBytes(packet);
	}

	public static CSendRologiaMessage decode(PacketBuffer packet)
	{
		CSendRologiaMessage message = new CSendRologiaMessage();

		message.fromBytes(packet);
		return message;
	}

	@OnlyIn(Dist.CLIENT)
	public static void onMessage(CSendRologiaMessage packet, Supplier<NetworkEvent.Context> ctx) {
		for(IRologiaMessageHandler handler : Rologia.instance.serverProxy.getHandlers())
			handler.handleMessage(Rologia.instance.serverProxy.getRologiaInstance(), Minecraft.getInstance().world, Minecraft.getInstance().player, packet.key, packet.body);
	
		ctx.get().setPacketHandled(true);
	}

}
