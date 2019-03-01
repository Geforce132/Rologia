package net.geforcemods.rologia.network.packets;

import java.util.function.Supplier;

import net.geforcemods.rologia.Rologia;
import net.geforcemods.rologia.os.imc.IRologiaMessageHandler;
import net.geforcemods.rologia.utils.PlayerUtils;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class SSendRologiaMessage {

	private String destinationPlayer;
	private String key;
	private String body;

	public SSendRologiaMessage() {

	}

	public SSendRologiaMessage(String destination, String key, String body) {
		this.destinationPlayer = destination;
		this.key = key;
		this.body = body;
	}

	public void fromBytes(PacketBuffer buf) {
		destinationPlayer = buf.readString(Integer.MAX_VALUE / 4);
		key = buf.readString(Integer.MAX_VALUE / 4);
		body = buf.readString(Integer.MAX_VALUE / 4);
	}

	public void toBytes(PacketBuffer buf) {
		buf.writeString(destinationPlayer);
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
		if(PlayerUtils.isPlayerOnline(packet.destinationPlayer)) {
			for(IRologiaMessageHandler handler : Rologia.instance.serverProxy.getHandlers())
				handler.handleMessage(null, ctx.get().getSender().world, ctx.get().getSender(), packet.key, packet.body);

			//Rologia.channel.sendTo(new CSendRologiaMessage(packet.key, packet.body), (EntityPlayerMP) PlayerUtils.getPlayerFromName(packet.destinationPlayer));
		}
		
		ctx.get().setPacketHandled(true);
	}


}
