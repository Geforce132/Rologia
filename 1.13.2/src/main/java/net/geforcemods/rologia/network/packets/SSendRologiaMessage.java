package net.geforcemods.rologia.network.packets;

import java.util.function.Supplier;

import net.geforcemods.rologia.Rologia;
import net.geforcemods.rologia.os.imc.RologiaMessage;
import net.geforcemods.rologia.os.imc.IRologiaMessageHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class SSendRologiaMessage {

	private String sendingPlayer;
	private String receivingPlayer;
	private String key;
	private String body;

	public SSendRologiaMessage() {

	}

	public SSendRologiaMessage(RologiaMessage message) {
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

	public static void encode(SSendRologiaMessage message, PacketBuffer packet)
	{
		message.toBytes(packet);
	}

	public static SSendRologiaMessage decode(PacketBuffer packet)
	{
		SSendRologiaMessage message = new SSendRologiaMessage();

		message.fromBytes(packet);
		return message;
	}

	public static void onMessage(SSendRologiaMessage packet, Supplier<NetworkEvent.Context> ctx) {
		for(IRologiaMessageHandler handler : Rologia.instance.serverProxy.getHandlers()) {
			RologiaMessage message = new RologiaMessage(packet.sendingPlayer, packet.receivingPlayer, packet.key, packet.body);
			handler.handleMessage(null, ctx.get().getSender().world, message);
		}
		
		ctx.get().setPacketHandled(true);
	}


}
