package net.geforcemods.rologia.network.packets;

import java.util.function.Supplier;

import net.geforcemods.rologia.Rologia;
import net.geforcemods.rologia.os.imc.RologiaMessage;
import net.geforcemods.rologia.os.imc.IRologiaMessageHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

public class CSendRologiaMessage {

	private String sendingPlayer;
	private String receivingPlayer;
	private String key;
	private String body;

	public CSendRologiaMessage() {

	}

	public CSendRologiaMessage(RologiaMessage message) {
		this.sendingPlayer = message.senderName;
		this.receivingPlayer = message.recipientName;
		this.key = message.key;
		this.body = message.body;
	}

	public void fromBytes(PacketBuffer buf) {
		sendingPlayer = buf.readString(Integer.MAX_VALUE / 4);
		receivingPlayer = buf.readString(Integer.MAX_VALUE / 4);
		key = buf.readString(Integer.MAX_VALUE / 4);
		body = buf.readString(Integer.MAX_VALUE / 4);
	}

	public void toBytes(PacketBuffer buf) {
		buf.writeString(sendingPlayer);
		buf.writeString(receivingPlayer);
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
		for(IRologiaMessageHandler handler : Rologia.instance.serverProxy.getHandlers()) {
			RologiaMessage message = new RologiaMessage(packet.sendingPlayer, packet.receivingPlayer, packet.key, packet.body);
			handler.handleMessage(Rologia.instance.serverProxy.getRologiaInstance(), Minecraft.getInstance().world, message);
		}
	
		ctx.get().setPacketHandled(true);
	}

}
