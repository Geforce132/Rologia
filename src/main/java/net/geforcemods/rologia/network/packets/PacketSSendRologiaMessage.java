package net.geforcemods.rologia.network.packets;

import io.netty.buffer.ByteBuf;
import net.geforcemods.rologia.Rologia;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSSendRologiaMessage implements IMessage {

	private String message;

	public PacketSSendRologiaMessage() {

	}

	public PacketSSendRologiaMessage(String message) {
		this.message = message;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		message = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, message);
	}

	public static class Handler implements IMessageHandler<PacketSSendRologiaMessage, IMessage> {

		@Override
		public IMessage onMessage(PacketSSendRologiaMessage packet, MessageContext ctx) {
			Rologia.network.sendToAll(new PacketCSendRologiaMessage(packet.message));
			return null;
		}

	}

}
