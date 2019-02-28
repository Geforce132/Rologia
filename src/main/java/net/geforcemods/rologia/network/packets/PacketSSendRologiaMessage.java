package net.geforcemods.rologia.network.packets;

import io.netty.buffer.ByteBuf;
import net.geforcemods.rologia.Rologia;
import net.geforcemods.rologia.os.imc.IRologiaMessageHandler;
import net.geforcemods.rologia.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSSendRologiaMessage implements IMessage {

	private String destinationPlayer;
	private String key;
	private String body;

	public PacketSSendRologiaMessage() {

	}

	public PacketSSendRologiaMessage(String destination, String key, String body) {
		this.destinationPlayer = destination;
		this.key = key;
		this.body = body;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		destinationPlayer = ByteBufUtils.readUTF8String(buf);
		key = ByteBufUtils.readUTF8String(buf);
		body = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, destinationPlayer);
		ByteBufUtils.writeUTF8String(buf, key);
		ByteBufUtils.writeUTF8String(buf, body);
	}

	public static class Handler implements IMessageHandler<PacketSSendRologiaMessage, IMessage> {

		@Override
		public IMessage onMessage(PacketSSendRologiaMessage packet, MessageContext ctx) {
			if(PlayerUtils.isPlayerOnline(packet.destinationPlayer)) {
				for(IRologiaMessageHandler handler : Rologia.instance.serverProxy.getHandlers())
					handler.handleMessage(null, ctx.getServerHandler().player.world, ctx.getServerHandler().player, packet.key, packet.body);

				Rologia.network.sendTo(new PacketCSendRologiaMessage(packet.key, packet.body), (EntityPlayerMP) PlayerUtils.getPlayerFromName(packet.destinationPlayer));
			}

			return null;
		}

	}

}
